package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.IGraph;
import model.MatrixAdjacency;
import model.Place;

class MatrixTest {

	@Test
	void test() {
		IGraph<Place> g = new MatrixAdjacency<>();
		Place p = new Place("Cali");
		Place p1 = new Place("Popayan");
		g.addVertex(p);
		g.addVertex(p1);
		g.addEdge(p, p1, 123);
	}

}
