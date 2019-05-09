import java.util.ArrayList;

public class Tree<T>{
	
	ArrayList<T> sortedList;
	Node<T> root;
	
	public Tree() {
		root = null;
		sortedList = new ArrayList<T>();
	}
	
	public void insert(T value) {
		root = insertRecursion(this.root, value);
	}
	
	public Node<T> insertRecursion(Node<T> root, T value) {
		
		if (root == null) {
			root = new Node<T>(value);
			return root;
		}
		
		if (root.compareTo(value) > 0) {
			root.left = insertRecursion(root.left, value);
		}
		
		else if (root.compareTo(value) < 0) {
			root.right = insertRecursion(root.right, value);
		}
		
		return root;
		
	}
	
	public void inorder(Node<T> root) {
		
		if (root != null) {
			inorder(root.left);
			sortedList.add(root.getValue());
			inorder(root.right);
		}
	}
	
	public void insertTree(ArrayList<T> list) {
		
		for (int i = 0; i < list.size(); i++) {
			insert(list.get(i));
		}
		
	}
	
	public void reverse() {
		int left = 0;
		int right = sortedList.size() - 1;
		T tmp;
		
		while (left < right) {
			tmp = sortedList.get(left);
			sortedList.set(left, sortedList.get(right));
			sortedList.set(right, tmp);		
			left++;
			right--;
		}
	}
	
	public String toString() {
		
		String output = "";
		
		for (int i = 0; i < sortedList.size(); i++) {
			output += sortedList.get(i) + " ";
		}
		
		return output;	
	}
	
}
