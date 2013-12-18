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
}
