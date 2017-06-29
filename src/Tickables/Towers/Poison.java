package Tickables.Towers;

import Tickables.Creeps.Knight;
import Tickables.Creeps.Mike;
import Tickables.Creeps.Naji;
import Tickables.Creeps.Skully;
import Manage.Board;
import Tickables.Tickable;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a {@code Poison}
 */
public class Poison extends Tower
{
	/**
	 * Stores this poison's image
	 */
	private static final ImageIcon IMAGE_ICON=
			new ImageIcon(Tickable.class.getResource("/Media/towers/Poison.png"));
	
	/**
	 * Creates a new poison
	 * @param location the location of this poison
	 * @see Poison#location
	 */
	public Poison(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappened()
	{
		if ((!Board.getTimer().isFastForward() && Board.getTimer().getTicks()%4==0)/*Every second*/ ||
		    (Board.getTimer().isFastForward() && Board.getTimer().getTicks()%2==0)/*Every half a second*/)
			hitCreep(1, false);
	}
	
	@Override
	public void visit(Knight knight)
	{
		knight.setPoisoned();
		knight.setInjured(true);
	}
	
	@Override
	public void visit(Naji naji)
	{
		naji.setPoisoned();
		naji.setInjured(true);
	}
	
	@Override
	public void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-20);
		skully.setInjured(true);
	}
	
	@Override
	public void visit(Mike mike)
	{
		mike.setInjured(true);
	}
	
	@Override
	public ImageIcon getImageIcon()
	{
		return IMAGE_ICON;
	}
}