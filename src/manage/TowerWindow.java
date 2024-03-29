package manage;

import tickables.towers.Dart;
import tickables.towers.Goku;
import tickables.towers.Lava;
import tickables.towers.Magician;
import tickables.towers.Poison;
import tickables.towers.Sam;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Represents a Singleton-window of towers to choose from
 */
final class TowerWindow extends JFrame
{
	private static final JButton
			dartButton=new JButton(new ImageIcon(new ImageIcon(TowerWindow.class.getResource(
					"/media/towers/Dart.png")).getImage().getScaledInstance(100, 135,
			                                                                Image.SCALE_SMOOTH))),
			poisonButton=new JButton(new ImageIcon(new ImageIcon(TowerWindow.class.getResource(
					"/media/towers/Poison.png")).getImage().getScaledInstance(100, 131,
			                                                                  Image.SCALE_SMOOTH))),
			lavaButton=new JButton(new ImageIcon(new ImageIcon(TowerWindow.class.getResource(
					"/media/towers/Lava.png")).getImage().getScaledInstance(100, 162,
			                                                                Image.SCALE_SMOOTH))),
			magicianButton=new JButton(new ImageIcon(new ImageIcon(TowerWindow.class.getResource(
					"/media/towers/Magician.png")).getImage().getScaledInstance(100, 145,
			                                                                    Image.SCALE_SMOOTH))),
			samButton=new JButton(new ImageIcon(new ImageIcon(TowerWindow.class.getResource(
					"/media/towers/Sam.png")).getImage().getScaledInstance(100, 205,
			                                                                    Image.SCALE_SMOOTH))),
			gokuButton=new JButton(new ImageIcon(new ImageIcon(TowerWindow.class.getResource(
					"/media/towers/Goku.png")).getImage().getScaledInstance(100, 222,
			                                                                Image.SCALE_SMOOTH)));
	/**
	 * Holds the only instance of {@code TowerWindow}
	 */
	private static final TowerWindow ourInstance=new TowerWindow();
	/**
	 * Holds the remaining number of towers left from this type
	 */
	private static int numOfDart, numOfLava, numOfPoison, numOfMagician, numOfSam, numOfGoku;
	private static MouseEvent e;
	
	private TowerWindow()
	{
		super("Choose a Tower:");
		setLayout(new GridLayout(2, 3));
		dartButton.addActionListener(e1 ->
		                             {
			                             if (numOfDart>0)
			                             {
				                             Game.getBoard().getTimer().register(new Dart(
				                             		new Point(e.getX()*32/800, e.getY()*32/800)));
				                             numOfDart--;
				                             disposeTowerWindow();
			                             }
		                             });
		poisonButton.addActionListener(e1 ->
		                               {
			                               if (numOfPoison>0)
			                               {
				                               Game.getBoard().getTimer().register(new Poison(
				                               		new Point(e.getX()*32/800, e.getY()*32/800)));
				                               numOfPoison--;
				                               disposeTowerWindow();
			                               }
		                               });
		lavaButton.addActionListener(e1 ->
		                             {
			                             if (numOfLava>0)
			                             {
				                             Game.getBoard().getTimer().register(new Lava(
				                             		new Point(e.getX()*32/800, e.getY()*32/800)));
				                             numOfLava--;
				                             disposeTowerWindow();
			                             }
		                             });
		magicianButton.addActionListener(e1 ->
		                                 {
			                                 if (numOfMagician>0)
			                                 {
				                                 Game.getBoard().getTimer().register(new Magician(
				                                 		new Point(e.getX()*32/800, e.getY()*32/800)));
				                                 numOfMagician--;
				                                 disposeTowerWindow();
			                                 }
		                                 });
		samButton.addActionListener(e1 ->
		                            {
		                            	if (numOfSam>0)
		                            	{
				                            Game.getBoard().getTimer().register(new Sam(
		                            				new Point(e.getX()*32/800, e.getY()*32/800)));
		                            		numOfSam--;
		                            		disposeTowerWindow();
		                            	}
		                            });
		gokuButton.addActionListener(e1 ->
		                            {
			                            if (numOfGoku>0)
			                            {
				                            Game.getBoard().getTimer().register(new Goku(
				                            		new Point(e.getX()*32/800, e.getY()*32/800)));
				                            numOfGoku--;
				                            disposeTowerWindow();
			                            }
		                            });
		
		add(dartButton);
		add(poisonButton);
		add(lavaButton);
		add(magicianButton);
		add(samButton);
		add(gokuButton);
		setResizable(false);
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e1)
			{
				e=null;
				Game.getBoard().repaint();
			}
		});
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	static void disposeTowerWindow()
	{
		e=null;
		Game.getBoard().repaint();
		ourInstance.dispose();
	}
	
	static void reset()
	{
		numOfDart=numOfLava=numOfPoison=numOfMagician=numOfSam=numOfGoku=3;
	}
	
	static void displayFrame(MouseEvent e1)
	{
		e=e1;
		dartButton.setText(numOfDart+"");
		poisonButton.setText(numOfPoison+"");
		lavaButton.setText(numOfLava+"");
		magicianButton.setText(numOfMagician+"");
		samButton.setText(numOfSam+"");
		gokuButton.setText(numOfGoku+"");
		ourInstance.pack();
		ourInstance.setVisible(true);
	}
	
	static MouseEvent getE()
	{
		return e;
	}
}