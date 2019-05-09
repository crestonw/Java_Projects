/* Author: Creston Wilson
 * References: Professor Lei Jiao, CIS212 Course Lecture Slides and Example Code and Textbook, stackoverflow.com 
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OccurrenceSet<T> implements Set<T> {

	// A new Comparator instance that will allow us to sort by values within the HashMap.
    private Comparator<Map.Entry<T, Integer>> comparer = new Comparator<Map.Entry<T, Integer>>() {
		public int compare(Map.Entry<T, Integer> object1, Map.Entry<T, Integer> object2) {
			return object1.getValue().compareTo(object2.getValue());
		}
    };
    
	// Private class variable.
	private HashMap<T, Integer> hMap;
	
	// Constructor that creates a HashMap.
	public OccurrenceSet() {

		hMap = new HashMap<T, Integer>();
	}

	// Override that returns the size of the HashMap.
	@Override
	public int size() {

		return hMap.keySet().size();
	}

	// Override that determines whether or not the argument object is contained within the HashMap.
	@Override
	public boolean contains(Object o) {

		return hMap.containsKey(o);
	}

	// Override for the iterator method that returns an iterator instance.
	@Override
	public Iterator<T> iterator() {
        
        // Creating an ArrayList instance of the HashMap entries so they can be sorted as a Collection	 
		Set<Map.Entry<T, Integer>> hMapSet = hMap.entrySet();
		ArrayList<Map.Entry<T, Integer>> hMapList = new ArrayList<>(hMapSet);
		
		// Sort using Comparator and ArrayList.
		Collections.sort(hMapList, comparer);
		
		
		// Override of the Iterator class for this Iterator instance.
        Iterator<T> entrySet = new Iterator<T>() {
        	
            private int currentIndex = 0;
            public final ArrayList<Map.Entry<T, Integer>> entryList = hMapList;
            
            
            // Determines if there's an element in the next index position of the iterator.
            @Override
            public boolean hasNext() {
                return currentIndex < hMap.size() && entryList.get(currentIndex++) != null;
            }

            // Returns the next position in the iterator.
            @Override
            public T next() {
                return entryList.get(currentIndex++).getKey();
             
            }

            // Left out per instruction.
            @Override
            public void remove() {
            		return;
            }
        };
        
		// Iterator instance returned
        return entrySet;
	}

	// Override that converts the HashMap into an Array and returns the Array.
	@Override
	public Object[] toArray() {
		
		// Creates an iterator and sorts HashMap.
		Iterator<T> it = iterator();
		
		T[] array = (T[]) new Object[hMap.size()];
		
		
		for(int i = 0; i < hMap.size(); i++) { 
			
			Array.set(array, i, it.next());
			
		}

		return array;
	}

	// Override that takes an argument array and replace. 
	@Override
	public <T> T[] toArray(T[] a) {
		
		T[] array2 = (T[]) new Object[hMap.size()];
		
		for (int i = 0; i < array2.length; i++) {
			Array.set(array2, i, a[i]);
		}
		
		return array2;
	}

	// Override that adds an key-value pair to the HashMap.
	@Override
	public boolean add(T e) {
		
		if (hMap.putIfAbsent(e, 1) == null){
			return true;
		}
		
		// If the HashMap already contains the key than the value of the key is increased by 1.
		else if (hMap.containsKey(e)) {
			int value = hMap.get(e) + 1;
			hMap.put(e, value);
			return true;
		}
		
		return false;
	}

	// Override that removes an argument object from the HashMap.
	@Override
	public boolean remove(Object o) {
		
		if (hMap.remove(o) != null) return true;
		
		return false;
	}

	// Override that determines if the HashMap containsAll the objects in the argument collection.
	@Override
	public boolean containsAll(Collection<?> c) {

		boolean logic_controller = true;

		for (Object object: c) {
			if (hMap.containsKey(object) != true) return false;
		}

		return logic_controller;
	}

	// Override that adds all the objects in the argument collection to the HashMap.
	@Override
	public boolean addAll(Collection<? extends T> c) {
		
		for (T object: c) {
			
			if (hMap.put(object, null) != null) {
				
				return true;
			}
		}
		
		return false;
	}

	// Override that removes all the objects in the HashMap except for those found in the argument collection as well.
	@Override
	public boolean retainAll(Collection<?> c) {

		for (Object o: hMap.keySet()) {
			
			if(c.contains(o) != true) {
				
				hMap.remove(o);
			}
		}
		
		return false;
	}

	// Override that removes all the objects from HashMap that coincide with the argument collection.
	@Override
	public boolean removeAll(Collection<?> c) {
		
		for (Object object: c) {
			
			if (hMap.remove(object) != null) {
				continue;
			}
			
			else {
				return false;
			}
				
		}
		return true;
	}

	// Override that clears the HashMap of objects.
	@Override
	public void clear() {

		hMap.clear();
	}

	// Override that determines if the HashMap is empty or not.
	@Override
	public boolean isEmpty() {

		return hMap.isEmpty();
	}

	// Override that prints a string representation of the ordered HashMap.
	public String toString() {
		
		Iterator<T> it = iterator();
		int i = hMap.size();
		String str = new String();
		
		System.out.print("[");
		while (i > 0) {
			System.out.print(it.next());
			if (i != 1) {
				
				System.out.print(", ");

			}
			i--;
		}
		System.out.print("]");
		
		return str;
	}

}
