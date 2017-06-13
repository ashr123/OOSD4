import javax.swing.*;
import java.awt.*;

public class Game extends JFrame
{
	public static void main(String[] args)
	{
		new Game();
	}
	
	private Game(){
		super("Tower Defence");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800,800));
		setResizable(false);
		setVisible(true);
	}
}