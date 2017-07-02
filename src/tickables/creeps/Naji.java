package tickables.creeps;

import manage.Board;
import tickables.Tickable;
import tickables.towers.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a {@code Naji}
 */
public class Naji extends Creep
{
	/**
	 * Stores this naji's image
	 */
	private static final ImageIcon IMAGE_ICON=
			new ImageIcon(Tickable.class.getResource("/media/creeps/naji-1.png"));
	/**
	 * States the severity of poisoning
	 */
	private static final double POISON_DEGREE=1.5;
	/**
	 * States if this naji has been poisoned
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
			if ((!Board.getTimer().isFastForward() && ticks%2==0)/*Every 5 seconds*/ ||
			    (Board.getTimer().isFastForward())/*Every 2.5 seconds*/)
				isPoisoned=false;
			ticks++;
			if (isPoisoned())
				cancelEffect.restart();
		}
	});
	
	/**
	 * Creates a new Naji
	 * @param location the location of this knight
	 * @see Naji#location
	 */
	public Naji(Point location)
	{
		super(location);
	}
	
	/**
	 * @return returns the {@link Naji#POISON_DEGREE} of this naji
	 */
	public static double getPoisonDegree()
	{
		return POISON_DEGREE;
	}
	
	@Override
	public void tickHappened()
	{
		if ((!Board.getTimer().isFastForward() && Board.getTimer().getTicks()%2==0)/*Moves every half a second*/ ||
		    (Board.getTimer().isFastForward())/*Moves every quarter of a second*/)
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
	
	/**
	 * @return {@code true} if this naji is poisoned, {@code false} otherwise
	 */
	public boolean isPoisoned()
	{
		return isPoisoned;
	}
	
	/**
	 * Changes this naji's poisoned state
	 */
	public void setPoisoned()
	{
		ticks=1;
		isPoisoned=true;
		cancelEffect.setRepeats(false);
		cancelEffect.restart();
	}
}