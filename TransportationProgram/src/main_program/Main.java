package main_program;

import vehicles.*;

import java.util.*;

import stops.*;

import useful.*;

public class Main {

	public static void main(String[] args) {
		StopSign[] stops = new StopSign[]{
				new StopSign("0", "000", new double[]{0.0, 0.0}),
				new StopSign("1", "001", new double[]{1.0, 2.0}),
				new StopSign("2", "002", new double[]{3.0, 5.0}),
				new StopSign("3", "003", new double[]{6.0, 7.0}),
				new StopSign("4", "004", new double[]{7.0, 8.0}),
				new StopSign("5", "005", new double[]{8.0, 9.0}),
				new StopSign("6", "006", new double[]{10.0, 11.0}),
				new StopSign("7", "007", new double[]{12.0, 13.0}),
				};
		
		Vehicle buses[] = new Vehicle[] { 
				new NormalBus("Test1", "000", stops, 0.5, 2.0),
				new NormalBus("Test2", "001", stops, 0.4, 1.5),
				new NormalBus("Test3", "002", stops, 0.25, 3.5),
				new NormalBus("Test4", "003", stops, 0.49, 4.9),
				new NormalBus("Test5", "004", stops, 1.1, 5.6)
		};
		
		
		Vehicle single_buse[] = new Vehicle[] { 
				new NormalBus("Test1", "000", stops, 0.5, 2.0)
		};
		
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Please select target stop: ");
		int stopIndex = scan.nextInt();
		StopSign userStop = stops[stopIndex];
		
		// Check if the distance logic is correct
		for (int i = 0; i < stops.length - 1; i++) {
			System.out.printf("%s - %s is %.2f%n", 
					stops[i].getStopName(), 
					stops[i + 1].getStopName(),
					UsefulFunc.distanceBetweenTwoPoints(stops[i].getPosition(), stops[i + 1].getPosition()));
		}
		
		System.out.println("-----------------");
		
		/* 
		for (Vehicle bus : buses) {
			System.out.println("----------------------");
			System.out.printf("%s bus is currently at %s stop, and is heading %s%n", 
					bus.getVehicleName(), 
					bus.getCurrentStop().getStopName(),
					bus.vehicleDirection());
			
			System.out.printf("Distance from %s bus to %s stop is %.2f%n", 
					bus.getVehicleName(), 
					userStop.getStopName(), 
					bus.distanceToTargetStop(userStop));
			
			System.out.printf("Estimated Time %.2f%n", 
					bus.getEstimatedTime(userStop));
			System.out.println("----------------------");
		}
		*/
		
		// 1 second in real life equals to 1 hour in this program
		
		while (true) {
			
			System.out.println("New Time------------------------------------");
			
			for (Vehicle bus : single_buse) {
				System.out.println("----------------------");
				System.out.printf("%s bus is currently at %s stop, and is heading %s to %s%n", 
						bus.getVehicleName(), 
						bus.getCurrentStop().getStopName(),
						bus.vehicleDirection(),
						bus.getNextStop().getStopName());
				
				System.out.printf("Distance from %s bus to %s stop is %.2f%n", 
						bus.getVehicleName(), 
						userStop.getStopName(), 
						bus.distanceToTargetStop(userStop));
				
				System.out.printf("Current speed_x: %.2f%n", bus.getCurrentSpeed()[0]);
				System.out.printf("Current speed_y: %.2f%n", bus.getCurrentSpeed()[1]);
				
				System.out.printf("Current Position: (%.2f, %.2f)%n", bus.getVehiclePostion()[0], bus.getVehiclePostion()[1]);
				
				System.out.printf("Estimated Time %.2f%n", 
						bus.getEstimatedTime(userStop));
				System.out.println("----------------------");
			}
			
			for (Vehicle bus : single_buse) {
				bus.update(0.1);
			}
			
			// wait before next loop
			try {
                Thread.sleep(500); // 1000 ms = 1 s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		
		/* 
		Vehicle testBus = new NormalBus("Test", "69", stops, 0.5, 50.0);
		
		StopSign currentStop = stops[3];
		System.out.printf("%s bus is currently at %s stop, and is heading %s%n", 
				testBus.getVehicleName(), 
				testBus.getCurrentStop().getStopName(),
				testBus.vehicleDirection());
		
		System.out.printf("Distance from %s bus to %s stop is %.2f", 
				testBus.getVehicleName(), 
				currentStop.getStopName(), 
				testBus.distanceToCertainStop(currentStop));
		*/
		
		
		/* 
		Vehicle testBus2 = new NormalBus("Test", "69", stops, 1800, 100.0, 4, true);
		
		for (int i = 0; i < stops.length - 1; i++) {
			System.out.printf("%s - %s is %.2f%n", 
					stops[i].getStopName(), 
					stops[i + 1].getStopName(),
					distanceBetweenTwoPoints(stops[i].getPosition(), stops[i + 1].getPosition()));
		}
		
		System.out.println("-----------------");
		
		for (StopSign stop: stops) {
			System.out.printf("%s -> %s is %.2f%n", 
					testBus2.getCurrentStop().getStopName(), 
					stop.getStopName(),
					testBus2.distanceToCertainStop(stop));
		}
		*/
	}

}
