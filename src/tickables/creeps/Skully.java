package tickables.creeps;

import manage.Game;
import tickables.towers.Tower;

import javax.swing.ImageIcon;
import java.awt.Point;

/**
 * Represents a {@code Skully}
 */
public class Skully extends Creep
{
	/**
	 * Stores this skully's images
	 */
	private static final ImageIcon[]
			IMAGE_ICON={new ImageIcon(Skully.class.getResource("/media/creeps/guli-1.png")),
			            new ImageIcon(Skully.class.getResource("/media/creeps/guli-2.png"))};
	private int picTick;
	private static final int SLOWDOWN_DURATION=3;
	
	/**
	 * Creates a new skully
	 * @param location the location of this knight
	 * @see Skully#location
	 */
	public Skully(Point location)
	{
		super(location, SLOWDOWN_DURATION);
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
}