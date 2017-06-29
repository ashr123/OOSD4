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
 * Represents a {@code Magician}
 */
public class Magician extends Tower
{
	/**
	 * Stores this magician's image
	 */
	private static final ImageIcon IMAGE_ICON=
			new ImageIcon(Tickable.class.getResource("/Media/towers/Magician.png"));
	
	/**
	 * Creates a new magician
	 * @param location the location of this magician
	 * @see Magician#location
	 */
	public Magician(Point location)
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
		knight.setHP(knight.getHP()-(knight.isPoisoned() ? 30*Knight.getPoisonDegree() : 30));
		knight.setInjured(true);
	}
	
	@Override
	public void visit(Naji naji)
	{
		naji.setHP(naji.getHP()-(naji.isPoisoned() ? 10*Naji.getPoisonDegree() : 10));
		naji.setInjured(true);
	}
	
	@Override
	public void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-25);
		skully.setInjured(true);
	}
	
	@Override
	public void visit(Mike mike)
	{
		mike.setHP(mike.getHP()-10);
		mike.setInjured(true);
	}
	
	@Override
	public ImageIcon getImageIcon()
	{
		return IMAGE_ICON;
	}
}