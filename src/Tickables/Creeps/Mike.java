package Creeps;

import Manage.Board;
import Manage.Tickable;
import Towers.Tower;

import javax.swing.*;
import java.awt.*;

public class Mike extends Creep
{
	private static final ImageIcon IMAGE_ICON=
			new ImageIcon(Tickable.class.getResource("../Media/creeps/mike-1.png"));
	
	public Mike(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappend()
	{
		if ((!Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%4==0)/*Every second*/ ||
		    (Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%2==0)/*Every half a second*/)
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