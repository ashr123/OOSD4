package tickables.creeps;

import manage.Game;
import manage.LevelLoader;
import tickables.Tickable;
import tickables.towers.Tower;

import javax.swing.Timer;
import java.awt.Point;

/**
 * Represents a general type of Creep
 */
public abstract class Creep implements Tickable//Also represents the Visited
{
	/**
	 * Represents this creep's location
	 */
	private final Point location;
	private final Timer slowdownTimer;
	/**
	 * Represents this creep's health-points
	 */
	private double HP=100;
	/**
	 * Indicates if this creep has been injured
	 */
	private boolean injured;
	/**
	 * the rate of which the creeps will be slowed down
	 */
	private int slowdownFactor=1;
	private int ticks, picTick;
	
	/**
	 * Creates a new creep
	 * @param location the location of this creep
	 * @see Creep#location
	 */
	Creep(Point location, int slowdownDuration)
	{
		this.location=location;
		slowdownTimer=new Timer(slowdownDuration*1000/2, e ->
		{
			if ((!Game.getBoard().getTimer().isFastForward() && ticks%2==1)/*Every 2*slowdownDuration seconds*/ || (Game.getBoard().getTimer().isFastForward())/*Every slowdownDuration seconds*/)
			{
				slowdownFactor=1;
				return;
			}
			ticks++;
//			if (!Game.getBoard().getTimer().isFastForward())
			((Timer)e.getSource()).restart();
		});
		
		slowdownTimer.setRepeats(false);
	}
	
	/**
	 * Orders this creep to be impacted
	 * @param tower the tower that had impacted this creep
	 */
	public abstract void impact(Tower tower);
	
	/**
	 * @return this creep's {@link Creep#HP}
	 */
	public double getHP()
	{
		return HP;
	}
	
	/**
	 * Sets this creep's {@link Creep#HP}
	 * @param HP current HP after the change
	 */
	public void setHP(double HP)
	{
		this.HP=HP;
	}
	
	@Override
	public Point getLocation()
	{
		return location;
	}
	
	/**
	 * Moves this creep to new {@link Creep#location}
	 */
	void moveCreep()
	{
		getLocation().translate((int)LevelLoader.get(
				Game.getMapNum())[(int)getLocation().getX()][(int)getLocation().getY()].getX(),
		                        (int)LevelLoader.get(Game.getMapNum())
				                             [(int)getLocation().getX()][(int)getLocation().getY()].getY());
		picTick++;
	}
	
	/**
	 * @return {@code true} if this creep has been injured, {@code false} otherwise
	 * @see Creep#injured
	 */
	public boolean isInjured()
	{
		return injured;
	}
	
	/**
	 * Sets this creep's injured condition
	 * @param injured this creep's condition
	 * @see Creep#injured
	 */
	public void setInjured(boolean injured)
	{
		this.injured=injured;
	}
	
	/**
	 * Slows this creep down for {@code slowdownDuration} seconds
	 */
	public void slowdown()
	{
		ticks=0;
		slowdownFactor=2;
		slowdownTimer.restart();
	}
	
	/**
	 * @return the slowdown factor
	 */
	int getSlowdownFactor()
	{
		return slowdownFactor;
	}
	
	int getPicTick()
	{
		return picTick;
	}
}