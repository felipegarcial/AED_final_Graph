package unionFind;

public class Node<V> {
	private V node;
	private Node<V> next;
	private Node<V> presesor;
	private int id;
	
	public Node(V n) {
		this.node=n;
		next = null;
	}

	public V getNode() {
		return node;
	}

	public void setNode(V node) {
		this.node = node;
	}

	public Node<V> getNext() {
		return next;
	}

	public void setNext(Node<V> next) {
		this.next = next;
	}

	public Node<V> getPresesor() {
		return presesor;
	}

	public void setPresesor(Node<V> presesor) {
		this.presesor = presesor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
