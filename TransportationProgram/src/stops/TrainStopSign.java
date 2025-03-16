package stops;

import java.io.IOException;

import javax.imageio.ImageIO;

public class TrainStopSign extends StopSign {

	public TrainStopSign(String stopName, String stopID, double[] position) {
		super(stopName, stopID, position);
		try {
			signIcon = ImageIO.read(getClass().getResourceAsStream("/icons/trainStopSign.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.iconWidth = 20;
		this.iconHeight = 20;
	}

}
