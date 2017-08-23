package tickables.creeps;

import manage.Board;
import tickables.towers.Tower;

import javax.swing.ImageIcon;
import java.awt.Point;

/**
 * Represents a {@code Mike}
 */
public class Mike extends Creep
{
	/**
	 * Stores this mike's image
	 */
	private static final ImageIcon[]
			IMAGE_ICON={new ImageIcon(Mike.class.getResource("/media/creeps/mike-1.png")),
			            new ImageIcon(Mike.class.getResource("/media/creeps/mike-2.png"))};
	private int picTick;
	
	/**
	 * Creates a new mike
	 * @param location the location of this knight
	 * @see Mike#location
	 */
	public Mike(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappened()
	{
		if ((!Board.getTimer().isFastForward() && Board.getTimer().getTicks()%4==0)/*Every second*/ ||
		    (Board.getTimer().isFastForward() && Board.getTimer().getTicks()%2==0)/*Every half a second*/)
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
		return IMAGE_ICON[picTick%2==0 ? 0 : 1];
	}
}