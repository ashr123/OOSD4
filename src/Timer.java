import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Timer
{
	private javax.swing.Timer timer=new javax.swing.Timer(1000, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			notifyEveryone();
		}
	});
	private LinkedList<Enemy> enemies=new LinkedList<>();
	private LinkedList<Tower> towers=new LinkedList<>();
	
	private void notifyEveryone()
	{
	
	}
	
	void register(Tickable tickable)
	{
	
	}
	
	void unRegister(Tickable tickable)
	{
	
	}
}