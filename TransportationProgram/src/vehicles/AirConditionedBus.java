package vehicles;

import javax.swing.JOptionPane;

import program_designs.ProgramColorAndFont;
import stops.*;

public class AirConditionedBus extends AirConditionedVehicle {

	public AirConditionedBus(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval,
			double averageSpeed, double temperature) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, temperature, "/icons/airConditionedBusIcon.png");
		setSpeedVariance(4.0);
		this.routeColor = ProgramColorAndFont.LIGHT_BLUE;
		
		this.iconWidth = 27;
		this.iconHeight = 27;
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
		
		return 10.0 + floor*2.0;
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
