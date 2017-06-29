package Tickables.Creeps;

import Manage.Board;
import Tickables.Tickable;
import Tickables.Towers.Tower;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a {@code Skully}
 */
public class Skully extends Creep
{
	/**
	 * Stores this skully's image
	 */
	private static final ImageIcon IMAGE_ICON=
			new ImageIcon(Tickable.class.getResource("/Media/creeps/guli-1.png"));
	
	/**
	 * Creates a new skully
	 * @param location the location of this knight
	 * @see Skully#location
	 */
	public Skully(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappened()
	{
		if ((!Board.getTimer().isFastForward() && Board.getTimer().getTicks()%4==0)/*Moves every second*/ ||
		    (Board.getTimer().isFastForward() && Board.getTimer().getTicks()%2==0)/*Moves every half a second*/)
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
		return IMAGE_ICON;
	}
}