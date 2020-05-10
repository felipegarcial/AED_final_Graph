package model;

public class Vertex <V> {
	public final static char WHITE = 'w';
	public final static char BLACK = 'b';
	public final static char GREY = 'g';
	private V node;
	private char color;
	private V izq;
	private V der;
	
	public Vertex(V node) {
		this.node= node;
		color = 'w';
	}
	
	public char getColor() {
		return color;
	}
	
	public void setColor(char newColor) {
		color = newColor;
	}
	
	public V getIzq() {
		return izq;
	}
	
	public void setIzq(V newIzq) {
		izq = newIzq;
	}
	
	public V getDer() {
		return der;
	}
	
	public void setDer(V newDer) {
		der = newDer;
	}
}
