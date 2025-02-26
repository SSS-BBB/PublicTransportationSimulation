package stops;

public class StopSign {
	
	private String stopName;
	private String stopID; // unique for every stop
	private double[] position;
	
	public StopSign(String stopName, String stopID, double[] position) {
		this.stopName = stopName;
		this.position = position;
		this.stopID = stopID;
	}

	public String getStopName() {
		return stopName;
	}

	public double[] getPosition() {
		return position;
	}

	public String getStopID() {
		return stopID;
	}
	
}
