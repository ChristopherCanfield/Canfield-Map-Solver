package cdc.search;

public class PathNode
{
	// The underlying node that this path node wraps.
	private Node underlyingNode;
	// The parent to this node in the path.
	private Node parent;
	
	public PathNode(Node underlyingNode, Node parent)
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
}
