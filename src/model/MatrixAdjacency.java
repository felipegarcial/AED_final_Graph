package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixAdjacency <V> implements IGraph<V> {
	public static int QUANTITY_VERTEX = 15;
	private Map<Integer, Vertex<V>> vertex;
	private Map<Vertex<V>, Integer> indexVertex;
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
	
	   private ArrayList<VertexConected<V>> getAList(V v) {
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
			indexVertex.put(ve, size);
			toReturn = true;
		}
		return toReturn;
	}

	@Override
	public void addEdge(V u, V v, int w) {
		int i = indexVertex.get(u);
		int j = indexVertex.get(v);
		System.out.println(i+""+j);
	}

	@Override
	public IGraph<V> bfs(V v) { 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<V> dfs(V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGraph<V> prim(V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGraph<VertexConected<V>> kurskal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGraph<V> dijsktra(V v) {
		// TODO Auto-generated method stub
		return null;
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
