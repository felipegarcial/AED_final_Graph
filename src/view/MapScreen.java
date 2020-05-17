package view;

import processing.core.PApplet;

public class MapScreen extends Screen{
	
	private PApplet app;
	
	public MapScreen(PApplet app) {
		super("Mapa Enrutador",app);
		this.app = app;
		
	}
	
	public void draw() {
		drawHeader();
	}

}
