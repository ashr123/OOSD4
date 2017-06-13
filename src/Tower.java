public class Tower implements Tickable, Visitor
{
	@Override
	public void tickHappend()
	{
	
	}
	
	@Override
	public void visit(Enemy enemy)
	{
		enemy.impact(this);
	}
}