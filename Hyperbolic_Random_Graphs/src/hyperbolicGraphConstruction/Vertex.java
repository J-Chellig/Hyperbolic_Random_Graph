package hyperbolicGraphConstruction;

/**This class describes the properties of a vertex which will be contained within a Hyperbolic Graph. Vertices
 * have a set of polarCo_ordinates, along with an array containing their neighbours. The neighbours are determined
 * by the Hyperbolic Graph class.
 * 
 * @author Jordan Chellig
 *
 */

public class Vertex {
	
	private static int sharedcounter = 0;
	
	/**
	 * @param polarCo_ordinates (r, \theta) co-ordinates of the vertex.
	 */
	private double[] polarCo_ordinates = new double[2];
	private Vertex[] adjacentVertices;
	
	
	private int adjacencyPointer = 0;
	private int label;
	
	public Vertex(int N) {
		
		label = sharedcounter;
		sharedcounter++;
		
		polarCo_ordinates = HyperbolicMath.randomHyperbolicPolars();
		adjacentVertices = new Vertex[N];
		
	}
	
	
	public double[] getPolarCo_ordinates() {
		return polarCo_ordinates;
	}
    public Vertex[] getAdjacentVertices() {
		return adjacentVertices;
	}
    public int getAdjacencyPointer() {
		return adjacencyPointer;
	}
    
    public int getLabel() {
    	return label;
    }	
	
    
    /**
     * Adds the input vertex to the neighbourhood of this.vertex. The vertex will first scan it's current neighbourhood
     * for the input. If it is found, then nothing happens, otherwise the vertex is added at the position of the adjacencyPointer.
     * The adjacency pointer is then incremented, this structure almost functions like a stack.
     * @param v
     */
	public void addNeighbour(Vertex v) {
	
		
		if(isInNeighbourhood(v)) {System.out.println("Vertex already in Neighbourhood");
		return;}
		
		adjacentVertices[adjacencyPointer] = v;
		
		adjacencyPointer++;
		}
	
	
	/**The scanning method, an enhanced loop runs through the vertex's entire neighbourhood looking for v. 
	 * Importantly the neighbourhood most likely will not be full, thus the loop will break once the first null
	 * state is found. 
	 * 
	 * @param v
	 * @return true if v is occupying one of the index's of this.vertex's neighbourhood.
	 * false if neighbourhood is empty, or a null object is reached before v is found.
	 */
	public boolean isInNeighbourhood(Vertex v) {
		
		if(adjacencyPointer == 0) {return false;}
		
	scanningLoop :	for(Vertex u : adjacentVertices) {
			
			if(u == null) {break scanningLoop;}
			
			if(v == u) {return true;}
			}
	
	return false;}
	
	
	/**
	 * Mainly for testing purposes, prints out the entire array of labels of this.vertex's neighbourhood.
	 */
	
public void printNeighbours() {
		
		for(Vertex u : this.adjacentVertices) {
			
			if(u == null) {break;}
			
			System.out.print(u.label + " : ");
		}
		
		System.out.println();
	}


}
