import java.awt.*;
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
	
	Knight(Point location)
	{
		super(location);
	}
	
	static int getPoisonDegree()
	{
		return poisonDegree;
	}
	
	@Override
	public void tickHappend()
	{
		if (!Timer.isFastFoword() && Timer.getTicks()%4==0)//Moves every second
			moveCreep();
		if (Timer.isFastFoword() && Timer.getTicks()%2==0)//Moves every half a second
			moveCreep();
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
		ticks=0;
		isPoisoned=true;
		cancelEffect.setRepeats(false);
		cancelEffect.restart();
	}
}