public class Magician extends Tower
{
	@Override
	public void visit(Knight knight)
	{
		knight.setHP(knight.getHP()-30);
	}
	
	@Override
	public void visit(Naji naji)
	{
		naji.setHP(naji.getHP()-10);
	}
	
	@Override
	public void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-25);
	}
	
	@Override
	public void visit(Mike mike)
	{
		mike.setHP(mike.getHP()-10);
	}
}