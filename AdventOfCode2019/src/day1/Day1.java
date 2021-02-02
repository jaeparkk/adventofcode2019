package day1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Day1 {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/day1/input.txt")));
			
			int totalFuelRequired = 0;
			int fuelOnFuel = 0;
			Iterator<String> it = br.lines().iterator();
			
			while (it.hasNext()) {
				int input = Integer.parseInt(it.next());
				totalFuelRequired += getFuelRequired(input);
				fuelOnFuel += getFuelRecursed(input);
			}
			System.out.println("Total fuel required: " + totalFuelRequired + "\nTotal fuel required (accounting for the total fuel required for the total fuel required): " + fuelOnFuel);
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File not found!");
		}
	}
	
	private static int getFuelRequired(int mass) {  // for use with part 1
		return mass / 3 - 2;
	}
	
	private static int getFuelRecursed(int mass) {  // for use with part 2
		int rec = mass / 3 - 2;
		if (rec <= 0)
			return 0;
		return rec + getFuelRecursed(rec);
	}
}
