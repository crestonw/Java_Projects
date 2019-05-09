/* Author: Creston Wilson 10/4/17
 * References: stackoverflow.com & CIS212 Lecture Slides
 * Project 1/ Fall CIS_212
 */

import java.util.Scanner;

public class ListSum {

	public static void main(String[] args) {
		
		// Prompt the user concerning their options and assign variables.
		System.out.println("Enter a positive integer \"-3 to print, -2 to reset, -1 to quit\":");
		int sum = 0;
		
		// Read an integer from the user.
		Scanner input = new Scanner(System.in);
		int number = input.nextInt();

		while (number != -1) {
			// If the user enters -3, we print the current sum and then prompt the user for another integer.
			if (number == -3) {
				System.out.println("Sum: " + sum);
				System.out.println("Enter a positive integer \"-3 to print, -2 to reset, -1 to quit\":");
				number = input.nextInt();
				
				}
		
			// If the user enters -2, we set the current sum back to 0 and then prompt the user for another integer.
			else if (number == -2) {
				sum = 0;
				System.out.println("Enter a positive integer \"-3 to print, -2 to reset, -1 to quit\":");		
				number = input.nextInt();
				}
				
			// If the user enters a positive integer, we add it to the current sum and then prompt the user for another integer.
			else if (number > 0) {
				sum = sum + number;
				System.out.println("Enter a positive integer \"-3 to print, -2 to reset, -1 to quit\": ");
				number = input.nextInt();
				}
				
			// If the user enters a number less than -3 or equal to 0, we ignore and prompt the user again.
			else if (number == 0 || number < -3) {
				System.out.println("Enter a positive integer \"-3 to print, -2 to reset, -1 to quit\": ");
				number = input.nextInt();
				}
			}	
		
		// If the user enters -1, the loop is broken and we print the sum and then quit the application.
		System.out.println("Sum: " + sum);
		input.close();
		return;
		}
		
	}