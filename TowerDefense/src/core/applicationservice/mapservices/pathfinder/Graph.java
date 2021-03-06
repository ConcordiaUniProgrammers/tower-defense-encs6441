package core.applicationservice.mapservices.pathfinder;


import infrastructure.loggin.Log4jLogger;

import java.util.List;
import java.util.Stack;




public class Graph {
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	/**
	 * application logger definition
	 */
	private static final Log4jLogger logger = new Log4jLogger();

	/**
	 * Initializes an empty graph with <tt>V</tt> vertices and 0 edges.
	 * @param V the number of vertices
	 */
	public Graph(int V) {
		if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	/**  
	 * Initializes a graph from an input stream.
	 * The format is the number of vertices <em>V</em>,
	 * followed by the number of edges <em>E</em>,
	 * followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
	 * @param graphInfo  the input stream
	 * @param nodes graph nodes
	 */
	public Graph(List<String> graphInfo, int nodes){
		this(nodes);
		int E = graphInfo.size();
		if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
		for (String str : graphInfo) {
			String[] strs = str.split(" ");
			int v = Integer.parseInt(strs[0]);
			int w = Integer.parseInt(strs[1]);
			addEdge(v, w);
		}
	}

	/**
	 * Initializes a new graph that is a deep copy of <tt>G</tt>.
	 * @param G the graph to copy
	 */
	public Graph(Graph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adj[v]) {
				reverse.push(w);
			}
			for (int w : reverse) {
				adj[v].add(w);
			}
		}
	}

	/**
	 * Returns the number of vertices in the graph.
	 * @return the number of vertices in the graph
	 */
	public int V() {
		return V;
	}

	/**
	 * Returns the number of edges in the graph.
	 * @return the number of edges in the graph
	 */
	public int E() {
		return E;
	}

	// throw an IndexOutOfBoundsException unless 0 <= v < V
	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
	}

	/**
	 * Adds the undirected edge v-w to the graph.
	 * @param v one vertex in the edge
	 * @param w the other vertex in the edge
	 */
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}


	/**
	 * Returns the vertices adjacent to vertex <tt>v</tt>.
	 * @return the vertices adjacent to vertex <tt>v</tt> as an Iterable
	 * @param v the vertex
	 */
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

}