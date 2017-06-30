package Tickables.Towers;

import Manage.Board;
import Tickables.Creeps.*;
import Tickables.Tickable;

import java.awt.*;

/**
 * Represents a general type of tower
 */
public abstract class Tower implements Tickable//Represents also the Visitor
{
	/**
	 * Represents this cree['s location
	 */
	private final Point location;
	/**
	 * States if this tower has been clicked
	 */
	private boolean clicked;
	
	/**
	 * Creates a new tower
	 * @param location the location of this tower
	 * @see Tower#location
	 */
	public Tower(Point location)
	{
		this.location=location;
	}
	
	@Override
	public abstract void tickHappened();
	
	/**
	 * Injures a {@code Knight}
	 * @param knight the knight to be injured
	 * @see Tower#hitCreep(int, boolean)
	 */
	public abstract void visit(Knight knight);
	
	/**
	 * Injures a {@code Naji}
	 * @param naji the naji to be injured
	 * @see Tower#hitCreep(int, boolean)
	 */
	public abstract void visit(Naji naji);
	
	/**
	 * Injures a {@code Skully}
	 * @param skully the skully to be injured
	 * @see Tower#hitCreep(int, boolean)
	 */
	public abstract void visit(Skully skully);
	
	/**
	 * Injures a {@code Mike}
	 * @param mike the mike to be injured
	 * @see Tower#hitCreep(int, boolean)
	 */
	public abstract void visit(Mike mike);
	
	@Override
	public Point getLocation()
	{
		return location;
	}
	
	/**
	 * Hits a one creep or many according to this tower's type (regular or cash'an) and range
	 * @param range the range of this tower
	 * @param isCashan the type of this tower
	 */
	void hitCreep(int range, boolean isCashan)
	{
		for (Tickable tickable : Board.getTimer().getTickables())
			if (tickable instanceof Creep &&
			    tickable.getLocation().distance(getLocation())<=range*Math.sqrt(2))
			{
				((Creep)tickable).impact(this);
				if (!isCashan)
					return;
			}
	}
	
	//@Override
	//public abstract ImageIcon getImageIcon();
	
	/**
	 * @return if this tower has been clicked
	 */
	public boolean isClicked()
	{
		return clicked;
	}
	
	/**
	 * Changes this tower's {@link Tower#clicked} state
	 * @param clicked the new {@code injured} state
	 */
	public void setClicked(boolean clicked)
	{
		this.clicked=clicked;
	}
}