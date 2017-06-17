import java.awt.*;

class Magician extends Tower
{
	Magician(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappend()
	{
		if (!Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%4==0)//Every second
			hitCreep(1, false);
		if (Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%2==0)//Every half a second
			hitCreep(1, false);
	}
	
	@Override
	void visit(Knight knight)
	{
		knight.setHP(knight.getHP()-(knight.isPoisoned() ? 30*Knight.getPoisonDegree() : 30));
		if (knight.getHP()<=0)
			Board.getTimer().unRegister(knight);
	}
	
	@Override
	void visit(Naji naji)
	{
		naji.setHP((int)(naji.getHP()-(naji.isPoisoned() ? 10*Naji.getPoisonDegree() : 10)));
		if (naji.getHP()<=0)
			Board.getTimer().unRegister(naji);
	}
	
	@Override
	void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-25);
		if (skully.getHP()<=0)
			Board.getTimer().unRegister(skully);
	}
	
	@Override
	void visit(Mike mike)
	{
		mike.setHP(mike.getHP()-10);
		if (mike.getHP()<=0)
			Board.getTimer().unRegister(mike);
	}
}