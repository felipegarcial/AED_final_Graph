package unionFind;

public interface IUnionFind<V> {
	
	public void makeset(V v);
	public int find(V v);
	public void union(V v, V u);

}
