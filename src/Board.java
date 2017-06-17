import sun.awt.WindowClosingListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Board extends JPanel
{
	private final Point[][] boardPath;
	private final JLabel[][] boardLabels;
	private static int level;
	private int numOfDart;
	private int numOfLava;
	private int numOfPoison;
	private int numOfMagician;
	private int xPosition;
	private int yPosition;
	private static Timer timer;
	
	/*
	Image IMAGE_TOWER_LAVA = Toolkit.getDefaultToolkit().getImage("Media/towers/Lava.png");
	Image IMAGE_TOWER_DART = Toolkit.getDefaultToolkit().getImage("Media/towers/Dart.png");
	Image IMAGE_TOWER_POISON = Toolkit.getDefaultToolkit().getImage("Media/towers/Poison.png");
	Image IMAGE_TOWER_MAGICIAN = Toolkit.getDefaultToolkit().getImage("Media/towers/Magician.png");
	Image IMAGE_CREEP_KNIGHT = Toolkit.getDefaultToolkit().getImage("Media/creeps/abir-1.png");
	Image IMAGE_CREEP_MIKE = Toolkit.getDefaultToolkit().getImage("Media/creeps/mike-1.png");
	Image IMAGE_CREEP_NAJI = Toolkit.getDefaultToolkit().getImage("Media/creeps/naji-1.png");
	Image IMAGE_CREEP_SKULLY = Toolkit.getDefaultToolkit().getImage("Media/creeps/guli-1.png");
	Image IMAGE_GRASS = Toolkit.getDefaultToolkit().getImage("Media/environment/grass.png");
	Image IMAGE_PATH = Toolkit.getDefaultToolkit().getImage("Media/environment/path.png");
	*/
	
	private static final ImageIcon IMAGE_ICON_TOWER_LAVA=
			new ImageIcon(new ImageIcon(Board.class.getResource("Media/towers/Lava.png")).getImage()
					              .getScaledInstance(100,162,Image.SCALE_SMOOTH));
	private static final ImageIcon IMAGE_ICON_TOWER_DART=
			new ImageIcon(new ImageIcon(Board.class.getResource("Media/towers/Dart.png")).getImage()
					.getScaledInstance(100,135,Image.SCALE_SMOOTH));
	private static final ImageIcon IMAGE_ICON_TOWER_POISON=
			new ImageIcon(new ImageIcon(Board.class.getResource("Media/towers/Poison.png")).getImage()
					.getScaledInstance(100,131,Image.SCALE_SMOOTH));
	private static final ImageIcon IMAGE_ICON_TOWER_MAGICIAN=
			new ImageIcon(new ImageIcon(Board.class.getResource("Media/towers/Magician.png")).getImage()
					.getScaledInstance(100,145,Image.SCALE_SMOOTH));
	private static final ImageIcon IMAGE_ICON_GRASS=
			new ImageIcon(Board.class.getResource("Media/environment/grass.png"));
	private static final ImageIcon IMAGE_ICON_PATH=
			new ImageIcon(Board.class.getResource("Media/environment/path.png"));
	
	
	public Board(int level)
	{
		timer = new Timer();
		setBorder(BorderFactory.createLineBorder(Color.black));
		boardPath = Game.getLoader().get(level);
		boardLabels = new JLabel[32][32];
		numOfDart=numOfLava=numOfPoison=numOfMagician=3;
		
		addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				for (Tickable t: timer.getTickables()){
					if (t instanceof Tower && t.getLocation().equals(new Point(e.getX()*32/800,e.getY()*32/800))){
						return;
					}
				}
				if(boardPath[e.getX()*32/800][e.getY()*32/800].equals(new Point())){
					Graphics graphics = getGraphics();
					graphics.setColor(Color.BLUE);
					graphics.fillRect(e.getX()*32/800*25,e.getY()*32/800*25,25,25);
					xPosition = e.getX();
					yPosition = e.getY();
					
					final JFrame towerWindow = new JFrame("Choose a Tower");
					towerWindow.setLayout(new GridLayout(2,2));
					JButton dartButton = new JButton(IMAGE_ICON_TOWER_DART);
					dartButton.setText(numOfDart+"");
					JButton poisonButton = new JButton(IMAGE_ICON_TOWER_POISON);
					poisonButton.setText(numOfPoison+"");
					JButton lavaButton = new JButton(IMAGE_ICON_TOWER_LAVA);
					lavaButton.setText(numOfLava+"");
					JButton magicianButton = new JButton(IMAGE_ICON_TOWER_MAGICIAN);
					magicianButton.setText(numOfMagician+"");
					
					dartButton.addActionListener(new ActionListener()
					{
						
						@Override
						public void actionPerformed(ActionEvent e)
						{
							if (numOfDart>0)
							{
								timer.register(new Dart(new Point(xPosition*32/800, yPosition*32/800)));
								numOfDart--;
								repaint();
								towerWindow.dispose();
							}
						}
					});
					poisonButton.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							if (numOfPoison>0)
							{
								timer.register(new Poison(new Point(xPosition*32/800, yPosition*32/800)));
								numOfPoison--;
								repaint();
								towerWindow.dispose();
							}
						}
					});
					lavaButton.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							if (numOfLava>0)
							{
								timer.register(new Lava(new Point(xPosition*32/800, yPosition*32/800)));
								numOfLava--;
								repaint();
								towerWindow.dispose();
							}
						}
					});
					magicianButton.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							if (numOfMagician>0)
							{
								timer.register(new Magician(new Point(xPosition*32/800, yPosition*32/800)));
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
					towerWindow.setSize(150,150);
					towerWindow.setResizable(false);
					towerWindow.pack();
				
					towerWindow.addWindowListener(new WindowAdapter(){
						public void windowClosing(WindowEvent e){
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
	
	static int getLevel()
	{
		return level;
	}
	
	public static Timer getTimer()
	{
		return timer;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		//g.drawString("This is my custom Panel!",10,20);
		//super.paintComponent(g);
		for (int i=0 ; i<boardPath.length ; i++){
			for (int j=0 ; j<boardPath[i].length ; j++){
				if (boardPath[i][j].getX()==0 && boardPath[i][j].getY()==0){
					//g.drawString("This is my custom Panel!",10,20);
					boardLabels[i][j] = new JLabel(IMAGE_ICON_GRASS);
					//g.setColor(Color.GREEN);
					//g.fillRect(i*25,j*25,25,25);
					//g.setColor(Color.GREEN);
					g.drawImage(IMAGE_ICON_GRASS.getImage(),i*25,j*25,25,25,this);
					
				}
				else
				{
					boardLabels[i][j]=new JLabel(IMAGE_ICON_PATH);
					//g.drawImage(IMAGE_GRASS,i*25,j*25,25,25,this);
					//g.setColor(Color.pink);
					//g.fillRect(i*25,j*25,25,25);
					//g.setColor(Color.pink);
					g.drawImage(IMAGE_ICON_PATH.getImage(),i*25,j*25,25,25,this);
				}
			}
		}
		
		for (Tickable t : timer.getTickables()){
			g.drawImage(t.getImageIcon().getImage(),(int)t.getLocation().getX()*25,(int)t.getLocation().getY()*25,25,25,this);
		}
	}
}