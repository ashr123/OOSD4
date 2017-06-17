import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Knight extends Creep
{
	private static final ImageIcon IMAGE_ICON=new ImageIcon(
			Tickable.class.getResource("Media/creeps/abir-1.png"));
	private static final int poisonDegree=2;
	private boolean isPoisoned;
	private int ticks;
	private final javax.swing.Timer cancelEffect=new javax.swing.Timer(2500, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (Board.getTimer().isFastFoword())//Every 2.5 seconds
				isPoisoned=false;
			if (!Board.getTimer().isFastFoword() && ticks%2==0)//Every 5 seconds
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
		if ((!Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%4==0)/*Moves every second*/ ||
		    (Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%2==0)/*Moves every half a second*/)
			moveCreep();
	}
	
	@Override
	void impact(Tower tower)
	{
		tower.visit(this);
	}
	
	@Override
	public ImageIcon getImageIcon()
	{
		return IMAGE_ICON;
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