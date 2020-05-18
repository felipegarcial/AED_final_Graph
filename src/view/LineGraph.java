package view;
import processing.core.PApplet;

public class LineGraph {
	private int posXOne,posXTwo,posYOne,posYTwo;
	private PApplet app;
	
	
	public LineGraph(PApplet app,int posXOne,int posYOne, int posXTwo, int posYTwo) {
		this.app = app;
		this.posXOne = posXOne+26;
		this.posYOne = posYOne+26;
		this.posXTwo = posXTwo+26;
		this.posYTwo = posYTwo+26;
	}
	
	public void draw() {
		app.stroke(100);
		app.strokeWeight(1);
		app.line(posXOne, posYOne, posXTwo, posYTwo);
	}
}
