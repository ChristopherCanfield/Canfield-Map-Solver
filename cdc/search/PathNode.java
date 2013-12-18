package cdc.search;

import java.awt.Color;
import java.awt.Graphics;

import cdc.app.Drawable;
import cdc.maze.Node;

public class PathNode implements Drawable
{
	// The underlying node that this path node wraps.
	private Node underlyingNode;
	
	public PathNode(Node underlyingNode)
	{
		this.underlyingNode = underlyingNode;
	}

	@Override
	public void draw(Graphics g, int startX, int startY, int pixelsPerNode)
	{
		// Calculate the x and y pixels.
		int pixelX = underlyingNode.getColumn() * pixelsPerNode;
		int pixelY = underlyingNode.getRow() * pixelsPerNode;
				
		g.setColor(Color.GREEN);
		// Draw the node as a green rectangle.
		g.fillRect(pixelX, pixelY, pixelsPerNode, pixelsPerNode);
	}

	@Override
	public String toString()
	{
		return underlyingNode.toString();
	}
}
