package cdc.search;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

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
	 * @param heuristic The heuristic function to use when calculating the A* "h" value.
	 * @return A queue of nodes representing the path from the start node to
	 * the end node. Note that this will return null if no path from the start to end
	 * can be found.
	 */
	public static SearchResult aStar(Node start, Node end, Heuristic heuristic)
	{
		// Instantiate the frontier priority queue and searched set. The frontier 
		// is used to identify nodes that are at the edge of the explored zone. The
		// lowest cost of these nodes will then have their edges searched.
		// The searched set ensures that a previously explored node is not re-explored.
		PriorityQueue<SearchNode> frontier = new PriorityQueue<SearchNode>();
		Set<SearchNode> searched = new HashSet<SearchNode>();
		
		// Wrap the start node in the SearchNode decorator.
		SearchNode startNode = 
				new SearchNode(start, null, 0, heuristic.calculateCost(start, end));
		
		// Add the start node to the frontier priority queue and the searched set.
		// The searched set ensures that the start node isn't searched
		searched.add(startNode);
		frontier.add(startNode);
		
		// Loop through the frontier nodes. If all nodes are searched and
		// no path to the exit is found, then no path is possible, and null
		// will be returned.
		while (!frontier.isEmpty())
		{
			SearchNode lowestCost = frontier.remove();
			
			// Check if the lowest cost equals the end node. If it does, 
			// the algorithm has reached the end, so construct the path using
			// the linked list of parents, and return the result.
			if (lowestCost.equals(end))
			{
				Queue<Node> path = constructPath(lowestCost, start);
				// Return the path and the searched set, so both can be displayed.
				SearchResult result = new SearchResult(path, searched);
				return result;
			}
			
			// Iterate through each node that is connected to the current lowest
			// cost node.
			for (Node edge : lowestCost.getEdges())
			{
				// Calculate the g (from start) cost of the lowest cost node by 
				// taking the parent's g cost and adding 1 to it.
				int g = 1;
				if (lowestCost.getParent() != null)
				{
					g += lowestCost.getParent().getG();
				}

				// Calculate the h (heuristic) cost of the lowest cost node
				// to the end node.
				int h = heuristic.calculateCost(edge, end);
				
				// Wrap the edge in the SearchNode decorator, so the parent and 
				// costs can be stored with it.
				SearchNode edgeSearchNode = new SearchNode(edge, lowestCost, g, h);
				
				// Determine if the edge has already been searched.
				if (!searched.contains(edgeSearchNode))
				{
					// Add the edge to the frontier priority queue, if it
					// is open (i.e., passable).
					if (edgeSearchNode.isOpen())
					{
						frontier.add(edgeSearchNode);
					}
					
					// Add the edge to the searched set.
					searched.add(edgeSearchNode);
				}
			}
		}
		
		// Return null if no path can be found from 
		// the start node to the end node.
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
			path.addFirst(new PathNode(currentPathNode));
			
			// Set the current node reference to the parent of the 
			// node that was just added.
			currentPathNode = currentPathNode.getParent();
		}
		return path;
	}
}
