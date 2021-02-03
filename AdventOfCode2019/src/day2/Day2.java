package day2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Day2 {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/day2/input.txt")));
			String[] opcodes = br.readLine().split(",");
			String[] opcodes2 = opcodes.clone();
			br.close();
			
			System.out.println(toString(Intcode(opcodes)));
			nounAndVerb(50, 50, opcodes2);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File not found!");
		}
	}
	
	private static void nounAndVerb(int noun, int verb, String[] opcodes) {  // for use with part 2, NOT WORKING
		if (noun == 99 || noun == 0 || verb == 0 || verb == 99) {
			return;
		}
		int address0 = Integer.parseInt(opcodes[0]);
		String[] test = opcodes.clone();
		System.out.println("hi");
		if (address0 > 19690720) {
			if (Integer.parseInt(Intcode(noun - 1, verb, test)[0]) < 19690720) {
				if (verb == 0) {
					System.out.println("Error!");
					return;
				}
				nounAndVerb(noun, verb - 1, opcodes);
			} else {
				nounAndVerb(noun - 1, verb, opcodes);
			}
		
		} else if (address0 < 19690720) {
			if (Integer.parseInt(Intcode(noun + 1, verb, test)[0]) > 19690720) {
				nounAndVerb(noun, verb + 1, opcodes);
			} else {
				nounAndVerb(noun + 1, verb, opcodes);
			}
		} else {
			System.out.println(noun + " " + verb);
			return;
		}
		return;
		
	}
	
	private static String[] Intcode(int noun, int verb, String[] opcodes) {   // overloaded function for testing with part 2
		for (int i = 0; i < opcodes.length; i+=4) {
			int targetOverwrite = Integer.parseInt(opcodes[i+3]);
			int arg1 = Integer.parseInt(opcodes[noun]);
			int arg2 = Integer.parseInt(opcodes[verb]);
			
			if (opcodes[i].equals("1")) {
				opcodes[targetOverwrite] = String.valueOf(arg1 + arg2); 
			} else if (opcodes[i].equals("2")) {
				opcodes[targetOverwrite] = String.valueOf(arg1 * arg2); 
			} else {
				break;
			}
		}
		return opcodes;
	}
	
	private static String[] Intcode(String[] opcodes) {  // for use with part 1
		for (int i = 0; i < opcodes.length; i+=4) {
			int targetOverwrite = Integer.parseInt(opcodes[i+3]);
			int arg1 = Integer.parseInt(opcodes[Integer.parseInt(opcodes[i+1])]);
			int arg2 = Integer.parseInt(opcodes[Integer.parseInt(opcodes[i+2])]);
			
			if (opcodes[i].equals("1")) {
				opcodes[targetOverwrite] = String.valueOf(arg1 + arg2); 
			} else if (opcodes[i].equals("2")) {
				opcodes[targetOverwrite] = String.valueOf(arg1 * arg2); 
			} else {
				break;
			}
		}
		return opcodes;
	}
	
	private static String toString(String[] opcodes) {
		String text = opcodes[0];
		for (int i = 1; i < opcodes.length; i++) {
			text += "," + opcodes[i];
		}
		return text;
	}
}
