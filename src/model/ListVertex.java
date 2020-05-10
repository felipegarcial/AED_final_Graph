package model;

import java.util.ArrayList;

public class ListVertex<V> {
	private V node;
	private ArrayList<VertexConected<V>> adjacents; 
	
	public ListVertex(V node) {
		this.node = node;
	}
	
	public ArrayList<VertexConected<V>> getAdjacents(){
		return adjacents;
	}

	public V getNode() {
		return node;
	}

	
}
