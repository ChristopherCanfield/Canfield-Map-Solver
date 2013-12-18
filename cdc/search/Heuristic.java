package cdc.search;

/**
 * Interface for A* search heuristics.
 * @author Christopher D. Canfield
 */
public interface Heuristic
{
	/**
	 * Calculates the distance cost between two nodes.
	 * @param start The start node.
	 * @param target The end node.
	 * @return
	 */
	public int calculateCost(Node start, Node end);
}
