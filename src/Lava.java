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
		if (!Timer.isFastFoword() && Timer.getTicks()%4==0)//Every second
			hitCreep(1, true);
		if (Timer.isFastFoword() && Timer.getTicks()%2==0)//Every half a second
			hitCreep(1, true);
	}
	
	@Override
	void visit(Knight knight)
	{
		knight.setHP(knight.getHP()-(knight.isPoisoned() ? 10*Knight.getPoisonDegree() : 10));
		if (knight.getHP()<=0)
			Timer.unRegister(knight);
	}
	
	@Override
	void visit(Naji naji)
	{
		naji.setHP((int)(naji.getHP()-(naji.isPoisoned() ? 15*Naji.getPoisonDegree() : 15)));
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
		mike.setHP(mike.getHP()-15);
		if (mike.getHP()<=0)
			Timer.unRegister(mike);
	}
}