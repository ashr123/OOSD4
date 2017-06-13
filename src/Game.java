import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game extends JFrame
{
	private static final LevelLoader loader=new LevelLoader();
	private int numberOfCreeps = 20;
	private int HP = 20;
	
	private Game() throws IOException
	{
		super("Tower Defence");
		loader.load();
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800,800));
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) throws IOException
	{
		new Game();
	}
}