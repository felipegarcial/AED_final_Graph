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
	
	@Test
	void testGetVertex() {
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
		ArrayList<Vertex<Place>> actual = l.getVertex();
		assertEquals(4, actual.size());
	}
	
	@Test
	void dijsktra() {
		MatrixAdjacency<Place> l = new MatrixAdjacency<>();
		l.setDirected(false);
		Place d = new Place("a", null, null, 0, 0);
		Place d1 = new Place("b", null, null, 0, 0);
		Place d2 = new Place("c", null, null, 0, 0);
		Place d3 = new Place("d", null, null, 0, 0);
		Place d4 = new Place("e", null, null, 0, 0);
		Place d5 = new Place("z", null, null, 0, 0);
		l.addVertex(d);
		l.addVertex(d1);
		l.addVertex(d2);
		l.addVertex(d3);
		l.addVertex(d4);
		l.addVertex(d5);
		l.addEdge(d, d1, 4);
		l.addEdge(d, d2, 2);
		l.addEdge(d1, d2, 1);
		l.addEdge(d1, d3, 5);
		l.addEdge(d2, d3, 8);
		l.addEdge(d2, d4, 10);
		l.addEdge(d3, d4, 2);
		l.addEdge(d3, d5, 6);
		l.addEdge(d4, d5, 3);
		ArrayList<Vertex<Place>> actual = l.dijsktra(d);
		assertEquals("a", actual.get(2).getNode().getGuide());
	}
	
	@Test
	void Prim() {
		MatrixAdjacency<Place> l = new MatrixAdjacency<>();
		l.setDirected(false);
		Place d = new Place("at", null, null, 0, 0);
		Place d1 = new Place("ch", null, null, 0, 0);
		Place d2 = new Place("ny", null, null, 0, 0);
		Place d4 = new Place("de", null, null, 0, 0);
		Place d5 = new Place("sf", null, null, 0, 0);
		l.addVertex(d);
		l.addVertex(d1);
		l.addVertex(d2);
		l.addVertex(d4);
		l.addVertex(d5);
		l.addEdge(d, d1, 700);
		l.addEdge(d, d2, 800);
		l.addEdge(d, d4, 1400);
		l.addEdge(d, d5, 2200);
		l.addEdge(d4, d2, 1600);
		l.addEdge(d4, d1, 1300);
		l.addEdge(d4, d5, 900);
		l.addEdge(d1, d5, 1200);
		l.addEdge(d1, d2, 1000);
		l.addEdge(d2, d5, 2000);
		MatrixAdjacency<VertexConected<Place>> actual = (MatrixAdjacency<VertexConected<Place>>) l.prim(d);
		assertEquals(4, actual.getSize());
	}
}
