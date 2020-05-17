package view;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class Main extends PApplet {

	public static int screen;
	private MainScreen mainS;
	private RoutesScreen routesS;
	private MapScreen mapS;

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());
	}

	public void settings() {
		size(900, 600);
	}

	public void setup() {
		screen = 2;
		routesS = new RoutesScreen(this);
		mainS = new MainScreen(this);
		mapS  = new MapScreen(this);
	}

	public void draw() {
		background(255, 255, 255);

		switch (screen) {
		case 0:
			mainS.draw();
			break;
		case 1:
			routesS.draw();
			break;
		case 2:
			mapS.draw();
			break;

		default:
			break;
		}

	}

	public void mousePressed() {
		switch (screen) {
		case 0:
			mainS.goScreen(1);
			break;
		case 1:
			routesS.loadInfo();
			routesS.goInitScreen(0);
			routesS.goMapScreen(2);
			break;
		case 2:
			
			break;

		default:
			break;
		}
	}
	
	public void mouseWheel(MouseEvent event) {
		 
		 
		  
		  switch (screen) {
			case 0:
				mainS.goScreen(1);
				break;
			case 1:
				int e = event.getCount();
				routesS.scroolTableComponent(e);
				break;
			case 2:
				
				break;

			default:
				break;
			}
		}
}
