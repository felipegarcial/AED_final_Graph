package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import unionFind.IUnionFind;
import unionFind.UnionFind;

public class ListAdjacency<V> implements IGraph<V> {
	private boolean directed;
	int size;
	// der
	private Map<Integer, List<VertexConected<V>>> adjacents;
	// izq
	private Map<Integer, Vertex<V>> vertex;
	private Map<V, Integer> vertexI;

	public ListAdjacency() {
		directed = true;
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
	public void addEdge(V u, V v, int w) {
		Vertex<V> v1 = search(v);
		int i1 = vertexI.get(v);
		Vertex<V> v2 = search(u);
		int i2 = vertexI.get(u);
		List<VertexConected<V>> l = new ArrayList<>();
		VertexConected<V> edge = new VertexConected<>(v1, v2, w);
		l.add(edge);
		adjacents.put(i1, l);
		if(directed) {
			List<VertexConected<V>> l2 = new ArrayList<>();
			VertexConected<V> edgeD = new VertexConected<>(v2, v1, w);
			l.add(edgeD);
			adjacents.put(i2, l2);
		}
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
	public ArrayList<V> dfs(V v) {
		ArrayList<V> dfs = new ArrayList<V>();
		boolean [] visited = new boolean[size];
		dfs(v, visited, dfs);
		return dfs;
	}
	
	private void dfs(V v, boolean[] visited, ArrayList<V> dfs) {
		dfs.add(v);
		int index = vertexI.get(v);
		visited[index] = true;
		List<VertexConected<V>> la = adjacents.get(index);
		for(int i=0;i<la.size();i++) {
			if (la.get(i) != null && visited[vertexI.get(la.get(i))] == false) {
				Vertex<V> vv = search(la.get(i).getV().getNode());
				dfs(vv.getNode(),visited,dfs);
			}
		}
		
		
		
	}

	public void prim(V v) {
		Vertex<V> r = search(v);
		PriorityQueue<Vertex<V>> pq = new PriorityQueue<>();
		for (int i = 0; i < vertex.size(); i++) {
			Vertex<V> e = vertex.get(i);
			e.setColor(Vertex.WHITE);
			e.setDistance(Integer.MAX_VALUE);
		}
		r.setDistance(0);
		pq.add(r);
		while (!pq.isEmpty()) {
			Vertex<V> u = pq.poll();
			int index = vertexI.get(u.getNode());
			List<VertexConected<V>> ed = adjacents.get(index);// lista de adjacency
			for (int j = 0; j <= ed.size(); j++) {
				VertexConected<V> edge = ed.get(j);// adyacente en la posicion
				Vertex<V> node = edge.getV();// mi vertice adjacente
				if (node.getColor() == Vertex.WHITE && edge.getWeigth() < node.getDistance()) {
					node.setDistance(edge.getWeigth());
					pq.add(node);//se tendria que actualizar?
					node.setPredecessor(u);
				}
			}
			u.setColor(Vertex.BLACK);
		}
	}

	@Override
	public IGraph<V> prim(IGraph<V> g, V v) {
		// que no se ppierda el grafo original y sacar el grafo a retornar
		IGraph<V> temp = this;
		IGraph<V> toReturn = new ListAdjacency<>();
		toReturn.addVertex(v);
//			toReturn.addEdge(u, v, w);
		return toReturn;
	}

	@Override
	public IGraph<VertexConected<V>> kurskal(IGraph<V> g, V v) {
		IGraph<VertexConected<V>> toReturn = new ListAdjacency<>();
		IUnionFind<Vertex<V>> ds = new UnionFind<>();
		PriorityQueue<VertexConected<V>> pq = new PriorityQueue<>();//ordenar que me saque el menor
		for(int i = 0;i<size;i++) {
			ds.makeset(vertex.get(i));
			List<VertexConected<V>> adj = adjacents.get(i);
			for (int j = 0; j < adj.size(); j++) {				
				pq.add(adj.get(j));
			}
		}
		while(!pq.isEmpty()) {
			VertexConected<V> edge = pq.poll();
			if(ds.find(edge.getV())!= ds.find(edge.getVertexEnd())) {//mira si start y end pertenecen al mismo conjunto
				toReturn.addVertex(edge);
				ds.union(edge.getV(), edge.getVertexEnd());
			}
		}
		return toReturn;
	}

	@Override
	public IGraph<V> dijsktra(V v) {
		int [] dist = new int[size];
		Vertex<V> ve = search(v); 
		PriorityQueue<Vertex<V>> pq = new PriorityQueue<>();
		dist[ve.getIndex()] = 0;
		ve.setDistance(0);
		for(int i = 0; i< size; i++) {
			if(ve.getIndex() != vertex.get(i).getIndex()) {
				vertex.get(i).setDistance(Integer.MAX_VALUE);
				vertex.get(i).setPredecessor(null);
				dist[vertex.get(i).getIndex()] = Integer.MAX_VALUE;
			}
			pq.add(vertex.get(i));
		}
		while(!pq.isEmpty()) {
			Vertex<V> u = pq.poll();
			int key = u.getIndex();
			List<VertexConected<V>> adjacent = adjacents.get(key);
			for(int j = 0; j<=adjacent.size(); j++ ) {
				int alt = dist[u.getIndex()] + adjacent.get(j).getWeigth();
				if(alt< adjacent.get(j).getVertexEnd().getDistance()) {
					adjacent.get(j).getVertexEnd().setDistance(alt);
					dist[adjacent.get(j).getVertexEnd().getIndex()] = alt;
					adjacent.get(j).getVertexEnd().setPredecessor(u);
					//update pq
					pq.remove(adjacent.get(j).getVertexEnd());
					pq.add(adjacent.get(j).getVertexEnd());
				}	
			}
		}
		return null;
	}


	@Override
	public int[][] floyd() {
		// TODO Auto-generated method stub
		return null;
	}

}
