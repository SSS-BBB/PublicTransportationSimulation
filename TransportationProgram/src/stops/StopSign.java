package stops;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StopSign {
	
	protected String stopName;
	protected String stopID; // unique for every stop
	protected double[] position;
	
	protected BufferedImage signIcon;
	protected int iconWidth, iconHeight;
	
	public StopSign(String stopName, String stopID, double[] position) {
		this.stopName = stopName;
		this.position = position;
		this.stopID = stopID;
		this.iconWidth = 22;
		this.iconHeight = 22;
		try {
			signIcon = ImageIO.read(getClass().getResourceAsStream("/icons/busStopIcon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public StopSign(String stopName, String stopID, double[] position, String stopSignImagePath) {
		this(stopName, stopID, position);
		try {
			signIcon = ImageIO.read(getClass().getResourceAsStream(stopSignImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(double[] position) {
		this.position = position;
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
	
	public int getIconWidth() {
		return iconWidth;
	}
	
	public int getIconHeight() {
		return iconHeight;
	}
	
}
