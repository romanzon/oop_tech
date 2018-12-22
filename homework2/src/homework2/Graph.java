package homework2;

import java.util.ArrayList;
import java.util.HashSet;

public class Graph<Node> {
	
	/** A set of all graph's nodes **/
	private HashSet<Node> nodes = new HashSet<>();
	
	/** A set of all graph's edges **/
	private HashSet<Edge<Node>> edges = new HashSet<>();
	
	
	/**
	 * @effects Create a new graph
	 */
	public Graph()
	{
	
	}
	
	
	/**
	 * @effects Add a new node to the graph
	 * @requires node != null && !this.m_nodes.contains(node)
	 */
	public void addNode(Node node)
	{
		this.nodes.add(node);
	}
	
	
	/**
	 * @effects Add a new edge to the graph between parentNode and childNode 
	 * @requires parentNode != null &&
	 * 			 childNode != null &&
	 * 			 this.isNodeContained(parentNode) &&
	 * 			 this.isNodeContained(childNode) &&
	 * 			 !this.isEdgeContained(parentNode,childNode)
	 */
	public void addEdge(Node parentNode, Node childNode)
	{
		this.edges.add(new Edge<Node>(parentNode, childNode));
	}
	
	
	/**
	 * @return True if the graph contains the node.
	 * @requires node != null
	 */
	public boolean isNodeContained(Node node)
	{
		return this.nodes.contains(node);
	}
	
	
	/**
	 * @return True if the graph contains the edge.
	 * @requires parentNode != null && childNode != null
	 */
	public boolean isEdgeContained(Node parentNode, Node childNode)
	{
		return edges.contains(new Edge<Node>(parentNode, childNode));
	}
	

	/**
	 * @return True if the graph contains the edge.
	 * @requires parentNode != null
	 */
	public boolean isEdgeContained(Node parentNode)
	{
		for (Edge<Node> edge : edges) 
		{
			if (edge.getParent().equals(parentNode))
			{
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * @return All graph nodes
	 */
	public ArrayList<Node> getNodes()
	{
		return new ArrayList<Node>(this.nodes);
	}
	
	
	/**
	 * @effects Get all parent's children names
	 * @requires parentNodeName != null &&
	 * 			 this.isNodeContained(parentNodeName) &&
	 * 			 this.isEdgeContained(parentNodeName)
	 */	
	public ArrayList<Node> getChildren(Node parentNode)
	{
		ArrayList<Node> children = new ArrayList<>();
		for (Edge<Node> edge : edges) 
		{
			if (edge.getParent().equals(parentNode))
				children.add(edge.getChild());
		}
		return children;
	}
}


class Edge <Node>
{
	/** The parent node **/
	private final Node parentNode;
	
	/** The child node **/
	private final Node childNode;
	
	
	/**
	 * @effect Create a new edge between parent node and child node
	 * @requires parentNode != null && childNode != null && parentNode != childNode
	 */
	public Edge(Node parentNode, Node childNode)
	{
		this.parentNode = parentNode;
		this.childNode = childNode;
		this.checkRep();
	}
	
	
	/**
	 * @return The parent
	 */
	public Node getParent()
	{
		return this.parentNode;
	}
	
	
	/**
	 * @return The child
	 */
	public Node getChild()
	{
		return this.childNode;
	}
	
	
	/**
	 * Standard equality operation.
	 * @return true iff o.instaceOf(Edge) &&
	 *         (this.parentNode.eqauls(o.getParent()) && (this.childNode == o.getChild()))
	 */
	public boolean equals(Object o) {
    	if (o instanceof Edge<?>) 
    	{
      		Edge<?> other = (Edge<?>)o;
      		return (this.parentNode.equals(other.getParent()) &&
				    this.childNode.equals(other.getChild()));
    	}
    	return false;
  	}
	
	
	/**
	 * @effects Check that the parent doesn't equal to the child.
	 * @throws AssertionError if representation invariant is violated.
	 */
	private void checkRep()
	{
		assert (this.parentNode != null) 				:"Parent musn't be null";
		assert (this.childNode != null)					:"Child musn't be null";
		//assert (!this.parentNode.equals(this.childNode)) :"Parent must be different from the child";
	}
}
