import java.io.*;
import java.util.*;

public class BuildGraph<T> {

	private ArrayList<LinkedList<Integer>> adjArray;
	private HashMap<T, Integer> nameMap;
	private HashMap<Integer, String> intMap;
	private Stack<T> stack = new Stack<T>();
	
	public BuildGraph(String fileName) throws FileNameException {
		adjArray = new ArrayList<LinkedList<Integer>>();
		nameMap = new HashMap<T, Integer>();
		intMap = new HashMap<Integer, String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = "";
			String[] list;
			int index = 0;
			int listIndex;
			
			while((line = br.readLine()) != null) {
				list = line.split(" ");
				listIndex = 0;
				
				for(String s : list) {
					if (!nameMap.containsKey(s)) {
						
						if (listIndex == 0) {
							// Create relationship in HashMap and create LinkedList.
							nameMap.put((T) s, index);
							intMap.put(index, s);
							adjArray.add(new LinkedList<Integer>());
						}
						
						else {
							// Create relationship in HashMap, create LinkedList, and add integer to list.
							nameMap.put((T) s, index);
							intMap.put(index, s);
							adjArray.add(new LinkedList<Integer>());
							addEdge(index, list[0]);
						}
						
						// Print current LinkedList that we are inputting and increment index.
						index++;
					}
					
					else if (nameMap.containsKey(s) && listIndex != 0) {
						addEdge(nameMap.get(s), list[0]);
					}
					
					listIndex++;
				}
			}
		}
		
		catch(FileNotFoundException e) {
			throw new FileNameException();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addEdge(Integer item, String position) {
		adjArray.get(nameMap.get(position)).add(item);
	}
	
	public void topoOrder(String vertex) throws CycleDetectedException, ClassNotFoundException {
		
		if (!nameMap.containsKey(vertex)) {
			throw new ClassNotFoundException();
		}
		
		LinkedList<Integer> list = adjArray.get(nameMap.get(vertex));
		
		if(stack.contains(vertex)) {
			throw new CycleDetectedException();
		}
		
		for (Integer i : list) {
			
			if (i == nameMap.get(vertex)) {
				throw new CycleDetectedException();
			}
			
			topoOrder(intMap.get(i));
		}
		stack.push((T) vertex);
	}
	
	public Stack<T> getStack() {
		return stack;	
	}
}
