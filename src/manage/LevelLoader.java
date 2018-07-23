package manage;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"ClassHasNoToStringMethod", "UtilityClassCanBeEnum"})
public final class LevelLoader
{
	private static final Map<Character, Point> charToPoint=new HashMap<>(5);

	static
	{
		charToPoint.put('*', new Point());
		charToPoint.put('>', new Point(1, 0));
		charToPoint.put('<', new Point(-1, 0));
		charToPoint.put('^', new Point(0, -1));
		charToPoint.put('v', new Point(0, 1));
	}

	/**
	 * Contains the initial board state of all the levels
	 */
	private static final ArrayList<Point[][]> _levels=new ArrayList<>();

	/**
	 * Private no-args constructor for ensuring against instantiation.
	 */
	private LevelLoader()
	{

	}

	/**
	 * Loads all the levels to the internal levels buffer
	 *
	 * @throws IOException if there is any error with the file
	 */
	static void load() throws IOException
	{
		_levels.clear();

		try (BufferedReader br=new BufferedReader(new FileReader("levels.txt")))
		{
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
					if (level!=null)
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
				if (level==null && h>0 && w>0)
				{
					level=new Point[w][h];
					row=0;
				}

				// regular board line
				for (int col=0; col<line.length(); col++)
				{
					Point point=charToPoint.get(line.charAt(col));
					if (point!=null && level!=null)
						level[col][row]=point;
					else
						return;
				}
				row++;
			}
			if (level!=null)
				_levels.add(level);
			_levels.trimToSize();
		}
	}

	//	/**
	//	 * @return the number of levels available
	//	 */
	//	int getLevelsCount()
	//	{
	//		return _levels.size();
	//	}

	/**
	 * @param index the map number
	 * @return map number {@code index}
	 */
	public static Point[][] get(int index)
	{
		return _levels.get(index);
	}

	//	/**
	//	 * @param index the map number
	//	 * @return a deep copy of the {@code index}'s map
	//	 */
	//	public static Point[][] getDeep(int index)
	//	{
	//		Point[][] newArr=new Point[_levels.get(index).length][_levels.get(index)[0].length];
	//		for (int i=0; i<_levels.get(index).length; i++)
	//			for (int j=0; j<_levels.get(index)[i].length; j++)
	//				newArr[i][j]=new Point(_levels.get(index)[i][j]);
	//		return newArr;
	//	}
}