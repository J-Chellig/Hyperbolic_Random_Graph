package visuals;

public class EuclideanConversion {
	
	/** a collection of Static methods used to convert the pure Hyperbolic graph into a drawing on the 
	 * Euclidean plane. (Mainly focused on turning Hyperbolic Polars to Euclidean Co-ords.) Two different projections are given
	 * however the linear scaling tends to be more realistic. The global EuclideanProjection variable is controlled by a combo-box in the GUI.
	 */
	
	
	private static boolean useEuclideanProjection = true;
	
    public static void setuseEuclideanProjection(boolean input) {
		
		useEuclideanProjection = input;
		
	}
	
	
	/** Takes a pair of (r, theta) co-ordinates in spaces and outputs the corresponding (x, y) Euclidean rectangular
	 * co-ordinates. An intermediary step of mapping r -> f(r) is given by the projection method. The pair (f(r), theta)
	 * is then transformed using the standard conversions:
	 * x = r*cos(theta)
	 * y = r*sin(theta).
	 *
	 * @param input The pair (r, theta) in hyperbolic polars.
	 * @return The pair (x,y) in rectangular euclidean co_ordinates.
	 */
	
	public static double[] HyperbolicPolartoEuclidean(double [] input) {
		
		double[] outputX_Y = new double[2];
		
		double r = input[0];
		double theta = input[1];
		
		double euclidr = radiiConversion(r);
		
		
		outputX_Y[0] = euclidr*Math.cos(theta);
		outputX_Y[1] = euclidr*Math.sin(theta);
		
		return outputX_Y;
		
		
	}
	
	/** Completes the mapping r -> f(r). This is used to scale the Poincare disk so it is suitable for the canvas size.
	 * By this scaling maxRadius should be a most 6.8 for Euclidean and 6.35 for Linear. The EuclideanProjection uses a hyperbolic conversion
	 * which should push more points into the centre of the disk. While the non-Euclidean Projection uses just a pure
	 * scalling factor, more points will appear towards the edge. In both cases the underlying connections are the same,
	 * it is only the arrangement of the points.
	 * 
	 * @param hypRadius
	 * @return
	 */
	
	public static double radiiConversion(double hypRadius) {
		
		if(useEuclideanProjection == true) {
		
		return 15*Math.sqrt(2*Math.cosh(hypRadius) - 2);}
		
		else { return 55*hypRadius;}
	}
	

}
