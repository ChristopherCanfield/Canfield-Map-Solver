package cdc.search;

import java.awt.Color;
import java.awt.Graphics;

import cdc.app.Drawable;
import cdc.maze.MazeLocation;
import cdc.maze.Node;

/**
 * Wraps a maze node with additional information required when searching
 * for a path.
 * @author Christopher D. Canfield
 */
public class SearchNode extends Node
{
	// The parent to this node in the path.
	private SearchNode parent;
	
	public SearchNode(Node underlyingNode, SearchNode parent)
	{
		super(new MazeLocation(underlyingNode.getRow(), underlyingNode.getColumn()), 
				underlyingNode.isOpen(), underlyingNode.isEntrance(), underlyingNode.isExit(),
				underlyingNode.getEdges());
		this.parent = parent;
	}
	
	/**
	 * Returns the parent of this node in the path.
	 * @return This search node's parent.
	 */
	public SearchNode getParent()
	{
		return parent;
	}

	@Override
	public void draw(Graphics g, int startX, int startY, int pixelsPerNode)
	{
		// Calculate the x and y pixels.
		int pixelX = getColumn() * pixelsPerNode;
		int pixelY = getRow() * pixelsPerNode;
		
		g.setColor(Color.BLUE);
		// Draw the node as a blue rectangle.
		g.fillRect(pixelX, pixelY, pixelsPerNode, pixelsPerNode);
	}
}
