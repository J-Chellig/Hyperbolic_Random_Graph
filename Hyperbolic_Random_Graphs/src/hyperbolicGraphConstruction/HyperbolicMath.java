package hyperbolicGraphConstruction;

public class HyperbolicMath {
	

	/** The three main parameters of the hyperbolic random graph. MaxRadius describes the radius of the
	 * Poincare disk, it controls connectivity, alpha describes the strength of the power-law distribution (roughly
	 * values between 0 and 20). NumberofNodes describes the number of nodes in the model. These values are set
	 * within the GUI. Each setter has a corresponding GUI element, upon changing these the graph is redrawn.   
	 * 
	 */
	public static double MaxRadius = 6.2;
	public static double alpha = 1.4;
	public static int numberOfNodes = 150;
	

	public static void setMaxRadius(String maxRadius) {
		
		try {
			MaxRadius = Double.parseDouble(maxRadius);
		} catch(NumberFormatException e) {
			
			System.out.println("Error Value");
			MaxRadius = 2.0;
		}
		
		
	}

	public static void setAlpha(String inputalpha) {
		
		try {
			alpha = Double.parseDouble(inputalpha);
		} catch(NumberFormatException e) {
			
			System.out.println("Error Value");
			alpha = 1.0;
		}
	}

	public static void setNumerOfNodes(String input) {
		
		try {
			
			int output = Integer.parseInt(input);
			
			if(output <= 0) { numberOfNodes = 2;}
			
			else { numberOfNodes = output;}
		}catch(NumberFormatException e) {
			
			System.out.println("Error");
			numberOfNodes = 2;
		}
		
		
	}	

	
	/**
	 * 
	 * @return A random angle picked uniformly from (0, 2Pi).
	 */
	public static double randomAngle() {
		
		
		return 2*Math.PI*Math.random();
		
	}
	
	/**
	 * 
	 * @return Returns a random radius with a probability distribution given by
	 * 
	 * p(r) = alpha*sinh(alpha*r)/[cosh(alpha*maxRadius) - 1]. This was achieved using
	 * inversion and the uniform distribution of (0, 1).
	 */
	
	public static double randomHyperbolicRadius() {
		
		return 
				(1/alpha)*acosh(1 + (Math.cosh(alpha*MaxRadius) - 1)*Math.random());
		
	}
	
	/**
	 * 
	 * @return A random set of polar Co-ordinates in the form (r, theta), where r is picked according to randomHyperbolicRadius,
	 * and theta is uniform on (0, 2pi).
	 */
	
	public static double[] randomHyperbolicPolars(){
		
		double[] output = {randomHyperbolicRadius(), randomAngle()};
		
		return output;
	}
	
	/**
	 * 
	 * 
	 * @return Inverse hyperbolic Cosine of x.
	 */
	public static double acosh(double x) {
		
		return Math.log(x + Math.sqrt(x*x - 1));
		
	}
	
	/** This method decides the metric which points use to determine whether they should be adjacent or not. 
	 * The metric is a function of (r1, theta1) and (r2, theta2) and computes the hyperbolic distance between these
	 * points.
	 * 
	 * @param co_ords1
	 * @param co_ords2
	 * @return The method returns true if this distance is at most maxRadius, and false otherwise. 
	 */
	
	public static boolean isWithinHyperbolicDistanceR(double[] co_ords1, double[] co_ords2) {
		
		if(co_ords1.length != 2 || co_ords2.length != 2) {System.out.println("Incorrect Size!");
		return false;}
		
		
		double radius1 = co_ords1[0];
		double theta1 = co_ords1[1];
		double radius2 = co_ords2[0];
		double theta2 = co_ords2[1];
		
		double testvalue =
				
		acosh(Math.cosh(radius1)*Math.cosh(radius2) - Math.sinh(radius1)*Math.sinh(radius2)*Math.cos(Math.PI - Math.abs(Math.PI - Math.abs(theta1 - theta2))));		
		
		if(testvalue < MaxRadius) {return true;}
		else {return false;}
		}
	
}
