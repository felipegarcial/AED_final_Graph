package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ListAdjacency<V> implements IGraph<V> {
	int size;
	//der
	private Map<Integer, List<VertexConected<Vertex<V>>>> adjacents;
	//izq
	private Map<Integer, Vertex<V>> vertex;
	private Map<V, Integer> vertexI;

	public ListAdjacency() {
		size = 0;
		adjacents = new HashMap<>();
		vertex = new HashMap<>();
		vertexI = new HashMap<>();
	}

	@Override
	public boolean addVertex(V v) {
		boolean toReturn = false;
		if(!vertexI.containsValue(v)) {
			Vertex<V> node = new Vertex<>(v, size);
			vertex.put(size, node);
			vertexI.put(v, size);
			List<VertexConected<Vertex<V>>> l = new ArrayList<>(); 
			adjacents.put(size,l );
			size++;
			toReturn = true;
		}
		return toReturn;
	}

	@Override
	public void addEdge(V u, V v, double w) {
		// TODO Auto-generated method stub

	}

	@Override
	public ListAdjacency<V> bfs(V v) {		
		ListAdjacency<V> ret = new ListAdjacency<V>();
		Queue<Vertex<V>> queue= new LinkedList<>();
		Vertex<V> f = search(v); 
		if (vertexI.containsValue(v)) {
		queue.add(f);
		f.setColor(Vertex.GREY);
			while(!queue.isEmpty()) {
				Vertex<V> element = queue.poll();
				if(ret.size == 0) {
				ret.addVertex(element.getNode());
				}
				List<VertexConected<Vertex<V>>> lv = adjacents.get(element.getIndex());
				for(int i=0;i<lv.size();i++) {
					if(lv.get(i) != null && (lv.get(i).getV().getColor() == Vertex.WHITE)) {
						Vertex<V> n = search(lv.get(i).getV().getNode());
						n.setColor(Vertex.GREY);
						queue.add(n);
						ret.addEdge(element.getNode(),n.getNode(),lv.get(i).getWeigth());
					}
					Vertex<V> y =vertex.get(element);
					y.setColor(Vertex.BLACK);
				}
			}
			return ret;			
		}else {
			return null;
		}
	}
	
	public Vertex<V> search(V v){		
		int i = vertexI.get(v);
		Vertex<V> r = vertex.get(i);
		return r;
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
