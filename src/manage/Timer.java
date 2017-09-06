package manage;

import tickables.Tickable;
import tickables.creeps.Creep;
import tickables.creeps.Knight;
import tickables.creeps.Mike;
import tickables.creeps.Naji;
import tickables.creeps.Skully;

import java.awt.Point;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Represents the "engine" of the game responsible for the towers's attacks and the movement of the creeps
 */
@SuppressWarnings("ClassHasNoToStringMethod")
public class Timer
{
	@SuppressWarnings("UnsecureRandomNumberGeneration")
	private static final Random random=new Random();
	/**
	 * Holds all the towers and the creeps that participate in the current wave
	 */
	private final LinkedList<Tickable> tickables=new LinkedList<>();
	/**
	 * The starting point of the new creeps that to be created
	 */
	private final Point startingPoint;
	/**
	 * Creates and moves the creeps and hits them with the towers
	 */
	private final javax.swing.Timer timer;
	private int ticks, wave=1, numberOfKnights, numberOfMikes, numberOfNagis, numberOfSkullies, passedCreeps, deadCreeps, totalDeadCreeps, totalPassedCreeps;
	/**
	 * {@code true} if the game to be tick twice as fase, {@code false} otherwise
	 */
	private boolean fastForward;
	/**
	 * Counting the time since the 1st wave
	 */
	private double time, numOfCreeps=Math.pow(2, getWave()-1);
	
	/**
	 * Creates a new timer
	 */
	@SuppressWarnings("ConstantConditions")
	Timer(int mapNum)
	{
		startingPoint=getStartLocation(mapNum);
		timer=new javax.swing.Timer(250, e ->
		{
			ListIterator<Tickable> iterator=getTickables().listIterator();
			while (iterator.hasNext())
			{
				try
				{
					Tickable tickable=iterator.next();
					tickable.tickHappened();
					if (tickable instanceof Creep && ((Creep)tickable).getHP()<=0)
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
					if (Game.getHP()==20)
						return;
				}
			}
			if (((!isFastForward() && getTicks()%4==0)/*Every second*/ ||
			     (isFastForward() && getTicks()%2==0)/*Every half a second*/) &&
			    (numberOfMikes!=numOfCreeps ||
			     numberOfNagis!=numOfCreeps ||
			     numberOfKnights!=numOfCreeps ||
			     numberOfSkullies!=numOfCreeps))
			{
				final Creeps[] enumConstants=Creeps.class.getEnumConstants();
				boolean isRegistered=false;
				while (!isRegistered)
					switch (enumConstants[random.nextInt(enumConstants.length)])
					{
						case MIKE:
							if (numberOfMikes==numOfCreeps)
								break;
							register(new Mike(new Point(startingPoint)));
							numberOfMikes++;
							isRegistered=true;
							break;
						case NAGI:
							if (numberOfNagis==numOfCreeps)
								break;
							register(new Naji(new Point(startingPoint)));
							numberOfNagis++;
							isRegistered=true;
							break;
						case KNIGHT:
							if (numberOfKnights==numOfCreeps)
								break;
							register(new Knight(new Point(startingPoint)));
							numberOfKnights++;
							isRegistered=true;
							break;
						case SKULLY:
							if (numberOfSkullies==numOfCreeps)
								break;
							register(new Skully(new Point(startingPoint)));
							numberOfSkullies++;
							isRegistered=true;
					}
			}
			ticks++;
			if ((!isFastForward() && getTicks()%2==0) || isFastForward())
				time+=.5;
			if (deadCreeps+passedCreeps==numOfCreeps*4)
				increaseWave();
			Game.getBoard().repaint();
		});
	}
	
	/**
	 * @return the starting point of the creeps to be created
	 */
	private static Point getStartLocation(int mapNum)
	{
		Point zeroPoint=new Point();
		for (int i=0; i<LevelLoader.get(mapNum)[0].length; i++)
			if (!LevelLoader.get(mapNum)[0][i].equals(zeroPoint))
				return new Point(0, i);
		return null;
	}
	
	/**
	 * @return the number of tick since the 1st wave
	 */
	public int getTicks()
	{
		return ticks;
	}
	
	/**
	 * @return {@code true} if the gave moves twice as fast, {@code false} otherwise
	 */
	public boolean isFastForward()
	{
		return fastForward;
	}
	
	/**
	 * Changes the speed of the game
	 * @param fastForward the desired speed
	 */
	void setFastForward(boolean fastForward)
	{
		this.fastForward=fastForward;
	}
	
	/**
	 * @return the list of towers and creeps
	 */
	public LinkedList<Tickable> getTickables()
	{
		return tickables;
	}
	
	/**
	 * Registers a new tower or creep
	 * @param tickable the tower or creep to be registered
	 */
	void register(Tickable tickable)
	{
		getTickables().addFirst(tickable);
	}
	
	/**
	 * Starts the timer and the current wave
	 */
	void start()
	{
		TowerWindow.disposeTowerWindow();
		Game.getGoButton().setEnabled(false);
		timer.start();
	}
	
	/**
	 * Increases the current wave
	 */
	private void increaseWave()
	{
		if (wave==5)
			Game.playerWon();
		else
		{
			stop();
//			ticks=0;
			wave++;
			resetCreeps();
//			ListIterator<Tickable> iterator=getTickables().listIterator();
//			while (iterator.hasNext())
//				if (iterator.next() instanceof Creep)
//					iterator.remove();
		}
	}
	
	private void resetCreeps()
	{
		numberOfKnights=numberOfMikes=numberOfNagis=numberOfSkullies=deadCreeps=passedCreeps=0;
		numOfCreeps=Math.pow(2, getWave()-1);
	}
	
	/**
	 * @return the current wave
	 */
	int getWave()
	{
		return wave;
	}
	
	/**
	 * @return the total time that has passed since the 1st wave
	 */
	double getTime()
	{
		return time;
	}
	
	/**
	 * Stops the timer
	 */
	void stop()
	{
		timer.stop();
		Game.getGoButton().setEnabled(true);
	}
	
	/**
	 * @return the total creeps that has been killed by the towers
	 */
	int getTotalDeadCreeps()
	{
		return totalDeadCreeps;
	}
	
	/**
	 * @return the total creeps that managed to pass all the way through the path
	 */
	int getTotalPassedCreeps()
	{
		return totalPassedCreeps;
	}
	
	/**
	 * Resets the timer
	 */
	void reset()
	{
		wave=1;
		time=0;
		ticks=totalDeadCreeps=totalPassedCreeps=0;
		fastForward=false;
		resetCreeps();
		getTickables().clear();
		
	}
	
	private enum Creeps
	{
		KNIGHT, MIKE, NAGI, SKULLY
	}
}