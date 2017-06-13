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
	private LinkedList<Tickable> tickables=new LinkedList<>();
	
	private void notifyEveryone()
	{
		for (Tickable tickable : tickables)
			tickable.tickHappend();
	}
	
	void register(Tickable tickable)
	{
		tickables.add(tickable);
	}
	
	void unRegister(Tickable tickable)
	{
		tickables.remove(tickable);
	}
	
	void start()
	{
		timer.start();
	}
}