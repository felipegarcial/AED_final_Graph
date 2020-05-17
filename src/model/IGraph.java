package model;

import java.util.ArrayList;

public interface IGraph <V>{
	public boolean addVertex(V v);
	public void addEdge(V u, V v, int w);
	public IGraph<V> bfs(V v);
	public ArrayList<V> dfs(V v);
	public IGraph<VertexConected<V>> prim(V v);
	public IGraph<VertexConected<V>> kurskal();
	public ArrayList<Vertex<V>> dijsktra(V v);
	public int[][] floyd();
	
}
