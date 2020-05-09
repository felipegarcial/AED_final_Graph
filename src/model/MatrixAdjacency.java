package model;

import java.util.HashMap;
import java.util.Map;

public class MatrixAdjacency <V> implements IGraph<V> {
	public static int QUANTITY_VERTEX = 15;
	private Map<V, Integer> vertex;
	private double [][] matrixWeight;
	
	public MatrixAdjacency() {
		vertex = new  HashMap<>();
		matrixWeight = new double [QUANTITY_VERTEX][QUANTITY_VERTEX];
		initMatrix();
	}
	
	public void initMatrix() {
		for (int i = 0; i < matrixWeight.length; i++) {
			for (int j = 0; j < matrixWeight.length; j++) {
				if(i==j) {
					matrixWeight[i][j] = 0;
				}else{					
					matrixWeight[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
	}

	@Override
	public boolean addVertex(V v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addEdge(V u, V v, double w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IGraph<V> bfs() {
		// TODO Auto-generated method stub
		return null;
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
