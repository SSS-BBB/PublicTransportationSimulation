package maps;

import stops.*;
import vehicles.*;

public class VehiclesTestMap extends MapData {
	
	public VehiclesTestMap() {
		// Attributes setting
		mapName = "Vehicles Test Map";
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
				new StopSign("Van Garage", "A06", new double[] { 34.0/mapScale, 242.0/mapScale }),
				new StopSign("Coco Industry", "A01", new double[] { 116.0/mapScale, 139.0/mapScale }),
				new StopSign("Tiny Bangna", "A07", new double[] { 247.0/mapScale, 178.0/mapScale }),
				new StopSign("Peerapol Staduium", "A08", new double[] { 333.0/mapScale, 126.0/mapScale }),
				new StopSign("Mee", "A09", new double[] { 429.0/mapScale, 74.0/mapScale }),
				new StopSign("Bastoni's Park", "A10", new double[] { 518.0/mapScale, 103.0/mapScale }),
				new StopSign("Poom Gaming", "A11", new double[] { 635.0/mapScale, 75.0/mapScale }),
				new StopSign("Garfield and The cats", "A12", new double[] { 755.0/mapScale, 60.0/mapScale })
		};
		
		StopSign[] trainStop = new StopSign[] { 
				new StopSign("Train Garage", "A13", new double[] { 47.0/mapScale, 540.0/mapScale }),
				new StopSign("39 Bridge", "A14", new double[] { 188.0/mapScale, 540.0/mapScale }),
				new StopSign("Warm Warm where you go last night", "A15", new double[] { 312.0/mapScale, 540.0/mapScale }),
				new StopSign("Mix Tong Gold shop", "A16", new double[] { 420.0/mapScale, 540.0/mapScale }),
				new StopSign("Tew strip club", "A17", new double[] { 535.0/mapScale, 540.0/mapScale }),
				new StopSign("Copter helicopter", "A18", new double[] { 643.0/mapScale, 540.0/mapScale }),
				new StopSign("BenzZaWorld", "A19", new double[] { 756.0/mapScale, 540.0/mapScale })
		};
		
		StopSign[] skyTrainStop = new StopSign[] { 
				new StopSign("Sky train Garage", "A20", new double[] { 102.0/mapScale, 356.0/mapScale }),
				new StopSign("Yong Tui strip club", "A21", new double[] { 260.0/mapScale, 318.0/mapScale }),
				new StopSign("Focus's Bar", "A22", new double[] { 441.0/mapScale, 307.0/mapScale }),
				new StopSign("Niti 1st Racing Car", "A23", new double[] { 609.0/mapScale, 344.0/mapScale }),
				new StopSign("Coconut Melon", "A24", new double[] { 714.0/mapScale, 424.0/mapScale }),
				new StopSign("Pakawee Amusement Park", "A25", new double[] { 581.0/mapScale, 461.0/mapScale }),
				new StopSign("Gotaji Football Stadium", "A26", new double[] { 408.0/mapScale, 439.0/mapScale }),
				new StopSign("Ultra's Cartoon Shop", "A27", new double[] { 208.0/mapScale, 426.0/mapScale })
		};
		
		
		// vehicles
		vehicles = new Vehicle[] {
				new MiniBus("Mini Bus", "ABC 023", miniBusStop, 3.0, 70.0),
				new Van("Van", "VAN 69", vanStop, 3.5, 50.0, 25.0),
				new Train("Train", "TRA 042", trainStop, 0.5, 80.0),
				new SkyTrain("Sky Train", "STA 056", skyTrainStop, 0.33, 90.0, 24.0)
		};
		
		setStopSign(new StopSign[][] { 
			miniBusStop, vanStop, trainStop, skyTrainStop
		});
		
	}
	
}