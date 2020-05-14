package view;

import processing.core.PApplet;

public class Main extends PApplet {

	public static int screen;
	private MainScreen mainS;
	private DeliveryScreen deliveryS;

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());
	}

	public void settings() {
		size(900, 600);
	}

	public void setup() {
		screen = 0;
		deliveryS = new DeliveryScreen(this);
		mainS = new MainScreen(this);
	}

	public void draw() {
		background(255, 255, 255);

		switch (screen) {
		case 0:
			mainS.draw();
			break;
		case 1:
			deliveryS.draw();
			break;

		default:
			break;
		}

	}

	public void mousePressed() {
		switch (screen) {
		case 0:
			mainS.goDeliveryScreen(1);
			break;
		case 1:
			
			break;

		default:
			break;
		}
	}
}
