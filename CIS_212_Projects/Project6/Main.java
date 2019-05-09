/* Author: Creston Wilson
 * References: Professor Lei Jiao, CIS212 Course Lecture Slides and Example Code and Textbook, stackoverflow.com 
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		
		// Add Tests for Integer OccurrenceSet
		OccurrenceSet<Integer> intSet = new OccurrenceSet<Integer>();
		
		intSet.add(1);
		intSet.add(3);
		intSet.add(5);
		intSet.add(5);
		intSet.add(3);
		intSet.add(3);
		intSet.add(3);
		
		// Contains and ContainsAll Tests
		ArrayList<Integer> i = new ArrayList<Integer>(10);

		i.add(3);
		i.add(3);
		i.add(5);
		
		System.out.println(intSet.containsAll(i));
		System.out.println(intSet.contains(1));
		
		// toArray() Test
		Object[] array = intSet.toArray();
		for(Object o: array) {
			System.out.print(o);
		}
		
		// String Representation Test
		System.out.println(intSet);
		
		// Add Tests for String OccurrenceSet
		OccurrenceSet<String> stringSet = new OccurrenceSet<String>();
		
		stringSet.add("hello");
		stringSet.add("hello");
		stringSet.add("world");
		stringSet.add("world");
		stringSet.add("world");
		stringSet.add("here");
		
		// Print Test
		System.out.println(stringSet);
		
		// toArray(T[]) Test
		Object[] array2 = stringSet.toArray(array);
		for(Object o: array2) {
			System.out.print(o);
		}
	}

}
