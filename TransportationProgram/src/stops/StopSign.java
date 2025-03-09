package stops;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StopSign {
	
	private String stopName;
	private String stopID; // unique for every stop
	private double[] position;
	private BufferedImage signIcon;
	
	public StopSign(String stopName, String stopID, double[] position) {
		this.stopName = stopName;
		this.position = position;
		this.stopID = stopID;
		try {
			signIcon = ImageIO.read(getClass().getResourceAsStream("/icons/blueBusStopSign.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public StopSign(String stopName, String stopID, double[] position, String stopSignImagePath) {
		this.stopName = stopName;
		this.position = position;
		this.stopID = stopID;
		try {
			signIcon = ImageIO.read(getClass().getResourceAsStream(stopSignImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public BufferedImage getSignIcon() {
		return signIcon;
	}
	
}
