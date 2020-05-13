package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.ListAdjacency;
import model.Place;
import model.Vertex;

class ListTest {

	@Test
	void test() {
		ListAdjacency<Place> l = new ListAdjacency<>();
		Place d = new Place("Cali");
		Place d1 = new Place("Popayan");
		l.addVertex(d);
		l.addVertex(d1);
		Vertex<Place> p = l.search(d);
		System.out.println(p.getNode().getName());
		
	}
	
	@Test
	void Prim() {
		ListAdjacency<Place> l = new ListAdjacency<>();
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
		l.addEdge(d1, d5, 1200);
		l.addEdge(d4, d5, 900);
		l.prim(d);
	}
	
	@Test
	void dijsktra() {
		ListAdjacency<Place> l = new ListAdjacency<>();
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

}
