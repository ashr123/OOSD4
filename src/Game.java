import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class Game
{
	private static final LevelLoader loader=new LevelLoader();
	private static final ImageIcon IMAGE_MAP_1=
			new ImageIcon(new ImageIcon(Board.class.getResource("Media/toolbar/level0.png")).getImage()
			                                                                                .getScaledInstance(200, 200,
			                                                                                                   Image.SCALE_SMOOTH));
	private static final ImageIcon IMAGE_MAP_2=
			new ImageIcon(new ImageIcon(Board.class.getResource("Media/toolbar/level1.png")).getImage()
			                                                                                .getScaledInstance(200, 200,
			                                                                                                   Image.SCALE_SMOOTH));
	private static final ImageIcon IMAGE_MAP_3=
			new ImageIcon(new ImageIcon(Board.class.getResource("Media/toolbar/level2.png")).getImage()
			                                                                                .getScaledInstance(200, 200,
			                                                                                                   Image.SCALE_SMOOTH));
	private static int HP=1;//change to 20
	private static final JLabel HPLBL=new JLabel("HP: "+HP+"    ");
	private static final JLabel WaveLBL=new JLabel("Wave: 1    ");
	private static final JLabel TimeLBL=new JLabel("Time: 0    ");
	private static final JFrame frame=new JFrame("Tower Defence");
	private Board board;
	private static JPanel panel=new JPanel(new BorderLayout());
	private static final JButton goButton=new JButton("Go!");
	
	private Game() throws IOException
	{
		if(Board.getLevel()>5||getHP()==0){//Player Won
			JPanel winPanel = new JPanel();
			JLabel mes = new JLabel("You Won!");
			JButton continueButton = new JButton("Continue");
			mes.setFont(new Font("Serif", Font.PLAIN, 26));
			winPanel.add(mes);
			winPanel.add(continueButton);
			Game.getFrame().add(winPanel);
			continueButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					getFrame().setContentPane(Game.getPanel());
				}
			});
			
			//Game.getPanel().setVisible(true);
			//Game.getPanel().setEnabled(true);
			
		}
		loader.load();
		fireUpScreen();
		JLabel maps=new JLabel("Please Choose a Map:");
		maps.setFont(new Font("Courier", Font.BOLD, 20));
		JButton map0=new JButton(IMAGE_MAP_1);
		JButton map1=new JButton(IMAGE_MAP_2);
		JButton map2=new JButton(IMAGE_MAP_3);
		panel.add(maps, BorderLayout.NORTH);
		panel.add(map0, BorderLayout.WEST);
		panel.add(map1, BorderLayout.CENTER);
		panel.add(map2, BorderLayout.EAST);
		frame.add(panel);
		panel.setBackground(Color.orange);
		map0.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				board=new Board(0);
				frame.add(board);
				frame.setVisible(true);
				panel.setVisible(false);
				createToolBar();
			}
		});
		map1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				board=new Board(1);
				frame.add(board);
				frame.setVisible(true);
				panel.setVisible(false);
				createToolBar();
			}
		});
		map2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				board=new Board(2);
				frame.add(board);
				frame.setVisible(true);
				panel.setVisible(false);
				createToolBar();
			}
		});
		frame.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException
	{
		new Game();
		playBackgroundMusic();
	}
	
	static LevelLoader getLoader()
	{
		return loader;
	}
	
	private static void playBackgroundMusic() throws LineUnavailableException, IOException,
	                                                 UnsupportedAudioFileException
	{
		Clip clip=AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(Game.class.getResource("Sounds/backgroundMusic.wav")));
		clip.loop(Integer.MAX_VALUE);
	}
	
	static void decreaseHP()
	{
		HP--;
	}
	
	static int getHP()
	{
		return HP;
	}
	
	static JLabel getHPLBL()
	{
		return HPLBL;
	}
	
	static JLabel getWaveLBL()
	{
		return WaveLBL;
	}
	
	static JLabel getTimeLBL()
	{
		return TimeLBL;
	}
	
	private void fireUpScreen()
	{
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(800, 800);
		//frame.add(new Board(map));
	}
	
	private void createToolBar()
	{
		JToolBar toolBar=new JToolBar();
		JButton fastForwardButton=new JButton("Fast Forward");
		toolBar.add(HPLBL);
		toolBar.add(WaveLBL);
		toolBar.add(TimeLBL);
		toolBar.add(fastForwardButton);
		toolBar.add(new JLabel("     "));
		toolBar.add(goButton);
		frame.add(toolBar, BorderLayout.NORTH);
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
	}
	
	static JButton getGoButton()
	{
		return goButton;
	}
	
	static JPanel getPanel()
	{
		return panel;
	}
	
	public static JFrame getFrame()
	{
		return frame;
	}
}