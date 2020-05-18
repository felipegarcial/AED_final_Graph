package view;
import processing.core.PApplet;

public class LineGraph {
	private int posXOne,posXTwo,posYOne,posYTwo;
	private PApplet app;
	
	
	public LineGraph(PApplet app,int posXOne,int posXTwo, int posYOne, int posYTwo) {
		this.app = app;
		this.posXOne = posXOne;
		this.posXTwo = posXTwo;
		this.posYOne = posYOne;
		this.posYTwo = posYTwo;
	}
	
	public void draw() {
		app.stroke(100);
		app.strokeWeight(1);
		app.line(posXOne, posYOne, posXTwo, posYTwo);
	}
	
	

}
