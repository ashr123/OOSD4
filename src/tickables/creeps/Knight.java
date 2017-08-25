package tickables.creeps;

import manage.Game;
import tickables.towers.Tower;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Point;

/**
 * Represents a {@code Knight}
 */
public class Knight extends Creep
{
	/**
	 * Stores this knight's images
	 */
	private static final ImageIcon[]
			IMAGE_ICON={new ImageIcon(Knight.class.getResource("/media/creeps/abir-1.png")),
			            new ImageIcon(Knight.class.getResource("/media/creeps/abir-2.png"))};
	/**
	 * States the severity of poisoning
	 */
	private static final double POISON_DEGREE=2;
	/**
	 * States if this knight has been poisoned
	 */
	private boolean isPoisoned;
	private int ticks, picTick;
	private static final int SLOWDOWN_DURATION=6;
	/**
	 * Responsible of canceling the poison's effect
	 */
	private final Timer cancelEffect=new Timer(2500, e ->
	{
		if ((!Game.getBoard().getTimer().isFastForward() && ticks%2==1)/*Every 5 seconds*/ ||
		    (Game.getBoard().getTimer().isFastForward())/*Every 2.5 seconds*/)
			isPoisoned=false;
		ticks++;
		if (isPoisoned())
			((Timer)e.getSource()).restart();
	});
	
	/**
	 * Creates a new knight
	 * @param location the location of this knight
	 * @see Knight#location
	 */
	public Knight(Point location)
	{
		super(location, SLOWDOWN_DURATION);
		cancelEffect.setRepeats(false);
	}
	
	/**
	 * @return the {@link Knight#POISON_DEGREE} of this knight
	 */
	public static double getPoisonDegree()
	{
		return POISON_DEGREE;
	}
	
	@Override
	public void tickHappened()
	{
		if ((!Game.getBoard().getTimer().isFastForward() && Game.getBoard().getTimer().getTicks()%(4*getSlowdownFactor())==0)/*Moves every second*/ ||
		    (Game.getBoard().getTimer().isFastForward() && Game.getBoard().getTimer().getTicks()%(2*getSlowdownFactor())==0)/*Moves every half a second*/)
		{
			picTick++;
			moveCreep();
		}
	}
	
	@Override
	public void impact(Tower tower)
	{
		tower.visit(this);
	}
	
	@Override
	public ImageIcon getImageIcon()
	{
		return IMAGE_ICON[picTick%2];
	}
	
	/**
	 * @return {@code true} if this knight is poisoned, {@code false} otherwise
	 */
	public boolean isPoisoned()
	{
		return isPoisoned;
	}
	
	/**
	 * Changes this knight's poisoned state
	 */
	public void setPoisoned()
	{
		ticks=0;
		isPoisoned=true;
		cancelEffect.restart();
	}
}