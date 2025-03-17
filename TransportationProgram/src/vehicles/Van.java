package vehicles;
import java.awt.Color;
import javax.swing.JOptionPane;
import stops.*;
public class Van extends AirConditionedVehicle {
	public Van(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval, double averageSpeed,
			double temperature) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, temperature, "/icons/vanIcon.png");
		setSpeedVariance(3.5);
		this.routeColor = Color.gray;
		this.iconWidth = 30;
		this.iconHeight = 30;
	}
	@Override
	public double fee(StopSign from, StopSign to) {
		int numberStation = numberOfStation(from, to);
		if (numberStation == -1) {
			JOptionPane.showMessageDialog(null, "Cannot find " + from.getStopID() + " and/or " + to.getStopID(), 
					"Somethin went wrong!", JOptionPane.ERROR_MESSAGE);
			return -1.0;	
		}
		int floor = (int) Math.ceil(numberStation / 2.0);
		if (floor == 0) return 0.0; // same from and to
		return 15.0 + floor*3.0;
	}
	@Override
	public boolean validStop() {
		for (StopSign stop : stops) {
			// cannot use train stop sign for non train vehicle
			if (stop instanceof TrainStopSign) return false;
			// cannot use sky train stop sign for non sky train vehicle
			else if (stop instanceof SkyTrainStopSign) return false;
		}
		return true;
	}

}