package Towers;

import Creeps.Knight;
import Creeps.Mike;
import Creeps.Naji;
import Creeps.Skully;
import Manage.Board;
import Manage.Tickable;

import javax.swing.*;
import java.awt.*;

public class Poison extends Tower
{
	private static final ImageIcon IMAGE_ICON=
			new ImageIcon(Tickable.class.getResource("../Media/towers/Poison.png"));
	
	public Poison(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappend()
	{
		if ((!Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%4==0)/*Every second*/ ||
		    (Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%2==0)/*Every half a second*/)
			hitCreep(1, false);
	}
	
	@Override
	public void visit(Knight knight)
	{
		knight.setPoisoned();
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