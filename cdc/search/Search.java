package cdc.search;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import canfield.DirHelper;
import canfield.SearchNode;
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
		PriorityQueue<SearchNode> frontier = new PriorityQueue<SearchNode>();
		Set<SearchNode> searched = new HashSet<SearchNode>();
		
		final int costPerNode = 4;
		SearchNode startNode = new SearchNode(start, null, initialDirection,
				0, heuristic.calculateCost(start, target, costPerNode));
		
		searched.add(startNode);
		frontier.add(startNode);
		
		while (!frontier.isEmpty())
		{
			SearchNode lowestCost = frontier.remove();
			
			if (lowestCost.getNode().equals(target))
			{
				return constructPath(lowestCost, start);
			}
			
			for (Node edge : lowestCost.getNode().getEdges())
			{
				int edgeDirection = 
						DirHelper.getDirectionToTarget(lowestCost.getDirection(), lowestCost.getNode(), edge.row, edge.column);
				int edgeRotateCost = DirHelper.getRotationCost(lowestCost.getDirection(), edgeDirection);
				
				int h = heuristic.calculateCost(edge, target, costPerNode);
				
				int riskCost = (edge.isSafe()) ? 0 : edge.isUnsafe() ? 9999 : 16;
				int strategyCost = getStrategyCost(edge, strategy);
				
				int previousG = 0;
				if (lowestCost.getParentNode() != null)
				{
					// TODO: double check this.
					previousG = lowestCost.getParentNode().getGCost();
				}
				// Increases the cost if the edge's row is not in the preferred row.
				int rowPreferenceCost = 0;
				if (preferredRow != NO_ROW_PREFERENCE)
				{
					rowPreferenceCost = (edge.row != preferredRow) ? 8 : 0;
				}
				
				int g = costPerNode + previousG + edgeRotateCost + riskCost + strategyCost + rowPreferenceCost;
				
				SearchNode edgeSearchNode = new SearchNode(edge, lowestCost, edgeDirection, h, g);
				if (!searched.contains(edgeSearchNode))
				{
					if (!edgeSearchNode.)
					{
						frontier.add(edgeSearchNode);
					}
					searched.add(edgeSearchNode);
				}
			}
		}
		return null;
	}
	
	/**
	 * Constructs a path from the final PathNode back to the start node. This
	 * should be used with the Queue<Path> returned by the A* algorithm.
	 * @param finalNodeInPath The last node in the path returned by the A* algorithm.
	 * @param startNode The start node (the entrance node).
	 * @return The path, starting at the start node to the end node.
	 */
	private static Queue<Node> constructPath(SearchNode finalNodeInPath, Node startNode)
	{
		Deque<Node> path = new ArrayDeque<Node>();
		SearchNode currentPathNode = finalNodeInPath;
		
		// Loop through the nodes until there are no more parents.
		while (currentPathNode != null)
		{
			// Add the current node to the path.
			path.addFirst(currentPathNode.getNode());
			
			// Set the current node reference to the parent of the 
			// node that was just added.
			currentPathNode = currentPathNode.getParent();
		}
		return path;
	}
}
