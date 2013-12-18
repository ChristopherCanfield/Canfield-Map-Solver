package cdc.search;

import java.util.Queue;

import cdc.maze.Node;

/**
 * Contains a method that implements the A* search algorithm.
 * @author Christopher D. Canfield
 */
public class Search
{
	/**
	 * Performs an A* search between the start and end node. Returns a a SearchResult
	 * object, which contains a queue of nodes representing the path from 
	 * start to end, as well as the set of searched nodes.
	 * @param start The initial node.
	 * @param end The final node.
	 * @param heuristic The heuristic function to use when calculating the A* "g" value.
	 * @return A queue of nodes representing the path from the start node to
	 * the end node.
	 */
	public static SearchResult aStar(Node start, Node end, Heuristic heuristic)
	{
		// TODO: implement this...
		return null;
	}
}
