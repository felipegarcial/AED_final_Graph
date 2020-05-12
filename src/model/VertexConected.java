package model;

public class VertexConected<V> implements Comparable<VertexConected <V>> {
	private Vertex<V> vertex;
	private Vertex<V> vertexEnd;
	private double weigth;

	public Vertex<V> getV() {
		return vertex;
	}
	
	public double getWeigth() {
		return weigth;
	}

	@Override
	public int compareTo(VertexConected<V> o) {
		return (int) (weigth- o.getWeigth());
	}

	public Vertex<V> getVertexEnd() {
		return vertexEnd;
	}

	public void setVertexEnd(Vertex<V> vertexEnd) {
		this.vertexEnd = vertexEnd;
	}
	
	

}
