import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

class Timer
{
	private enum Creeps
	{
		KNIGHT, MIKE, NAGI, SKULLY
	}
	private final LinkedList<Tickable> tickables=new LinkedList<>();
	private int ticks, wave=1, numberOfKnights, numberOfMikes, numberOfNagis, numberOfSkullies, passedCreeps, deadCreeps;
	private boolean fastFoword;
	private Point startingPoint=getStartLocation();
	private Board board;
	private double time;
	private final javax.swing.Timer timer=new javax.swing.Timer(250, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			ListIterator<Tickable> iterator=tickables.listIterator();
			while (iterator.hasNext())
			{
				try
				{
					Tickable tickable=iterator.next();
					tickable.tickHappend();
					if (tickable instanceof Creep && ((Creep)tickable).getHP()<=0)
					{
						iterator.remove();
						deadCreeps++;
					}
				}
				catch (Exception e1)
				{
					iterator.remove();
					Game.decreaseHP();
					passedCreeps++;
				}
			}
			
			if (((!isFastFoword() && getTicks()%4==0)/*Every second*/ ||
			     (isFastFoword() && getTicks()%2==0)/*Every half a second*/) &&
			    (numberOfMikes!=getWave() || numberOfNagis!=getWave() || numberOfKnights!=getWave() || numberOfSkullies!=getWave()))
			{
				Creeps[] enumConstants=Creeps.class.getEnumConstants();
				boolean isRegistered=false;
				while (!isRegistered)
					switch (enumConstants[new Random().nextInt(enumConstants.length)])
					{
						case MIKE:
							if (numberOfMikes==getWave())
								break;
							register(new Mike(new Point(startingPoint)));
							numberOfMikes++;
							isRegistered=true;
							break;
						case NAGI:
							if (numberOfNagis==getWave())
								break;
							register(new Naji(new Point(startingPoint)));
							numberOfNagis++;
							isRegistered=true;
							break;
						case KNIGHT:
							if (numberOfKnights==getWave())
								break;
							register(new Knight(new Point(startingPoint)));
							numberOfKnights++;
							isRegistered=true;
							break;
						case SKULLY:
							if (numberOfSkullies==getWave())
								break;
							register(new Skully(new Point(startingPoint)));
							numberOfSkullies++;
							isRegistered=true;
					}
			}
			ticks++;
			if (!isFastFoword() && getTicks()%2==0)
				time+=.5;
			if (isFastFoword())
				time+=.5;
			if (deadCreeps+passedCreeps==getWave()*4)
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
		numberOfKnights=numberOfMikes=numberOfNagis=numberOfSkullies=deadCreeps=passedCreeps=0;
		wave*=2;
		getTickables().clear();
	}
	
	int getWave()
	{
		switch (wave)
		{
			case 1:
				return 1;
			case 2:
				return 2;
			default:
				return wave/2;
		}
	}
	
	boolean isRunning()
	{
		return timer.isRunning();
	}
	
	double getTime()
	{
		return time;
	}
}