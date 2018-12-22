package homework2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class DfsAlgorithm
{
	/** The visited node list **/
	private LinkedList<WeightedNode> visited;
	
	/** The graph to apply the algorithm **/
	private Graph<WeightedNode> graph; 
		
	/**
	 * @effects Find the path between startNode and endNode in the graph 
	 * @requires graph != null && startNode != null and endNode != null &&
	 * 			 graph.isNodeContained(startNode) && graph.isNodeContained(endNode)
	 */
	public DfsAlgorithm(Graph<WeightedNode> graph)
	{
		this.graph = graph;
	}
	
	
	/**
	 * @modifies Color the nodes
	 * @requires nodes != null
	 */
	private static void Color(HashSet<WeightedNode> nodes, String color)
	{
		for (WeightedNode node : nodes)
		{
			node.setColor(color);
		}
	}
	

	/**
	 * @modifies Color the node
	 * @requires node != null
	 */
	private static void Color(WeightedNode node, String color)
	{
		node.setColor(color);
	}
	
	
	/**
	 * @return The path between the startNode and endNode. 
	 * 		   If endNode == null then the algorithm runs all over the graph from the startNode 
	 * @modifies Color the graph nodes to white
	 * @requires startNode != null
	 * 			 this.m_graph.isNodeContained(startNode) && this.m_graph.isNodeContained(endNode)
	 */
	public LinkedList<WeightedNode> DFS(WeightedNode startNode, WeightedNode endNode)
	{
		this.visited = new LinkedList<>();
		
		// Color all nodes to white
		DfsAlgorithm.Color(this.graph.getNodes(), "White");
		boolean doesPathExist = this.doesPathExist(startNode, endNode);
		DfsAlgorithm.Color(this.graph.getNodes(), "White");
		
		if (doesPathExist)
			return this.visited;
		else
			return null;
	}
	
	
	/**
	 * @return True if a path exists between startNode and endNode
	 * @requires startNode != null && endNode != null &&
	 * 			 this.m_graph.isNodeContained(startNode) && this.m_graph.isNodeContained(endNode)
	 */
	private boolean doesPathExist(WeightedNode startNode, WeightedNode endNode)
	{
		// The priority queue contains nodes with priority equal to the cost of the nodes. If
		// two nodes shares the same cost, choose them by alphabetical order (higher one
		// first). Otherwise, choose randomly
		PriorityQueue<WeightedNode> children = this.graph.getChildren(startNode);
		
		// add the starting Node to the visited linked list
		this.visited.add(startNode);
		
		// each node is marked by one of three colors. color[v] - the color of node v
		// white - an unvisited node
		// gray  - a visited node
		// black - a node whose adjacency list has been examined
		// 		   completely (whose descendants were all searched).
		
		DfsAlgorithm.Color(startNode, "Grey");
		
		// Clear back edges count before running 
		startNode.clearBackEdgeCounts();
		// Search for back edges
		for (WeightedNode child : children)
		{
			if (child.getColor().equals("Grey"))
			{	// Parent child. Increment back edge
				startNode.incrementBackEdgesCount();
			}			
		}
		
		if (startNode.equals(endNode))
			return true;
		
		for (WeightedNode child : children)
		{
			if (child.getColor().equals("White"))
			{	// Child that hasn't been visited
				if (this.doesPathExist(child, endNode))
					return true;
			}		
		}
		
		DfsAlgorithm.Color(startNode, "Black");
		
		if (endNode == null)
			return true;	// When there is no endNode, the algorithm keeps running all over the graph 
		else
			return false;
	}
}
