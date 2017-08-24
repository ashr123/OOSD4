package tickables.towers;

import manage.Board;
import tickables.creeps.Knight;
import tickables.creeps.Mike;
import tickables.creeps.Naji;
import tickables.creeps.Skully;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Point;

/**
 * Represents a {@code Sam}
 */
public class Sam extends Tower
{
	/**
	 * Stores this Sam's image
	 */
	private static final ImageIcon IMAGE_ICON=
			new ImageIcon(Sam.class.getResource("/media/towers/Sam.png"));
	/**
	 * The radius of the affected area
	 */
	private static final int RADIUS=2;
	/**
	 * The color of the affected area
	 */
	private static final Color RADIUS_COLOR=Color.decode("#42f46e");
	
	/**
	 * Creates a new sam
	 * @param location the location of this sam
	 * @see Sam#location
	 */
	public Sam(Point location)
	{
		super(location);
	}
	
	@Override
	public void visit(Knight knight)
	{
		knight.slowdown();
		knight.setInjured(true);
	}
	
	@Override
	public void visit(Naji naji)
	{
		naji.slowdown();
		naji.setInjured(true);
	}
	
	@Override
	public void visit(Skully skully)
	{
		skully.slowdown();
		skully.setInjured(true);
	}
	
	@Override
	public void visit(Mike mike)
	{
		mike.slowdown();
		mike.setHP(mike.getHP()-10);
		mike.setInjured(true);
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
		if ((!Board.getTimer().isFastForward() && Board.getTimer().getTicks()%2==0)/*Every half a second*/ ||
		    (Board.getTimer().isFastForward())/*Every quarter of a second*/)
			hitCreep(RADIUS, true);
	}
}