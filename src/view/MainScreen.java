package view;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.concurrent.Callable;


public class MainScreen {

	private PApplet app;
	private PImage imageMain;
	private ButtonComponent[] btns;
	private final int NUMBER_BUTTONS;

	public MainScreen(PApplet app) {
		this.app = app;
		imageMain = app.loadImage("./resources/images/mainImage.png");
		NUMBER_BUTTONS = 1;
		btns = new ButtonComponent[NUMBER_BUTTONS];

		for (int i = 0; i < NUMBER_BUTTONS; i++) {
			btns[i] = new ButtonComponent(app, "./resources/images/init-btn-" + (i) + ".png", (i * 163) + 45, 250);
		}
	}

	public void draw() {
		app.image(imageMain, 0, 0);

		for (int i = 0; i < btns.length; i++) {
			btns[i].draw();
		}
	}

	public void goScreen(int screen) {
		btns[0].click(new Callable<Void>() {
			public Void call() {
				Main.screen = screen;
				return null;
			}
		});
	}

}
