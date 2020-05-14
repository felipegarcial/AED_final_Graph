package view;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Screen {

	private String title;
	private PImage icon;
	private PApplet appP;
	
	public Screen(String title,PApplet appP) {
		this.title = title;
		this.appP = appP;
		icon = appP.loadImage("./resources/images/Componente 1 – 1.png");
	}
	
	public abstract void draw();
	
	
	public void drawHeader() {
		appP.fill(0);
		appP.text(title,25,19);
		appP.image(icon, 800, 21,73,19);
	}
}
