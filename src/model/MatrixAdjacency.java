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

public class MatrixAdjacency <V> implements IGraph<V> {
	public static int QUANTITY_VERTEX = 15;
	private Map<Integer, Vertex<V>> vertex;
	private Map<V, Integer> indexVertex;
	private int [][] matrixWeight;
	private int size;
	
	public MatrixAdjacency() {
		size = 0;
		vertex = new  HashMap<>();
		indexVertex = new HashMap<>();
		matrixWeight = new int [QUANTITY_VERTEX][QUANTITY_VERTEX];
		initMatrix();
	}
	
	public void initMatrix() {
		for (int i = 0; i < matrixWeight.length; i++) {
			for (int j = 0; j < matrixWeight.length; j++) {
				if(i==j) {
					matrixWeight[i][j] = 0;
				}else{					
					matrixWeight[i][j] = Integer.MAX_VALUE;
				}
			}
		}
	}
	
	   private ArrayList<VertexConected<V>> getAList(V v) {//test
	        int index = indexVertex.get(v);
	        Vertex<V> e = vertex.get(index);
	        ArrayList<VertexConected<V>> result = new ArrayList<>();
	        for(int i=0; i<matrixWeight[index].length; i++) {
	            if(matrixWeight[index][i] != 0 && matrixWeight[index][i] != Double.POSITIVE_INFINITY) {
	                result.add(new VertexConected<V>(e, vertex.get(i), matrixWeight[index][i]));
	            }
	        }
	        return result;
	    }

	@Override
	public boolean addVertex(V v) {
		Vertex<V> ve= new Vertex<>(v, size);
		boolean toReturn = false;
		if(!vertex.containsValue(v)) {
			vertex.put(size, ve);
			indexVertex.put(ve.getNode(), size);
			toReturn = true;
			size++;
		}
		return toReturn;
	}

	@Override
	public void addEdge(V u, V v, int w) {
		int i = indexVertex.get(u);
		int j = indexVertex.get(v);
		matrixWeight[i][j] = w;
		System.out.println(i+""+j);
	}

	@Override
	public ListAdjacency<V> bfs(V v) {//Test
		ListAdjacency<V> ret = new ListAdjacency<V>();
		Queue<Vertex<V>> queue = new LinkedList<>();
		int index = indexVertex.get(v);
	    Vertex<V> f = vertex.get(index);
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
				List<VertexConected<V>> lv = getAList(element.getNode());
				for (int i = 0; i < lv.size(); i++) {
					if (lv.get(i) != null && (lv.get(i).getVertexEnd().getColor() == Vertex.WHITE)) {
						int index2 = indexVertex.get(lv.get(i));
						Vertex<V> n = vertex.get(index2);;
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

	@Override
	public ArrayList<V> dfs(V v) {//test
		ArrayList<V> dfs = new ArrayList<V>();
		boolean [] visited = new boolean[QUANTITY_VERTEX];
		dfs(v, visited, dfs);
		return dfs;
	}
	
	private void dfs(V v, boolean[] visited, ArrayList<V> dfs) {
		dfs.add(v);
		int index = indexVertex.get(v);
		visited[index] = true;
		List<VertexConected<V>> lv = getAList(v);
		for(int i=0;i<lv.size();i++) {
			if (lv.get(i) != null && visited[lv.get(i).getVertexEnd().getIndex()] == false) {
				Vertex<V> vv = lv.get(i).getVertexEnd();
				dfs(vv.getNode(),visited,dfs);
			}
		}	
	}
	

	@Override
	public IGraph<V> prim(V v) {//test
		ListAdjacency<V> toReturn = new ListAdjacency<>();
		int index = indexVertex.get(v);
	    Vertex<V> f = vertex.get(index);
	    f.setDistance(0);
		PriorityQueue<Vertex<V>> pq = new PriorityQueue<>();
		for (int i = 0; i < vertex.size(); i++) {
			Vertex<V> e = vertex.get(i);
			toReturn.addVertex(e.getNode());
			Vertex<V> e1 = toReturn.search(e.getNode());
			e1.setColor(Vertex.WHITE);
			e1.setDistance(Integer.MAX_VALUE);
			e1.setPredecessor(null);
		}
		pq.add(f);
		while(!pq.isEmpty()) {
			Vertex<V> u = pq.poll();
			ArrayList<VertexConected<V>> adjacent = getAList(u.getNode());
			for (int j = 0; j < adjacent.size(); j++) {
				VertexConected<V> edge = adjacent.get(j);
				Vertex<V> node = toReturn.search(edge.getVertexEnd().getNode());
				if (node.getColor() == Vertex.WHITE && edge.getWeigth() < node.getDistance()) {
					node.setDistance(edge.getWeigth());
					node.setPredecessor(u);
					pq.add(node);
				}
			}
			Vertex<V> u2 =toReturn.search(u.getNode());
			u2.setColor(Vertex.BLACK);
		}
		return toReturn;
	}

	@Override
	public IGraph<VertexConected<V>> kurskal() {
		IGraph<VertexConected<V>> toReturn = new ListAdjacency<>();
		IUnionFind<Vertex<V>> ds = new UnionFind<>();
		PriorityQueue<VertexConected<V>> pq = new PriorityQueue<>();
		for(int i = 0;i<size;i++) {
			ds.makeset(vertex.get(i));
			ArrayList<VertexConected<V>> adjacent = getAList(vertex.get(i).getNode());
			for (int j = 0; j < adjacent.size(); j++) {				
				pq.add(adjacent.get(j));
			}
		}
		while(!pq.isEmpty()) {
			VertexConected<V> edge = pq.poll();
			if(ds.find(edge.getV()).getId() != ds.find(edge.getV()).getId()) {
				toReturn.addVertex(edge);
				ds.union(edge.getV(), edge.getVertexEnd());
			}
		}
		
		return toReturn;
	}

	@Override
	public IGraph<V> dijsktra(V v) {//debe retornar grafo de aristas?
		int [] dist = new int[size];
		int index = indexVertex.get(v);
	    Vertex<V> ve = vertex.get(index);
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
	    	ArrayList<VertexConected<V>> adjacent = getAList(vertex.get(key).getNode());
	    	for(int j = 0; j<adjacent.size(); j++ ) {
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
		return null;//TODO check method of lsit
	}

	@Override
	public int[][] floyd() {
		int dist[][] = new int[QUANTITY_VERTEX][QUANTITY_VERTEX];
		int i,j,k;
		
		for(i=0;i<QUANTITY_VERTEX;i++) {
			for(j=0;j<QUANTITY_VERTEX;j++) {
				dist[i][j] = matrixWeight[i][j];
			}
		}
		
		for(k=0;k<QUANTITY_VERTEX;k++) {
			for(i=0;i<QUANTITY_VERTEX;i++) {
				for(j=0;j<QUANTITY_VERTEX;j++) {
					if(dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		return dist;
	}
			
}
