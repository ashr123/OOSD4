public class Knight extends Creep
{
	@Override
	void impact(Tower tower)
	{
		tower.visit(this);
	}
}