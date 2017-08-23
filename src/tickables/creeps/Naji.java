package tickables.creeps;

import manage.Board;
import tickables.towers.Tower;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Point;

/**
 * Represents a {@code Naji}
 */
public class Naji extends Creep
{
	/**
	 * Stores this naji's images
	 */
	private static final ImageIcon[]
			IMAGE_ICON={new ImageIcon(Naji.class.getResource("/media/creeps/naji-1.png")),
			            new ImageIcon(Naji.class.getResource("/media/creeps/naji-2.png"))};
	/**
	 * States the severity of poisoning
	 */
	private static final double POISON_DEGREE=1.5;
	/**
	 * States if this naji has been poisoned
	 */
	private boolean isPoisoned;
	private int ticks, picTick;
	/**
	 * Responsible of canceling the poison's effect
	 */
	private final Timer cancelEffect=new Timer(2500, e ->
	{
		if ((!Board.getTimer().isFastForward() && ticks%2==1)/*Every 5 seconds*/ ||
		    (Board.getTimer().isFastForward())/*Every 2.5 seconds*/)
			isPoisoned=false;
		ticks++;
		if (isPoisoned())
			((Timer)e.getSource()).restart();
	});
	
	/**
	 * Creates a new Naji
	 * @param location the location of this knight
	 * @see Naji#location
	 */
	public Naji(Point location)
	{
		super(location);
		cancelEffect.setRepeats(false);
	}
	
	/**
	 * @return the {@link Naji#POISON_DEGREE} of this naji
	 */
	public static double getPoisonDegree()
	{
		return POISON_DEGREE;
	}
	
	@Override
	public void tickHappened()
	{
		if ((!Board.getTimer().isFastForward() && Board.getTimer().getTicks()%2==0)/*Moves every half a second*/ ||
		    (Board.getTimer().isFastForward())/*Moves every quarter of a second*/)
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
	 * @return {@code true} if this naji is poisoned, {@code false} otherwise
	 */
	public boolean isPoisoned()
	{
		return isPoisoned;
	}
	
	/**
	 * Changes this naji's poisoned state
	 */
	public void setPoisoned()
	{
		ticks=0;
		isPoisoned=true;
		cancelEffect.restart();
	}
}