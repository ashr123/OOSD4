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
	private int wave=1, numberOfKnights, numberOfMikes, numberOfNagis, numberOfSkullies;
	private boolean fastFoword;
	private int ticks;
	private Point startingPoint=getStartLocation();
	private Board board;
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
						iterator.remove();
				}
				catch (Exception e1)
				{
					iterator.remove();
				}
			}
			
			if (((!isFastFoword() && getTicks()%4==0)/*Every second*/ ||
			     isFastFoword() && getTicks()%2==0/*Every half a second*/) &&
			    (numberOfMikes!=wave || numberOfNagis!=wave || numberOfKnights!=wave || numberOfSkullies!=wave))
			{
				Creeps[] enumConstants=Creeps.class.getEnumConstants();
				boolean isRegistered=false;
				while (!isRegistered)
					switch (enumConstants[new Random().nextInt(enumConstants.length)])
					{
						case MIKE:
							if (numberOfMikes==wave)
								break;
							register(new Mike(new Point(startingPoint)));
							numberOfMikes++;
							isRegistered=true;
							break;
						case NAGI:
							if (numberOfNagis==wave)
								break;
							register(new Naji(new Point(startingPoint)));
							numberOfNagis++;
							isRegistered=true;
							break;
						case KNIGHT:
							if (numberOfKnights==wave)
								break;
							register(new Knight(new Point(startingPoint)));
							numberOfKnights++;
							isRegistered=true;
							break;
						case SKULLY:
							if (numberOfSkullies==wave)
								break;
							register(new Skully(new Point(startingPoint)));
							numberOfSkullies++;
							isRegistered=true;
					}
			}
			ticks++;
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
	
	void unRegister(Tickable tickable)
	{
		tickables.remove(tickable);
	}
	
	void start()
	{
		timer.start();
	}
	
	private Point getStartLocation()
	{
		for (int i=0; i<Game.getLoader().get(Board.getLevel()).length; i++)
			if (!Game.getLoader().get(Board.getLevel())[0][i].equals(new Point()))
				return new Point(0, i);
		return null;
	}
	
	void increaseWave()
	{
		numberOfKnights=numberOfMikes=numberOfNagis=numberOfSkullies=0;
		wave*=2;
		getTickables().clear();
	}
}