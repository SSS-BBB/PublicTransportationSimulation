package maps;

import stops.*;
import vehicles.*;

public class OldYork extends MapData {
	
	public OldYork() {
		// Attributes setting
		mapName = "Old York";
		mapScale = 20;
		
		// stops
		StopSign[] miniBusStop = new StopSign[] { 
				new StopSign("Mini Bus Garage", "A00", new double[] { 104.0/mapScale, 68.0/mapScale }),
				new StopSign("Coco Industry", "A01", new double[] { 116.0/mapScale, 139.0/mapScale }),
				new StopSign("Kaow's Mall", "A02", new double[] { 231.0/mapScale, 115.0/mapScale }),
				new StopSign("Pepe Shabu", "A03", new double[] { 391.0/mapScale, 206.0/mapScale }),
				new StopSign("Moodeng", "A04", new double[] { 593.0/mapScale, 178.0/mapScale }),
				new StopSign("Space Museum", "A05", new double[] { 741.0/mapScale, 293.0/mapScale })
		};
		
		StopSign[] vanStop = new StopSign[] { 
				new StopSign("Transport Garage", "A06", new double[] { 34.0/mapScale, 242.0/mapScale }),
				new StopSign("Coco Industry", "A01", new double[] { 116.0/mapScale, 139.0/mapScale }),
				new StopSign("Tiny Bangna", "A07", new double[] { 247.0/mapScale, 178.0/mapScale }),
				new StopSign("Peerapol Stadium", "A08", new double[] { 333.0/mapScale, 126.0/mapScale }),
				new StopSign("Mee", "A09", new double[] { 429.0/mapScale, 74.0/mapScale }),
				new StopSign("Bastoni's Park", "A10", new double[] { 518.0/mapScale, 103.0/mapScale }),
				new StopSign("Poom Gaming", "A11", new double[] { 635.0/mapScale, 75.0/mapScale }),
				new StopSign("Garfield and The cats", "A12", new double[] { 755.0/mapScale, 60.0/mapScale })
		};
		
		StopSign[] trainStop = new StopSign[] { 
				new TrainStopSign("Train Garage", "A13", new double[] { 47.0/mapScale, 540.0/mapScale }),
				new TrainStopSign("39 Bridge", "A14", new double[] { 188.0/mapScale, 540.0/mapScale }),
				new TrainStopSign("Warm Warm where you go last night", "A15", new double[] { 312.0/mapScale, 540.0/mapScale }),
				new TrainStopSign("Mix Tong Gold shop", "A16", new double[] { 420.0/mapScale, 540.0/mapScale }),
				new TrainStopSign("Tew gaming club", "A17", new double[] { 535.0/mapScale, 540.0/mapScale }),
				new TrainStopSign("Copter helicopter", "A18", new double[] { 643.0/mapScale, 540.0/mapScale }),
				new TrainStopSign("BenzZaWorld", "A19", new double[] { 756.0/mapScale, 540.0/mapScale })
		};
		
		StopSign[] skyTrainStop = new StopSign[] { 
				new SkyTrainStopSign("Sky train Garage", "A20", new double[] { 102.0/mapScale, 356.0/mapScale }),
				new SkyTrainStopSign("Yong J tennis club", "A21", new double[] { 260.0/mapScale, 318.0/mapScale }),
				new SkyTrainStopSign("Focus's Bar", "A22", new double[] { 441.0/mapScale, 307.0/mapScale }),
				new SkyTrainStopSign("Niti 1st Racing Car", "A23", new double[] { 609.0/mapScale, 344.0/mapScale }),
				new SkyTrainStopSign("Coconut Melon", "A24", new double[] { 714.0/mapScale, 424.0/mapScale }),
				new SkyTrainStopSign("Pakawee Amusement Park", "A25", new double[] { 581.0/mapScale, 461.0/mapScale }),
				new SkyTrainStopSign("Gotaji Football Stadium", "A26", new double[] { 408.0/mapScale, 439.0/mapScale }),
				new SkyTrainStopSign("Ultra's Cartoon Shop", "A27", new double[] { 208.0/mapScale, 426.0/mapScale })
		};
		
		StopSign[] airConditionedBusStop = new StopSign[] {
				new StopSign("Transport Garage", "A06", new double[] { 34.0/mapScale, 242.0/mapScale }),
				new StopSign("Tiny Bangna", "A07", new double[] { 247.0/mapScale, 178.0/mapScale }),
				new StopSign("Peerapol Stadium", "A08", new double[] { 333.0/mapScale, 126.0/mapScale }),
				new StopSign("Asus Shop", "A28", new double[] { 575.0/mapScale, 260.0/mapScale }),
				new StopSign("Moodeng", "A04", new double[] { 593.0/mapScale, 178.0/mapScale }),
				new StopSign("Mee Mee Fountain", "A29", new double[] { 750.0/mapScale, 367.0/mapScale })
		};
		
		StopSign[] smartBusStop = new StopSign[] {
				new StopSign("Transport Garage", "A06", new double[] { 34.0/mapScale, 242.0/mapScale }),
				new StopSign("Tiny Bangna", "A07", new double[] { 247.0/mapScale, 178.0/mapScale }),
				new StopSign("Pepe Shabu", "A03", new double[] { 391.0/mapScale, 206.0/mapScale }),
				new StopSign("Asus Shop", "A28", new double[] { 590.0/mapScale, 209.0/mapScale }),
				new StopSign("Bastoni's Park", "A10", new double[] { 518.0/mapScale, 103.0/mapScale }),
				new StopSign("Poom Gaming", "A11", new double[] { 635.0/mapScale, 75.0/mapScale }),
				new StopSign("Garfield and The cats", "A12", new double[] { 755.0/mapScale, 60.0/mapScale })
		};
		
		
		// vehicles
		vehicles = new Vehicle[] {
				new MiniBus("Mini Bus - 850", "ABC 023", miniBusStop, 3.0, 70.0),
				new MiniBus("Mini Bus - 850", "OWU 362", miniBusStop, 3.0, 70.0),
				new MiniBus("Mini Bus - 850", "PWL 837", miniBusStop, 3.0, 70.0),
				
				new Van("Van - 135", "VAN 69", vanStop, 3.5, 50.0, 25.0),
				new Van("Van - 135", "BWC 8754", vanStop, 3.5, 50.0, 25.0),
				new Van("Van - 135", "PU 23050", vanStop, 3.5, 50.0, 25.0),
				
				new Train("Train - 756", "TRA 042", trainStop, 0.5, 80.0),
				new Train("Train - 756", "DQR 121", trainStop, 0.5, 80.0),
				
				new SkyTrain("Blue Sky Train", "STA 056", skyTrainStop, 0.33, 90.0, 24.0),
				new SkyTrain("Blue Sky Train", "8873PXX", skyTrainStop, 0.33, 90.0, 24.0),
				new SkyTrain("Blue Sky Train", "QWO 1213", skyTrainStop, 0.33, 90.0, 24.0),
				
				new AirConditionedBus("Air Bus - 418", "ABS 880", airConditionedBusStop, 1.0, 65.0, 25.0),
				new AirConditionedBus("Air Bus - 418", "QLF 044", airConditionedBusStop, 1.0, 65.0, 25.0),
				new AirConditionedBus("Air Bus - 418", "XDW 893", airConditionedBusStop, 1.0, 65.0, 25.0),
				new AirConditionedBus("Air Bus - 418", "IKO 689", airConditionedBusStop, 1.0, 65.0, 25.0),
				
				new SmartBus("Smart Bus - 391", "SBS 900", smartBusStop, 0.7, 68.0, 24.0),
				new SmartBus("Smart Bus - 391", "SUR 1574", smartBusStop, 0.7, 68.0, 24.0),
		};
		
		setStopSign(new StopSign[][] { 
			miniBusStop, vanStop, trainStop, skyTrainStop, airConditionedBusStop, smartBusStop
		});
		
	}
	
}