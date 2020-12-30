package hyperbolicGraphConstruction;

public class HyperbolicGraph {
	
	
	private Vertex[] vertices;
	
	/** Initialises a hyperbolic random graph. This contains an array of vertices, which each have their own
	 * neighbourhood array.
	 * 
	 * @param numberOfNodes The number of Nodes in the graph.
	 */
	
	
	
	
	public HyperbolicGraph(int numberOfNodes) {
		
		vertices = new Vertex[numberOfNodes];
		
		for(int i = 0; i < vertices.length; i++) {
			
			vertices[i] = new Vertex(numberOfNodes);
			
		}
		
		
		initaliseGraph();
	}
	
	
	public Vertex[] getVertices() {
		return vertices;
	}


	public void addEdge(Vertex v, Vertex u) {
		
		if(v == u) {return;}
		
		v.addNeighbour(u);
		u.addNeighbour(v);
	}
	
	
	/** A nested loop which runs through every pair of vertices. For each pair their hyperbolic distance 
	 * is checked, if it is less than HyperbolicMath.maxRadius, then the nodes are connected via the addEdge
	 * method.
	 * 
	 */
	public void initaliseGraph() {
		
		for(int i = 0; i < vertices.length - 1; i++) {
			
			for(int j = i; j < vertices.length; j++) {
				
	
				
				if(HyperbolicMath.isWithinHyperbolicDistanceR(vertices[i].getPolarCo_ordinates(), vertices[j].getPolarCo_ordinates()))
				{addEdge(vertices[i], vertices[j]);}
				
			}
			
			
		}
		
		
		
		
	}
	

	
	

}
