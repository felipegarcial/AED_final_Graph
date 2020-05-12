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

}
