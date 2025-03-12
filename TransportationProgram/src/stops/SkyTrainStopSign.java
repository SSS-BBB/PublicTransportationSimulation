package stops;

import java.io.IOException;

import javax.imageio.ImageIO;

public class SkyTrainStopSign extends StopSign {

	public SkyTrainStopSign(String stopName, String stopID, double[] position) {
		super(stopName, stopID, position);
		// TODO: add image
		try {
			signIcon = ImageIO.read(getClass().getResourceAsStream("/icons/blueBusStopSign.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		this.iconWidth = 20;
		this.iconHeight = 20;
	}

}
