package manage;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

/**
 * Represents the main class of this game
 */
@SuppressWarnings({"MagicNumber", "UtilityClassCanBeEnum"})
public final class Game
{
	private static final ImageIcon
			IMAGE_MAP_1=new ImageIcon(new ImageIcon(Game.class.getResource("/media/toolbar/level0.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)),
			IMAGE_MAP_2=new ImageIcon(new ImageIcon(Game.class.getResource("/media/toolbar/level1.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)),
			IMAGE_MAP_3=new ImageIcon(new ImageIcon(Game.class.getResource("/media/toolbar/level2.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
	private static final JFrame frame=new JFrame("Tower Defence");
	private static final JPanel panel=new JPanel(new BorderLayout());
	private static final JButton goButton=new JButton("Go!");
	/**
	 * Represents the helth-points of the player
	 */
	private static int HP;
	private static final JLabel
			WaveLBL=new JLabel("Wave: 1    "),
			TimeLBL=new JLabel("Time: 0    "),
			HPLBL=new JLabel("HP: "+HP+"    ");
	
	/**
	 * Builds the main frame: the maps selection frame
	 * @throws IOException if there is any error with the file
	 */
	private Game() throws IOException
	{
		LevelLoader.load();
		final JLabel maps=new JLabel("Please choose a map:");
		maps.setFont(new Font("Courier", Font.BOLD, 20));
		final JButton
				map0=new JButton(IMAGE_MAP_1),
				map1=new JButton(IMAGE_MAP_2),
				map2=new JButton(IMAGE_MAP_3);
		panel.add(maps, BorderLayout.NORTH);
		panel.add(map0, BorderLayout.WEST);
		panel.add(map1, BorderLayout.CENTER);
		panel.add(map2, BorderLayout.EAST);
		frame.setContentPane(panel);
		panel.setBackground(Color.orange);
		map0.addActionListener(e -> createMap(0));
		map1.addActionListener(e -> createMap(1));
		map2.addActionListener(e -> createMap(2));
	}
	
	private static void createMap(int mapNum)
	{
		final JPanel gamePanel=new JPanel(new BorderLayout());
		createToolBar(gamePanel);
		gamePanel.add(new Board(mapNum));
		frame.setContentPane(gamePanel);
		frame.pack();
	}
	
	/**
	 * Main function
	 * @param args an empty {@link String} array
	 * @throws IOException if an I/O exception occurs
	 * @throws LineUnavailableException if a clip object is not available due to resource restrictions
	 * @throws UnsupportedAudioFileException if the {@code File} does not point to valid audio file data recognized by the system
	 */
	public static void main(String[] args) throws IOException, LineUnavailableException,
	                                              UnsupportedAudioFileException
	{
		new Game();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(800, 800);
		frame.setVisible(true);
		resetHP();
		playBackgroundMusic();
	}
	
	/**
	 * Plays the background music
	 * @throws LineUnavailableException if a clip object is not available due to resource restrictions
	 * @throws IOException if an I/O exception occurs
	 * @throws UnsupportedAudioFileException if the {@code File} does not point to valid audio file data recognized by the system
	 */
	private static void playBackgroundMusic() throws LineUnavailableException, IOException,
	                                                 UnsupportedAudioFileException
	{
		final Clip clip=AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(Game.class.getResource(
				"/sounds/backgroundMusic.wav")));
		clip.loop(Integer.MAX_VALUE);
	}
	
	/**
	 * Decreases the HP by 1 and checks if the player lost the game
	 */
	static void decreaseHP()
	{
		HPLBL.setText("HP: "+(--HP)+"    ");
		if (getHP()==0)
			playerLost();
	}
	
	/**
	 * @return the current player's HP
	 */
	static int getHP()
	{
		return HP;
	}
	
	/**
	 * @return the HP label
	 */
	static JLabel getHPLBL()
	{
		return HPLBL;
	}
	
	/**
	 * @return the wave label
	 */
	static JLabel getWaveLBL()
	{
		return WaveLBL;
	}
	
	/**
	 * @return the time label
	 */
	static JLabel getTimeLBL()
	{
		return TimeLBL;
	}
	
	/**
	 * @return the "go" button
	 */
	static JButton getGoButton()
	{
		return goButton;
	}
	
	/**
	 * Called when the player passed 5 waves
	 */
	static void playerWon()
	{
		after(new JLabel("You Won!"+'\n'));
	}
	
	/**
	 * Called when HP=0
	 */
	private static void playerLost()
	{
		after(new JLabel("You Lost!"+'\n'));
	}
	
	/**
	 * Builds the win/lost panel
	 * @param afterLabel the label for winning or loosing
	 */
	private static void after(JLabel afterLabel)
	{
		Board.getTimer().stop();
		final JPanel afterPanel=new JPanel();
		afterPanel.setLayout(new BoxLayout(afterPanel, BoxLayout.PAGE_AXIS));
		final JLabel
				hpLeft=new JLabel("HP:  "+getHP()),
				passedCreeps=new JLabel("Passed creeps:  "+Board.getTimer().getTotalPassedCreeps()),
				deadCreeps=new JLabel("Dead creeps:  "+Board.getTimer().getTotalDeadCreeps()),
				time=new JLabel("Time elapsed:  "+Board.getTimer().getTime()+" seconds");
		final JButton tryAgainButton=new JButton("Choose a different map");
		
		final Font
				smallFont=new Font("Courier", Font.PLAIN, 20),
				bigFont=new Font("Courier", Font.PLAIN, 26);
		afterLabel.setFont(bigFont);
		hpLeft.setFont(smallFont);
		passedCreeps.setFont(smallFont);
		deadCreeps.setFont(smallFont);
		time.setFont(smallFont);
		tryAgainButton.setFont(bigFont);
		
		afterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		hpLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
		passedCreeps.setAlignmentX(Component.CENTER_ALIGNMENT);
		deadCreeps.setAlignmentX(Component.CENTER_ALIGNMENT);
		time.setAlignmentX(Component.CENTER_ALIGNMENT);
		tryAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		afterPanel.add(afterLabel);
		afterPanel.add(hpLeft);
		afterPanel.add(passedCreeps);
		afterPanel.add(deadCreeps);
		afterPanel.add(time);
		afterPanel.add(tryAgainButton);
		
		frame.setContentPane(afterPanel);
		tryAgainButton.addActionListener(e ->
		                                 {
			                                 frame.setContentPane(panel);
			                                 frame.setSize(800, 800);
		                                 });
		frame.setVisible(true);
		resetHP();
	}
	
	/**
	 * Resets the HP to 20
	 */
	private static void resetHP()
	{
		HP=20;
	}
	
	/**
	 * Creates the toolbar for the new game
	 * @param gamePanel the toolbar will be inserted to this panel
	 */
	private static void createToolBar(JPanel gamePanel)
	{
		final JToolBar toolBar=new JToolBar();
		toolBar.setFloatable(false);
		final JButton fastForwardButton=new JButton("Fast Forward");
		toolBar.add(HPLBL);
		toolBar.add(WaveLBL);
		toolBar.add(TimeLBL);
		toolBar.add(fastForwardButton);
		toolBar.add(new JLabel("     "));
		toolBar.add(goButton);
		gamePanel.add(toolBar, BorderLayout.NORTH);
		goButton.addActionListener(e ->
		                           {
			                           Board.getTimer().start();
			                           goButton.setEnabled(false);
		                           });
		
		fastForwardButton.addActionListener(e ->
		                                    {
			                                    Board.getTimer().setFastForward(!Board.getTimer()
			                                                                          .isFastForward());
			                                    fastForwardButton.setText(Board.getTimer().isFastForward() ?
			                                                              "Slow Down" : "Fast Forward");
		                                    });
	}
}