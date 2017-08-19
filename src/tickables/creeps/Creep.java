package tickables.creeps;

import manage.Board;
import manage.Game;
import tickables.Tickable;
import tickables.towers.Tower;

import java.awt.Point;

/**
 * Represents a general type of Creep
 */
public abstract class Creep implements Tickable
{
	/**
	 * Represents this creep's location
	 */
	private final Point location;
	/**
	 * Represents this creep's health-points
	 */
	private double HP=100;
	/**
	 * Indicates if this creep has been injured
	 */
	private boolean injured;
	
	/**
	 * Creates a new creep
	 * @param location the location of this creep
	 * @see Creep#location
	 */
	Creep(Point location)
	{
		this.location=location;
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
		getLocation().translate((int)Game.getLoader().get(
				Board.getMapNum())[(int)getLocation().getX()][(int)getLocation().getY()].getX(),
		                        (int)Game.getLoader()
		                                 .get(Board.getMapNum())
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
}