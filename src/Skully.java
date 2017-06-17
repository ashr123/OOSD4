import java.awt.*;

class Skully extends Creep
{
	Skully(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappend()
	{
		if (!Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%4==0)//Moves every second
			moveCreep();
		if (Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%2==0)//Moves every half a second
			moveCreep();
	}
	
	@Override
	void impact(Tower tower)
	{
		tower.visit(this);
	}
}