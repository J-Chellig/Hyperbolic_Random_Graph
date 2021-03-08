package visuals;

import hyperbolicGraphConstruction.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.stage.Screen;

public class HyperbolicCanvas extends Canvas {
	
	/**
	 * This class deals with drawing a contained HyperbolicGraph. The drawingPlace is the canvas that will be used, with
	 * g it's associated GraphicsContext.
	 */
	
	private HyperbolicGraph graph;
	
	private double drawnRadius;
	private double vertexRadius = 2;
	
	private GraphicsContext g;
	
	public HyperbolicCanvas(int numberofNodes) {
		
		super(Screen.getPrimary().getVisualBounds().getWidth()-120, Screen.getPrimary().getVisualBounds().getHeight()-120);
		graph = new HyperbolicGraph(numberofNodes);
		drawnRadius = EuclideanConversion.radiiConversion(HyperbolicMath.MaxRadius);
		g = super.getGraphicsContext2D();

	} 
	
	/**
	 * Draws the Poincare disk, with visual radius given by the projection of the input hyperbolic radius. Centre
	 * at the middle of the drawingPlace.
	 */
	private void drawDisk() {
		
		g.setStroke(Color.BLACK);
		
		g.strokeOval(super.getWidth()/2 - drawnRadius, super.getHeight()/2 - drawnRadius, 2*drawnRadius, 2*drawnRadius);
		
	}	
	
	
	/**Takes a vertex's hyperbolic co-ordinates, converts them to Euclidean and then shifts them so that their centre
	 * corresponds with the canvas. All vertices should appear within the disk.
	 * 
	 * @param v
	 * @return The co-ordinates of the centre of the vertex's centre on the canvas.
	 */
	
    private double[] getCanvasCo_Ord(Vertex v){
    	
    	double[] euclideanCenter = EuclideanConversion.HyperbolicPolartoEuclidean(v.getPolarCo_ordinates());
    	
    	double[] vertexCanvasCenter = {
    			
    			0.5*super.getWidth() + euclideanCenter[0],
    			0.5*super.getHeight() - euclideanCenter[1]};
    	
    	return vertexCanvasCenter;
   }
	
    /**Draws a vertex with hyperbolic Centre (r, theta), applies Euclidean conversion methods.
     * 
     * @param v
     */
	private void drawVertex(Vertex v) {
		
		
	double[] vertexCanvasCenter = getCanvasCo_Ord(v);
			
	g.setStroke(Color.BLACK);
	g.setFill(Color.BLACK);
	
	g.strokeOval(vertexCanvasCenter[0] - vertexRadius, vertexCanvasCenter[1] - vertexRadius, 2*vertexRadius, 2*vertexRadius);
	g.fillOval(vertexCanvasCenter[0] - vertexRadius, vertexCanvasCenter[1] - vertexRadius, 2*vertexRadius, 2*vertexRadius);
	}
	
	private void drawVertices() {
		
		for(Vertex v : graph.getVertices()) {
			
			drawVertex(v); }
		
	}
	
	/**For each pair, compute their canvas co-ordinates, then draw a line between them.
	 * 
	 * @param v
	 * @param u
	 */
	private void drawEdge(Vertex v, Vertex u) {
		
	double[] vCanvasCenter = getCanvasCo_Ord(v);
	double[] uCanvasCenter= getCanvasCo_Ord(u);
		
	g.setStroke(Color.CORNFLOWERBLUE);
	
	g.strokeLine(uCanvasCenter[0], uCanvasCenter[1], vCanvasCenter[0], vCanvasCenter[1]);
	}
	
	
	/**Draws all required edges as follows:
	 * 
	 * Enhanced loop runs through each vertex, at a given vertex we then run through it's adjacent vertices and connect
	 * all those which have a larger index than the current vertex. This will connect ever pair of vertices exactly once.
	 * 
	 */
	private void drawAllEdges() {
		
		for(Vertex v : graph.getVertices()) {
			
             neighbourhood : for(int j = 0; j < graph.getVertices().length; j++) {
				
            	 if(v.getAdjacentVertices()[j] == null) {break neighbourhood;}
            	 
            	 if(v.getAdjacentVertices()[j].getLabel() < v.getLabel()) {continue;}
				 
            	 drawEdge(v, v.getAdjacentVertices()[j]);
			}
			
		}
	}
	
	
	/**
	 * Creates a drawing of the Graph on the Canvas, using the parameters as given by the user Input
	 * 
	 */
	public void reDraw() {
		
		drawDisk();
		drawAllEdges();
		drawVertices();
		
	}
	

	
	
	}
	

