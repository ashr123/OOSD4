import java.awt.*;

class Mike extends Creep
{
	Mike(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappend()
	{
		if (!Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%4==0)
			moveCreep();
		if (Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%2==0)
			moveCreep();
	}
	
	@Override
	void impact(Tower tower)
	{
		tower.visit(this);
	}
}