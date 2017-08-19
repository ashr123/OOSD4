package tickables.towers;

import manage.Board;
import tickables.Tickable;
import tickables.creeps.Knight;
import tickables.creeps.Mike;
import tickables.creeps.Naji;
import tickables.creeps.Skully;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Point;

/**
 * Represents a {@code Dart}
 */
public class Dart extends Tower
{
	/**
	 * Stores this dart's image
	 */
	private static final ImageIcon IMAGE_ICON=
			new ImageIcon(Tickable.class.getResource("/media/towers/Dart.png"));
	/**
	 * The radius of the affected area
	 */
	private static final int RADIUS=2;
	/**
	 * The color of the affected area
	 */
	private static final Color RADIUS_COLOR=Color.decode("#42f46e");
	
	/**
	 * Creates a new dart
	 * @param location the location of this dart
	 * @see Dart#location
	 */
	public Dart(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappened()
	{
		if ((!Board.getTimer().isFastForward() && Board.getTimer().getTicks()%2==0)/*Every half a second*/ ||
		    (Board.getTimer().isFastForward())/*Every quarter of a second*/)
			hitCreep(RADIUS, false);
	}
	
	@Override
	public void visit(Knight knight)
	{
		knight.setInjured(true);
	}
	
	@Override
	public void visit(Naji naji)
	{
		naji.setHP(naji.getHP()-(naji.isPoisoned() ? 30*Naji.getPoisonDegree() : 30));
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
		mike.setHP(mike.getHP()-30);
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
}