public class Knight extends Creep
{
	@Override
	void impact(Visitor visitor)
	{
		visitor.visit(visitor);
	}
}