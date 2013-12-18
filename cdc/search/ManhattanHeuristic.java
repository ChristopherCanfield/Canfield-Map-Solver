package cdc.search;

import cdc.maze.Node;

/**
 * Heuristic used to calculate the Manhattan distance between two squares.
 * The Manhattan distance provides the grid-based distance between the squares.
 * @author Christopher D. Canfield
 */
public class ManhattanHeuristic implements Heuristic
{
	@Override
	public int calculateCost(Node start, Node end)
	{
		int rowDiff = Math.abs(start.getRow() - end.getRow());
		int colDiff = Math.abs(start.getColumn() - end.getColumn());
		return (rowDiff + colDiff);
	}	
}