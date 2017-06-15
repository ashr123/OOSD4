import java.awt.Point;
import java.util.LinkedList;

abstract class Tower implements Tickable//Represents also the Visitor
{
	private final Point location;
	
	Tower(Point location)
	{
		this.location=location;
	}
	
	@Override
	public abstract void tickHappend();
	
	abstract void visit(Knight knight);
	
	abstract void visit(Naji naji);
	
	abstract void visit(Skully skully);
	
	abstract void visit(Mike mike);
	
	Point getLocation()
	{
		return location;
	}
	
	void hitCreep(int range)
	{
		for (Tickable tickable : Timer.getTickables())
			if (tickable instanceof Creep && ((Creep)tickable).getLocation().distance(getLocation())
			                                 <=range*Math.sqrt(2))
				((Creep)tickable).impact(this);
	}
}