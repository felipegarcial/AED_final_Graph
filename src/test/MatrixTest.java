package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import model.*;

class MatrixTest {

	@Test
	void addEdge() {
		IGraph<Place> g = new MatrixAdjacency<>();
		Place p = new Place("Cali", null, null, 0, 0);
		Place p1 = new Place("Popayan", null, null, 0, 0);
		g.addVertex(p);
		g.addVertex(p1);
		g.addEdge(p, p1, 123);
	}
	
	@Test
	void getAList() {
		MatrixAdjacency<Place> l = new MatrixAdjacency<>();
		l.setDirected(false);
		Place d = new Place("a", null, null, 0, 0);
		Place d1 = new Place("b", null, null, 0, 0);
		Place d2 = new Place("c", null, null, 0, 0);
		Place d3 = new Place("d", null, null, 0, 0);
		l.addVertex(d);
		l.addVertex(d1);
		l.addVertex(d2);
		l.addVertex(d3);
		l.addEdge(d, d1, 10);
		l.addEdge(d, d2, 7);
		l.addEdge(d1, d2, 5);
		l.addEdge(d1, d3, 3);
		ArrayList<VertexConected<Place>> actual = l.getAList(d1);
		assertEquals(3, actual.size());
	}
	
	
	@Test
	void kruskal() {
		MatrixAdjacency<Place> l = new MatrixAdjacency<>();
		l.setDirected(false);
		Place d = new Place("a", null, null, 0, 0);
		Place d1 = new Place("b", null, null, 0, 0);
		Place d2 = new Place("c", null, null, 0, 0);
		Place d3 = new Place("d", null, null, 0, 0);
		l.addVertex(d);
		l.addVertex(d1);
		l.addVertex(d2);
		l.addVertex(d3);
		l.addEdge(d, d1, 10);
		l.addEdge(d, d2, 7);
		l.addEdge(d1, d2, 5);
		l.addEdge(d1, d3, 3);
		MatrixAdjacency<VertexConected<Place>> actual = (MatrixAdjacency<VertexConected<Place>>) l.kurskal();
		assertEquals(3, actual.getSize());
		
	}
}
