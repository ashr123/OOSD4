package manage;

import tickables.Tickable;
import tickables.creeps.Creep;
import tickables.towers.Tower;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Represents the {@code Board}
 */
@SuppressWarnings({"ClassHasNoToStringMethod", "MagicNumber"})
public class Board extends JPanel
{
	private static final Image
			IMAGE_GRASS=new ImageIcon(Board.class.getResource("/media/environment/grass.png"))
					            .getImage(),
			IMAGE_PATH=new ImageIcon(Board.class.getResource("/media/environment/path.png"))
					           .getImage();
	private final Timer timer;
	private final int mapNum;
	
	/**
	 * Builds a new board
	 * @param mapNum the number of the map to be loaded
	 */
	Board(int mapNum)
	{
		setPreferredSize(new Dimension(800, 800));
		TowerWindow.reset();
		this.mapNum=mapNum;
		timer=new Timer(mapNum);
		addMouseListener(new MouseListener()
		{
			/**
			 * Builds a new frame to choose a tower from only if its between waves and the user didn't clicked on the path
			 */
			@Override
			public void mouseClicked(MouseEvent e)
			{
				for (Tickable t : getTimer().getTickables())
					if (t instanceof Tower && t.getLocation().equals(new Point(e.getX()*32/800,
					                                                           e.getY()*32/800)))
					{
						((Tower)t).setClicked(!((Tower)t).isClicked());
						repaint();
						return;
					}
				if (!Game.getGoButton().isEnabled())
					return;
				if (LevelLoader.get(Game.getMapNum())[e.getX()*32/800][e.getY()*32/800].equals(new Point()))
				{
					TowerWindow.displayFrame(e);
					repaint();
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e)
			{
			
			}
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
			
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
			
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
			
			}
		});
	}
	
	/**
	 * @return returns the board's timer
	 */
	public Timer getTimer()
	{
		return timer;
	}
	
	/**
	 * Draws an area of attack of a tower
	 * @param tower the tower to be painted around
	 * @param g the {@code Graphics} context in which to paint
	 */
	private static void markNeighbors(Tower tower, Graphics g)
	{
		g.setColor(tower.getRadiusColor());
		for (int i=-tower.getRadius(); i<=tower.getRadius(); i++)
			for (int j=-tower.getRadius(); j<=tower.getRadius(); j++)
				if (i!=0 || j!=0)
					g.fillRect(((int)tower.getLocation().getX()+i)*25,
					           ((int)tower.getLocation().getY()+j)*25, 25, 25);
	}
	
	@Override
	public void paint(Graphics g)
	{
		Game.getHPLBL().setText("HP: "+Game.getHP()+"    ");
		Game.getTimeLBL().setText("Time: "+getTimer().getTime()+"    ");
		Game.getWaveLBL().setText("Wave: "+getTimer().getWave()+"    ");
		for (int i=0; i<LevelLoader.get(Game.getMapNum()).length; i++)//Draws the land
			for (int j=0; j<LevelLoader.get(Game.getMapNum())[i].length; j++)
				if (LevelLoader.get(mapNum)[i][j].getX()==0 &&
				    LevelLoader.get(mapNum)[i][j].getY()==0)
					g.drawImage(IMAGE_GRASS, i*25, j*25, 25, 25, this);
				else
					g.drawImage(IMAGE_PATH, i*25, j*25, 25, 25, this);
		
		for (Tickable t : getTimer().getTickables())//Draws all the clicked towers affected area
			if (t instanceof Tower && ((Tower)t).isClicked())
				markNeighbors((Tower)t, g);
		
		if (TowerWindow.getE()!=null)//Draws the selected square
		{
			g.setColor(new Color(32, 32, 32));
			g.fillRect(TowerWindow.getE().getX()*32/800*25, TowerWindow.getE().getY()*32/800*25,
			           25, 25);
		}
		
		for (Tickable t : getTimer().getTickables())
		{
			if (t instanceof Creep && ((Creep)t).isInjured())//Marks square of injured creep
			{
				g.setColor(Color.red);
				g.fillRect((int)t.getLocation().getX()*25, (int)t.getLocation().getY()*25,
				           25, 25);
				((Creep)t).setInjured(false);
			}
//			if (t instanceof Creep)
//				g.drawImage(t.getImageIcon().getImage(), (int)t.getLocation().getX()*25,
//				            (int)t.getLocation().getY()*25, 25, 25, this);
//			else
			g.drawImage(t.getImageIcon().getImage(), (int)t.getLocation().getX()*25,
			            (int)t.getLocation().getY()*25-(t.getImageIcon().getIconHeight()*25/
			                                            t.getImageIcon().getIconWidth()-25), 25,
			            t.getImageIcon().getIconHeight()*25/t.getImageIcon().getIconWidth(),
			            this);//Draws all the tickables
		}
	}
}