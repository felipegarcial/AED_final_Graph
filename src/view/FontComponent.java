package view;

import processing.core.PApplet;
import processing.core.PFont;

public class FontComponent {
	
	private PFont montserratBold,montserratSemibold;
	private PApplet app;
	
	public FontComponent(PApplet app) {
		this.app = app;
		montserratBold = app.createFont("./resources/font/Montserrat-Bold.ttf",18);
		montserratSemibold = app.createFont("./resources/font/Montserrat-SemiBold.ttf",18);
	}
	
	public void draw(String text,String font,int size,int r,int g,int b,int posX,int posY) {
		app.fill(r,g,b);
		switch (font) {
		case "montserrat-bold":
			app.textFont(montserratBold,size);
			
		case "montserrat-semibold":
			app.textFont(montserratSemibold,size);
			break;

		default:
			break;
		}
		app.text(text,posX,posY);
	}

}
