package model;

public interface IGraph <V>{
	
	public boolean addVertex(V v);
	public void addEdge(V u, V v, int w);
	public IGraph<V> bfs(V v);
	public IGraph<V> dfs(V v);
	public IGraph<V> prim(IGraph<V> g, V v);
	public IGraph<V> kurskal(IGraph<V> g, V v);
	public IGraph<V> dijsktra(IGraph<V> g,V v);
	public IGraph<V> floyd(IGraph<V> g);
	
}
