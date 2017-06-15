import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

class Timer
{
	private static final LinkedList<Tickable> tickables=new LinkedList<>();
	private static boolean fastFoword;
	private static int ticks;
	private final javax.swing.Timer timer=new javax.swing.Timer(250, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			for (Tickable tickable : tickables)
				tickable.tickHappend();
			ticks++;
		}
	});
	
	static int getTicks()
	{
		return ticks;
	}
	
	static boolean isFastFoword()
	{
		return fastFoword;
	}
	
	static void setFastFoword(boolean fastFoword)
	{
		Timer.fastFoword=fastFoword;
	}
	
	static LinkedList<Tickable> getTickables()
	{
		return tickables;
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