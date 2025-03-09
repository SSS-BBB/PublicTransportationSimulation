package maps;

import stops.StopSign;
import vehicles.AirConditionedBus;
import vehicles.NormalBus;
import vehicles.SmartBus;
import vehicles.Vehicle;

public class TestMap2 extends MapData {
	
	public TestMap2() {
		// set stops
		stops = new StopSign[]{
				new StopSign("0", "000", new double[]{0.0, 0.0}),
				new StopSign("1", "001", new double[]{10.0, 20.0}),
				new StopSign("2", "002", new double[]{20.0, 50.0}),
				new StopSign("5", "005", new double[]{80.0, 80.0}),
				new StopSign("6", "006", new double[]{100.0, 110.0}),
				new StopSign("7", "007", new double[]{100.0, 130.0})
		};
		
		// set vehicles
		vehicles = new Vehicle[] { 
				new SmartBus("Test1", "000", stops, 9.5, 3.5, 24.0),
				new AirConditionedBus("Test2", "001", stops, 7.4, 2.5, 25.0),
				new NormalBus("Test4", "003", stops, 10.5, 4.9),
				new NormalBus("Test5", "004", stops, 11.2, 5.6)
		};
		
		mapName = "Test Map 2";
	}
	
}