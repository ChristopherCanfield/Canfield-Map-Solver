package cdc.search;

import java.awt.Color;
import java.awt.Graphics;

import cdc.app.Drawable;
import cdc.maze.Node;

/**
 * Wraps a maze node with additional information required when searching
 * for a path.
 * @author Christopher D. Canfield
 */
public class SearchNode implements Drawable
{
	// The underlying node that this search node wraps.
	private Node underlyingNode;
	// The parent to this node in the path.
	private Node parent;
	
	public SearchNode(Node underlyingNode, Node parent)
	{
		this.underlyingNode = underlyingNode;
		this.parent = parent;
	}
	
	/**
	 * Returns the underlying node.
	 * @return The underlying node.
	 */
	public Node getNode()
	{
		return underlyingNode;
	}
	
	/**
	 * Returns the parent of this node in the path.
	 * @return This node's parent.
	 */
	public Node getParent()
	{
		return parent;
	}

	@Override
	public void draw(Graphics g, int startX, int startY, int pixelsPerNode)
	{
		// Calculate the x and y pixels.
		int pixelX = getNode().getColumn() * pixelsPerNode;
		int pixelY = getNode().getRow() * pixelsPerNode;
		
		g.setColor(Color.BLUE);
		// Draw the node as a blue rectangle.
		g.fillRect(pixelX, pixelY, pixelsPerNode, pixelsPerNode);
	}
}
