/* Author: Creston Wilson
 * References: Professor Lei Jiao, CIS212 Course Lecture Slides and Example Code and Textbook, stackoverflow.com 
 */

// Import Statements
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Assignment5 {
	
	// Shared variable that will hold all the lines in given file.
	private static ArrayList<String> entries = new ArrayList<String>(50000);
	
	public static void main(String[] args) throws IOException {

		
		// Path of the given fill saved in variable.
		Path p = Paths.get("src", "/phonebook.txt");
		
		// Reader that will be used to iterate through the lines of the file.
		BufferedReader br = Files.newBufferedReader(p);
		
		// While the line in question is not empty we add the line to our shared variable entries.
		String line = br.readLine();
		while (line != null) {
			entries.add(line);
			line = br.readLine();
		}
		
		System.out.println(mergeSort(entries));

		// Some tests of the methods implemented below.
		write(entries, "new");

		timer();
		
		// We are done with the file, so it's closed.
		br.close();
	}

	public static void write(ArrayList<String> phoneBook, String input) throws IOException {
		
		// Create a new file in source folder called Output.txt
		File file = new File("src", "Output.txt");
		
		// Using the new file we open a FileWriter to write into the file.
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
		
		// Use a buffered writer to write in the new file.
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (String str : phoneBook) {
			
			// If the string contains the input string then it's copied to new file.
			if (str.contains(input)) {
				bw.write(str);;
				bw.newLine();
		
		// Flushes the buffer, so all the lines in buffer are now written into our new file.	
		bw.flush();
				
			}
		}
	}

	public static ArrayList<String> alphaSort(ArrayList<String> list) {
		
		// Creates a copy of the argument array.
		ArrayList<String> newList = list;
		
		// The outer loop that marks the boundary between the sorted portion and unsorted portion of newList.
		for (int j = 0; j < (newList.size()-1); j++) {
			
			// The minimum index is set to the beginning of the unsorted list.
			int minIndex = j;
			
			// The inner loop iterates through the unsorted strings in the list.
			for (int i = j+1; i < newList.size(); i++) {
				
				// Compares two strings at a time to determine which string comes first.
				if (newList.get(minIndex).split(" ")[1].compareTo(newList.get(i).split(" ")[1]) > 0) {
					
					// If argument string precedes the string at minIndex, then minIndex is set to argument string's index.
					minIndex = i;
					
				}
			}
			// For each iteration through outer loop the jth string is swapped with the minIndex string.
			String temp = newList.get(j);
			newList.set(j, newList.get(minIndex));
			newList.set(minIndex, temp);
		}
		
		// Our sorted list is returned.
		return newList;
		
	}
	
	public static ArrayList<String> mergeSort(ArrayList<String> list) {
		
		// Base case for recursion.
		if (list.size() <= 1) return list;
		
		// Creates a left list, right list, and copy of argument list.
		ArrayList<String> arrayLeft = new ArrayList<String>();
		ArrayList<String> arrayRight = new ArrayList<String>();
		ArrayList<String> sortedArray = list;
		
		// Sets the middle of the list to be half the size of copy list.
		int middle = sortedArray.size()/2; 
			
		// Add the first half of the copy list to left list
		for (int i = 0;  i < middle; i++) {
			arrayLeft.add(sortedArray.remove(0));
			}
		
		// Adds the rest of the copy list to the right list.
		while (sortedArray.size() != 0) {
			arrayRight.add(sortedArray.remove(0));
		}
			
		// Calls itself on the left and right list until the base case is satisfied.
		arrayLeft = mergeSort(arrayLeft);
		arrayRight = mergeSort(arrayRight);
		
		// For each base case string in left and right list.
		while ((arrayLeft.size() != 0) && (arrayRight.size() != 0)) {
			
			// If left string is comes before right string alphabetically, then left string is removed from left list and added to copy list.
			if (arrayLeft.get(0).split(" ")[1].compareTo(arrayRight.get(0).split(" ")[1]) < 0) {
				sortedArray.add(arrayLeft.remove(0));
			}
			// Otherwise, right string is removed from right list and added to the copy list.
			else {
				sortedArray.add(arrayRight.remove(0));
			}
		}
		
		// If there are any remaining strings in left list they are removed and added to copy list.
		while (arrayLeft.size() != 0) {
			sortedArray.add(arrayLeft.remove(0));
		}
		
		// If there are any remaining strings in right list they are removed and added to copy list.
		while(arrayRight.size() != 0) {
			sortedArray.add(arrayRight.remove(0));
		}
		
		// Sorted copy of argument list is returned.
		return sortedArray;
		
	}
	
	public static Boolean isSorted(ArrayList<String> list) {
		
		// Makes a copy of the argument list.
		ArrayList<String> copyArray = list;
		
		// Iterates through the size of copy list - 1, comparing each last name string with the following last name string.
		for (int j = 0; j < (copyArray.size()-1); j++) {
			
			if (copyArray.get(j).split(" ")[1].compareTo(copyArray.get(j+1).split(" ")[1]) > 0) {
				
				// If any of the strings are out of alphabetical order we return false.
				return false;
			}
		}
		
		// Otherwise, the argument list is sorted and we return true.
		return true; 
	}
	
	public static void timer() {
		
		// Measures and prints the execution time of the alphasSort() method in seconds.
		long InitialTime = System.currentTimeMillis();
		alphaSort(entries);
		double timeElapse = (System.currentTimeMillis() - InitialTime) / 1000;
		System.out.printf("Selection Sort: %s seconds \n", timeElapse);
		
		// Measures and prints the execution time of the mergeSort() method in milliseconds.
		InitialTime = System.currentTimeMillis();
		mergeSort(entries);
		double timeElapse2 = (System.currentTimeMillis() - InitialTime);
		System.out.printf("Merge Sort: %s milliseconds \n", timeElapse2);
		
	}
}
