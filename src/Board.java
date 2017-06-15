import javax.swing.*;
import java.awt.*;

class Board extends JPanel
{
	private final Point[][] boardPath=new Point[32][32];
	private final JButton[][] boardButtuns=new JButton[32][32];
	private static int level;
	private JLabel[][] jLabels;
	
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
	
	
	private Board(int level)
	{
		this.level = level;
		
	}
	
	static int getLevel()
	{
		return level;
	}
}