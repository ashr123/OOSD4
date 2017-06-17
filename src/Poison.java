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
		if (!Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%4==0)//Every second
			hitCreep(1, false);
		if (Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%2==0)//Every half a second
			hitCreep(1, false);
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
		if (skully.getHP()<=0)
			Board.getTimer().unRegister(skully);
	}
	
	@Override
	void visit(Mike mike)
	{
	}
}