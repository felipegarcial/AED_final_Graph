package unionFind;
import java.util.*;

public class UnionFind<V> implements IUnionFind<V>{
	
	private ArrayList<Node<V>> representantes;
	private int id;
	
	public UnionFind() {
		representantes = new ArrayList<>();
		id = 0;
	}
	
	@Override
	public void makeset(V v) {
		Node<V> n = new Node<>(v);
		n.setId(id);
		representantes.add(n);
		id++;
	}

	@Override
	public int find(V v) {
		return search(v).getId(); 
	}

	@Override
	public void union(V v, V u) {
		Node<V> v1 = search(v);
		Node<V> v2 = search(u);
		v1.setNext(v2);
		v2.setPresesor(v1);
		representantes.remove(v2.getId());
	}
	
	
	public ArrayList<Node<V>> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(ArrayList<Node<V>> representantes) {
		this.representantes = representantes;
	}

	public Node<V> search(V v){
		boolean stop = false;
		Node<V> toReturn = null;
		for (int i = 0; i < representantes.size() && !stop; i++) {
			if(representantes.get(i).getNode().equals(v)) {
				toReturn = representantes.get(i);
				stop= true;
			}
		}
			return toReturn;
	}

}
