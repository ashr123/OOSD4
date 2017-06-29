package Manage;

import Tickables.Creeps.Creep;
import Tickables.Tickable;
import Tickables.Towers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Represents the {@code Board}
 */
public class Board extends JPanel
{
	private static final ImageIcon IMAGE_ICON_TOWER_LAVA=new ImageIcon(new ImageIcon(Board.class.getResource(
			"/Media/towers/Lava.png")).getImage().getScaledInstance(100, 162,
	                                                                  Image.SCALE_SMOOTH));
	private static final ImageIcon IMAGE_ICON_TOWER_DART=new ImageIcon(new ImageIcon(Board.class.getResource(
			"/Media/towers/Dart.png")).getImage().getScaledInstance(100, 135,
	                                                                  Image.SCALE_SMOOTH));
	private static final ImageIcon IMAGE_ICON_TOWER_POISON=new ImageIcon(new ImageIcon(Board.class.getResource(
			"/Media/towers/Poison.png")).getImage().getScaledInstance(100, 131,
	                                                                    Image.SCALE_SMOOTH));
	private static final ImageIcon IMAGE_ICON_TOWER_MAGICIAN=new ImageIcon(new ImageIcon(Board.class.getResource("/Media/towers/Magician.png")).getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH));
	private static final ImageIcon IMAGE_ICON_GRASS=
			new ImageIcon(Board.class.getResource("/Media/environment/grass.png"));
	private static final ImageIcon IMAGE_ICON_PATH=
			new ImageIcon(Board.class.getResource("/Media/environment/path.png"));
	private static int mapNum;
	private static Timer timer;
	private final Point[][] boardPath;
	private int numOfDart;
	private int numOfLava;
	private int numOfPoison;
	private int numOfMagician;
	
	/**
	 * Builds a new board
	 * @param mapNum the number of the map to be loaded
	 */
	public Board(int mapNum)
	{
		setPreferredSize(new Dimension(800, 800));
		Board.mapNum=mapNum;
		timer=new Timer(this);
		setBorder(BorderFactory.createLineBorder(Color.black));
		boardPath=Game.getLoader().get(mapNum);
		numOfDart=numOfLava=numOfPoison=numOfMagician=3;
		
		addMouseListener(new MouseListener()
		{
			/**
			 * Builds a new frame to choose a tower from only if its between waves andthe user didn't clicked on the path
			 */
			@Override
			public void mouseClicked(final MouseEvent e)
			{
				for (Tickable t : timer.getTickables())
					if (t instanceof Tower && t.getLocation().equals(new Point(e.getX()*32/800,
					                                                           e.getY()*32/800)))
					{
						((Tower)t).setClicked(!((Tower)t).isClicked());
						repaint();
						return;
					}
				if (timer.isRunning())
					return;
				if (boardPath[e.getX()*32/800][e.getY()*32/800].equals(new Point()))
				{
					Graphics graphics=getGraphics();
					graphics.setColor(Color.decode("#132044"));
					graphics.fillRect(e.getX()*32/800*25, e.getY()*32/800*25, 25, 25);
					
					final JFrame towerWindow=new JFrame("Choose a Tower:");
					towerWindow.setLayout(new GridLayout(2, 2));
					JButton dartButton=new JButton(IMAGE_ICON_TOWER_DART);
					dartButton.setText(numOfDart+"");
					JButton poisonButton=new JButton(IMAGE_ICON_TOWER_POISON);
					poisonButton.setText(numOfPoison+"");
					JButton lavaButton=new JButton(IMAGE_ICON_TOWER_LAVA);
					lavaButton.setText(numOfLava+"");
					JButton magicianButton=new JButton(IMAGE_ICON_TOWER_MAGICIAN);
					magicianButton.setText(numOfMagician+"");
					dartButton.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(final ActionEvent e1)
						{
							if (numOfDart>0)
							{
								timer.register(new Dart(new Point(e.getX()*32/800, e.getY()*32/800)));
								numOfDart--;
								repaint();
								towerWindow.dispose();
							}
						}
					});
					poisonButton.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(final ActionEvent e1)
						{
							if (numOfPoison>0)
							{
								timer.register(new Poison(new Point(e.getX()*32/800, e.getY()*32/800)));
								numOfPoison--;
								repaint();
								towerWindow.dispose();
							}
						}
					});
					lavaButton.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(final ActionEvent e1)
						{
							if (numOfLava>0)
							{
								timer.register(new Lava(new Point(e.getX()*32/800, e.getY()*32/800)));
								numOfLava--;
								repaint();
								towerWindow.dispose();
							}
						}
					});
					magicianButton.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(final ActionEvent e1)
						{
							if (numOfMagician>0)
							{
								timer.register(new Magician(new Point(e.getX()*32/800, e.getY()*32/800)));
								numOfMagician--;
								repaint();
								towerWindow.dispose();
							}
						}
					});
					
					towerWindow.add(dartButton);
					towerWindow.add(poisonButton);
					towerWindow.add(lavaButton);
					towerWindow.add(magicianButton);
					towerWindow.setVisible(true);
					towerWindow.setSize(150, 150);
					towerWindow.setResizable(false);
					towerWindow.pack();
					
					towerWindow.addWindowListener(new WindowAdapter()
					{
						public void windowClosing(WindowEvent e)
						{
							repaint();
						}
					});
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
	 * Draws a big range of attack of a tower
	 * @param xPosition the {@code x} coordinate of the tower
	 * @param yPosition the {@code y} coordinate of the tower
	 * @param g the {@code Graphics} context in which to paint
	 */
	private void markNeighborsBig(int xPosition, int yPosition, Graphics g)
	{
		g.setColor(Color.decode("#42f46e"));
		g.fillRect(((xPosition*32/800)+1)*25, yPosition*32/800*25, 25, 25);
		g.fillRect(((xPosition*32/800)-1)*25, yPosition*32/800*25, 25, 25);
		g.fillRect(xPosition*32/800*25, ((yPosition*32/800)+1)*25, 25, 25);
		g.fillRect(xPosition*32/800*25, ((yPosition*32/800)-1)*25, 25, 25);
		g.fillRect(((xPosition*32/800)+1)*25, ((yPosition*32/800)+1)*25, 25, 25);
		g.fillRect(((xPosition*32/800)-1)*25, ((yPosition*32/800)-1)*25, 25, 25);
		g.fillRect(((xPosition*32/800)+1)*25, ((yPosition*32/800)-1)*25, 25, 25);
		g.fillRect(((xPosition*32/800)-1)*25, ((yPosition*32/800)+1)*25, 25, 25);
		g.fillRect(((xPosition*32/800)+2)*25, yPosition*32/800*25, 25, 25);
		g.fillRect(((xPosition*32/800)-2)*25, yPosition*32/800*25, 25, 25);
		g.fillRect(xPosition*32/800*25, ((yPosition*32/800)+2)*25, 25, 25);
		g.fillRect(xPosition*32/800*25, ((yPosition*32/800)-2)*25, 25, 25);
		g.fillRect(((xPosition*32/800)+2)*25, ((yPosition*32/800)+1)*25, 25, 25);
		g.fillRect(((xPosition*32/800)-2)*25, ((yPosition*32/800)+1)*25, 25, 25);
		g.fillRect(((xPosition*32/800)+2)*25, ((yPosition*32/800)-1)*25, 25, 25);
		g.fillRect(((xPosition*32/800)-2)*25, ((yPosition*32/800)+1)*25, 25, 25);
		g.fillRect(((xPosition*32/800)+1)*25, ((yPosition*32/800)+2)*25, 25, 25);
		g.fillRect(((xPosition*32/800)-1)*25, ((yPosition*32/800)-2)*25, 25, 25);
		g.fillRect(((xPosition*32/800)+1)*25, ((yPosition*32/800)-2)*25, 25, 25);
		g.fillRect(((xPosition*32/800)-1)*25, ((yPosition*32/800)+2)*25, 25, 25);
		g.fillRect(((xPosition*32/800)+2)*25, ((yPosition*32/800)+2)*25, 25, 25);
		g.fillRect(((xPosition*32/800)-2)*25, ((yPosition*32/800)+2)*25, 25, 25);
		g.fillRect(((xPosition*32/800)+2)*25, ((yPosition*32/800)-2)*25, 25, 25);
		g.fillRect(((xPosition*32/800)-2)*25, ((yPosition*32/800)+2)*25, 25, 25);
		g.fillRect(((xPosition*32/800)-2)*25, ((yPosition*32/800)-2)*25, 25, 25);
		g.fillRect(((xPosition*32/800)-2)*25, ((yPosition*32/800)-1)*25, 25, 25);
	}
	
	@Override
	public void paint(Graphics g)
	{
		Game.getHPLBL().setText("HP: "+Game.getHP()+"    ");
		Game.getTimeLBL().setText("Time: "+timer.getTime()+"    ");
		Game.getWaveLBL().setText("Wave: "+timer.getWave()+"    ");
		if (!timer.isRunning())
			Game.getGoButton().setEnabled(true);
		for (int i=0; i<boardPath.length; i++)
			for (int j=0; j<boardPath[i].length; j++)
				if (boardPath[i][j].getX()==0 && boardPath[i][j].getY()==0)
					g.drawImage(IMAGE_ICON_GRASS.getImage(), i*25, j*25, 25, 25,
					            this);
				else
					g.drawImage(IMAGE_ICON_PATH.getImage(), i*25, j*25, 25, 25, this);
		
		for (Tickable t : timer.getTickables())//Draws all Tickables
		{
			if (t instanceof Tower)
				if (t instanceof Dart)
				{
					if (((Dart)t).isClicked())
						markNeighborsBig((int)t.getLocation().getX()*25,
						                 (int)t.getLocation().getY()*25, g);
				}
				else
					if (((Tower)t).isClicked())
						markNeighbors((int)t.getLocation().getX()*25,
						              (int)t.getLocation().getY()*25, g);
		}
		for (Tickable t : timer.getTickables())
		{
			if (t instanceof Creep && ((Creep)t).isInjured())//Marks square of injured creep
			{
				g.setColor(Color.decode("#77252d"));
				g.fillRect((int)t.getLocation().getX()*25, (int)t.getLocation().getY()*25,
				           25, 25);
				((Creep)t).setInjured(false);
			}
			g.drawImage(t.getImageIcon().getImage(), (int)t.getLocation().getX()*25,
			            (int)t.getLocation().getY()*25, 25, 25, this);
		}
	}
	
	/**
	 * Draws a regular range of attack of a tower
	 * @param xPosition the {@code x} coordinate of the tower
	 * @param yPosition the {@code y} coordinate of the tower
	 * @param g the {@code Graphics} context in which to paint
	 */
	private void markNeighbors(int xPosition, int yPosition, Graphics g)
	{
		g.setColor(Color.decode("#d3d9ed"));
		g.fillRect(((xPosition*32/800)+1)*25, yPosition*32/800*25, 25, 25);
		g.fillRect(((xPosition*32/800)-1)*25, yPosition*32/800*25, 25, 25);
		g.fillRect(xPosition*32/800*25, ((yPosition*32/800)+1)*25, 25, 25);
		g.fillRect(xPosition*32/800*25, ((yPosition*32/800)-1)*25, 25, 25);
		g.fillRect(((xPosition*32/800)+1)*25, ((yPosition*32/800)+1)*25, 25, 25);
		g.fillRect(((xPosition*32/800)-1)*25, ((yPosition*32/800)-1)*25, 25, 25);
		g.fillRect(((xPosition*32/800)+1)*25, ((yPosition*32/800)-1)*25, 25, 25);
		g.fillRect(((xPosition*32/800)-1)*25, ((yPosition*32/800)+1)*25, 25, 25);
	}
}