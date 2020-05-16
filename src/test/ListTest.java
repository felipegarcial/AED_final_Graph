package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.ListAdjacency;
import model.Place;
import model.Vertex;

class ListTest {

	@Test
	void search() {
		ListAdjacency<Place> l = new ListAdjacency<>();
		Place d = new Place("Cali");
		Place d1 = new Place("Popayan");
		l.addVertex(d);
		l.addVertex(d1);
		Vertex<Place> p = l.search(d);
		assertEquals("Cali", p.getNode().getName());		
	}
	
	@Test
	void Prim() {
		ListAdjacency<Place> l = new ListAdjacency<>();
		l.setDirected(false);
		Place d = new Place("at");
		Place d1 = new Place("ch");
		Place d2 = new Place("ny");
		Place d4 = new Place("de");
		Place d5 = new Place("sf");
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
		l.prim(d);
	}
	
	@Test
	void dijsktra() {
		ListAdjacency<Place> l = new ListAdjacency<>();
		l.setDirected(false);
		Place d = new Place("a");
		Place d1 = new Place("b");
		Place d2 = new Place("c");
		Place d3 = new Place("d");
		Place d4 = new Place("e");
		Place d5 = new Place("z");
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
		l.dijsktra(d);
	}
	
	@Test
	void kruskal() {
		ListAdjacency<Place> l = new ListAdjacency<>();
		l.setDirected(false);
		Place d = new Place("a");
		Place d1 = new Place("b");
		Place d2 = new Place("c");
		Place d3 = new Place("d");
		l.addVertex(d);
		l.addVertex(d1);
		l.addVertex(d2);
		l.addVertex(d3);
		l.addEdge(d, d1, 10);
		l.addEdge(d, d2, 7);
		l.addEdge(d1, d2, 5);
		l.addEdge(d1, d3, 3);
		l.kurskal();
	}
	
	@Test
	void bfs() {
		ListAdjacency<Place> l = new ListAdjacency<>();
		l.setDirected(false);
		Place d = new Place("r");
		Place d1 = new Place("s");
		Place d2 = new Place("t");
		Place d3 = new Place("u");
		Place d4 = new Place("v");
		Place d5 = new Place("w");
		Place d6 = new Place("x");
		Place d7 = new Place("y");
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
		
	}

}
