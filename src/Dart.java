import java.awt.*;

class Dart extends Tower
{
	Dart(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappend()
	{
	
	}
	
	@Override
	public void visit(Knight knight)
	{
	}
	
	@Override
	void visit(Naji naji)
	{
		naji.setHP((int)(naji.getHP()-(naji.isPoisoned() ? 30*Naji.getPoisonDegree() : 30)));
	}
	
	@Override
	void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-15);
	}
	
	@Override
	void visit(Mike mike)
	{
		mike.setHP(mike.getHP()-30);
	}
}