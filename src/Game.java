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
	private static int HP;
	private static final JLabel HPLBL=new JLabel("HP: "+HP+"    ");
	private static final JLabel WaveLBL=new JLabel("Wave: 1    ");
	private static final JLabel TimeLBL=new JLabel("Time: 0    ");
	private static final JFrame frame=new JFrame("Tower Defence");
	private static Board board;
	private static JPanel panel=new JPanel(new BorderLayout());
	private static final JButton goButton=new JButton("Go!");
	
	private Game() throws IOException
	{
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
		frame.setContentPane(panel);
		panel.setBackground(Color.orange);
		map0.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				board=new Board(0);
				JPanel gamePanel=new JPanel(new BorderLayout());
				createToolBar(gamePanel);
				gamePanel.add(board);
				frame.setContentPane(gamePanel);
				frame.setVisible(true);
			}
		});
		map1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				board=new Board(1);
				JPanel gamePanel=new JPanel(new BorderLayout());
				createToolBar(gamePanel);
				gamePanel.add(board);
				frame.setContentPane(gamePanel);
				frame.setVisible(true);
			}
		});
		map2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				board=new Board(2);
				JPanel gamePanel=new JPanel(new BorderLayout());
				createToolBar(gamePanel);
				gamePanel.add(board);
				frame.setContentPane(gamePanel);
				frame.setVisible(true);
			}
		});
		frame.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException
	{
		new Game();
		resetHP();
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
		HPLBL.setText("HP: "+HP+"    ");
		if (getHP()==0)
			playerLost();
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
	}
	
	private void createToolBar(JPanel gamePanel)
	{
		JToolBar toolBar=new JToolBar();
		JButton fastForwardButton=new JButton("Fast Forward");
		toolBar.add(HPLBL);
		toolBar.add(WaveLBL);
		toolBar.add(TimeLBL);
		toolBar.add(fastForwardButton);
		toolBar.add(new JLabel("     "));
		toolBar.add(goButton);
		gamePanel.add(toolBar, BorderLayout.NORTH);
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
	
	static void playerWon()
	{
		final JPanel wonPanel=new JPanel();
		wonPanel.setLayout(new BoxLayout(wonPanel,BoxLayout.PAGE_AXIS));
		Timer timer = Board.getTimer();
		
		JLabel wonMes=new JLabel("You Won!" +"\n");
		JLabel hpLeft = new JLabel("HP:  "+getHP() +"\n");
		JLabel passedCreeps = new JLabel("Passed Creeps:  "+timer.getPassedCreeps() +"\n");
		JLabel deadCreeps = new JLabel("Dead Creeps:  "+timer.getDeadCreeps() +"\n");
		JLabel time = new JLabel("Time elapsed"+timer.getTime() +"\n");
		JButton tryAgainButton=new JButton("Chose a different level");
		
		wonMes.setFont(new Font("Courier", Font.PLAIN, 26));
		hpLeft.setFont(new Font("Courier", Font.PLAIN, 20));
		passedCreeps.setFont(new Font("Courier", Font.PLAIN, 20));
		deadCreeps.setFont(new Font("Courier", Font.PLAIN, 20));
		time.setFont(new Font("Courier", Font.PLAIN, 20));
		tryAgainButton.setFont(new Font("Courier", Font.PLAIN, 26));
		
		wonMes.setAlignmentX(Component.CENTER_ALIGNMENT);
		hpLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
		passedCreeps.setAlignmentX(Component.CENTER_ALIGNMENT);
		deadCreeps.setAlignmentX(Component.CENTER_ALIGNMENT);
		time.setAlignmentX(Component.CENTER_ALIGNMENT);
		tryAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		wonPanel.add(wonMes);
		wonPanel.add(hpLeft);
		wonPanel.add(passedCreeps);
		wonPanel.add(deadCreeps);
		wonPanel.add(time);
		wonPanel.add(tryAgainButton);
		
		frame.setContentPane(wonPanel);
		timer.stop();
		tryAgainButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				frame.setContentPane(panel);
			}
		});
		resetHP();
		frame.setVisible(true);
	}
	
	//Player Lost
	private static void playerLost()
	{
		final JPanel lostPanel=new JPanel();
		lostPanel.setLayout(new BoxLayout(lostPanel,BoxLayout.PAGE_AXIS));
		Timer timer = Board.getTimer();
		
		JLabel lostMes=new JLabel("You Lost!"+"\n");
		JLabel hpLeft = new JLabel("HP:  "+getHP() +"\n");
		JLabel passedCreeps = new JLabel("Passed Creeps:  "+timer.getPassedCreeps() +"\n");
		JLabel deadCreeps = new JLabel("Dead Creeps:  "+timer.getDeadCreeps() +"\n");
		JLabel time = new JLabel("Time elapsed"+timer.getTime() +"\n");
		JButton tryAgainButton=new JButton("Try again");
		
		lostMes.setFont(new Font("Courier", Font.PLAIN, 26));
		hpLeft.setFont(new Font("Courier", Font.PLAIN, 20));
		passedCreeps.setFont(new Font("Courier", Font.PLAIN, 20));
		deadCreeps.setFont(new Font("Courier", Font.PLAIN, 20));
		time.setFont(new Font("Courier", Font.PLAIN, 20));
		tryAgainButton.setFont(new Font("Courier", Font.PLAIN, 26));
		
		lostMes.setAlignmentX(Component.CENTER_ALIGNMENT);
		hpLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
		passedCreeps.setAlignmentX(Component.CENTER_ALIGNMENT);
		deadCreeps.setAlignmentX(Component.CENTER_ALIGNMENT);
		time.setAlignmentX(Component.CENTER_ALIGNMENT);
		tryAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		lostPanel.add(lostMes);
		lostPanel.add(hpLeft);
		lostPanel.add(passedCreeps);
		lostPanel.add(deadCreeps);
		lostPanel.add(time);
		lostPanel.add(tryAgainButton);
		
		frame.setContentPane(lostPanel);
		timer.stop();
		tryAgainButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				frame.setContentPane(panel);
			}
		});
		resetHP();
	}
	
	private static void resetHP()
	{
		HP=20;
	}
}