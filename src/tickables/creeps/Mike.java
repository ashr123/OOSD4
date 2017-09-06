package tickables.creeps;

import manage.Game;
import tickables.towers.Tower;

import javax.swing.ImageIcon;
import java.awt.Point;

/**
 * Represents a {@code Mike}
 */
public class Mike extends Creep
{
	/**
	 * Stores this mike's images
	 */
	private static final ImageIcon[]
			IMAGE_ICON={new ImageIcon(Mike.class.getResource("/media/creeps/mike-1.png")),
			            new ImageIcon(Mike.class.getResource("/media/creeps/mike-2.png"))};
	private static final int SLOWDOWN_DURATION=3;
	
	/**
	 * Creates a new mike
	 * @param location the location of this knight
	 * @see Mike#location
	 */
	public Mike(Point location)
	{
		super(location, SLOWDOWN_DURATION);
	}
	
	@Override
	public void tickHappened()
	{
		if ((!Game.getBoard().getTimer().isFastForward() &&
		     Game.getBoard().getTimer().getTicks()%(4*getSlowdownFactor())==0)/*Every second*/ ||
		    (Game.getBoard().getTimer().isFastForward() &&
		     Game.getBoard().getTimer().getTicks()%(2*getSlowdownFactor())==0)/*Every half a second*/)
			moveCreep();
	}
	
	@Override
	public void impact(Tower tower)
	{
		tower.visit(this);
	}
	
	@Override
	public ImageIcon getImageIcon()
	{
		return IMAGE_ICON[getPicTick()%IMAGE_ICON.length];
	}
}