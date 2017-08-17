package manage;

import tickables.towers.Dart;
import tickables.towers.Lava;
import tickables.towers.Magician;
import tickables.towers.Poison;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Represents a Singleton-window of towers to choose from
 */
final class TowerWindow extends JFrame
{
	private static final JButton
			dartButton=new JButton(new ImageIcon(new ImageIcon(Board.class.getResource(
			"/media/towers/Dart.png")).getImage().getScaledInstance(100, 135,
			                                                        Image.SCALE_SMOOTH))),
			poisonButton=new JButton(new ImageIcon(new ImageIcon(Board.class.getResource(
					"/media/towers/Poison.png")).getImage().getScaledInstance(100, 131,
			                                                                  Image.SCALE_SMOOTH))),
			lavaButton=new JButton(new ImageIcon(new ImageIcon(Board.class.getResource(
					"/media/towers/Lava.png")).getImage().getScaledInstance(100, 162,
			                                                                Image.SCALE_SMOOTH))),
			magicianButton=new JButton(new ImageIcon(new ImageIcon(Board.class.getResource(
					"/media/towers/Magician.png")).getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH)));
	/**
	 * Holds the only instance of {@code TowerWindow}
	 */
	private static final TowerWindow ourInstance=new TowerWindow();
	/**
	 * Holds the remaining number of towers left from this type
	 */
	private static int numOfDart, numOfLava, numOfPoison, numOfMagician;
	private static MouseEvent e;
	private static Board board;
	
	private TowerWindow()
	{
		super("Choose a Tower:");
		setLayout(new GridLayout(2, 2));
		setLabels();
		dartButton.addActionListener(e1 ->
		                             {
			                             if (numOfDart>0)
			                             {
				                             Board.getTimer().register(new Dart(
				                             		new Point(e.getX()*32/800, e.getY()*32/800)));
				                             numOfDart--;
				                             board.repaint();
				                             dispose();
			                             }
		                             });
		poisonButton.addActionListener(e1 ->
		                               {
			                               if (numOfPoison>0)
			                               {
				                               Board.getTimer().register(new Poison(
				                               		new Point(e.getX()*32/800, e.getY()*32/800)));
				                               numOfPoison--;
				                               board.repaint();
				                               dispose();
			                               }
		                               });
		lavaButton.addActionListener(e1 ->
		                             {
			                             if (numOfLava>0)
			                             {
				                             Board.getTimer().register(new Lava(
				                             		new Point(e.getX()*32/800, e.getY()*32/800)));
				                             numOfLava--;
				                             board.repaint();
				                             dispose();
			                             }
		                             });
		magicianButton.addActionListener(e1 ->
		                                 {
			                                 if (numOfMagician>0)
			                                 {
				                                 Board.getTimer().register(new Magician(
				                                 		new Point(e.getX()*32/800, e.getY()*32/800)));
				                                 numOfMagician--;
				                                 board.repaint();
				                                 dispose();
			                                 }
		                                 });
		
		add(dartButton);
		add(poisonButton);
		add(lavaButton);
		add(magicianButton);
		pack();
		setResizable(false);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				board.repaint();
			}
		});
	}
	
	private static void setLabels()
	{
		dartButton.setText(numOfDart+"");
		poisonButton.setText(numOfPoison+"");
		lavaButton.setText(numOfLava+"");
		magicianButton.setText(numOfMagician+"");
	}
	
	static void reset(Board board)
	{
		numOfDart=numOfLava=numOfPoison=numOfMagician=3;
		TowerWindow.board=board;
	}
	
	static void displayFrame(MouseEvent e)
	{
		TowerWindow.e=e;
		setLabels();
		ourInstance.setVisible(true);
	}
}
