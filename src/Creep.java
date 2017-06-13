public class Creep implements Tickable
{
	@Override
	public void tickHappend()
	{
	
	}
	
	void impact(Visitor visitor)
	{
		visitor.visit(this);
	}
}