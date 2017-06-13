public class Dart extends Tower
{
	@Override
	public void visit(Knight knight)
	{
	
	}
	
	@Override
	public void visit(Naji naji)
	{
		naji.setHP(naji.getHP()-30);
	}
	
	@Override
	public void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-15);
	}
	
	@Override
	public void visit(Mike mike)
	{
		mike.setHP(mike.getHP()-30);
	}
}