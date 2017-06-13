public abstract class Creep implements Tickable
{
	private int HP=100;
	
	@Override
	public void tickHappend()
	{
	
	}
	
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