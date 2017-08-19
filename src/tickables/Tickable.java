package tickables;

import javax.swing.ImageIcon;
import java.awt.Point;

/**
 * Represents an object that can response to timer's listener
 */
public interface Tickable
{
	/**
	 * @return this tickable's location
	 */
	Point getLocation();
	
	/**
	 * @return this tickable's image
	 */
	ImageIcon getImageIcon();
	
	/**
	 * Performs an action
	 */
	void tickHappened();
}
