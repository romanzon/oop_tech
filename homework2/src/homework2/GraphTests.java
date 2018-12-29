package homework2;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;



/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

	// black-box test are inherited from super
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
		
		WhiteTestsGraph();
	}

	
	/**
	 * @effects Run white boxes tests for Graph class 
	 */
	@Test
	public void WhiteTestsGraph()
	{
		HashMap<String, WeightedNode> expectedNodes = new HashMap<>();
		
		// Create a new graph
		Graph<WeightedNode> graph = new Graph<>();
		
		// Create node "n1" that added to the graph
		WeightedNode node = new WeightedNode("n1", 1);
		this.addNode(node, graph, expectedNodes);
		
		// Adding the same node twice
		this.addNode(node, graph, expectedNodes);
		assertEquals("adding nodes twice", expectedNodes, graph.getNodes());
		
		// Adding the same node name with a different cost
		node = new WeightedNode("n1", 2);
		this.addNode(node, graph, expectedNodes);
		assertEquals("adding nodes with the same name", expectedNodes, graph.getNodes());

		assertEquals("getting children of non existed edges", null, graph.getChildren(node));
	}
	
	
	/**
	 * @modifies Add a node to a graph and fill expectedNodes
	 * @requires graph != null && node != null && expectedNodes != null
	 */
	private <Node> void addNode(Node node, Graph<Node> graph, HashMap<String, Node> expectedNodes)
	{
		graph.addNode(node);
		expectedNodes.put(node.toString(), node);
	}
	
	
	@Test
	public void WhiteTestsDFS()
	{
		// Create graph with two nodes
		Graph<WeightedNode> graph = new Graph<>();
		WeightedNode a = new WeightedNode("a", 1);
		graph.addNode(a);
		WeightedNode b = new WeightedNode("b", 2);
		graph.addNode(b);
		DfsAlgorithm dfs = new DfsAlgorithm(graph);
		assertEquals("null path of a non-exsited node", new NodeCountingPath(new WeightedNode("c", 1)), dfs.DFS(new WeightedNode("c", 1), null));
		assertEquals("null path of two unconnected nodes", null, dfs.DFS(a, b));
		assertEquals("null path of a single node", new NodeCountingPath(a), dfs.DFS(a, null));
		
		// Add edge a->b
		graph.addEdge(a, b);
		NodeCountingPath path = new NodeCountingPath(a);
		path = path.extend(b);
		assertEquals("path of two connected nodes", path, dfs.DFS(a, b));
		assertEquals("path of a to to itself", new NodeCountingPath(a), dfs.DFS(a, a));
		
		// Add back edge b->a
		graph.addEdge(b, a);
		ArrayList<WeightedNode> startNodes = new ArrayList<>(); startNodes.add(a);
		ArrayList<WeightedNode> endNodes = new ArrayList<>(); endNodes.add(b);		
		assertEquals("path of two connected nodes with back-edges", path, PathFinder.FindShortestPath(graph, startNodes, endNodes));
		assertTrue("cost of a path of two connected nodes with back-edges", 3 == PathFinder.FindShortestPath(graph, startNodes, endNodes).getCost());
		
		// Create two different paths and run PathFinder
		WeightedNode c = new WeightedNode("c", 2);
		graph.addNode(c); graph.addEdge(b, c);
		WeightedNode d = new WeightedNode("d", 3);
		graph.addNode(d); graph.addEdge(b, d);
		path = new NodeCountingPath(a);
		path = path.extend(b);
		path = path.extend(d);
		startNodes = new ArrayList<>(); startNodes.add(a);
		endNodes = new ArrayList<>(); endNodes.add(c); endNodes.add(d);
		assertEquals("shortest path", path, PathFinder.FindShortestPath(graph, startNodes, endNodes));
		endNodes = new ArrayList<>(); endNodes.add(c);
		assertTrue("cost of the first path", 5 == PathFinder.FindShortestPath(graph, startNodes, endNodes).getCost());
		endNodes = new ArrayList<>(); endNodes.add(c); endNodes.add(d);
		assertTrue("cost of the second path", 4 == PathFinder.FindShortestPath(graph, startNodes, endNodes).getCost());
	}
}
