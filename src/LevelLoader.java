import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

class LevelLoader
{
	private static final char CHAR_RIGHT='>';
	private static final char CHAR_LEFT='<';
	private static final char CHAR_UP='^';
	private static final char CHAR_DOWN='v';
	private static final char CHAR_GRASS='*';
	
	/**
	 * Contains the initial board state of all the levels
	 */
	private Vector<Point[][]> _levels;
	
	LevelLoader()
	{
		_levels=new Vector<>();
	}
	
	/**
	 * Creates {@link Point} instance from {@code char} representation
	 * @return the {@link Point} object
	 */
	private static Point parsePoint(char Point)
	{
		switch (Point)
		{
			case CHAR_GRASS:
				return new Point();
			case CHAR_RIGHT:
				return new Point(1, 0);
			case CHAR_LEFT:
				return new Point(-1, 0);
			case CHAR_UP:
				return new Point(0, -1);
			case CHAR_DOWN:
				return new Point(0, 1);
			default:
				return null;
		}
	}
	
	/**
	 * Loads all the levels to the internal levels buffer
	 * @throws IOException if there is any error with the file
	 */
	void load() throws IOException
	{
		_levels.clear();
		
		BufferedReader br=new BufferedReader(new FileReader("levels.txt"));
		String line;
		Point level[][]=null;
		int w=0;
		int h=0;
		int row=0;
		while ((line=br.readLine())!=null)
		{
			// end of level
			if (line.trim().isEmpty())
			{
				if (null!=level)
				{
					_levels.add(level);
					level=null;
				}
				continue;
			}
			
			// board size
			if (line.trim().startsWith("w"))
			{
				w=Integer.valueOf(line.trim().substring(1));
				continue;
			}
			if (line.trim().startsWith("h"))
			{
				h=Integer.valueOf(line.trim().substring(1));
				continue;
			}
			
			// comment
			if (line.startsWith(";"))
				continue;
			
			// start of level definition
			if (null==level && h>0 && w>0)
			{
				level=new Point[w][h];
				row=0;
			}
			
			// regular board line
			for (int col=0; col<line.length(); col++)
			{
				Point Point=parsePoint(line.charAt(col));
				if (null!=Point && level!=null)
					level[col][row]=Point;
				else
				{
					br.close();
					return;
				}
			}
			row++;
		}
		if (null!=level)
			_levels.add(level);
		br.close();
	}
	
	/**
	 * @return the number of levels available
	 */
	int getLevelsCount()
	{
		return _levels.size();
	}
	
	/**
	 * @param index the level number
	 * @return a deep copy of the initial state of level number {@code index}
	 */
	Point[][] get(int index)
	{
		Point[][] output=new Point[_levels.get(index)[0].length][_levels.get(index).length];
		for (int i=0; i<_levels.get(index)[0].length; i++)
			for (int j=0; j<_levels.get(index).length; j++)
				output[i][j]=new Point(_levels.get(index)[j][i]);
		return output;
	}
}