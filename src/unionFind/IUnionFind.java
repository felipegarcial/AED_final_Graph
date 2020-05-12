package unionFind;

public interface IUnionFind<V> {
	
	public void makeset(V v);
	public V find(V v);
	public void union(V v, V u);

}
