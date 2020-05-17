package view;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import controller.RoutesController;
import processing.core.PApplet;
import processing.core.PFont;

public class RoutesScreen extends Screen{
	
	private FontComponent fontC;
	private PApplet app;
	private ButtonComponent[] btns;
	private final int NUMBER_BUTTONS;
	private RoutesController routesC;
	private TableRoutesComponent tableRoutes;
	
	public RoutesScreen(PApplet app) {
		super("Rutas",app);
		this.app = app;
		fontC = new FontComponent(app);
		NUMBER_BUTTONS = 3;
		btns = new ButtonComponent[NUMBER_BUTTONS];
		
		btns[0] = new ButtonComponent(app, "./resources/images/routes-btn-" + 0 + ".png",712,60);
		
		for (int i = 1; i < NUMBER_BUTTONS; i++) {
			btns[i] = new ButtonComponent(app, "./resources/images/routes-btn-" + (i) + ".png", (i * 163) + app.width-490, app.height-70);
		}
		
		routesC = new RoutesController();
		tableRoutes = new TableRoutesComponent(app);
	}
	

	public void draw() { 
		drawHeader();
		drawSubHeader();
		drawButtons();
		tableRoutes.draw();
	}
	
	private void drawSubHeader() {
		fontC.draw("Rutas para entregar", "montserrat-semibold", 18, 168, 168, 168, 26, 100);
	}
	
	private void drawButtons() {
		for (int i = 0; i < btns.length; i++) {
			btns[i].draw();
		}
	}
	
	public void loadInfo() {	
		btns[0].click(new Callable<Void>() {
			public Void call() {
				ArrayList <String> places = routesC.loadRoutes();
				tableRoutes.setPlacesDraw(places);
				return null;
			}
		});
		
		
	}
}
