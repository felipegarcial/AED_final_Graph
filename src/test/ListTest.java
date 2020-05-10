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

}
