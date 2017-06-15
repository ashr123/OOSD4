import java.awt.Point;

abstract class Creep implements Tickable
{
	private int HP=100;
	private Point location;
	
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
	
	void setLocation(Point location)
	{
		this.location=location;
	}
}