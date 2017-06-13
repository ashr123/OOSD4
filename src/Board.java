import javax.swing.*;

public class Board extends JPanel
{
	private int[][] gameBoard;
	
	private Board(){
		this.gameBoard = new int[32][32];
	}
}