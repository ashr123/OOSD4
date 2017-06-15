import java.awt.Point;

class Magician extends Tower
{
	Magician(Point location)
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
		knight.setHP(knight.getHP()-(knight.isPoisoned() ? 30*Knight.getPoisonDegree() : 30));
	}
	
	@Override
	void visit(Naji naji)
	{
		naji.setHP((int)(naji.getHP()-(naji.isPoisoned() ? 10*Naji.getPoisonDegree() : 10)));
	}
	
	@Override
	void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-25);
	}
	
	@Override
	void visit(Mike mike)
	{
		mike.setHP(mike.getHP()-10);
	}
}