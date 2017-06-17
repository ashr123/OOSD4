import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

class Timer
{
	private final LinkedList<Tickable> tickables=new LinkedList<>();
	private boolean fastFoword;
	private int ticks;
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
	
	int getTicks()
	{
		return ticks;
	}
	
	boolean isFastFoword()
	{
		return fastFoword;
	}
	
	void setFastFoword(boolean fastFoword)
	{
		this.fastFoword=fastFoword;
	}
	
	LinkedList<Tickable> getTickables()
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