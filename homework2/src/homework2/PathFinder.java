package homework2;

import java.util.ArrayList;

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
	static public Path<WeightedNode, ?> FindShortestPath(Graph<WeightedNode> graph, ArrayList<WeightedNode> startNodes, ArrayList<WeightedNode> endNodes)
	{
		DfsAlgorithm dfsAlgorithm = new DfsAlgorithm(graph);
		
		Path<WeightedNode, ?> shortestPath = null;
		for (WeightedNode startNode : startNodes)
		{
			for (WeightedNode endNode : endNodes)
			{
				Path<WeightedNode, ?> path = dfsAlgorithm.DFS(startNode, endNode);
				
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
