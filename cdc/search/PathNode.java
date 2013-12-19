package cdc.search;

import java.awt.Color;
import java.awt.Graphics;

import cdc.maze.MazeLocation;
import cdc.maze.Node;

/**
 * A node in the completed search path, from the entrance to the exit.
 * This enables the appearance of the path nodes to be changed.
 * @author Christopher D. Canfield
 */
public class PathNode extends Node
{
	/**
	 * Wraps a node in a PathNode.
	 * @param underlyingNode The node that will be wrapped.
	 */
	public PathNode(Node underlyingNode)
	{
		super(new MazeLocation(underlyingNode.getRow(), underlyingNode.getColumn()),
				underlyingNode.isOpen(), underlyingNode.isEntrance(), underlyingNode.isExit(),
				underlyingNode.getEdges());
	}

	@Override
	public void draw(Graphics g, int startX, int startY, int pixelsPerNode)
	{
		// Calculate the x and y pixels.
		int pixelX = getColumn() * pixelsPerNode + startX;
		int pixelY = getRow() * pixelsPerNode + startY;
				
		g.setColor(Color.GREEN);
		// Draw the node as a green rectangle.
		g.fillRect(pixelX, pixelY, pixelsPerNode, pixelsPerNode);
	}
}
