package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import unionFind.*;
import model.*;
class UnionFindTest {

	@Test
	void make() {
		UnionFind<Place> u = new UnionFind<>(); 
		Place c = new Place("cali", null, null, 0, 0,0);
		u.makeset(c);
		assertEquals(1, u.getRepresentantes().size());
	}
	
	@Test
	void find() {
		UnionFind<Place> u = new UnionFind<>(); 
		Place c = new Place("cali", null, null, 0, 0,0);
		Place c1 = new Place("popayan", null, null, 0, 0,0);
		Place c2 = new Place("pasto", null, null, 0, 0,0);
		u.makeset(c);
		u.makeset(c1);
		u.makeset(c2);
		assertEquals(2, u.find(c2).getId());
	}
	
	@Test
	void union() {
		UnionFind<Place> u = new UnionFind<>(); 
		Place c = new Place("cali", null, null, 0, 0,0);
		Place c1 = new Place("popayan", null, null, 0, 0,0);
		Place c2 = new Place("pasto", null, null, 0, 0,0);
		u.makeset(c);
		u.makeset(c1);
		u.makeset(c2);
		u.union(c, c2);
		assertEquals(1, u.getRepresentantes().size()-1);
	}

}
