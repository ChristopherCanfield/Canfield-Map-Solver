package cdc.search;

import java.util.Queue;
import java.util.Set;

import cdc.maze.Node;

/**
 * The results of an A* search. Bundles together the path as well as the 
 * set of searched nodes.
 * @author Christopher D. Canfield
 */
public class SearchResult
{
	private Queue<Node> path;
	private Set<SearchNode> searchedNodes;
	
	public SearchResult(Queue<Node> path, Set<SearchNode> searchedNode)
	{
		this.path = path;
		this.searchedNodes = searchedNode;
	}
	
	/**
	 * The path calculated by the search.
	 * @return
	 */
	public Queue<Node> getPath()
	{
		return path;
	}
	
	/**
	 * The nodes that were explored during the search.
	 * @return
	 */
	public Set<SearchNode> getSearchedNode()
	{
		return searchedNodes;
	}
	
	/**
	 * Prints the path. Does not print the list of searched nodes.
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (Node node : path)
		{
			sb.append(node.toString()).append(" ");
		}
		return sb.toString();
	}
}
