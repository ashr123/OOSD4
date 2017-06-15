import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Knight extends Creep
{
	private static final int poisonDegree=2;
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
	
	static int getPoisonDegree()
	{
		return poisonDegree;
	}
	
	@Override
	public void tickHappend()
	{
		getLocation().translate(Game.getLoader().get(Board));
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