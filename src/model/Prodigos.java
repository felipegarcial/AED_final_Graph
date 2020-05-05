package model;

public class Prodigos {
	private IGraph<Place> graph1;
	private IGraph<Place> graph2;
	
	public Prodigos() {
		graph1 = new ListAdjacency<Place>();
		graph2 = new MatrixAdjacency<Place>();
	}

}
