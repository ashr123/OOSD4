import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Naji extends Creep
{
	private static final double poisonDegree=1.5;
	private boolean isPoisoned;
	
	static double getPoisonDegree()
	{
		return poisonDegree;
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
		Timer cancelEffect=new Timer(5000, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				isPoisoned=false;
			}
		});
		cancelEffect.setRepeats(false);
		cancelEffect.start();
	}
}