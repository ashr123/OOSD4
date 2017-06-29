package Creeps;

import Manage.Board;
import Manage.Tickable;
import Towers.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Naji extends Creep
{
	private static final ImageIcon IMAGE_ICON=
			new ImageIcon(Tickable.class.getResource("../Media/creeps/naji-1.png"));
	private static final double POISON_DEGREE=1.5;
	private boolean isPoisoned;
	private int ticks=1;
	private final javax.swing.Timer cancelEffect=new javax.swing.Timer(2500, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if ((!Board.getTimer().isFastFoword() && ticks%2==0)/*Every 5 seconds*/ ||
			    (Board.getTimer().isFastFoword())/*Every 2.5 seconds*/)
				isPoisoned=false;
			ticks++;
			if (isPoisoned())
				cancelEffect.restart();
		}
	});
	
	public Naji(Point location)
	{
		super(location);
	}
	
	public static double getPoisonDegree()
	{
		return POISON_DEGREE;
	}
	
	@Override
	public void tickHappend()
	{
		if ((!Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%2==0)/*Moves every half a second*/ ||
		    (Board.getTimer().isFastFoword())/*Moves every quarter of a second*/)
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
	
	public boolean isPoisoned()
	{
		return isPoisoned;
	}
	
	public void setPoisoned()
	{
		ticks=1;
		isPoisoned=true;
		cancelEffect.setRepeats(false);
		cancelEffect.restart();
	}
}