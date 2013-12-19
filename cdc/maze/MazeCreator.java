package cdc.maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Contains the method for creating a maze.
 * @author Christopher D. Canfield
 */
public class MazeCreator
{
	private enum SearchFor { ENTRANCE, EXIT };
	
	/** The number of rows in the maze. **/
	public static final int MAZE_ROWS = 10;
	/** The number of columns in the maze. **/
	public static final int MAZE_COLUMNS = 10;
	
	/**
	 * Load the maze file. The file should be named "mazes.txt", and be located
	 * in this application's current directory. Each maze should be 10x10 characters,
	 * and contain the following characters per row: W (wall), O (open), E (entrance),
	 * X (exit). Mazes should have exactly one entrance and one exit. Each maze 
	 * definition in the file should be separated by a line. The first line in the
	 * file is ignored.
	 * @param mazeNumber The maze number in the maze file, starting with 1.
	 * @return The instantiated maze.
	 */
	public static Node[][] loadMaze(int mazeNumber)
	{
		try (FileReader reader = new FileReader("mazes.txt"))
		{
			Node[][] maze = readMaze(reader, mazeNumber);
			processEdges(maze);
			return maze;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.toString());
		}
	}
	
	/**
	 * Reads the maze from the FileReader object, processes the characters,
	 * and loads Nodes based on the characters into the maze array.
	 * @param fileReader A FileReader object that points to the mazes.txt file.
	 * @param mazeNumber The number of the maze definition in the file, starting
	 * with 1.
	 * @return The instantiated maze array.
	 * @throws IOException If there is a problem processing the maze array.
	 */
	private static Node[][] readMaze(FileReader fileReader, int mazeNumber) throws IOException
	{
		Node[][] maze = new Node[MAZE_ROWS][MAZE_COLUMNS];
		BufferedReader reader = new BufferedReader(fileReader);
		skipLines(reader, mazeNumber);
		
		for (int row = 0; row < maze[0].length; ++row)
		{
			final String line = reader.readLine();
			for (int column = 0; column < maze.length; ++column)
			{
				boolean open = true;
				boolean entrance = false;
				boolean exit = false;
				
				final char character = line.charAt(column);
				if (character == 'W')
				{
					open = false;
				}
				else if (character == 'E')
				{
					entrance = true;
				}
				else if (character == 'X')
				{
					exit = true;
				}
				
				MazeLocation location = new MazeLocation(row, column);
				maze[row][column] = new Node(location, open, entrance, exit);
			}
		}
		return maze;
	}
	
	/**
	 * Skips the specified number of lines in a file.
	 * @param reader A BufferedReader pointing to the mazes.txt file.
	 * @param mazeNumber The number of the maze definition in the file, starting
	 * with 1.
	 * @throws IOException If there is a problem processing the maze array.
	 */
	private static void skipLines(BufferedReader reader, int mazeNumber) throws IOException
	{
		for (int i = 0; i < (mazeNumber - 1) * MAZE_ROWS + (1 * mazeNumber); ++i)
		{
			reader.readLine();
		}
	}
	
	/**
	 * Connects nodes to each other.
	 * @param maze Reference to the maze array.
	 */
	private static void processEdges(Node[][] maze)
	{
		// Iterate through all nodes in the maze, and add connections
		// where applicable.
		for (int row = 0; row < maze[0].length; ++row)
		{
			for (int col = 0; col < maze.length; ++col)
			{
				if (row > 0)
				{
					maze[row][col].addEdge(maze[row-1][col]);
				}
				if (row < maze.length - 1)
				{
					maze[row][col].addEdge(maze[row+1][col]);
				}
				if (col > 0)
				{
					maze[row][col].addEdge(maze[row][col-1]);
				}
				if (col < maze[0].length - 1)
				{
					maze[row][col].addEdge(maze[row][col+1]);
				}
			}
		}
	}
	
	/**
	 * Returns the entrance to the maze.
	 * @param maze Reference to the maze.
	 * @return The entrance to the maze.
	 * @throws RuntimeException If the entrance is not found.
	 */
	public static Node getEntrance(Node[][] maze)
	{
		return findNode(maze, SearchFor.ENTRANCE);
	}
	
	/**
	 * Returns the exit from the maze.
	 * @param maze Reference to the maze.
	 * @return The exit from the maze.
	 * @throws RuntimeException If the exit is not found.
	 */
	public static Node getExit(Node[][] maze)
	{
		return findNode(maze, SearchFor.EXIT);
	}
	
	/**
	 * Finds the specified node in the maze.
	 * @param maze Reference to the maze.
	 * @param searchItem The item to search for.
	 * @return The found node.
	 * @throws RuntimeException If the search item is not found.
	 */
	private static Node findNode(Node[][] maze, SearchFor searchItem)
	{
		for (Node[] m : maze)
		{
			for (Node node : m)
			{
				if (searchItem == SearchFor.ENTRANCE && node.isEntrance() ||
						searchItem == SearchFor.EXIT && node.isExit())
				{
					return node;
				}
			}
		}
		throw new RuntimeException("Maze is missing an exit or entrance.");
	}
	

	// Methods for testing //
	
	/**
	 * Generates a test maze where all squares are open.
	 * @param entrance The location of the entrance.
	 * @param exit The location of the exit.
	 * @return A test maze where all squares are open.
	 */
	public static Node[][] generateAllOpen(MazeLocation entrance, MazeLocation exit)
	{
		Node[][] maze = new Node[MAZE_ROWS][MAZE_COLUMNS];
		for (int row = 0; row < maze[0].length; ++row)
		{
			for (int col = 0; col < maze.length; ++col)
			{
				maze[row][col] = new Node(new MazeLocation(row, col), true, false, false);
			}
		}
		maze[entrance.getRow()][entrance.getColumn()] = new Node(entrance, true, true, false);
		maze[exit.getRow()][exit.getRow()] = new Node(exit, true, false, true);
		processEdges(maze);
		
		return maze;
	}
	
	/**
	 * Generates a test maze where all squares are closed.
	 * @return A test maze where all squares are closed.
	 */
	public static Node[][] generateAllClosed()
	{
		Node[][] maze = new Node[MAZE_ROWS][MAZE_COLUMNS];
		for (int row = 0; row < maze[0].length; ++row)
		{
			for (int col = 0; col < maze.length; ++col)
			{
				maze[row][col] = new Node(new MazeLocation(row, col), false, false, false);
			}
		}
			
		return maze;
	}
}
