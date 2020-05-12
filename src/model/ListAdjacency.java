package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ListAdjacency<V> implements IGraph<V> {
	int size;
	// der
	private Map<Integer, List<VertexConected<V>>> adjacents;
	// izq
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
		if (!vertexI.containsValue(v)) {
			Vertex<V> node = new Vertex<>(v, size);
			vertex.put(size, node);
			vertexI.put(v, size);
			List<VertexConected<V>> l = new ArrayList<>();
			adjacents.put(size, l);
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
		Queue<Vertex<V>> queue = new LinkedList<>();
		Vertex<V> f = search(v);
		if (vertexI.containsValue(v)) {
			queue.add(f);
			f.setColor(Vertex.GREY);
			while (!queue.isEmpty()) {
				Vertex<V> element = queue.poll();
				if (ret.size == 0) {
					ret.addVertex(element.getNode());
				}
				List<VertexConected<V>> lv = adjacents.get(element.getIndex());
				for (int i = 0; i < lv.size(); i++) {
					if (lv.get(i) != null && (lv.get(i).getV().getColor() == Vertex.WHITE)) {
						Vertex<V> n = search(lv.get(i).getV().getNode());
						n.setColor(Vertex.GREY);
						queue.add(n);
						ret.addEdge(element.getNode(), n.getNode(), lv.get(i).getWeigth());
					}
					Vertex<V> y = vertex.get(element);
					y.setColor(Vertex.BLACK);
				}
			}
			return ret;
		} else {
			return null;
		}
	}

	public Vertex<V> search(V v) {
		int i = vertexI.get(v);
		Vertex<V> r = vertex.get(i);
		return r;
	}

	@Override
	public ListAdjacency<V> dfs(V v) {
		ListAdjacency<V> ret = new ListAdjacency<V>();
		Stack<Vertex<V>> stack = new Stack<>();
		Vertex<V> f = search(v);
		if (vertexI.containsValue(v)) {
			stack.push(f);
			f.setColor(Vertex.GREY);
			while (!stack.isEmpty()) {
				Vertex<V> element = stack.peek();
				if (ret.size == 0) {
					ret.addVertex(element.getNode());
				}
				List<VertexConected<V>> lv = adjacents.get(element.getIndex());
				for (int i = 0; i < lv.size(); i++) {
					if (lv.get(i) != null && (lv.get(i).getV().getColor() == Vertex.WHITE)) {
						Vertex<V> n = search(lv.get(i).getV().getNode());
						n.setColor(Vertex.GREY);
						stack.push(n);
						ret.addEdge(element.getNode(), n.getNode(), lv.get(i).getWeigth());
					}
					Vertex<V> y = vertex.get(element);
					y.setColor(Vertex.BLACK);
				}
			}
			return ret;
		} else {
			return null;
		}
	}

	public void prim(V v) {
		Vertex<V> r = search(v);
		PriorityQueue<Vertex<V>> pq = new PriorityQueue<>();
		for (int i = 0; i <= vertex.size(); i++) {
			Vertex<V> e = vertex.get(i);
			e.setColor(Vertex.WHITE);
			e.setDistance(Integer.MAX_VALUE);
		}
		r.setDistance(0);
		while (!pq.isEmpty()) {
			Vertex<V> u = pq.poll();
			int index = vertexI.get(u.getNode());
			List<VertexConected<V>> ed = adjacents.get(index);// lista de adjacency
			for (int j = 0; j <= ed.size(); j++) {
				VertexConected<V> edge = ed.get(j);// adyacente en la posicion
				Vertex<V> node = edge.getV();// mi vertice adjacente
				if (node.getColor() == Vertex.WHITE && edge.getWeigth() < node.getDistance()) {
					node.setDistance((int) edge.getWeigth());
					pq.add(node);
					node.setPredecessor(u);
				}
			}
			u.setColor(Vertex.BLACK);
		}
	}

	@Override
	public IGraph<V> prim(IGraph<V> g, V v) {
		// que no se ppierda el grafo original y sacar el grafo a retornar
		IGraph<V> toReturn = new ListAdjacency<>();
		toReturn.addVertex(v);
//			toReturn.addEdge(u, v, w);
		return toReturn;
	}

	@Override
	public IGraph<V> kurskal(IGraph<V> g, V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGraph<V> dijsktra(IGraph<V> g, V v) {
		int[] dist = new int[vertex.size()];
		PriorityQueue<Vertex<V>> pq = new PriorityQueue<Vertex<V>>();
		dist[0] = 0;
				
		for (int i = 1; i < vertex.size(); i++) {
			if(vertex.get(i) != search(v)) {
				dist[i] = Integer.MAX_VALUE;
			}
			vertex.get(i).setDistance(dist[i]);
			pq.add(vertex.get(i));
		}
		
		while (!pq.isEmpty()) {
			Vertex <V> vPq = pq.poll();
			int key = vertexI.get(vPq);
			for (int i = 0; i < adjacents.get(key).size(); i++) {
				int alt = dist[i] + (adjacents.get(key).get(i).getWeigth());
				if(alt < dist[i]) {
					dist[i] = alt;
					//prev[i] = 
				}
			}
			
		}

		return null;
	}

	@Override
	public IGraph<V> floyd(IGraph<V> g) {
		// TODO Auto-generated method stub
		return null;
	}

}
