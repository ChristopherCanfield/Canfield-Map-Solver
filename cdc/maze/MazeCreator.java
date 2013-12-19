package cdc.maze;

import java.util.Random;

/**
 * Contains the method for creating a maze.
 * @author Christopher D. Canfield
 */
public class MazeCreator
{
	private enum SearchFor { ENTRANCE, EXIT };
	
	/**
	 * Generate the maze.
	 * @param seed The seed for the random number generator, which is used to 
	 * generate the maze.
	 * @return The array of nodes representing a maze.
	 */
	public static Node[][] generateMaze(int seed)
	{
		Random rand = new Random(seed);
		Node[][] maze = new Node[10][10];
		
		// TODO: implement this...
		return null;
	}
	
	/**
	 * Connect nodes to each other.
	 * @param maze
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
	
	public static Node getEntrance(Node[][] maze)
	{
		return findNode(maze, SearchFor.ENTRANCE);
	}
	
	public static Node getExit(Node[][] maze)
	{
		return findNode(maze, SearchFor.EXIT);
	}
	
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
		return null;
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
		Node[][] maze = new Node[10][10];
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
		Node[][] maze = new Node[10][10];
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
