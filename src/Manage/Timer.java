import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

class Timer
{
	private final LinkedList<Tickable> tickables=new LinkedList<>();
	private int ticks, wave=1, numberOfKnights, numberOfMikes, numberOfNagis, numberOfSkullies, passedCreeps, deadCreeps, totalDeadCreeps, totalPassedCreeps;
	private boolean fastFoword;
	private final Point startingPoint=getStartLocation();
	private final Board board;
	private double time;
	private final javax.swing.Timer timer=new javax.swing.Timer(250, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			ListIterator<Tickable> iterator=getTickables().listIterator();
			while (iterator.hasNext())
			{
				try
				{
					Tickable tickable=iterator.next();
					tickable.tickHappend();
					if (tickable instanceof Creeps.Creep && ((Creeps.Creep)tickable).getHP()<=0)
					{
						deadCreeps++;
						totalDeadCreeps++;
						iterator.remove();
					}
				}
				catch (IndexOutOfBoundsException e1)
				{
					passedCreeps++;
					totalPassedCreeps++;
					iterator.remove();
					Game.decreaseHP();
				}
			}
			if (((!isFastFoword() && getTicks()%4==0)/*Every second*/ ||
			     (isFastFoword() && getTicks()%2==0)/*Every half a second*/) &&
			    (numberOfMikes!=Math.pow(2, getWave()-1) ||
			     numberOfNagis!=Math.pow(2, getWave()-1) ||
			     numberOfKnights!=Math.pow(2, getWave()-1) ||
			     numberOfSkullies!=Math.pow(2, getWave()-1)))
			{
				final Creeps[] enumConstants=Creeps.class.getEnumConstants();
				boolean isRegistered=false;
				while (!isRegistered)
					switch (enumConstants[new Random().nextInt(enumConstants.length)])
					{
						case MIKE:
							if (numberOfMikes==Math.pow(2, getWave()-1))
								break;
							register(new Creeps.Mike(new Point(startingPoint)));
							numberOfMikes++;
							isRegistered=true;
							break;
						case NAGI:
							if (numberOfNagis==Math.pow(2, getWave()-1))
								break;
							register(new Creeps.Naji(new Point(startingPoint)));
							numberOfNagis++;
							isRegistered=true;
							break;
						case KNIGHT:
							if (numberOfKnights==Math.pow(2, getWave()-1))
								break;
							register(new Creeps.Knight(new Point(startingPoint)));
							numberOfKnights++;
							isRegistered=true;
							break;
						case SKULLY:
							if (numberOfSkullies==Math.pow(2, getWave()-1))
								break;
							register(new Creeps.Skully(new Point(startingPoint)));
							numberOfSkullies++;
							isRegistered=true;
					}
			}
			ticks++;
			if (!isFastFoword() && getTicks()%2==0)
				time+=.5;
			if (isFastFoword())
				time+=.5;
			if (deadCreeps+passedCreeps>=Math.pow(2, getWave()-1)*4)
				increaseWave();
			board.repaint();
		}
	});
	
	Timer(Board board)
	{
		this.board=board;
	}
	
	int getTicks()
	{
		return ticks;
	}
	
	boolean isFastFoword()
	{
		return fastFoword;
	}
	
	void setFastFoword(boolean fastFoword)
	{
		this.fastFoword=fastFoword;
	}
	
	LinkedList<Tickable> getTickables()
	{
		return tickables;
	}
	
	void register(Tickable tickable)
	{
		tickables.add(tickable);
	}
	
	void start()
	{
		timer.start();
	}
	
	private Point getStartLocation()
	{
		for (int i=0; i<Game.getLoader().get(Board.getLevel())[0].length; i++)
			if (!Game.getLoader().get(Board.getLevel())[0][i].equals(new Point()))
				return new Point(0, i);
		return null;
	}
	
	private void increaseWave()
	{
		timer.stop();
		/*ticks=*/numberOfKnights=numberOfMikes=numberOfNagis=numberOfSkullies=deadCreeps=passedCreeps=0;
		if (++wave==6)
		{
			Game.playerWon();
			return;
		}
		ListIterator<Tickable> iterator=getTickables().listIterator();
		while (iterator.hasNext())
			if (iterator.next() instanceof Creeps.Creep)
				iterator.remove();
	}
	
	int getWave()
	{
		return wave;
	}
	
	boolean isRunning()
	{
		return timer.isRunning();
	}
	
	double getTime()
	{
		return time;
	}
	
	void stop()
	{
		timer.stop();
	}
	
	int getTotalDeadCreeps()
	{
		return totalDeadCreeps;
	}
	
	int getTotalPassedCreeps()
	{
		return totalPassedCreeps;
	}
	
	private enum Creeps
	{
		KNIGHT, MIKE, NAGI, SKULLY
	}
}