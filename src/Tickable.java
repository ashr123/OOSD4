import javax.swing.*;
import java.awt.*;

public interface Tickable
{
	Point getLocation();
	
	ImageIcon getImageIcon();
	
	void tickHappend();
}
