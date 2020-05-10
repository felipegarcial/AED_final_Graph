package model;

import java.util.HashMap;
import java.util.Map;

public class ListAdjacency<V> implements IGraph<V> {
	int size;
	private Map<Integer, ListVertex<Vertex<V>>> listV;

	public ListAdjacency() {
		size =0;
		listV = new HashMap<>();
	}
	
	@Override
	public boolean addVertex(V v) {
		Vertex<V> node = new Vertex<>(v);
		ListVertex<Vertex<V>> l = new ListVertex<>(node);
		listV.put(size, l);
		return false;
	}

	@Override
	public void addEdge(V u, V v, double w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IGraph<V> bfs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGraph<V> dfs(V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGraph<V> prim(IGraph<V> g, V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGraph<V> kurskal(IGraph<V> g, V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGraph<V> dijsktra(IGraph<V> g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGraph<V> floyd(IGraph<V> g) {
		// TODO Auto-generated method stub
		return null;
	}

}
