package view;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;

import processing.core.PApplet;
import processing.core.PImage;

public class ButtonComponent {

	private PImage imageBtn;
	private PApplet app;
	private int posX, posY;

	public ButtonComponent(PApplet app, String urlImage, int posX, int posY) {
		this.app = app;
		this.imageBtn = app.loadImage(urlImage);
		this.posX = posX;
		this.posY = posY;
	}

	public void draw() {
		app.image(imageBtn, posX, posY);
	}



	public void click(Callable<Void> func) {
		if ((app.mouseX > posX && app.mouseX < posX + imageBtn.width)
				&& (app.mouseY > posY && app.mouseY < posY + imageBtn.height)) {
			try {
				func.call();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	   public void dansMethod(Callable<Void> aMethod) {
		   try {
			   aMethod.call();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

}
