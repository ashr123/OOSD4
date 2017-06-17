import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
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
	private final javax.swing.Timer timer=new javax.swing.Timer(250, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (((!isFastFoword() && getTicks()%4==0)/*Every second*/ || isFastFoword() && getTicks()%2==0/*Every half a second*/) && (numberOfMikes!=wave || numberOfNagis!=wave || numberOfKnights!=wave || numberOfSkullies!=wave))
			{
				Creeps[] enumConstants=Creeps.class.getEnumConstants();
				boolean isRegistered=false;
				while (!isRegistered)
					switch (enumConstants[new Random().nextInt(enumConstants.length)])
					{
						case MIKE:
							register(new Mike(startingPoint));
							numberOfMikes++;
							isRegistered=true;
							break;
						case NAGI:
							register(new Naji(startingPoint));
							numberOfNagis++;
							isRegistered=true;
							break;
						case KNIGHT:
							register(new Knight(startingPoint));
							numberOfKnights++;
							isRegistered=true;
							break;
						case SKULLY:
							register(new Skully(startingPoint));
							numberOfSkullies++;
							isRegistered=true;
					}
			}
			for (Tickable tickable : tickables)
				tickable.tickHappend();
			ticks++;
		}
	});
	
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
		for (Point point : Game.getLoader().get(Board.getLevel())[0])
			if (!point.equals(new Point()))
				return point;
		return null;
	}
	
	void increaseWave()
	{
		numberOfKnights=numberOfMikes=numberOfNagis=numberOfSkullies=0;
		wave*=2;
		getTickables().clear();
	}
}