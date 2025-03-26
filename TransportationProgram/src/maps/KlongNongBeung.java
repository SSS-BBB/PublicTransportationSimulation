package maps;

import stops.*;
import vehicles.*;

public class KlongNongBeung extends MapData {
	
	public KlongNongBeung() {
		
		this.mapName = "Klong Nong Beung";
		this.mapScale = 77;
		
		this.stops = new StopSign[] { 
				new StopSign("Nong Beung Market", "A00", new double[] { 172, 126 }),
				new StopSign("Baan Beung", "A01", new double[] { 417, 118 }),
				new StopSign("Police Station", "A02", new double[] { 582, 83 }),
				new StopSign("Fire Station", "A03", new double[] { 897, 75 }),
				new StopSign("Warm Hospital", "A04", new double[] { 1260, 83 }),
				new StopSign("Bass School", "A05", new double[] { 1501, 85 }),
				new StopSign("ABC University", "A06", new double[] { 1827, 94 }),
				new StopSign("P Football Stadium", "A07", new double[] { 678, 200 }),
				new StopSign("Night Market", "A08", new double[] { 719, 390 }),
				new StopSign("Morning Market", "A09", new double[] { 687, 561 }),
				new StopSign("Nong Beung Mall", "A10", new double[] { 501, 732 }),
				new StopSign("Nong Care", "A11", new double[] { 303, 820 }),
				new StopSign("Keng Village", "A12", new double[] { 544, 957 }),
				new StopSign("Poom Museum", "A13", new double[] { 807, 912 }),
				new StopSign("Art Gallery", "A14", new double[] { 1084, 761 }),
				new StopSign("Exotic Zoo", "A15", new double[] { 1256, 612 }),
				new StopSign("Big Farm", "A16", new double[] { 1519, 630 }),
				new StopSign("John's Theater", "A17", new double[] { 1770, 640 }),
				new StopSign("City Bank", "A18", new double[] { 993, 545 }),
				new StopSign("City Skyscraper", "A19", new double[] { 900, 426 }),
				new StopSign("City Library", "A20", new double[] { 1789, 265 }),
				new StopSign("Chilling Cafe", "A21", new double[] { 1462, 326 }),
				new StopSign("Elegant Restaurant", "A22", new double[] { 1109, 453 }),
				new StopSign("Luxurious Fashion Store", "A23", new double[] { 823, 683 }),
				new StopSign("Nong Beung Town Hall", "A24", new double[] { 646, 808 }),
				new StopSign("Nakorn Bridge", "A25", new double[] { 193, 1026 }),
				new StopSign("Town Sports Center", "A26", new double[] { 142, 314 }),
				new StopSign("Steel Factory", "A27", new double[] { 313, 371 }),
				new StopSign("Shoe Shop", "A28", new double[] { 582, 385 }),
				new StopSign("Beung Care Mall", "A29", new double[] { 915, 328 }),
				new StopSign("Town Court", "A30", new double[] { 1066, 266 }),
				new StopSign("City Mall", "A31", new double[] { 1340, 222 }),
				new StopSign("Airport", "A32", new double[] { 1584, 216 }),
				new StopSign("BCA College", "A33", new double[] { 1809, 1024 }),
				new StopSign("AEA High School", "A34", new double[] { 1617, 924 }),
				new StopSign("Parking Lot", "A35", new double[] { 1397, 869 }),
				new StopSign("City Center", "A36", new double[] { 1213, 820 }),
		};
		
		scalePixelToKm(new double[] { 120, 120 });
		
		this.vehicles = new Vehicle[] {
				// Normal Bus
				// 579
				new NormalBus("Bus - 579", "NBS 4423", 
						getStopsFromIndex(new int[] { 0, 1, 2, 3, 4, 5, 6 }),
						1.0, 25.0),
				new NormalBus("Bus - 579", "NBK 8970", 
						getStopsFromIndex(new int[] { 0, 1, 2, 3, 4, 5, 6 }),
						1.0, 25.0),
				new NormalBus("Bus - 579", "PER 555", 
						getStopsFromIndex(new int[] { 0, 1, 2, 3, 4, 5, 6 }),
						1.0, 25.0),
				new MiniBus("Mini Bus - 579", "KHM 2619", 
						getStopsFromIndex(new int[] { 0, 1, 2, 3, 4, 5, 6 }),
						0.5, 30.0),
				new MiniBus("Mini Bus - 579", "BU 36675", 
						getStopsFromIndex(new int[] { 0, 1, 2, 3, 4, 5, 6 }),
						0.5, 30.0),
				
				// 180
				new NormalBus("Bus - 180", "ACT 5635", 
						getStopsFromIndex(new int[] { 0, 1, 7, 19, 18, 15, 16, 17 }),
						0.75, 25.0),
				new NormalBus("Bus - 180", "KER 7779", 
						getStopsFromIndex(new int[] { 0, 1, 7, 19, 18, 15, 16, 17 }),
						0.75, 25.0),
				
				// 62
				new NormalBus("Bus - 62", "ERR 87", 
						getStopsFromIndex(new int[] { 0, 1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 }),
						0.75, 28.0),
				new NormalBus("Bus - 62", "EPW 230", 
						getStopsFromIndex(new int[] { 0, 1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 }),
						0.75, 28.0),
				new NormalBus("Bus - 62", "VED 784", 
						getStopsFromIndex(new int[] { 0, 1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 }),
						0.75, 28.0),
				
				// Air Bus
				// 15
				new AirConditionedBus("Air Bus - 15", "ABS 774",
						getStopsFromIndex(new int[] { 20, 21, 22, 18, 23, 24, 12, 25 }),
						1.5, 30.0, 25.0 ),
				new AirConditionedBus("Air Bus - 15", "FEP 0467",
						getStopsFromIndex(new int[] { 20, 21, 22, 18, 23, 24, 12, 25 }),
						1.5, 30.0, 25.0 ),
				new AirConditionedBus("Air Bus - 15", "UCN 246",
						getStopsFromIndex(new int[] { 20, 21, 22, 18, 23, 24, 12, 25 }),
						1.5, 30.0, 25.0 ),
				
				// 18
				new AirConditionedBus("Air Bus - 18", "KOP 124",
						getStopsFromIndex(new int[] { 20, 32, 31, 30, 29, 8, 28, 27, 26 }),
						1.5, 30.0, 25.0 ),
				new AirConditionedBus("Air Bus - 18", "ISG 714",
						getStopsFromIndex(new int[] { 20, 32, 31, 30, 29, 8, 28, 27, 26 }),
						1.5, 30.0, 25.0 ),
				new AirConditionedBus("Air Bus - 18", "UBT 299",
						getStopsFromIndex(new int[] { 20, 32, 31, 30, 29, 8, 28, 27, 26 }),
						1.5, 30.0, 25.0 ),
				new AirConditionedBus("Air Bus - 18", "XEU 256",
						getStopsFromIndex(new int[] { 20, 32, 31, 30, 29, 8, 28, 27, 26 }),
						1.5, 30.0, 25.0 ),
				
				// Smart Bus
				// 7
				new SmartBus("Smart Bus - 7", "SMP 770",
						getStopsFromIndex(new int[] { 26, 27, 28, 9, 23, 36, 35, 34, 33 }),
						1.0, 29.0, 24.0 ),
				new SmartBus("Smart Bus - 7", "WKG 763",
						getStopsFromIndex(new int[] { 26, 27, 28, 9, 23, 36, 35, 34, 33 }),
						1.0, 29.0, 24.0 ),
				new SmartBus("Smart Bus - 7", "JSW 069",
						getStopsFromIndex(new int[] { 26, 27, 28, 9, 23, 36, 35, 34, 33 }),
						1.0, 29.0, 24.0 )
		};
	}

}