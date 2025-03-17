package maps;

import stops.*;
import vehicles.*;

public class LadKrapao extends MapData {
	
	public LadKrapao() {
		
		this.mapName = "Lad Krapao";
		this.mapScale = 77;
		
		this.stops = new StopSign[] { 
				new TrainStopSign("Nong Beung Market", "A00", new double[] { 130, 500 }),
				new TrainStopSign("Baan Beung", "A01", new double[] { 373, 500 }),
				new TrainStopSign("Police Station", "A02", new double[] { 696, 500 }),
				new TrainStopSign("Fire Station", "A03", new double[] { 1013, 500 }),
				new TrainStopSign("Warm Hospital", "A04", new double[] { 1346, 500 }),
				new TrainStopSign("Bass School", "A05", new double[] { 1685, 500 }),
				
				new StopSign("ABC University", "A06", new double[] { 1240, 1019 }),
				new StopSign("P Football Stadium", "A07", new double[] { 1240, 917 }),
				new StopSign("Night Market", "A08", new double[] { 1240, 794 }),
				new StopSign("Morning Market", "A09", new double[] { 1240, 620 }),
				new StopSign("Nong Beung Mall", "A10", new double[] { 1240, 370 }),
				new StopSign("Nong Care", "A11", new double[] { 1240, 167 }),
				new StopSign("Keng Village", "A12", new double[] { 1240, 49 }),
				
				new StopSign("Poom Museum", "A13", new double[] { 99, 90 }),
				new StopSign("Art Gallery", "A14", new double[] { 263, 179 }),
				new StopSign("Exotic Zoo", "A15", new double[] { 353, 359 }),
				new StopSign("Big Farm", "A16", new double[] { 578, 630 }),
				new StopSign("John's Theater", "A17", new double[] { 833, 745 }),
				new StopSign("City Bank", "A18", new double[] { 1045, 899 }),
				
				new StopSign("City Skyscraper", "A19", new double[] { 296, 615 }),
				new StopSign("City Library", "A20", new double[] { 263, 702 }),
				new StopSign("Chilling Cafe", "A21", new double[] { 188, 819 }),
				new StopSign("Elegant Restaurant", "A22", new double[] { 116, 975 }),
				
				new SkyTrainStopSign("Luxurious Fashion Store", "A23", new double[] { 459, 114 }),
				new SkyTrainStopSign("Nong Beung Town Hall", "A24", new double[] { 641, 114 }),
				new SkyTrainStopSign("Nakorn Bridge", "A25", new double[] { 868, 114 }),
				new SkyTrainStopSign("Nakorn Bridge", "A26", new double[] { 1083, 114 }),
				new SkyTrainStopSign("Nakorn Bridge", "A27", new double[] { 1468, 114 }),
				new SkyTrainStopSign("Nakorn Bridge", "A28", new double[] { 1760, 114 }),
		};
		
		scalePixelToKm(new double[] { 110, 110 });
		
		this.vehicles = new Vehicle[] {
				// Train
				new Train("TM Train - 128", "TTT 7879", 
						getStopsFromIndex(new int[] { 0, 1, 2, 3, 4, 5 }),
						3.0, 40.0),
				new Train("TM Train - 128", "NPN 390", 
						getStopsFromIndex(new int[] { 0, 1, 2, 3, 4, 5 }),
						3.0, 40.0),
				
				// Smart bus
				// 369
				new SmartBus("Smart Bus - 369", "HGB 714",
						getStopsFromIndex(new int[] { 6, 7, 8, 9, 10, 11, 12 }),
						1.5, 35.0, 24.0),
				new SmartBus("Smart Bus - 369", "NRP 581",
						getStopsFromIndex(new int[] { 6, 7, 8, 9, 10, 11, 12 }),
						1.5, 35.0, 24.0),
				new SmartBus("Smart Bus - 369", "YGL 205",
						getStopsFromIndex(new int[] { 6, 7, 8, 9, 10, 11, 12 }),
						1.5, 35.0, 24.0),
				
				// 168
				new SmartBus("Smart Bus - 168", "MFM 410",
						getStopsFromIndex(new int[] { 13, 14, 15, 16, 17, 18, 6 }),
						1.5, 35.0, 24.0),
				new SmartBus("Smart Bus - 168", "MFQ 173",
						getStopsFromIndex(new int[] { 13, 14, 15, 16, 17, 18, 6 }),
						1.5, 35.0, 24.0),
				
				// Van
				// 983
				new Van("Van - 983", "IDS 1321",
						getStopsFromIndex(new int[] { 13, 14, 15, 19, 20, 21, 22 }),
						0.75, 30.0, 25.0),
				new Van("Van - 983", "ZZU 548",
						getStopsFromIndex(new int[] { 13, 14, 15, 19, 20, 21, 22 }),
						0.75, 30.0, 25.0),
				new Van("Van - 983", "XSV 795",
						getStopsFromIndex(new int[] { 13, 14, 15, 19, 20, 21, 22 }),
						0.75, 30.0, 25.0),
				
				// BTS
				new SkyTrain("Yellow Sky Train", "ATA 128", 
						getStopsFromIndex(new int[] { 23, 24, 25, 26, 27, 28}),
						1.0, 50.0, 24.0
						),
				new SkyTrain("Yellow Sky Train", "KMITL 67", 
						getStopsFromIndex(new int[] { 23, 24, 25, 26, 27, 28}),
						1.0, 50.0, 24.0
						)
				
		};
	}
	
}