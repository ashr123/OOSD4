class Skully extends Creep
{
	@Override
	public void tickHappend()
	{
		if (!Timer.isFastFoword() && Timer.getTicks()%4==0)//Moves every second
			moveCreep();
		if (Timer.isFastFoword() && Timer.getTicks()%2==0)//Moves every half a second
			moveCreep();
	}
	
	@Override
	void impact(Tower tower)
	{
		tower.visit(this);
	}
}