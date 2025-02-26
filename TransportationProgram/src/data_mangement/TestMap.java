package data_mangement;

import stops.StopSign;
import vehicles.NormalBus;
import vehicles.Vehicle;

public class TestMap extends MapData {
	
	
	public TestMap() {
		// set stop signs
		stops = new StopSign[]{
				new StopSign("0", "000", new double[]{0.0, 0.0}),
				new StopSign("1", "001", new double[]{1.0, 2.0}),
				new StopSign("2", "002", new double[]{3.0, 5.0}),
				new StopSign("3", "003", new double[]{6.0, 7.0}),
				new StopSign("4", "004", new double[]{7.0, 8.0}),
				new StopSign("5", "005", new double[]{8.0, 9.0}),
				new StopSign("6", "006", new double[]{10.0, 11.0}),
				new StopSign("7", "007", new double[]{12.0, 13.0})
		};
		
		// set vehicles
		vehicles = new Vehicle[] { 
				new NormalBus("Test1", "000", stops, 0.5, 2.0),
				new NormalBus("Test2", "001", stops, 0.4, 1.5),
				new NormalBus("Test3", "002", stops, 0.25, 3.5),
				new NormalBus("Test4", "003", stops, 0.49, 4.9),
				new NormalBus("Test5", "004", stops, 1.1, 5.6)
		};
		
		vehicles = new Vehicle[] { 
				new NormalBus("Test1", "000", stops, 0.5, 2.0)
		};
	}
}
