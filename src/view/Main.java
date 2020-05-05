package view;

import processing.core.PApplet;

public class Main extends PApplet {

	private int screen;

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());
	}

	public void settings() {
		size(900, 600);
	}

	public void setup() {
		screen = 0;
	}

	public void draw() {
		background(255, 255, 255);

		switch (screen) {
		case 0:

			break;

		default:
			break;
		}

	}

	public void mousePressed() {

	}
}
