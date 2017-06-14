abstract class Creep implements Tickable
{
	private int HP=100;
	
	@Override
	public abstract void tickHappend();
	
	abstract void impact(Tower tower);
	
	int getHP()
	{
		return HP;
	}
	
	void setHP(int HP)
	{
		this.HP=HP;
	}
}