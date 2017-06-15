import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Naji extends Creep
{
	private static final double POISON_DEGREE=1.5;
	private boolean isPoisoned;
	private int ticks;
	private final javax.swing.Timer cancelEffect=new javax.swing.Timer(2500, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (Timer.isFastFoword())//Every 2.5 seconds
				isPoisoned=false;
			if (!Timer.isFastFoword() && ticks%2==0)//Every 5 seconds
				isPoisoned=false;
			ticks++;
		}
	});
	
	static double getPoisonDegree()
	{
		return POISON_DEGREE;
	}
	
	@Override
	public void tickHappend()
	{
	
	}
	
	@Override
	void impact(Tower tower)
	{
		tower.visit(this);
	}
	
	boolean isPoisoned()
	{
		return isPoisoned;
	}
	
	void setPoisoned()
	{
		isPoisoned=true;
		cancelEffect.setRepeats(false);
		cancelEffect.restart();
	}
}