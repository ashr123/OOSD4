import java.awt.Point;

class Poison extends Tower
{
	Poison(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappend()
	{
		if (!Timer.isFastFoword() && Timer.getTicks()%4==0)//Every second
			hitCreep(1);
		if (Timer.isFastFoword() && Timer.getTicks()%2==0)//Every half a second
			hitCreep(1);
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