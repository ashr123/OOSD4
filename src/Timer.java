import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

class Timer
{
	private int ticks;
	private final LinkedList<Tickable> tickables=new LinkedList<>();
	private final javax.swing.Timer timer=new javax.swing.Timer(500, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			for (Tickable tickable : tickables)
				tickable.tickHappend();
			ticks++;
		}
	});
	
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
	
	int getTicks()
	{
		return ticks;
	}
}