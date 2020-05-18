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
		n.setPresesor(null);
		representantes.add(n);
		id++;
	}

	@Override
	public Node<V> find(V v) {
		return search(v); 
	}

	@Override
	public void union(V v, V u) {
		Node<V> v1 = search(v);
		Node<V> v2 = search(u);
		if(v1.getPresesor() == null) {			
			v2.add(v1);
			v2.updateId();
			v1.setPresesor(v2);
			representantes.remove(v1);
//			v1.setId(v2.getId());
		}
		else {
			v1.add(v2);	
			v1.updateId();
			v2.setPresesor(v1);
			representantes.remove(v2);
//			v2.setId(v1.getId());
		}
//		v2.updatePredesesor(v1);
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
			Node<V> r = representantes.get(i);
			if(r.getNode().equals(v)) {
				toReturn = r;
				stop= true;
			}
			else {				
				Node<V> next = r.getNext();
				while(next != null && !stop) {
					if(next.getNode().equals(v)) {
						toReturn = next;
						stop= true;					
					}
					else {
						next = next.getNext();
					}
				}
			}
		}
			return toReturn;
	}

}
