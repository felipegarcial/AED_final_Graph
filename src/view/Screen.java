package view;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public abstract class Screen {

	private String title;
	private PImage icon;
	private PApplet app;
	private FontComponent fontC;
	
	public Screen(String title,PApplet app) {
		this.title = title;
		this.app = app;
		icon = app.loadImage("./resources/images/Componente 1 – 1.png");
		fontC = new FontComponent(app);
		
	}
	
	public abstract void draw();
	
	
	public void drawHeader() {
		app.textAlign(app.LEFT);
		fontC.draw(title, "montserrat-bold", 25, 0,26,79, 25, 35);
		app.image(icon, 800, 21,73,19);
	}
}
