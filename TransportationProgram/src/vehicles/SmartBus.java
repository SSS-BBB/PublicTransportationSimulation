package vehicles;

import java.util.Random;

import javax.swing.JOptionPane;

import program_designs.ProgramColorAndFont;
import stops.SkyTrainStopSign;
import stops.StopSign;
import stops.TrainStopSign;

public class SmartBus extends AirConditionedVehicle {
	
	private int peopleOnBus;
	private int maxPeopleOnBus; // maximum number of people on the bus
	private int maxSeatAmount; // maximum number of seat on the bus
	private int seatLeft; // how many seat left
	
	public SmartBus(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval,
			double averageSpeed, double temperature) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, temperature, "/icons/smartBusIcon.png");
		Random rand = new Random();
		this.maxPeopleOnBus = 40;
		this.maxSeatAmount = 30;
		this.peopleOnBus = rand.nextInt(maxPeopleOnBus + 1);
		this.seatLeft = Math.max(0, maxSeatAmount - peopleOnBus);
		this.routeColor = ProgramColorAndFont.DARK_BLUE;
		setSpeedVariance(2.5);
		
		this.iconWidth = 22;
		this.iconHeight = 22;
		
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
	
	@Override
	public double fee(StopSign from, StopSign to) {
		// No subscription
		int numberStation = numberOfStation(from, to);
		if (numberStation == -1) {
			JOptionPane.showMessageDialog(null, "Cannot find " + from.getStopID() + " and/or " + to.getStopID(), 
					"Somethin went wrong!", JOptionPane.ERROR_MESSAGE);
			return 0.0;	
		}
		int floor = (int) Math.ceil(numberStation / 2.0);
		if (floor == 0) return 0.0; // same from and to
		
		return 20.0 + floor*2.5;
	}
	
	public double subsriptionFee(StopSign from, StopSign to) {
		int numberStation = numberOfStation(from, to);
		if (numberStation == 0) return 0.0;
		return 15.0; // 15 baht throughout the line
	}
	
	@Override
	public void onUpdateCurrentStop() {
		super.onUpdateCurrentStop();
		
		// random people going in and out on each stop (assuming the tendency for people to get on and off each stop is equal)
		Random rand = new Random();
		
		// random people get off the bus
		peopleOnBus -= rand.nextInt(peopleOnBus + 1);
		// random people get on the bus
		peopleOnBus += rand.nextInt(maxPeopleOnBus - peopleOnBus + 1);
		
		seatLeft = Math.max(0, maxSeatAmount - peopleOnBus);
	}
	
	public int getNumberOfPeopleOnBus() {
		return peopleOnBus;
	}
	
	public int getSeatLeft() {
		return seatLeft;
	}
	
}
