package homework2;

import java.util.Iterator;
import java.util.LinkedList;

public class PathFinder
{
	/**
	 * @effects Private constructor
	 */
	private PathFinder()
	{
	}
	
	
	/**
	 * @return The shortest path between the start nodes and end nodes
	 * @requires startNode != null && endNode != null 
	 */
	static public Path<WeightedNode, ?> FindShortestPath(Graph<WeightedNode> graph, Path<WeightedNode, ?> startNodes, Path<WeightedNode,?> endNodes)
	{
		DfsAlgorithm dfsAlgorithm = new DfsAlgorithm(graph);
		
		Iterator<WeightedNode> iterStartNodes = startNodes.iterator();	// Get all start nodes
		Iterator<WeightedNode> iterEndNodes = endNodes.iterator();	// Get all end nodes
		Path<WeightedNode, ?> shortestPath = null;
		while (iterStartNodes.hasNext())
		{
			WeightedNode startNode = iterStartNodes.next();
			while (iterEndNodes.hasNext())
			{
				WeightedNode endNode = iterEndNodes.next();
				LinkedList<WeightedNode> linkedPath = dfsAlgorithm.DFS(startNode, endNode);
				Path<WeightedNode, ?> path = null;
				
				for (WeightedNode node : linkedPath)
				{
					if (path == null)
						path = new NodeCountingPath(node);
					else
						path = path.extend(node);
				}
				
				if (path != null)
				{
					if ((shortestPath == null) || (path.getCost() < shortestPath.getCost()))
					{
						shortestPath = path.copy();
					}
				}
			}
		}
		return shortestPath;
	}

}
