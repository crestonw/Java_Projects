/*
 * Author: Creston Wilson
 * References: CIS212 Lecture Slides
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Assignment2 {

// 1. 
	public static void main(String[] args) {
		
		// The user is prompted to enter an array length.
		System.out.println("Please enter an integer array lengh:");
		
		// A Scanner instance variable is initialized as input. 
		Scanner input = new Scanner(System.in);
		
		// The user input is saved as an integer.
		int intArrayLength = input.nextInt();
		
		// If the value given is less than 0, the user is prompted until condition is satisfied.
		while (intArrayLength < 0) {
			System.out.println("Please re-enter a positive integer:");
			intArrayLength = input.nextInt();
		}

		// The user is prompted to enter a double precision array density.
		System.out.println("Enter \'double precision\' array density:");
		
		// The double value is saved.
		double arrayDensity = input.nextDouble();
		
		// If the user's value isn't within the given range, he/she must re-enter the value until satisfied.
		while ((arrayDensity < 0) || (arrayDensity > 1)) {
			System.out.println("Please re-enter a value no smaller than 0.0 and no greater than 1.0:");
			arrayDensity = input.nextDouble();
		}
		
		
		
		// The Scanner object is closed.
		input.close();
		
//8. Use System.nanoTime() to calculate the amount of time taken to run each of the methods below.
		
/*		 When the values for arrayDensity and integerLength are kept consistent throughout the time tests, the populateDenseArray method has the greatest time to execute.
 * In about half the time with the same arguments the denseMaxAndTime and sparseMaxAndTime were executed. Followed in time by the convertToSparse, then something interesting
 * happened and the convertToDense took longer to execute than the populateSparseArray method. These methods worked the fastest with all my applications closed and with 
 * as low as possible values for arrayDensity and integerLength.
 */
		long InitialTime = System.nanoTime();
		populateDenseArray(10, 0.5);
		double timeElapse = (System.nanoTime() - InitialTime) / 1000;
		String timeElapsedString = timeElapse + "\u03BCs";
		System.out.printf("create dense length: %d time: %s%n\n", 10, timeElapsedString);
		
		InitialTime = System.nanoTime();
		convertToSparse(populateDenseArray(10, 0.5));
		timeElapse = (System.nanoTime() - InitialTime) / 1000;
		timeElapsedString = timeElapse + "\u03BCs";
		System.out.printf("convert to sparse length: %d time: %s%n\n", 10, timeElapsedString);
		
		
		InitialTime = System.nanoTime();
		populateSparseArray(10, 0.5);
		timeElapse = (System.nanoTime() - InitialTime) / 1000;
		timeElapsedString = timeElapse + "\u03BCs";
		System.out.printf("create sparse length: %d time: %s%n\n", 10, timeElapsedString);
		
		InitialTime = System.nanoTime();
		convertToDense(populateSparseArray(10, 0.5), 10);
		timeElapse = (System.nanoTime() - InitialTime) / 1000;
		timeElapsedString = timeElapse + "\u03BCs";
		System.out.printf("convert to dense length: %d time: %s%n\n", 10, timeElapsedString);
		
		InitialTime = System.nanoTime();
		denseMaxAndIndex(populateDenseArray(10,0.5));
		timeElapse = (System.nanoTime() - InitialTime) / 1000;
		timeElapsedString = timeElapse + "\u03BCs";
		System.out.printf("dense find time: %s%n\n", timeElapsedString);
		
		InitialTime = System.nanoTime();
		sparseMaxAndIndex(populateSparseArray(10,0.5));
		timeElapse = (System.nanoTime() - InitialTime) / 1000;
		timeElapsedString = timeElapse + "\u03BCs";
		System.out.printf("sparse find time: %s%n\n", timeElapsedString);

	}
// 2. Creates a dense array.
	public static ArrayList<Integer> populateDenseArray(int integerLength, double arrayDensity ) {
		
		// Initializes a new ArrayList of ArrayList of Integers of the size equal to the parameter integerLength.
		ArrayList<Integer> denseArray = new ArrayList<Integer>(integerLength);
		
		// Initializes the random generator. 
		Random generator = new Random();
		
		// The loop that populates the denseArray.
		for (int i = 0; i < integerLength; i++ ) {
			
			// The condition that compares the parameter density with a random value.
			if (arrayDensity >= generator.nextDouble()) {
				
				// Adds a random integer between 1 and 1000000 to the denseArray.
				denseArray.add(generator.nextInt(1000000));
				
			}
			
			// The condition that is executed if random generated value is greater than arrayDensity.
			else { 
				
				// Adds a 0 to the denseArray.
				denseArray.add(0);
			}
		}
		
		// Returns the denseArray and closes method.
		return denseArray;
		
	}

// 3. Creates a sparse array.
	public static ArrayList<int[]> populateSparseArray(int integerLength, double arrayDensity) {
		
		// Initiate the denseArray variable by calling the method populateDenseArray that returns a dense array.
		ArrayList<Integer> denseArray = populateDenseArray(integerLength, arrayDensity);
		
		// Initiate the sparseArray variable to hold integerLength of integer arrays.
		ArrayList<int[]> sparseArray = new ArrayList<int[]>(integerLength);
		
		// Loop through the integers in our denseArray.
		for (int integer: denseArray) {
			
			// If the integer in denseArray is not equal to zero.
			if (integer != 0) {
				
				// Initialize an array of integers to have the index position of the nonzero value in dense array followed by the integer value in dense array.
				int[] newArray = {denseArray.indexOf(integer), integer};

				// Add the integer array to the ArrayList of integer arrays called sparseArray.
				sparseArray.add(newArray);
				
			}
		}

		// Return the sparseArray.
		return sparseArray;
	}	

// 4. The method converts a dense array into a sparse array.
	public static ArrayList<int[]> convertToSparse(ArrayList<Integer> denseArray) {
		
		// Initiate the sparseArray variable to hold integerLength of integer arrays.
		ArrayList<int[]> sparseArray = new ArrayList<int[]>(denseArray.size());
				
		// Loop through the integers in our denseArray.
		for (int integer: denseArray) {
					
			// If the integer in denseArray is not equal to zero.
			if (integer != 0) {
						
				// Initialize an array of integers to have the index position of the nonzero value in dense array followed by the integer value in dense array.
				int[] newArray = {denseArray.indexOf(integer), integer};

				// Add the integer array to the ArrayList of integer arrays called sparseArray.
				sparseArray.add(newArray);
						
			}
		}

		// Return the sparseArray.
		return sparseArray;
	}

// 5. The method converts a sparse array into a dense array.
	public static ArrayList<Integer> convertToDense(ArrayList<int[]> sparseArray, int integerLength) {
		
		// Initializes a dense array variable of size integerLength
		ArrayList<Integer> denseArray = new ArrayList<Integer>(integerLength);
		
		// Loops through the dense array.
		for (int i = 0; i < integerLength; i++) {
			
			// Fills the dense array with zeros. 
			denseArray.add(0);
			
		}
		
		// Loops through the arrays of integers in a sparse array.
		for (int[] array : sparseArray) {
			
			// Sets the corresponding positions to the value in the dense array.
			denseArray.set(array [0], array [1]);
			
		}
		
		// Returns a dense array.
		return denseArray;
	}

//6. The method prints out the max integer and index of the max from a dense array.
	public static void denseMaxAndIndex(ArrayList<Integer> denseArray) {
		
		// Initialize the max value.
		int max = 0;
		// Initialize the index value.
		int index = 0;
		
		// Begin loop through integers in denseArray variable.
		for (int integer : denseArray) {
			
			// If the integer in denseArray is greater than max value.
			if (integer > max) {
				
				// Assign integer to max variable.
				max = integer;
				
				// Assign the index of the max to index variable.
				index = denseArray.indexOf(integer);
		
			}
		}
		
		// Print out max and index of the max value.
		System.out.printf("find max (dense): %d at: %d%n\n", max, index);
	}
	
//7. This method prints out the max integer and index of the max from the sparse array.
	public static void sparseMaxAndIndex(ArrayList<int[]> sparseArray) {
		
		// Initialize the max value.
		int max = 0;
		// Initialize the index value.
		int index = 0;
				
		// Begin loop through integers in denseArray variable.
		for (int[] array : sparseArray) {
					
			// If the integer in denseArray is greater than max value.
			if (array [1] > max) {
						
				// Assign integer to max variable.
				max = array [1];
						
				// Assign the index of the max to index variable.
				index = array [0];
				
			}
		}
				
		// Print out max and index of the max value.
		System.out.printf("find max (dense): %d at: %d%n\n", max, index);
	}
}


