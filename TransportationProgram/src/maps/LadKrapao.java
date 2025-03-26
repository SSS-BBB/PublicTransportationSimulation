package maps;

import stops.*;
import vehicles.*;

public class LadKrapao extends MapData {
	
	public LadKrapao() {
		
		this.mapName = "Lad Krapao";
		this.mapScale = 77;
		
		this.stops = new StopSign[] { 
				new TrainStopSign("Kolok", "A00", new double[] { 130, 500 }),
				new TrainStopSign("BuaYaimak", "A01", new double[] { 373, 500 }),
				new TrainStopSign("Laemfiao", "A02", new double[] { 696, 500 }),
				new TrainStopSign("Big mae", "A03", new double[] { 1013, 500 }),
				new TrainStopSign("Klongyao", "A04", new double[] { 1346, 500 }),
				new TrainStopSign("Khannasan", "A05", new double[] { 1685, 500 }),
				
				new StopSign("Watabreak", "A06", new double[] { 1240, 1019 }),
				new StopSign("Faifasathit", "A07", new double[] { 1240, 917 }),
				new StopSign("KMITL", "A08", new double[] { 1240, 794 }),
				new StopSign("Khokkakola", "A09", new double[] { 1240, 620 }),
				new StopSign("Changman", "A10", new double[] { 1240, 370 }),
				new StopSign("Nongnai", "A11", new double[] { 1240, 167 }),
				new StopSign("Bangpa", "A12", new double[] { 1240, 49 }),
				
				new StopSign("Thao hin", "A13", new double[] { 99, 90 }),
				new StopSign("Kawaii river", "A14", new double[] { 263, 179 }),
				new StopSign("Chai miang", "A15", new double[] { 353, 359 }),
				new StopSign("Khon mai", "A16", new double[] { 578, 630 }),
				new StopSign("Pattanine", "A17", new double[] { 833, 745 }),
				new StopSign("Funfueng", "A18", new double[] { 1045, 899 }),
				
				new StopSign("Bai", "A19", new double[] { 296, 615 }),
				new StopSign("Plathong", "A20", new double[] { 263, 702 }),
				new StopSign("Mai D", "A21", new double[] { 188, 819 }),
				new StopSign("Lel", "A22", new double[] { 116, 975 }),
				
				new SkyTrainStopSign("Somsak", "A23", new double[] { 459, 114 }),
				new SkyTrainStopSign("Somsree", "A24", new double[] { 641, 114 }),
				new SkyTrainStopSign("Plachan", "A25", new double[] { 868, 114 }),
				new SkyTrainStopSign("Khonkhong", "A26", new double[] { 1083, 114 }),
				new SkyTrainStopSign("Bualoy", "A27", new double[] { 1468, 114 }),
				new SkyTrainStopSign("Turtle Yai", "A28", new double[] { 1760, 114 }),
		};
		
		scalePixelToKm(new double[] { 110, 110 });
		
		this.vehicles = new Vehicle[] {
				// Train
				new Train("TM Train - 128", "TTT 7879", 
						getStopsFromIndex(new int[] { 0, 1, 2, 3, 4, 5 }),
						1.75, 40.0),
				new Train("TM Train - 128", "NPN 390", 
						getStopsFromIndex(new int[] { 0, 1, 2, 3, 4, 5 }),
						1.75, 40.0),
				
				// Smart bus
				// 369
				new SmartBus("Smart Bus - 369", "HGB 714",
						getStopsFromIndex(new int[] { 6, 7, 8, 9, 10, 11, 12 }),
						1.0, 35.0, 24.0),
				new SmartBus("Smart Bus - 369", "NRP 581",
						getStopsFromIndex(new int[] { 6, 7, 8, 9, 10, 11, 12 }),
						1.0, 35.0, 24.0),
				new SmartBus("Smart Bus - 369", "YGL 205",
						getStopsFromIndex(new int[] { 6, 7, 8, 9, 10, 11, 12 }),
						1.0, 35.0, 24.0),
				
				// 168
				new SmartBus("Smart Bus - 168", "MFM 410",
						getStopsFromIndex(new int[] { 13, 14, 15, 16, 17, 18, 6 }),
						1.0, 35.0, 24.0),
				new SmartBus("Smart Bus - 168", "MFQ 173",
						getStopsFromIndex(new int[] { 13, 14, 15, 16, 17, 18, 6 }),
						1.0, 35.0, 24.0),
				
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
						0.8, 50.0, 24.0
						),
				new SkyTrain("Yellow Sky Train", "KMITL 67", 
						getStopsFromIndex(new int[] { 23, 24, 25, 26, 27, 28}),
						0.8, 50.0, 24.0
						)
				
		};
	}
	
}