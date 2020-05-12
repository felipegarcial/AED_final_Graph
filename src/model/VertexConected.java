package model;

public class VertexConected<V> implements Comparable<VertexConected <V>> {
	private Vertex<V> vertex;
	private Vertex<V> vertexEnd;
	private int weigth; 

	public VertexConected(Vertex<V> start, Vertex<V> end, int weigth) {
		this.vertex = start;
		this.vertexEnd = end;
		this.weigth = weigth;
	}
	
	public Vertex<V> getV() {
		return vertex;
	}
	
	
	public void setVertex(Vertex<V> vertex) {
		this.vertex = vertex;
	}



	public int getWeigth() {
		return weigth;
	}

	@Override
	public int compareTo(VertexConected<V> o) {
		return (weigth- o.getWeigth());
	}

	public Vertex<V> getVertexEnd() {
		return vertexEnd;
	}

	public void setVertexEnd(Vertex<V> vertexEnd) {
		this.vertexEnd = vertexEnd;
	}
	
	

}
