package view;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.concurrent.Callable;

import controlP5.*;

public class MainScreen {

	private PApplet app;
	private PImage imageMain;
	private ControlP5 cp5;
	private ButtonComponent[] btns;

	public MainScreen(PApplet app) {
		this.app = app;
		cp5 = new ControlP5(app);
		imageMain = app.loadImage("./resources/images/mainImage.png");
		btns = new ButtonComponent[2];

		for (int i = 0; i < btns.length; i++) {
			btns[i] = new ButtonComponent(app, "./resources/images/btn-" + (i + 1) + ".png", (i * 163) + 45, 250);
		}

	}

	public void controlEvent(ControlEvent theEvent) {
		System.out.println("Entro");
	}

	public void draw() {
		app.image(imageMain, 0, 0);

		for (int i = 0; i < btns.length; i++) {
			btns[i].draw();
		}
	}

	public void goDeliveryScreen(int screen) {
		btns[0].click(new Callable<Void>() {
			public Void call() {
				Main.screen = 1;
				return null;
			}
		});
	}

}
