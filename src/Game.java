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
	private int waveNumber;
	private JButton map0;
	private JButton map1;
	private JButton map2;
	private JPanel panel;
	private JToolBar toolBar;
	private int time;
	
	private Game() throws IOException
	{
		super("Tower Defence");
		loader.load();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		JToolBar toolBar = new JToolBar();
		toolBar.add(new JLabel("HP: "+ HP + "    "));
		toolBar.add(new JLabel("Wave: "+ waveNumber + "    "));
		toolBar.add(new JLabel("Time: "+ time + "    " ));
		toolBar.add(new JButton("Fast Forward"));
		toolBar.add(new JLabel("     "));
		toolBar.add(new JButton("Go!"));
		contentPane.add(toolBar, BorderLayout.NORTH);
		panel = new JPanel();
		map0 = new JButton("Map 1");
		map1 = new JButton("Map 2");
		map2 = new JButton("Map 3");
		panel.add(map0);
		panel.add(map1);
		panel.add(map2);
		add(panel,BorderLayout.CENTER);
		setSize(800, 800);
		setResizable(false);
		setVisible(true);
		map0.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				map = 0;
				board = new Board(map);
				getContentPane().add(board);
			}
		});
		map1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				map = 1;
				board = new Board(map);
				getContentPane().add(board);
			}
		});
		map2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				map = 2;
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
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
	}
}