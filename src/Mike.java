public class Mike extends Creep
{
	@Override
	void impact(Tower tower)
	{
		tower.visit(this);
	}
}