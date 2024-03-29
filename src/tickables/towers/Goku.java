package tickables.towers;

import manage.Game;
import tickables.creeps.Knight;
import tickables.creeps.Mike;
import tickables.creeps.Naji;
import tickables.creeps.Skully;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Point;

/**
 * Represents a {@code Goku}
 */
public class Goku extends Tower
{
	/**
	 * Stores this Goku's image
	 */
	private static final ImageIcon IMAGE_ICON=
			new ImageIcon(Goku.class.getResource("/media/towers/Goku.png"));
	/**
	 * The radius of the affected area
	 */
	private static final int RADIUS=2;
	/**
	 * The color of the affected area
	 */
	private static final Color RADIUS_COLOR=Color.decode("#42f46e");
	/**
	 * Counts the number of hits this tower did
	 */
	private int visitsCounter;
	/**
	 * Represent the "hit" or the "strength" parameter of this tower
	 */
	private int H=1;
	
	/**
	 * Creates a new goku
	 * @param location the location of this tower
	 * @see Goku#location
	 */
	public Goku(Point location)
	{
		super(location);
	}
	
	@Override
	public void visit(Knight knight)
	{
		knight.setHP(knight.getHP()-(knight.isPoisoned() ? 7*H*Knight.getPoisonDegree() : 7*H));
		knight.setInjured(true);
		increaseHIfNeeded();
	}
	
	@Override
	public void visit(Naji naji)
	{
		naji.setHP(naji.getHP()-(naji.isPoisoned() ? 5*H*Naji.getPoisonDegree() : 5*H));
		naji.setInjured(true);
		increaseHIfNeeded();
	}
	
	@Override
	public void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-10*H);
		skully.setInjured(true);
		increaseHIfNeeded();
	}
	
	@Override
	public void visit(Mike mike)
	{
		mike.setHP(mike.getHP()-5*H);
		mike.setInjured(true);
		increaseHIfNeeded();
	}
	
	@Override
	public int getRadius()
	{
		return RADIUS;
	}
	
	@Override
	public Color getRadiusColor()
	{
		return RADIUS_COLOR;
	}
	
	@Override
	public ImageIcon getImageIcon()
	{
		return IMAGE_ICON;
	}
	
	@Override
	public void tickHappened()
	{
		if ((!Game.getBoard().getTimer().isFastForward() &&
		     Game.getBoard().getTimer().getTicks()%4==0)/*Every second*/ ||
		    (Game.getBoard().getTimer().isFastForward() &&
		     Game.getBoard().getTimer().getTicks()%2==0)/*Every half a second*/)
			hitCreep(RADIUS, false);
	}
	
	/**
	 * Increase {@link Goku#visitsCounter} by 1 and after every 10 hits multiplies {@link Goku#H} by 2
	 */
	private void increaseHIfNeeded()
	{
		if (++visitsCounter%10==0)
			H*=2;
	}
}