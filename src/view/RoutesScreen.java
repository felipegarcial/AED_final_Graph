package view;

import java.util.concurrent.Callable;

import controller.RoutesController;
import processing.core.PApplet;

public class RoutesScreen extends Screen{
	
	private FontComponent fontC;
	private PApplet app;
	private ButtonComponent[] btns;
	private final int NUMBER_BUTTONS;
	private RoutesController routesC;
	private TableRoutesComponent tableRoutes;
	private int posYDataTable;
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
		posYDataTable=0;
	}
	

	public void draw() {
		//-----
		//Table Routes
		tableRoutes.draw();
		app.fill(255);
		app.noStroke();
		app.rect(0, 0,900, 120);
		app.rect(0, 518,900, 150);
		//-----
		drawHeader();
		drawSubHeader();
		drawButtons();
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
		tableRoutes.setPosYInfoTable(0);
		posYDataTable = 0;
		//---------------
		btns[0].click(new Callable<Void>() {
			public Void call() {
				tableRoutes.setPlacesDraw(routesC.loadRoutes());
				return null;
			}
		});
	}
	
	
	public void goInitScreen(int screen) {	
		btns[1].click(new Callable<Void>() {
			public Void call() {
				Main.screen = 0;
				return null;
			}
		});
	}
	
	
	public void goMapScreen(int screen) {	
		btns[2].click(new Callable<Void>() {
			public Void call() {
				Main.screen = screen;
				return null;
			}
		});
	}
	
	public void scroolTableComponent(int posY) {
		posYDataTable+= posY*10;
		tableRoutes.setPosYInfoTable(posYDataTable*-1);
	}
}
