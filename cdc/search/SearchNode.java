package cdc.search;

import java.awt.Color;
import java.awt.Graphics;

import cdc.maze.MazeLocation;
import cdc.maze.Node;

/**
 * Wraps a maze node with additional information required when searching
 * for a path.
 * @author Christopher D. Canfield
 */
public class SearchNode extends Node implements Comparable<SearchNode>
{
	// The parent to this node in the path.
	private SearchNode parent;
	
	// The node's cumulative cost from the start node to this node.
	private int g;
	// The node's heuristic cost to the end node.
	private int h;
	
	/**
	 * Wraps a Node with additional information needed during the A* search.
	 * @param underlyingNode The node that will be wrapped.
	 * @param parent The node that lead to this node.
	 * @param g The node's cumulative cost from the start node to this node.
	 * @param h The node's heuristic cost to the end node.
	 */
	public SearchNode(Node underlyingNode, SearchNode parent, int g, int h)
	{
		super(new MazeLocation(underlyingNode.getRow(), underlyingNode.getColumn()), 
				underlyingNode.isOpen(), underlyingNode.isEntrance(), underlyingNode.isExit(),
				underlyingNode.getEdges());
		this.parent = parent;
		this.g = g;
		this.h = h;
	}
	
	/**
	 * Returns the parent of this node in the path.
	 * @return This search node's parent.
	 */
	public SearchNode getParent()
	{
		return parent;
	}
	
	/**
	 * Returns the cumulative cost from the start node to this node.
	 * @return The cumulative cost from the start node to this node.
	 */
	public int getG()
	{
		return g;
	}
	
	/**
	 * Returns the heuristic cost from the end node to this node.
	 * @return The heuristic cost from the end node to this node.
	 */
	public int getH()
	{
		return h;
	}

	@Override
	public void draw(Graphics graphics, int startX, int startY, int pixelsPerNode)
	{
		// Calculate the x and y pixels.
		int pixelX = getColumn() * pixelsPerNode + startX;
		int pixelY = getRow() * pixelsPerNode + startY;
		
		graphics.setColor(Color.BLUE);
		// Draw the node as a blue rectangle.
		graphics.fillRect(pixelX, pixelY, pixelsPerNode, pixelsPerNode);
	}
	
	
	/**
	 * The compareTo method is required for the A* algorithm's priority queue.
	 */
	@Override
	public int compareTo(SearchNode other)
	{
		int cost = g + h;
		int otherCost = other.g + other.h;
		return (cost < otherCost) ? -1 : 
				(cost > otherCost) ? 1 : 0;
	}
}
