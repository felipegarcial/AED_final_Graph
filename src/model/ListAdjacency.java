package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ListAdjacency<V> implements IGraph<V> {
	int size;
	private Map<Integer, ListVertex<Vertex<V>>> listV;

	public ListAdjacency() {
		size = 0;
		listV = new HashMap<>();
	}

	@Override
	public boolean addVertex(V v) {
		Vertex<V> node = new Vertex<>(v);
		ListVertex<Vertex<V>> l = new ListVertex<>(node);
		listV.put(size, l);
		size++;
		return false;
	}

	@Override
	public void addEdge(V u, V v, double w) {
		// TODO Auto-generated method stub

	}

	@Override
	public ListAdjacency<V> bfs(V v) {
		ListAdjacency<V> ret = new ListAdjacency<V>();
		Queue<V> queue= new LinkedList<V>();
		if (listV.containsValue(v)) {
		queue.add((V) listV.get(v).getNode());
		listV.get(v).getNode().setColor(Vertex.GREY);
			while(!queue.isEmpty()) {
				V element = queue.remove();
				ret.addVertex(v);
				ArrayList<VertexConected<Vertex<V>>> lv = listV.get(element).getAdjacents();
				for(int i=0;i<lv.size();i++) {
					if(lv.get(i) != null && (lv.get(i).getV().getColor() == Vertex.WHITE)) {
						queue.add((V) lv.get(i).getV());
					}
					listV.get(element).getNode().setColor(Vertex.BLACK);
				}
			}
			return ret;			
		}else {
			return null;
		}
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
