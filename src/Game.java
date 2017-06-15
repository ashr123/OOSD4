import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class Game extends JFrame
{
	private static final LevelLoader loader=new LevelLoader();
	private Board board;
	private int map;
	private int numberOfCreeps = 20;
	private int HP=20;
	private JButton map0;
	private JButton map1;
	private JButton map2;
	private JPanel panel;
	
	private Game() throws IOException
	{
		super("Tower Defence");
		loader.load();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		map0 = new JButton("Map 1");
		map1 = new JButton("Map 2");
		map2 = new JButton("Map 3");
		panel = new JPanel();
		panel.add(map0);
		panel.add(map1);
		panel.add(map2);
		add(panel);
		setSize(800, 800);
		setResizable(false);
		setVisible(true);
		map0.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				map = 1;
				board = new Board(map);
				getContentPane().add(board);
			}
		});
		map1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				map = 2;
				board = new Board(map);
				getContentPane().add(board);
			}
		});
		map2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				map = 3;
				board = new Board(map);
				getContentPane().add(board);
			}
		});
	}
	
	public static void main(String[] args) throws IOException
	{
		new Game();
	}
	
	static LevelLoader getLoader()
	{
		return loader;
	}
	
	public int getMap()
	{
		return map;
	}
}