package maps;

import stops.*;
import vehicles.*;

public class TestMap extends MapData {
	
	
	public TestMap() {
		// set stop signs
		stops = new StopSign[]{
				new StopSign("0", "000", new double[]{0.0, 0.0}),
				new StopSign("1", "001", new double[]{10.0, 20.0}),
				new StopSign("2", "002", new double[]{30.0, 50.0}),
				new StopSign("3", "003", new double[]{60.0, 70.0}),
				new StopSign("4", "004", new double[]{70.0, 80.0}),
				new StopSign("5", "005", new double[]{80.0, 90.0}),
				new StopSign("6", "006", new double[]{100.0, 110.0}),
				new StopSign("7", "007", new double[]{120.0, 130.0})
		};
		
		// set vehicles
		vehicles = new Vehicle[] { 
				new SmartBus("Test1", "000", stops, 0.5, 2.0, 24.0),
				new AirConditionedBus("Test2", "001", stops, 0.4, 1.5, 25.0),
				new NormalBus("Test3", "002", stops, 0.25, 3.5),
				new NormalBus("Test4", "003", stops, 0.49, 4.9),
				new NormalBus("Test5", "004", stops, 1.1, 5.6)
		};
		
		mapName = "Test Map";
		mapScale = 4;
		
		// vehicles = new Vehicle[] { new NormalBus("Test1", "000", stops, 0.5, 1.5) }; // single vehicle test
	}
}
