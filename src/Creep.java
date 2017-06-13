public abstract class Creep implements Tickable
{
	@Override
	public void tickHappend()
	{
	
	}
	
	abstract void impact(Visitor visitor);
}