import javax.swing.*;
import java.awt.*;

class Dart extends Tower
{
	private static final ImageIcon IMAGE_ICON=new ImageIcon(
			Tickable.class.getResource("Media/towers/Dart.png"));
	Dart(Point location)
	{
		super(location);
	}
	
	@Override
	public void tickHappend()
	{
		if ((!Board.getTimer().isFastFoword() && Board.getTimer().getTicks()%2==0)/*Every half a second*/ ||
		    (Board.getTimer().isFastFoword())/*Every quarter of a second*/)
			hitCreep(2, false);
	}
	
	@Override
	public void visit(Knight knight)
	{
		knight.setInjured(true);
	}
	
	@Override
	void visit(Naji naji)
	{
		naji.setHP((int)(naji.getHP()-(naji.isPoisoned() ? 30*Naji.getPoisonDegree() : 30)));
		naji.setInjured(true);
	}
	
	@Override
	void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-15);
		skully.setInjured(true);
	}
	
	@Override
	void visit(Mike mike)
	{
		mike.setHP(mike.getHP()-30);
		mike.setInjured(true);
	}
	
	@Override
	public ImageIcon getImageIcon()
	{
		return IMAGE_ICON;
	}
}