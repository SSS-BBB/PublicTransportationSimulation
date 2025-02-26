package useful;

public class UsefulFunc {

	public static double distanceBetweenTwoPoints(double[] point1, double[] point2) {
		double x_dist = Math.pow(point1[0] - point2[0], 2.0);
		double y_dist = Math.pow(point1[1] - point2[1], 2.0);
		
		return Math.sqrt(x_dist + y_dist);
	}
	
	public static double[] getCertainSizeVectorFromTwoPoints(double[] point1, double[] point2, double size) {
		double deltaX = point2[0] - point1[0];
		double deltaY = point2[1] - point1[1];
		double dominator = Math.sqrt(Math.pow(deltaX, 2.0) + Math.pow(deltaY, 2.0));
		
		double vi = (size * deltaX) / dominator;
		double vj = (size * deltaY) / dominator;
		
		return new double[] {vi, vj};
	}
	
}
