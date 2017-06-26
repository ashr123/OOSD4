import javax.swing.*;
import java.awt.*;

abstract class Creep implements Tickable
{
	private double HP=100;
	private Point location;
	private boolean injured;
	
	Creep(Point location)
	{
		this.location=location;
	}
	
	/**
	 * Moves the {@code Creep} to a new location
	 */
	@Override
	public abstract void tickHappend();
	
	abstract void impact(Tower tower);
	
	double getHP()
	{
		return HP;
	}
	
	void setHP(double HP)
	{
		this.HP=HP;
	}
	
	@Override
	public Point getLocation()
	{
		return location;
	}
	
	void moveCreep()
	{
		getLocation().translate((int)Game.getLoader().get(Board.getLevel())[(int)getLocation().getX()][(int)getLocation().getY()].getX(), (int)Game.getLoader().get(Board.getLevel())[(int)getLocation().getX()][(int)getLocation().getY()].getY());
	}
	
	public abstract ImageIcon getImageIcon();
	
	boolean isInjured()
	{
		return injured;
	}
	
	void setInjured(boolean injured)
	{
		this.injured=injured;
	}
}