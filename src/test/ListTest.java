package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import model.*;

class ListTest {

	@Test
	void search() {
		ListAdjacency<Place> l = new ListAdjacency<>();
		Place d = new Place("Cali", null, null, 0, 0);
		Place d1 = new Place("Popayan", null, null, 0, 0);
		l.addVertex(d);
		l.addVertex(d1);
		Vertex<Place> p = l.search(d);
		assertEquals("Cali", p.getNode().getGuide());		
	}
	
	@Test
	void Prim() {
		ListAdjacency<Place> l = new ListAdjacency<>();
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
		ListAdjacency<VertexConected<Place>> actual = (ListAdjacency<VertexConected<Place>>) l.prim(d);
		assertEquals(4, actual.getSize());
	}
	
	@Test
	void dijsktra() {
		ListAdjacency<Place> l = new ListAdjacency<>();
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
	void kruskal() {
		ListAdjacency<Place> l = new ListAdjacency<>();
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
		ListAdjacency<VertexConected<Place>> actual = (ListAdjacency<VertexConected<Place>>) l.kurskal();
		assertEquals(3, actual.getSize());
		
	}
	
	@Test
	void bfs() {
		ListAdjacency<Place> l = new ListAdjacency<>();
		l.setDirected(false);
		Place d = new Place("r", null, null, 0, 0);
		Place d1 = new Place("s", null, null, 0, 0);
		Place d2 = new Place("t", null, null, 0, 0);
		Place d3 = new Place("u", null, null, 0, 0);
		Place d4 = new Place("v", null, null, 0, 0);
		Place d5 = new Place("w", null, null, 0, 0);
		Place d6 = new Place("x", null, null, 0, 0);
		Place d7 = new Place("y", null, null, 0, 0);
		l.addVertex(d);
		l.addVertex(d1);
		l.addVertex(d2);
		l.addVertex(d3);
		l.addVertex(d4);
		l.addVertex(d5);
		l.addVertex(d6);
		l.addVertex(d7);
		l.addEdge(d, d4, 1);
		l.addEdge(d, d1, 1);
		l.addEdge(d1, d5, 1);
		l.addEdge(d5, d2, 1);
		l.addEdge(d5, d6, 1);
		l.addEdge(d6, d2, 1);
		l.addEdge(d6, d3, 1);
		l.addEdge(d6, d7, 1);
		l.addEdge(d7, d3, 1);
		l.bfs(d1);
	}
	
	@Test
	void dfs() {
		ListAdjacency<Place> l = new ListAdjacency<>();
		Place d = new Place("u", null, null, 0, 0);
		Place d1 = new Place("v", null, null, 0, 0);
		Place d2 = new Place("w", null, null, 0, 0);
		Place d3 = new Place("x", null, null, 0, 0);
		Place d4 = new Place("y", null, null, 0, 0);
		Place d5 = new Place("z", null, null, 0, 0);
		l.addVertex(d);
		l.addVertex(d1);
		l.addVertex(d2);
		l.addVertex(d3);
		l.addVertex(d4);
		l.addVertex(d5);
		l.addEdge(d, d1, 1);
		l.addEdge(d, d3, 1);
		l.addEdge(d1, d4, 1);
		l.addEdge(d4, d3, 1);
		l.addEdge(d3, d1, 1);
		l.addEdge(d2, d4, 1);
		l.addEdge(d2, d5, 1);
		l.addEdge(d5, d5, 1);
		l.dfs(d);
	}
	
	@Test
	void floyd() {
		
	}

}
