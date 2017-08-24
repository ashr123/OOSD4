package tickables.creeps;

import manage.Board;
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
	private int slowdownFactor=1, ticks;
	
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
			if ((!Board.getTimer().isFastForward() && ticks%2==1)/*Every 2*slowdownDuration seconds*/ ||
			    (Board.getTimer().isFastForward())/*Every slowdownDuration seconds*/)
			{
				slowdownFactor=1;
				return;
			}
			ticks++;
//			if (!Board.getTimer().isFastForward())
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
				Board.getMapNum())[(int)getLocation().getX()][(int)getLocation().getY()].getX(),
		                        (int)LevelLoader.get(Board.getMapNum())
				                             [(int)getLocation().getX()][(int)getLocation().getY()].getY());
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
	
	public void slowdown()
	{
		ticks=0;
		slowdownFactor=2;
		slowdownTimer.restart();
	}
	
	int getSlowdownFactor()
	{
		return slowdownFactor;
	}
}