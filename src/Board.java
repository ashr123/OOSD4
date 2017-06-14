import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

class Board extends JPanel
{
	Point[][] boardPath=new Point[32][32];
	Button[][] boardButtuns=new Button[32][32];
	LinkedList<Creep>[][] CreepsLocation=new LinkedList[32][32];
	
	private Board()
	{
	
	}
}