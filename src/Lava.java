import java.awt.*;

class Lava extends Tower
{
	Lava(Point location)
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
		knight.setHP(knight.getHP()-(knight.isPoisoned() ? 10*Knight.getPoisonDegree() : 10));
	}
	
	@Override
	void visit(Naji naji)
	{
		naji.setHP((int)(naji.getHP()-(naji.isPoisoned() ? 15*Naji.getPoisonDegree() : 15)));
	}
	
	@Override
	void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-15);
	}
	
	@Override
	void visit(Mike mike)
	{
		mike.setHP(mike.getHP()-15);
	}
}