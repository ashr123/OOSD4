import java.awt.*;

abstract class Creep implements Tickable
{
	private int HP=100;
	private Point location;
	
	/**
	 * Moves the {@code Creep} to a new location
	 */
	@Override
	public abstract void tickHappend();
	
	abstract void impact(Tower tower);
	
	int getHP()
	{
		return HP;
	}
	
	void setHP(int HP)
	{
		this.HP=HP;
	}
	
	Point getLocation()
	{
		return location;
	}
	
	protected void moveCreep()
	{
		getLocation().translate((int)Game.getLoader().get(Board.getLevel())[(int)getLocation().getX()][(int)getLocation().getY()].getX(), (int)Game.getLoader().get(Board.getLevel())[(int)getLocation().getX()][(int)getLocation().getY()] .getY());
	}
}