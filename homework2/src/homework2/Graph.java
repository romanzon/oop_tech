package homework2;

import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Graph<Node> {
	
	/** A set of all graph's nodes **/
	private HashMap<String, Node> nodes;
	
	/** A set of all graph's edges **/
	private HashMap<Node, PriorityQueue<Node>> edges;
	
	
	/**
	 * @effects Create a new graph
	 */
	public Graph()
	{
		nodes = new HashMap<>();
		edges = new HashMap<>();
	}
	
	
	/**
	 * @effects Add a new node to the graph
	 * @requires node != null && !this.m_nodes.contains(node)
	 */
	public void addNode(Node node)
	{
		this.nodes.put(node.toString(), node);
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
		PriorityQueue<Node> nodes;
		if (!this.isEdgeContained(parentNode))
			nodes = new PriorityQueue<>(Collections.reverseOrder());
		else
			nodes = this.edges.get(parentNode);
		
		nodes.add(childNode);
		this.edges.put(parentNode, nodes);
	}
	
	
	/**
	 * @return True if the graph contains the node.
	 * @requires node != null
	 */
	public boolean isNodeContained(Node node)
	{
		return this.nodes.containsKey(node.toString());
	}
	
	
	/**
	 * @return True if the graph contains the edge.
	 * @requires parentNode != null && childNode != null
	 */
	public boolean isEdgeContained(Node parentNode, Node childNode)
	{
		if (!this.isEdgeContained(parentNode))
			return false;
		else
			return this.edges.get(parentNode).contains(childNode);
	}
	

	/**
	 * @return True if the graph contains the edge.
	 * @requires parentNode != null
	 */
	public boolean isEdgeContained(Node parentNode)
	{
		return this.edges.containsKey(parentNode);
	}
	
	
	/**
	 * @return All graph nodes
	 */
	public HashMap<String, Node> getNodes()
	{
		return this.nodes;
	}
	
	
	/**
	 * @effects Get all parent's children in a reversed order
	 * @requires parentNodeName != null &&
	 * 			 this.isNodeContained(parentNodeName) &&
	 * 			 this.isEdgeContained(parentNodeName)
	 */	
	public PriorityQueue<Node> getChildren(Node parentNode)
	{
		return this.edges.get(parentNode);
	}
}
