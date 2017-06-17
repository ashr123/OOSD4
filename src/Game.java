import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class Game
{
	private JFrame frame;
	private static final LevelLoader loader=new LevelLoader();
	private Board board;
	private int map;
	private int HP=20;
	private int waveNumber;
	private JButton map0;
	private JButton map1;
	private JButton map2;
	private JPanel panel;
	private JToolBar toolBar;
	private int time;
	private static final ImageIcon IMAGE_MAP_1=
			new ImageIcon(new ImageIcon(Board.class.getResource("Media/toolbar/level0.png")).getImage()
					              .getScaledInstance(200,200,Image.SCALE_SMOOTH));
	private static final ImageIcon IMAGE_MAP_2=
			new ImageIcon(new ImageIcon(Board.class.getResource("Media/toolbar/level1.png")).getImage()
					              .getScaledInstance(200,200,Image.SCALE_SMOOTH));
	private static final ImageIcon IMAGE_MAP_3=
			new ImageIcon(new ImageIcon(Board.class.getResource("Media/toolbar/level2.png")).getImage()
					              .getScaledInstance(200,200,Image.SCALE_SMOOTH));
	
	private Game() throws IOException
	{
		loader.load();
		fireUpScreen();
		Container contentPane = frame.getContentPane();
		contentPane.setBackground(Color.orange);
		JToolBar toolBar = new JToolBar();
		final JButton goButton = new JButton("Go!");
		JButton fastForwardButton = new JButton("Fast Forward");
		toolBar.add(new JLabel("HP: "+ HP + "    "));
		toolBar.add(new JLabel("Wave: "+ waveNumber + "    "));
		toolBar.add(new JLabel("Time: "+ time + "    " ));
		toolBar.add(fastForwardButton);
		toolBar.add(new JLabel("     "));
		toolBar.add(goButton);
		contentPane.add(toolBar, BorderLayout.NORTH);
		frame.setVisible(true);
		
		goButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Board.getTimer().start();
				goButton.setEnabled(false);
			}
		});
		
		fastForwardButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Board.getTimer().setFastFoword(!Board.getTimer().isFastFoword());
			}
		});
		/*panel = new JPanel();
		JLabel maps = new JLabel("Please Choose a Map:");
		map0 = new JButton(IMAGE_MAP_1);
		map1 = new JButton(IMAGE_MAP_2);
		map2 = new JButton(IMAGE_MAP_3);
		panel.add(maps,BorderLayout.PAGE_START);
		panel.add(map0,BorderLayout.LINE_START);
		panel.add(map1,BorderLayout.CENTER);
		panel.add(map2,BorderLayout.LINE_END);
		frame.add(panel,BorderLayout.NORTH);
		map0.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				board = new Board(0);
				frame.add(board);
			}
		});
		map1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				board = new Board(1);
				frame.add(board);
			}
		});
		map2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				board = new Board(2);
				frame.add(board);
			}
		});*/
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
	
	public void fireUpScreen(){
		frame =  new JFrame("Tower Defence");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(800, 800);
		frame.add(new Board(map));
	}
	
}