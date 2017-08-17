package manage;

public class TowerWindow
{
	private static TowerWindow ourInstance=new TowerWindow();
	
	public static TowerWindow getInstance()
	{
		return ourInstance;
	}
	
	private TowerWindow()
	{
	}
}
