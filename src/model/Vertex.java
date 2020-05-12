package model;

public class Vertex <V> implements Comparable<Vertex<V>>{
	public final static char WHITE = 'w';
	public final static char BLACK = 'b';
	public final static char GREY = 'g';
	private V node;
	private Vertex<V> predecessor;
	private int distance;
	private char color;
	private int index;
	
	public Vertex(V node, Integer index) {
		this.node= node;
		this.distance = Integer.MAX_VALUE;
		this.index = index;
		color = 'w';
	}
	
	public char getColor() {
		return color;
	}
	
	public void setColor(char newColor) {
		color = newColor;
	}
	
	public V getNode() {
		return node;
	}

	public int getIndex() {
		return index;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Vertex<V> o) {
		return distance-o.getDistance();
	}

	public Vertex<V> getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Vertex<V> predecessor) {
		this.predecessor = predecessor;
	}

}
