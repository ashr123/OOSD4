package Creeps;

import Manage.Board;
import Manage.Tickable;
import Towers.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a {@code Knight}
 */
public class Knight extends Creep
{
	/**
	 * Stores this knight's image
	 */
	private static final ImageIcon IMAGE_ICON=
			new ImageIcon(Tickable.class.getResource("../Media/creeps/abir-1.png"));
	/**
	 * States the severity of poisoning
	 */
	private static final double POISON_DEGREE=2;
	/**
	 * States if this knight has been poisoned
	 */
	private boolean isPoisoned;
	private int ticks=1;
	/**
	 * Responsible of canceling the poison's effect
	 */
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
	
	/**
	 *
	 * @param location
	 */
	public Knight(Point location)
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
		if ((!Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%4==0)/*Moves every second*/ ||
		    (Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%2==0)/*Moves every half a second*/)
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