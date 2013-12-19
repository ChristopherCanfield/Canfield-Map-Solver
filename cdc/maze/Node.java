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
		
		// If the square is not open, fill it with black.
		if (!open)
		{
			g.setColor(Color.BLACK);
			g.fillRect(pixelX, pixelY, pixelsPerNode, pixelsPerNode);
		}
		
		// Set the outline color to gray if the node is an entrance or exit, 
		// white if the square is not open, or black if it is open.
		Color color = (entrance || exit) ? Color.MAGENTA : !open ? Color.WHITE : Color.BLACK;
		g.setColor(color);
		
		// Draw an outline around the node's square.
		g.drawRect(pixelX, pixelY, pixelsPerNode, pixelsPerNode);
	}
}
