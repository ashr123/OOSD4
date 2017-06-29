package Manage;

import javax.swing.*;
import java.awt.*;

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
	void tickHappend();
}
