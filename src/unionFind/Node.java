package unionFind;

public class Node<V> {
	private V node;
	private Node<V> next;
	private Node<V> cola;
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

	public Node<V> getCola() {
		return cola;
	}

	public void setCola(Node<V> cola) {
		this.cola = cola;
	}
	
	public void add(Node<V> n) {
		if(next == null) {
			next = n;
		}
		else {
			next.add(n);
		}
	}
	
	public void updateId() {
		if(next!= null) {
			next.setId(id);
			next.updateId();
		}
	}
	
	public void updatePredesesor(Node<V> n) {
		this.presesor = n;
		if(next != null) {
			next.updatePredesesor(n);
		}
	}
	
}
