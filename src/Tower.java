public abstract class Tower implements Tickable
{
	@Override
	public void tickHappend()
	{
	
	}
	
	abstract void visit(Knight knight);
	
	abstract void visit(Naji naji);
	
	abstract void visit(Skully skully);
	
	abstract void visit(Mike mike);
}