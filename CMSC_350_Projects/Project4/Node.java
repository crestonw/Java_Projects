
public class Node<T> implements Comparable<T> {
	
	private T value;
	Node<T> left, right;
	
	public Node(T value) {
		setValue(value);
		left = null;
		right = null;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public int compareTo(T o) {
		int cmp = ((Comparable<T>) this.value).compareTo(o);
		return cmp;
	}
}

