public class Naji extends Creep
{
	@Override
	void impact(Tower tower)
	{
		tower.visit(this);
	}
}