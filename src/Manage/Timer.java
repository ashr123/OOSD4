package Manage;

import Tickables.Creeps.*;
import Tickables.Tickable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Represents the "engine" of the game responsible for the towers's attacks and the movement of the creeps
 */
public class Timer
{
	/**
	 * Holds all the towers and the creeps that participate in the current wave
	 */
	private final LinkedList<Tickable> tickables=new LinkedList<>();
	/**
	 * The starting point of the new creeps that to be created
	 */
	private final Point startingPoint=getStartLocation();
	private final Board board;
	private int ticks, wave=1, numberOfKnights, numberOfMikes, numberOfNagis, numberOfSkullies, passedCreeps, deadCreeps, totalDeadCreeps, totalPassedCreeps;
	/**
	 * {@code true} if the game to be tick twice as fase, {@code false} otherwise
	 */
	private boolean fastForward;
	/**
	 * Counting the time since the 1st wave
	 */
	private double time;
	/**
	 * Runs the game
	 */
	private final javax.swing.Timer timer=new javax.swing.Timer(250, new ActionListener()
	{
		/**
		 * Creates and moves the creeps and hits them with the towers
		 */
		@SuppressWarnings("ConstantConditions")
		@Override
		public void actionPerformed(ActionEvent e)
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
				}
			}
			if (((!isFastForward() && getTicks()%4==0)/*Every second*/ ||
			     (isFastForward() && getTicks()%2==0)/*Every half a second*/) &&
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
							register(new Mike(new Point(startingPoint)));
							numberOfMikes++;
							isRegistered=true;
							break;
						case NAGI:
							if (numberOfNagis==Math.pow(2, getWave()-1))
								break;
							register(new Naji(new Point(startingPoint)));
							numberOfNagis++;
							isRegistered=true;
							break;
						case KNIGHT:
							if (numberOfKnights==Math.pow(2, getWave()-1))
								break;
							register(new Knight(new Point(startingPoint)));
							numberOfKnights++;
							isRegistered=true;
							break;
						case SKULLY:
							if (numberOfSkullies==Math.pow(2, getWave()-1))
								break;
							register(new Skully(new Point(startingPoint)));
							numberOfSkullies++;
							isRegistered=true;
					}
			}
			ticks++;
			if (!isFastForward() && getTicks()%2==0)
				time+=.5;
			if (isFastForward())
				time+=.5;
			if (deadCreeps+passedCreeps>=Math.pow(2, getWave()-1)*4)
				increaseWave();
			board.repaint();
		}
	});
	
	/**
	 * Creates a new timer
	 * @param board only for repainting
	 */
	Timer(Board board)
	{
		this.board=board;
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
		tickables.addFirst(tickable);
	}
	
	/**
	 * Starts the timer and the current wave
	 */
	void start()
	{
		timer.start();
	}
	
	/**
	 * @return the starting point of the creeps to be created
	 */
	private Point getStartLocation()
	{
		for (int i=0; i<Game.getLoader().get(Board.getMapNum())[0].length; i++)
			if (!Game.getLoader().get(Board.getMapNum())[0][i].equals(new Point()))
				return new Point(0, i);
		return null;
	}
	
	/**
	 * Increases the current wave
	 */
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
			if (iterator.next() instanceof Creep)
				iterator.remove();
	}
	
	/**
	 * @return the current wave
	 */
	int getWave()
	{
		return wave;
	}
	
	/**
	 * @return {@code true} if this timer is active, {@code false} otherwise
	 */
	boolean isRunning()
	{
		return timer.isRunning();
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
	
	private enum Creeps
	{
		KNIGHT, MIKE, NAGI, SKULLY
	}
}