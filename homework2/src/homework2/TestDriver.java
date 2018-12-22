package homework2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/**
 * This class implements a testing driver which reads test scripts
 * from files for testing Graph and PathFinder.
 */
public class TestDriver {

	// String -> Graph: maps the names of graphs to the actual graph
  	private final Map<String, Graph<WeightedNode>> graphs = new HashMap<>();
  	// String -> WeightedNode: maps the names of nodes to the actual node
  	private final Map<String,WeightedNode> nodes = new HashMap<>();
	private final BufferedReader input;
  	private final PrintWriter output;


  	/**
  	 * Creates a new TestDriver.
     * @requires r != null && w != null
     * @effects Creates a new TestDriver which reads command from
     * <tt>r</tt> and writes results to <tt>w</tt>.
     */
  	public TestDriver(Reader r, Writer w) {
    	input = new BufferedReader(r);
    	output = new PrintWriter(w);
  	}


  	/**
  	 * Executes the commands read from the input and writes results to the
  	 * output.
     * @effects Executes the commands read from the input and writes
     * 		    results to the output.
     * @throws IOException - if the input or output sources encounter an
     * 		   IOException.
     */
  	public void runTests() throws IOException {

    	String inputLine;
		while ((inputLine = input.readLine()) != null) {
			// echo blank and comment lines
      		if (inputLine.trim().length() == 0 ||
      		    inputLine.charAt(0) == '#') {
        		output.println(inputLine);
        		continue;
      		}

      		// separate the input line on white space
      		StringTokenizer st = new StringTokenizer(inputLine);
      		if (st.hasMoreTokens()) {
        		String command = st.nextToken();

        		List<String> arguments = new ArrayList<>();
        		while (st.hasMoreTokens())
          			arguments.add(st.nextToken());

        		executeCommand(command, arguments);
      		}
    	}

    	output.flush();
  	}


  	private void executeCommand(String command, List<String> arguments) {

    	try {
      		if (command.equals("CreateGraph")) {
        		createGraph(arguments);
      		} else if (command.equals("CreateNode")) {
        		createNode(arguments);
      		} else if (command.equals("AddNode")) {
        		addNode(arguments);
      		} else if (command.equals("AddEdge")) {
        		addEdge(arguments);
      		} else if (command.equals("ListNodes")) {
        		listNodes(arguments);
      		} else if (command.equals("ListChildren")) {
        		listChildren(arguments);
      		} else if (command.equals("FindPath")) {
        		findPath(arguments);
      		} else if (command.equals("DfsAlgorithm")){
				dfsAlgorithm(arguments);
			}else {
        		output.println("Unrecognized command: " + command);
      		}
    	} catch (Exception e) {
      		output.println("Exception: " + e.toString());
    	}
  	}


	private void createGraph(List<String> arguments) {

    	if (arguments.size() != 1)
      		throw new CommandException(
				"Bad arguments to CreateGraph: " + arguments);

    	String graphName = arguments.get(0);
    	createGraph(graphName);
  	}


  	private void createGraph(String graphName) {
  		
  		graphs.put(graphName, new Graph<WeightedNode>());
  		output.println("created graph " + graphName);

  	}
 
  	
  	private void createNode(List<String> arguments) {

    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to createNode: " + arguments);

    	String nodeName = arguments.get(0);
    	String cost = arguments.get(1);
    	createNode(nodeName, cost);
  	}


 	private void createNode(String nodeName, String cost) {
 		if (nodes.containsKey(nodeName))
 			throw new CommandException(nodeName + " has already existed ");
 			
 		nodes.put(nodeName, new WeightedNode(nodeName, Integer.parseInt(cost)));
 		output.println("create node " + nodeName + " with cost " + cost);
  	}
	

  	private void addNode(List<String> arguments) {

    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to addNode: " + arguments);

    	String graphName = arguments.get(0);
    	String nodeName = arguments.get(1);
    	addNode(graphName, nodeName);
  	}


  	private void addNode(String graphName, String nodeName) {

		if (!this.graphs.containsKey(graphName))
			throw new CommandException(graphName + " is not existed ");
		
		Graph<WeightedNode> graph = this.graphs.get(graphName);
		WeightedNode node = this.nodes.get(nodeName);
		if (graph.isNodeContained(node))
			throw new CommandException(nodeName + " has already existed in " + graphName);
		
  		graph.addNode(node);
  		output.println("add node " + nodeName + " to " + graphName);	
  	}


  	private void addEdge(List<String> arguments) {

    	if (arguments.size() != 3)
      		throw new CommandException(
				"Bad arguments to addEdge: " + arguments);

    	String graphName = arguments.get(0);
    	String parentName = arguments.get(1);
    	String childName = arguments.get(2);
    	addEdge(graphName, parentName, childName);
  	}


	private void addEdge(String graphName, String parentName, String childName) {
		
		if (!this.graphs.containsKey(graphName))
			throw new CommandException(graphName + " is not existed ");
		
		if (!this.nodes.containsKey(parentName))
			throw new CommandException(parentName + " is not existed ");		

		if (!this.nodes.containsKey(childName))
			throw new CommandException(childName + " is not existed ");
		
		Graph<WeightedNode> graph = this.graphs.get(graphName);
		if (!graph.isNodeContained(this.nodes.get(parentName)))
			throw new CommandException(parentName + " must be contained in " + graphName);
		
		if (!graph.isNodeContained(this.nodes.get(childName)))
			throw new CommandException(childName + " must be contained in " + graphName);

		if (graph.isEdgeContained(this.nodes.get(parentName), this.nodes.get(childName)))
			throw new CommandException("edge has already been contained");
		
		this.graphs.get(graphName).addEdge(this.nodes.get(parentName), this.nodes.get(childName));
		output.println("added edge from " + parentName + " to " + childName + " in " + graphName);
  	}


  	private void listNodes(List<String> arguments) {

    	if (arguments.size() != 1)
      		throw new CommandException(
				"Bad arguments to listNodes: " + arguments);

    	String graphName = arguments.get(0);
    	listNodes(graphName);
  	}


  	private void listNodes(String graphName) {
  		
		if (!this.graphs.containsKey(graphName))
			throw new CommandException(graphName + " is not existed ");
		
  		Graph<WeightedNode> graph = this.graphs.get(graphName);
  		output.print(graphName + " contains:");
		
		// Sort the nodes' name
		ArrayList<WeightedNode> nodes = new ArrayList<>(graph.getNodes());
		ArrayList<String> nodesNames = new ArrayList<>();
		for (WeightedNode node : nodes)
		{
			nodesNames.add(node.getName());
		}
		Collections.sort(nodesNames);
		
		for (String nodeName : nodesNames)
		{
			output.print(" " + nodeName);
		}
		output.print("\n");
  	}


  	private void listChildren(List<String> arguments) {

    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to listChildren: " + arguments);

    	String graphName = arguments.get(0);
    	String parentName = arguments.get(1);
    	listChildren(graphName, parentName);
  	}


  	private void listChildren(String graphName, String parentName) {

		if (!this.graphs.containsKey(graphName))
			throw new CommandException(graphName + " is not existed ");
		
		if (!this.nodes.containsKey(parentName))
			throw new CommandException(parentName + " is not existed ");

		if (!this.graphs.get(graphName).isNodeContained(this.nodes.get(parentName)))
			throw new CommandException(parentName + " must be contained in " + graphName);

		output.print("the children of " + parentName + " in " + graphName + " are:");

		// Get the children and sort them
		Graph<WeightedNode> graph = this.graphs.get(graphName);
		ArrayList<WeightedNode> children = graph.getChildren(nodes.get(parentName));
		ArrayList<String> childrenNames = new ArrayList<>();
		for (WeightedNode child : children)
		{
			childrenNames.add(child.getName());
		}
		Collections.sort(childrenNames);
		
		for (String childName : childrenNames)
		{
			output.print(" " + childName);
		}
		output.print("\n");
  	}


  	private void findPath(List<String> arguments) {

    	String graphName;
    	List<String> sourceArgs = new ArrayList<>();
    	List<String> destArgs = new ArrayList<>();

    	if (arguments.size() < 1)
      		throw new CommandException(
				"Bad arguments to FindPath: " + arguments);

    	Iterator<String> iter = arguments.iterator();
    	graphName = iter.next();

		// extract source arguments
    	while (iter.hasNext()) {
      		String s = iter.next();
      		if (s.equals("->"))
        		break;
      		sourceArgs.add(s);
    	}

		// extract destination arguments
    	while (iter.hasNext())
      		destArgs.add(iter.next());

    	if (sourceArgs.size() < 1)
      		throw new CommandException(
				"Too few source args for FindPath");

    	if (destArgs.size() < 1)
      		throw new CommandException(
				"Too few dest args for FindPath");

    	findPath(graphName, sourceArgs, destArgs);
  	}


  	private void findPath(String graphName, List<String> sourceArgs,
  						  List<String> destArgs) {

		if (!this.graphs.containsKey(graphName))
			throw new CommandException(graphName + " is not existed ");
		Graph<WeightedNode> graph = graphs.get(graphName);

		// Find all possible paths
		NodeCountingPath startNodes = null, endNodes = null;
		for (String sourceArg : sourceArgs)
		{
			if (!this.nodes.containsKey(sourceArg))
				throw new CommandException(sourceArg + " is not existed ");
			if (startNodes == null)
				startNodes = new NodeCountingPath(this.nodes.get(sourceArg));
			else
				startNodes = startNodes.extend(this.nodes.get(sourceArg));
		}
		for (String destArg : destArgs)
		{
			if (!this.nodes.containsKey(destArg))
				throw new CommandException(destArg + " is not existed ");
			if (endNodes == null)
				endNodes = new NodeCountingPath(this.nodes.get(destArg));
			else
				endNodes = endNodes.extend(this.nodes.get(destArg));
		}
		
		Path<WeightedNode, ?> shortestPath = PathFinder.FindShortestPath(graph, startNodes, endNodes);
		if (shortestPath != null)
		{
			output.print("found path in " + graphName + ":");
			
			// Print path
			Iterator<WeightedNode> iter = shortestPath.iterator();
			while (iter.hasNext())
				output.print(" " + iter.next().getName());
			
			// Print cost
			output.print(" with cost " + Integer.toString((int) shortestPath.getCost()));	
		}
		else
		{
			output.print("no path found in " + graphName);
		}
  	}
	
  	private void dfsAlgorithm(List<String> arguments) {
    	if ((arguments.size() > 3) || (arguments.size() == 0))
      		throw new CommandException(
				"Bad arguments to listChildren: " + arguments);
    	
    	String graphName, sourceArg, destArg;
    	switch (arguments.size())
    	{
    		case 2:
    	    	graphName = arguments.get(0);
    	    	sourceArg = arguments.get(1);
    	    	dfsAlgorithm(graphName, sourceArg);
    	    	break;
    	    	
    		case 3:
    	    	graphName = arguments.get(0);
    	    	sourceArg = arguments.get(1);
    	    	destArg = arguments.get(2);
    	    	dfsAlgorithm(graphName, sourceArg, destArg);
    	    	break;
    	    
    	    default:
          		throw new CommandException(
        				"Bad arguments to dfsAlgorithm: " + arguments);    	    	
    	}
  	}
  	
	private void dfsAlgorithm(String graphName, String sourceArg,
							  String destArg) {
		if (!this.graphs.containsKey(graphName))
			throw new CommandException(graphName + " is not existed ");
		
		if (!this.nodes.containsKey(sourceArg))
			throw new CommandException(sourceArg + " is not existed ");

		if (!this.nodes.containsKey(destArg))
			throw new CommandException(destArg + " is not existed ");
		
		if (!this.graphs.get(graphName).isNodeContained(this.nodes.get(sourceArg)))
			throw new CommandException(sourceArg + " must be contained in " + graphName);

		if (!this.graphs.get(graphName).isNodeContained(this.nodes.get(destArg)))
			throw new CommandException(destArg + " must be contained in " + graphName);
		
		Graph<WeightedNode> graph = this.graphs.get(graphName);
		WeightedNode startNode = this.nodes.get(sourceArg);
		WeightedNode endNode = this.nodes.get(destArg);
		
		DfsAlgorithm dfsAlgorithm = new DfsAlgorithm(graph);		
		LinkedList<WeightedNode> path = dfsAlgorithm.DFS(startNode, endNode);
		output.print("dfs algorithm output " + graphName + " " + sourceArg + " -> " + destArg + ":");
		if (path != null)
		{
			for (WeightedNode node : path)
			{
				output.print(" " + node.getName());
			}
		}
		else
		{
			output.print(" no path was found");
		}
		output.print("\n");
	}
	
	private void dfsAlgorithm(String graphName, String sourceArg) {
		if (!this.graphs.containsKey(graphName))
			throw new CommandException(graphName + " is not existed ");
		
		if (!this.nodes.containsKey(sourceArg))
			throw new CommandException(sourceArg + " is not existed ");
		
		if (!this.graphs.get(graphName).isNodeContained(this.nodes.get(sourceArg)))
			throw new CommandException(sourceArg + " must be contained in " + graphName);
		
		Graph<WeightedNode> graph = this.graphs.get(graphName);
		WeightedNode startNode = this.nodes.get(sourceArg);
		
		DfsAlgorithm dfsAlgorithm = new DfsAlgorithm(graph);		
		LinkedList<WeightedNode> path = dfsAlgorithm.DFS(startNode, null);
		output.print("dfs algorithm output " + graphName + " " + sourceArg + ":");
		if (path != null)
		{
			for (WeightedNode node : path)
			{
				output.print(" " + node.getName());
			}
		}
		else
		{
			output.print(" no path was found");
		}
		output.print("\n");
	}
	

	private static void printUsage() {
		System.err.println("Usage:");
		System.err.println("to read from a file: java TestDriver <name of input script>");
		System.err.println("to read from standard input: java TestDriver");
	}


	public static void main(String args[]) {

		try {
			// check for correct number of arguments
			if (args.length > 1) {
				printUsage();
				return;
			}

			TestDriver td;
			if (args.length == 0)
				// no arguments - read from standard input
				td = new TestDriver(new InputStreamReader(System.in),
								    new OutputStreamWriter(System.out));
			else {
				// one argument - read from file
				java.nio.file.Path testsFile = Paths.get(args[0]);
				if (Files.exists(testsFile) && Files.isReadable(testsFile)) {
					td = new TestDriver(
							Files.newBufferedReader(testsFile, Charset.forName("US-ASCII")),
							new OutputStreamWriter(System.out));
				} else {
					System.err.println("Cannot read from " + testsFile.toString());
					printUsage();
					return;
				}
			}

			td.runTests();

		} catch (IOException e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		}
	}
}


/**
 * This exception results when the input file cannot be parsed properly.
 */
class CommandException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CommandException() {
		super();
  	}

  	public CommandException(String s) {
		super(s);
  	}
}