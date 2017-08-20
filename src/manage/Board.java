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
	private static int mapNum;
	private static Timer timer;
	
	/**
	 * Builds a new board
	 * @param mapNum the number of the map to be loaded
	 */
	public Board(int mapNum)
	{
		setPreferredSize(new Dimension(800, 800));
		TowerWindow.reset(this);
		Board.mapNum=mapNum;
		timer=new Timer(this);
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
				if (getTimer().isRunning())
					return;
				if (LevelLoader.get(mapNum)[e.getX()*32/800][e.getY()*32/800].equals(new Point()))
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
	 * @return the number of current map
	 */
	public static int getMapNum()
	{
		return mapNum;
	}
	
	/**
	 * @return returns the board's timer
	 */
	public static Timer getTimer()
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
		if (!getTimer().isRunning())
			Game.getGoButton().setEnabled(true);
		for (int i=0; i<LevelLoader.get(getMapNum()).length; i++)//Draws the land
			for (int j=0; j<LevelLoader.get(getMapNum())[i].length; j++)
				if (TowerWindow.getE()!=null &&
				    TowerWindow.getE().getY()*32/800==j &&
				    TowerWindow.getE().getX()*32/800==i)
				{
					g.setColor(new Color(32, 32, 32));
					g.fillRect(i*25, j*25, 25, 25);
				}
				else
					if (LevelLoader.get(getMapNum())[i][j].getX()==0 &&
					    LevelLoader.get(getMapNum())[i][j].getY()==0)
						g.drawImage(IMAGE_GRASS, i*25, j*25, 25, 25, this);
					else
						g.drawImage(IMAGE_PATH, i*25, j*25, 25, 25, this);
		
		for (Tickable t : getTimer().getTickables())//Draws all the clicked towers affected area
			if (t instanceof Tower && ((Tower)t).isClicked())
				markNeighbors((Tower)t, g);
		
		for (Tickable t : getTimer().getTickables())
		{
			if (t instanceof Creep && ((Creep)t).isInjured())//Marks square of injured creep
			{
				g.setColor(Color.red);
				g.fillRect((int)t.getLocation().getX()*25, (int)t.getLocation().getY()*25,
				           25, 25);
				((Creep)t).setInjured(false);
			}
			g.drawImage(t.getImageIcon().getImage(), (int)t.getLocation().getX()*25,
			            (int)t.getLocation().getY()*25, 25, 25, this);
		}
	}
}