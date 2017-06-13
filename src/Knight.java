import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Knight extends Creep
{
	private static final int poisonDegree=2;
	private boolean isPoisoned;
	private Timer cancelEffect=new Timer(5000, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			isPoisoned=false;
		}
	});
	
	static int getPoisonDegree()
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
		cancelEffect.setRepeats(false);
		cancelEffect.restart();
	}
}