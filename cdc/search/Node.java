package cdc.search;

import java.util.ArrayList;
import java.util.List;

/**
 * A square in the maze.
 * @author Christopher D. Canfield
 */
public class Node
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
		this.location = location;
		this.open = isOpen;
		this.entrance = isEntrance;
		this.exit = isExit;
		this.edges = new ArrayList<Node>();
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
}
