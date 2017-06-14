class Mike extends Creep
{
	@Override
	public void tickHappend()
	{
	
	}
	
	@Override
	void impact(Tower tower)
	{
		tower.visit(this);
	}
}