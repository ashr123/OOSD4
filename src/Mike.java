class Mike extends Creep
{
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