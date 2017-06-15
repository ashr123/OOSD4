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
		if (!Timer.isFastFoword() && Timer.getTicks()%4==0)
			moveCreep();
		if (Timer.isFastFoword() && Timer.getTicks()%2==0)
			moveCreep();
	}
	
	@Override
	void impact(Tower tower)
	{
		tower.visit(this);
	}
}