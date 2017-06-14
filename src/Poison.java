import java.awt.*;

class Poison extends Tower
{
	Poison(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappend()
	{
	
	}
	
	@Override
	void visit(Knight knight)
	{
		knight.setPoisoned();
	}
	
	@Override
	void visit(Naji naji)
	{
		naji.setPoisoned();
	}
	
	@Override
	void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-20);
	}
	
	@Override
	void visit(Mike mike)
	{
	}
}