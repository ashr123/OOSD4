import javax.swing.*;
import java.awt.*;

class Board extends JPanel
{
	private final Point[][] boardPath;
	private final JButton[][] boardButtuns;
	private static int level;
	
	private static final ImageIcon IMAGE_TOWER_LAVA=
			new ImageIcon(Board.class.getResource("Media/towers/Lava.png"));
	private static final ImageIcon IMAGE_TOWER_DART=
			new ImageIcon(Board.class.getResource("Media/towers/Dart.png"));
	private static final ImageIcon IMAGE_TOWER_POISON=
			new ImageIcon(Board.class.getResource("Media/towers/Poison.png"));
	private static final ImageIcon IMAGE_TOWER_MAGICIAN=
			new ImageIcon(Board.class.getResource("Media/towers/Magician.png"));
	private static final ImageIcon IMAGE_CREEP_KNIGHT=
			new ImageIcon(Board.class.getResource("Media/creeps/abir-1.png"));
	private static final ImageIcon IMAGE_CREEP_MIKE=
			new ImageIcon(Board.class.getResource("Media/creeps/mike-1.png"));
	private static final ImageIcon IMAGE_CREEP_NAJI=
			new ImageIcon(Board.class.getResource("Media/creeps/naji-1.png"));
	private static final ImageIcon IMAGE_CREEP_SKULLY=
			new ImageIcon(Board.class.getResource("Media/creeps/guli-1.png"));
	private static final ImageIcon IMAGE_GRASS=
			new ImageIcon(Board.class.getResource("Media/environment/grass.png"));
	private static final ImageIcon IMAGE_PATH=
			new ImageIcon(Board.class.getResource("Media/environment/path.png"));
	
	
	public Board(int level)
	{
		boardPath = Game.getLoader().get(0);
		boardButtuns = new JButton[32][32];
		repaint();
		
		
		
	}
	
	static int getLevel()
	{
		return level;
	}

	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		for (int i=0 ; i<boardPath.length ; i++){
			for (int j=0 ; j<boardPath[i].length ; j++){
				if (boardPath[i][j].getX()==0 && boardPath[i][j].getY()==0){
					boardButtuns[i][j] = new JButton(IMAGE_PATH);
					boardButtuns[i][j].repaint(i*25,j*25,25,25);
					
				}
				else
				{
					boardButtuns[i][j]=new JButton(IMAGE_GRASS);
					boardButtuns[i][j].repaint(i*25, j*25, 25, 25);
				}
			}
		}
	}
}