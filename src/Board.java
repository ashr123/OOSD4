import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Board extends JPanel
{
	private final Point[][] boardPath;
	private final JLabel[][] boardLabels;
	private static int level;
	private int numOfDart;
	private int numOfLava;
	private int numOfPoison;
	private int numOfMagician;
	
	
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
	
	private static final ImageIcon IMAGE_ICON_TOWER_LAVA=
			new ImageIcon(Board.class.getResource("Media/towers/Lava.png"));
	private static final ImageIcon IMAGE_ICON_TOWER_DART=
			new ImageIcon(Board.class.getResource("Media/towers/Dart.png"));
	private static final ImageIcon IMAGE_ICON_TOWER_POISON=
			new ImageIcon(Board.class.getResource("Media/towers/Poison.png"));
	private static final ImageIcon IMAGE_ICON_TOWER_MAGICIAN=
			new ImageIcon(Board.class.getResource("Media/towers/Magician.png"));
	private static final ImageIcon IMAGE_ICON_CREEP_KNIGHT=
			new ImageIcon(Board.class.getResource("Media/creeps/abir-1.png"));
	private static final ImageIcon IMAGE_ICON_CREEP_MIKE=
			new ImageIcon(Board.class.getResource("Media/creeps/mike-1.png"));
	private static final ImageIcon IMAGE_ICON_CREEP_NAJI=
			new ImageIcon(Board.class.getResource("Media/creeps/naji-1.png"));
	private static final ImageIcon IMAGE_ICON_CREEP_SKULLY=
			new ImageIcon(Board.class.getResource("Media/creeps/guli-1.png"));
	private static final ImageIcon IMAGE_ICON_GRASS=
			new ImageIcon(Board.class.getResource("Media/environment/grass.png"));
	private static final ImageIcon IMAGE_ICON_PATH=
			new ImageIcon(Board.class.getResource("Media/environment/path.png"));
	
	
	public Board(int level)
	{
		setBorder(BorderFactory.createLineBorder(Color.black));
		boardPath = Game.getLoader().get(0);
		boardLabels = new JLabel[32][32];
		numOfDart=numOfLava=numOfPoison=numOfMagician=3;
		
		addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(boardPath[e.getX()*32/800][e.getY()*32/800].equals(new Point())){
					JPanel towerWindow = new JPanel();
					towerWindow.setSize(150,150);
					towerWindow.add(new JButton(IMAGE_ICON_TOWER_DART));
					towerWindow.add(new JButton(IMAGE_ICON_TOWER_POISON));
					towerWindow.add(new JButton(IMAGE_ICON_TOWER_LAVA));
					towerWindow.add(new JButton(IMAGE_ICON_TOWER_MAGICIAN));
					towerWindow.setVisible(true);
					
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

	
	@Override
	public void paintComponent(Graphics g)
	{
		//g.drawString("This is my custom Panel!",10,20);
		//super.paintComponent(g);
		for (int i=0 ; i<boardPath.length ; i++){
			for (int j=0 ; j<boardPath[i].length ; j++){
				if (boardPath[i][j].getX()==0 && boardPath[i][j].getY()==0){
					//g.drawString("This is my custom Panel!",10,20);
					//boardLabels[i][j] = new JLabel(IMAGE_ICON_PATH);
					g.drawImage(IMAGE_ICON_GRASS.getImage(),i*25,j*25,25,25,this);
					//g.setColor(Color.GREEN);
					//g.fillRect(i*25,j*25,25,25);
					//g.setColor(Color.GREEN);
				}
				else
				{
					//boardLabels[i][j]=new JLabel(IMAGE_ICON_GRASS);
					//g.drawImage(IMAGE_GRASS,i*25,j*25,25,25,this);
					//g.setColor(Color.pink);
					//g.fillRect(i*25,j*25,25,25);
					//g.setColor(Color.pink);
					g.drawImage(IMAGE_ICON_PATH.getImage(),i*25,j*25,25,25,this);
				}
			}
		}
	}
}