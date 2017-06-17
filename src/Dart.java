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
		if (!Timer.isFastFoword() && Timer.getTicks()%2==0)//Every half a second
			hitCreep(2, false);
		if (Timer.isFastFoword())//Every quarter of a second
			hitCreep(2, false);
	}
	
	@Override
	public void visit(Knight knight)
	{
	}
	
	@Override
	void visit(Naji naji)
	{
		naji.setHP((int)(naji.getHP()-(naji.isPoisoned() ? 30*Naji.getPoisonDegree() : 30)));
		if (naji.getHP()<=0)
			Timer.unRegister(naji);
	}
	
	@Override
	void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-15);
		if (skully.getHP()<=0)
			Timer.unRegister(skully);
	}
	
	@Override
	void visit(Mike mike)
	{
		mike.setHP(mike.getHP()-30);
		if (mike.getHP()<=0)
			Timer.unRegister(mike);
	}
}