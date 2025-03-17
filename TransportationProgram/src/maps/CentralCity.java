package maps;

import stops.*;
import vehicles.*;

public class CentralCity extends MapData {
	
	public CentralCity() {
			
			this.mapName = "Central City";
			this.mapScale = 77;
			
			this.stops = new StopSign[] { 
					new SkyTrainStopSign("Band Sue", "A00", new double[] { 170, 100 }),
					new SkyTrainStopSign("Hua Lamphong", "A01", new double[] { 832, 83 }),
					new SkyTrainStopSign("Don Mueang", "A02", new double[] { 1486, 81 }),
					new SkyTrainStopSign("Ayutthaya", "A03", new double[] { 1771, 368 }),
					new SkyTrainStopSign("Rangsit", "A04", new double[] { 1743, 703 }),
					new SkyTrainStopSign("Pak Chong", "A05", new double[] { 1329, 965 }),
					new SkyTrainStopSign("Chiang Mai", "A06", new double[] { 777, 946 }),
					new SkyTrainStopSign("Nakhon Ratchasima", "A07", new double[] { 379, 937 }),
					
					new StopSign("Khon Kaen", "A08", new double[] { 138, 500 }),
					new StopSign("Udon Thani", "A09", new double[] { 384, 492 }),
					new StopSign("Nong Khai", "A10", new double[] { 645, 489 }),
					new StopSign("Chachoengsao", "A11", new double[] { 882, 405 }),
					new StopSign("Rayong", "A12", new double[] { 1082, 318 }),
					new StopSign("Pattaya", "A13", new double[] { 1358, 285 }),
					new StopSign("Hua Hin", "A14", new double[] { 1577, 285 }),
					
					new StopSign("Surat Thani", "A15", new double[] { 892, 478 }),
					new StopSign("Hat Yai", "A16", new double[] { 1108, 481 }),
					new StopSign("Padang Besar", "A17", new double[] { 1390, 466 }),
					new StopSign("Old Trafford", "A18", new double[] { 1616, 457 }),
					
					new StopSign("Anfield", "A19", new double[] { 947, 611 }),
					new StopSign("London", "A20", new double[] { 1132, 650 }),
					new StopSign("White Heart Lane", "A21", new double[] { 1425, 657 }),
					new StopSign("Donmu", "A22", new double[] { 1630, 678 }),
					
					new StopSign("Silala", "A23", new double[] { 214, 594 }),
					new StopSign("Phutsai", "A24", new double[] { 414, 674 }),
					new StopSign("Fida", "A25", new double[] { 714, 668 })
			};
			
			scalePixelToKm(new double[] { 120, 120 });
			
			this.vehicles = new Vehicle[] {
					// Sky train
					new SkyTrain("Green Sky Train", "GSK 6179", 
							getStopsFromIndex(new int[] { 0, 1, 2, 3, 4, 5, 6, 7 }),
							0.5, 35.0, 24.5
							),
					new SkyTrain("Green Sky Train", "VFD 379", 
							getStopsFromIndex(new int[] { 0, 1, 2, 3, 4, 5, 6, 7 }),
							0.5, 35.0, 24.5
							),
					new SkyTrain("Green Sky Train", "XLF 159", 
							getStopsFromIndex(new int[] { 0, 1, 2, 3, 4, 5, 6, 7 }),
							0.5, 35.0, 24.5
							),
					
					// Normal Bus
					// 8
					new NormalBus("Bus - 8", "OOP 211", 
							getStopsFromIndex(new int[] { 8, 9, 10, 11, 12, 13, 14 }),
							1.5, 25.0),
					new NormalBus("Bus - 8", "NRQ 3436", 
							getStopsFromIndex(new int[] { 8, 9, 10, 11, 12, 13, 14 }),
							1.5, 25.0),
					new NormalBus("Bus - 8", "BPH 490", 
							getStopsFromIndex(new int[] { 8, 9, 10, 11, 12, 13, 14 }),
							1.5, 25.0),
					new NormalBus("Bus - 8", "NCZ 4617", 
							getStopsFromIndex(new int[] { 8, 9, 10, 11, 12, 13, 14 }),
							1.5, 25.0),
					new NormalBus("Bus - 8", "CKB 368", 
							getStopsFromIndex(new int[] { 8, 9, 10, 11, 12, 13, 14 }),
							1.5, 25.0),
					
					// 9
					new NormalBus("Bus - 9", "PRO 878", 
							getStopsFromIndex(new int[] { 8, 9, 10, 15, 16, 17, 18 }),
							1.5, 25.0),
					new NormalBus("Bus - 9", "ZGA 616", 
							getStopsFromIndex(new int[] { 8, 9, 10, 15, 16, 17, 18 }),
							1.5, 25.0),
					new NormalBus("Bus - 9", "OIA 5300", 
							getStopsFromIndex(new int[] { 8, 9, 10, 15, 16, 17, 18 }),
							1.5, 25.0),
					new NormalBus("Bus - 9", "TPF 281", 
							getStopsFromIndex(new int[] { 8, 9, 10, 15, 16, 17, 18 }),
							1.5, 25.0),
					
					// 10
					new NormalBus("Bus - 10", "KMT 442", 
							getStopsFromIndex(new int[] { 8, 9, 10, 19, 20, 21, 22 }),
							1.5, 25.0),
					new NormalBus("Bus - 10", "KJO 5624", 
							getStopsFromIndex(new int[] { 8, 9, 10, 19, 20, 21, 22 }),
							1.5, 25.0),
					new NormalBus("Bus - 10", "QOP 621", 
							getStopsFromIndex(new int[] { 8, 9, 10, 19, 20, 21, 22 }),
							1.5, 25.0),
					
					// Smart Bus
					// 99
					new SmartBus("Smart Bus - 99", "PHY 118",
							getStopsFromIndex(new int[] { 23, 24, 25, 19, 20, 21, 22 }),
							1.0, 30.0, 24.0 ),
					new SmartBus("Smart Bus - 99", "MTP 764",
							getStopsFromIndex(new int[] { 23, 24, 25, 19, 20, 21, 22 }),
							1.0, 30.0, 24.0 ),
					new SmartBus("Smart Bus - 99", "HYO 902",
							getStopsFromIndex(new int[] { 23, 24, 25, 19, 20, 21, 22 }),
							1.0, 30.0, 24.0 ),
					
			};
		}
}