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
 * Represents a {@code Lava}
 */
public class Lava extends Tower
{
	/**
	 * Stores this lava's image
	 */
	private static final ImageIcon IMAGE_ICON=
			new ImageIcon(Tickable.class.getResource("/Media/towers/Lava.png"));
	
	/**
	 * Creates a new lava
	 * @param location the location of this lava
	 * @see Lava#location
	 */
	public Lava(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappened()
	{
		if ((!Board.getTimer().isFastForward() && Board.getTimer().getTicks()%4==0)/*Every second*/ ||
		    (Board.getTimer().isFastForward() && Board.getTimer().getTicks()%2==0)/*Every half a second*/)
			hitCreep(1, true);
	}
	
	@Override
	public void visit(Knight knight)
	{
		knight.setHP(knight.getHP()-(knight.isPoisoned() ? 10*Knight.getPoisonDegree() : 10));
		knight.setInjured(true);
	}
	
	@Override
	public void visit(Naji naji)
	{
		naji.setHP(naji.getHP()-(naji.isPoisoned() ? 15*Naji.getPoisonDegree() : 15));
		naji.setInjured(true);
	}
	
	@Override
	public void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-15);
		skully.setInjured(true);
	}
	
	@Override
	public void visit(Mike mike)
	{
		mike.setHP(mike.getHP()-15);
		mike.setInjured(true);
	}
	
	@Override
	public ImageIcon getImageIcon()
	{
		return IMAGE_ICON;
	}
}