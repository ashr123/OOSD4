import javax.swing.*;
import java.awt.*;

class Magician extends Tower
{
	private static final ImageIcon IMAGE_ICON=new ImageIcon(
			Tickable.class.getResource("Media/towers/Magician.png"));
	Magician(Point location)
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
	void visit(Knight knight)
	{
		knight.setHP(knight.getHP()-(knight.isPoisoned() ? 30*Knight.getPoisonDegree() : 30));
		knight.setInjured(true);
	}
	
	@Override
	void visit(Naji naji)
	{
		naji.setHP(naji.getHP()-(naji.isPoisoned() ? 10*Naji.getPoisonDegree() : 10));
		naji.setInjured(true);
	}
	
	@Override
	void visit(Skully skully)
	{
		skully.setHP(skully.getHP()-25);
		skully.setInjured(true);
	}
	
	@Override
	void visit(Mike mike)
	{
		mike.setHP(mike.getHP()-10);
		mike.setInjured(true);
	}
	
	@Override
	public ImageIcon getImageIcon()
	{
		return IMAGE_ICON;
	}
}