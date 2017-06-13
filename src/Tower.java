public abstract class Tower implements Tickable
{
	@Override
	public void tickHappend()
	{
	
	}
	
	public abstract void visit(Knight knight);
	
	public abstract void visit(Naji naji);
	
	public abstract void visit(Skully skully);
	
	public abstract void visit(Mike mike);
}