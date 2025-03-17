package vehicles;

import java.awt.Color;

import javax.swing.JOptionPane;

import stops.*;

public class Train extends Vehicle {

	public Train(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval, double averageSpeed) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, "/icons/trainIcon.png");
		setSpeedVariance(0);
		stationWait = 0.0167;
		this.routeColor = new Color(244,164,96);
		
		this.iconWidth = 60;
		this.iconHeight = 60;
	}

	@Override
	public double fee(StopSign from, StopSign to) {
		int numberStation = numberOfStation(from, to);
		if (numberStation == -1) {
			JOptionPane.showMessageDialog(null, "Cannot find " + from.getStopID() + " and/or " + to.getStopID(), 
					"Somethin went wrong!", JOptionPane.ERROR_MESSAGE);
			return -1.0;	
		}
		int floor = (int) Math.ceil(numberStation / 3.0);
		if (floor == 0) return 0.0; // same from and to
		
		return 5.0 + floor*1.0;
	}
	
	@Override
	public boolean validStop() {
		for (StopSign stop : stops) {
			// train must use only train stop
			if (!(stop instanceof TrainStopSign)) return false;
		}
		return true;
	}

}
