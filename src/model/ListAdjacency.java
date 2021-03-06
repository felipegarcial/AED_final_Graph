package model;

import java.util.ArrayList;
import java.util.Collection;
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
	

	public void setDirected(boolean directed) {
		this.directed = directed;
	}

	public int getSize() {
		return size;
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
	
	public  ArrayList<Vertex<V>> getVertex() {
		ArrayList<Vertex<V>> toReturn = new ArrayList<>();
		for (int i = 0; i < vertex.size(); i++) {
			toReturn.add(vertex.get(i));
		}
		return toReturn;
	}

	@Override
	public void addEdge(V u, V v, int w) {
		Vertex<V> v1 = search(u);
		int i1 = vertexI.get(u);
		Vertex<V> v2 = search(v);
		int i2 = vertexI.get(v);
		List<VertexConected<V>> l =  adjacents.get(i1);
		VertexConected<V> edge = new VertexConected<>(v1, v2, w);
		l.add(edge);
		if(directed == false) {
			List<VertexConected<V>> l2 = adjacents.get(i2);
			VertexConected<V> edgeD = new VertexConected<>(v2, v1, w);
			l2.add(edgeD);
		}
	}

	@Override
	public ListAdjacency<V> bfs(V v) {
		ListAdjacency<V> ret = new ListAdjacency<V>();
		Queue<Vertex<V>> queue = new LinkedList<>();
		Vertex<V> f = search(v);
		//asumimmos que todas las distancias estan en infinito y todos en blanco con predesesor null
		if (f!=null) {
			queue.add(f);
			f.setColor(Vertex.GREY);
			f.setDistance(0);
			f.setPredecessor(null);
			while (!queue.isEmpty()) {
				Vertex<V> element = queue.poll();
				if (ret.size == 0) {
					ret.addVertex(element.getNode());
				}
				List<VertexConected<V>> lv = adjacents.get(element.getIndex());
				for (int i = 0; i < lv.size(); i++) {
					if (lv.get(i) != null && (lv.get(i).getVertexEnd().getColor() == Vertex.WHITE)) {
						Vertex<V> n = search(lv.get(i).getVertexEnd().getNode());
						n.setColor(Vertex.GREY);
						n.setDistance(element.getDistance()+1);
						n.setPredecessor(element);
						queue.add(n);
						ret.addEdge(element.getNode(), n.getNode(), n.getDistance());
					}
				}
				element.setColor(Vertex.BLACK);
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
			if (la.get(i) != null && visited[la.get(i).getVertexEnd().getIndex()] == false) {
				Vertex<V> vv = la.get(i).getVertexEnd();
				dfs(vv.getNode(),visited,dfs);
			}
		}	
	}
	

	@Override
	public IGraph<VertexConected<V>> prim(V v) {
		IGraph<VertexConected<V>> toReturn = new ListAdjacency<>();
		Vertex<V> r = search(v);
		r.setDistance(0);
		PriorityQueue<Vertex<V>> pq = new PriorityQueue<>();
		for (int i = 0; i < vertex.size(); i++) {
			Vertex<V> e = vertex.get(i);
			e.setColor(Vertex.WHITE);
			e.setDistance(Integer.MAX_VALUE);
			e.setPredecessor(null);
		}
		pq.add(r);
		while (!pq.isEmpty()) {
			Vertex<V> u = pq.poll();
			int index = vertexI.get(u.getNode());
			List<VertexConected<V>> ed = adjacents.get(index);// lista de adjacency
			for (int j = 0; j < ed.size(); j++) {
				VertexConected<V> edge = ed.get(j);// adyacente en la posicion
				Vertex<V> node = edge.getVertexEnd();// mi vertice adjacente
				if (node.getColor() == Vertex.WHITE && edge.getWeigth() < node.getDistance()) {
					node.setDistance(edge.getWeigth());
					node.setPredecessor(u);
					pq.add(node);
				}
			}
			u.setColor(Vertex.BLACK);
		}
		toReturn = returPrim();
		return toReturn;
	}
	
	public IGraph<VertexConected<V>> returPrim() {
		ListAdjacency<VertexConected<V>> toReturn = new ListAdjacency<>();
		for (int i = 0; i < vertex.size(); i++) {
			Vertex<V> u = vertex.get(i);
			List<VertexConected<V>> adja = adjacents.get(u.getIndex());
			for (int j = 0; j < adja.size(); j++) {
				VertexConected<V> t = adja.get(j);
				if(t.getVertexEnd().getPredecessor()!= null) {
					if(t.getVertexEnd().getPredecessor().getIndex() == u.getIndex()) {
						toReturn.addVertex(t);
					}					
				}
			}
		}
		return toReturn;
	}

	@Override
	public IGraph<VertexConected<V>> kurskal() {
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
			if(ds.find(edge.getV()).getId() != ds.find(edge.getVertexEnd()).getId()) {//mira si start y end pertenecen al mismo conjunto
				toReturn.addVertex(edge);
				ds.union(edge.getV(), edge.getVertexEnd());
			}
			//si me piden los disconexos le pongo el syso aqui del tama�o del arreglo del union
		}
		return toReturn;
	}

	@Override
	public ArrayList<Vertex<V>> dijsktra(V v) {//retornar arreglo de predesesores
		ArrayList<Vertex<V>> toReturn = new ArrayList<>();
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
			toReturn.add(null);
			pq.add(vertex.get(i));
		}
		while(!pq.isEmpty()) {
			Vertex<V> u = pq.poll();
			int key = u.getIndex();
			List<VertexConected<V>> adjacent = adjacents.get(key);
			for(int j = 0; j<adjacent.size(); j++ ) {
				int alt = dist[u.getIndex()] + adjacent.get(j).getWeigth();
				if(alt< adjacent.get(j).getVertexEnd().getDistance()) {
					adjacent.get(j).getVertexEnd().setDistance(alt);
					dist[adjacent.get(j).getVertexEnd().getIndex()] = alt;
					adjacent.get(j).getVertexEnd().setPredecessor(u);
					toReturn.set(adjacent.get(j).getVertexEnd().getIndex(), u);
					//update pq
					pq.remove(adjacent.get(j).getVertexEnd());
					pq.add(adjacent.get(j).getVertexEnd());
				}	
			}
		}
		return toReturn;
	}
	
	
    public int [][] Matrix(){
		int [][] matrix = new int[size][size];
		for (int i =0; i< matrix.length;i++) {
			for (int j = 0; j< matrix[0].length; j++) {
				if(i==j) {
					matrix[i][j] = 0;
				}
				else {					
					matrix[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		for (int i = 0; i < vertex.size(); i++) {
			Vertex<V> origin = vertex.get(i);
			List<VertexConected<V>> adjacent = adjacents.get(origin.getIndex());
			for (int j = 0; j <adjacent.size();j++) {
				VertexConected<V> currentEdge = adjacent.get(j);
				Vertex<V> destination = currentEdge.getVertexEnd();
				matrix[origin.getIndex()][destination.getIndex()] = currentEdge.getWeigth();
			}
		}
    	
    	return matrix;
    	
    }


	@Override
	public int[][] floyd() {
		int [][] initial= Matrix();
		int dist[][] = new int[size][size];
		int i,j,k;
		
		for(i=0;i<size;i++) {
			for(j=0;j<size;j++) {
				dist[i][j] = initial[i][j];
			}
		}
		
		for(k=0;k<size;k++) {
			for(i=0;i<size;i++) {
				for(j=0;j<size;j++) {
					int d1 = dist[i][j];
					int d2 = dist[i][k];
					int d3 = dist[k][j];
					if(d2!= Integer.MAX_VALUE && d3!= Integer.MAX_VALUE) {
						int d4 = ( d2+ d3);
						if(d1 > d4) {
							dist[i][j] = dist[i][k] + dist[k][j];
						}
					}
				}
			}
		}
		return dist;
	}

}
