public class Skully extends Creep
{
	@Override
	void impact(Tower tower)
	{
		tower.visit(this);
	}
}