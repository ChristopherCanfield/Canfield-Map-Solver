package cdc.maze;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import cdc.app.Drawable;

/**
 * A square in the maze.
 * @author Christopher D. Canfield
 */
public class Node implements Drawable
{
	// The node's (row, column) location in the maze.
	private MazeLocation location;
	
	// Whether the node is open & passable.
	private boolean open;
	// Whether the node is the maze's entrance.
	private boolean entrance;
	// Whether the node is the maze's exit.
	private boolean exit;
	
	// The node's list of edges (connections to other nodes).
	private List<Node> edges;
	
	public Node(MazeLocation location, boolean isOpen, 
			boolean isEntrance, boolean isExit)
	{
		this(location, isOpen, isEntrance, isExit, null);
	}
	
	public Node(MazeLocation location, boolean isOpen, 
			boolean isEntrance, boolean isExit, List<Node> edges)
	{
		this.location = location;
		this.open = isOpen;
		this.entrance = isEntrance;
		this.exit = isExit;
		this.edges = (edges == null) ? new ArrayList<Node>() : edges;
	}
	
	/**
	 * Returns the row.
	 * @return The row number.
	 */
	public int getRow()
	{
		return location.getRow();
	}
	
	/**
	 * Returns the column.
	 * @return The column number.
	 */
	public int getColumn()
	{
		return location.getColumn();
	}
	
	/**
	 * Whether this maze node is open and passable.
	 * @return true if this node is passable, or false if not.
	 */
	public boolean isOpen()
	{
		return open;
	}
	
	/**
	 * Whether this maze node is the maze entrance.
	 * @return true if this maze node is the entrance.
	 */
	public boolean isEntrance()
	{
		return entrance;
	}
	
	/**
	 * Whether this maze node is the maze exit.
	 * @return true if this maze node is the exit.
	 */
	public boolean isExit()
	{
		return exit;
	}
	
	/**
	 * Returns the list of edges.
	 * @return The list of edges.
	 */
	public List<Node> getEdges()
	{
		return edges;
	}
	
	/**
	 * Adds a connection to another node.
	 * @param edge The edge to add.
	 * @return A reference to this Node.
	 */
	public Node addEdge(Node edge)
	{
		edges.add(edge);
		return this;
	}

	@Override
	public void draw(Graphics g, int startX, int startY, int pixelsPerNode)
	{
		// Calculate the x and y pixels.
		int pixelX = getColumn() * pixelsPerNode + startX;
		int pixelY = getRow() * pixelsPerNode + startY;
		
		// If the square is not open, fill it with black. If it is the entrance,
		// fill it with light green. If it is the exit, fill it with dark green.
		if (!open || isEntrance() || isExit())
		{
			Color fillColor = isEntrance() ? new Color(196, 255, 170) : 
					isExit() ? new Color(38, 127, 0) : Color.BLACK;
			g.setColor(fillColor);
			g.fillRect(pixelX, pixelY, pixelsPerNode, pixelsPerNode);
		}
		
		// Set the outline color to white if the square is  open, 
		// or black if it is open.
		Color color = !open ? Color.WHITE : Color.BLACK;
		g.setColor(color);
		
		// Draw an outline around the node's square.
		g.drawRect(pixelX, pixelY, pixelsPerNode, pixelsPerNode);
	}
	
	
	// equals & hashCode are required by the A* algorithm.
	
	@Override
	public boolean equals(Object other)
	{
		if (!(other instanceof Node))
		{
			return false;
		}
		
		Node otherNode = (Node)other;
		return (this.getRow() == otherNode.getRow() &&
				this.getColumn() == otherNode.getColumn());
	}
	
	@Override
	public int hashCode()
	{
		// Adapted from J. Bloch, "Effective Java", 2nd edition, 2008.
		int result = 17;
		result = 31 * result + getRow();
		result = 31 * result + getColumn();
		return  result;
	}
}
