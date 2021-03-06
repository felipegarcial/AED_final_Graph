package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Callable;

import controller.Controller;
import model.Vehicle;
import model.Place;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class MapScreen extends Screen implements Runnable{

	private PApplet app;
	private FontComponent fontC;
	private ButtonComponent[] btns;
	private final int NUMBER_BUTTONS;
	private Controller mapC;
	private HashMap<String, Vehicle> vehicles;
	private int posYId;
	private ArrayList<Place> placesByVehicle;
	private ArrayList<IdVehicleText> idVehicleTList;
	private PImage home, mapa, vehicle;
	private int algorithmImp;
	private LinkedList<Place> placesGraph;
	private ArrayList<LineGraph> lineG;
	private int posXVehicle,posYVehicle;
	private boolean callAlgorimt;

	public MapScreen(PApplet app) {
		super("Mapa Enrutador", app);
		fontC = new FontComponent(app);
		NUMBER_BUTTONS = 6;
		btns = new ButtonComponent[NUMBER_BUTTONS];
		this.app = app;

		btns[0] = new ButtonComponent(app, "./resources/images/map-btn-0.png", 615, 70);
		btns[1] = new ButtonComponent(app, "./resources/images/map-btn-1.png", 745, 70);

		btns[2] = new ButtonComponent(app, "./resources/images/map-btn-2.png", 402, 530);
		btns[3] = new ButtonComponent(app, "./resources/images/map-btn-3.png", btns[2].getPosX() + 120, 530);
		btns[4] = new ButtonComponent(app, "./resources/images/map-btn-4.png", btns[2].getPosX() + 240, 530);
		btns[5] = new ButtonComponent(app, "./resources/images/map-btn-5.png", btns[2].getPosX() + 360, 530);

		mapC = new Controller();
		vehicles = mapC.loadInfoRoutesByVehicle();
		posYId = 0;
		idVehicleTList = new ArrayList<IdVehicleText>();

		for (Vehicle element : vehicles.values()) {
			idVehicleTList.add(new IdVehicleText(app, element.getId(), 110, 170 + (60 * posYId)));
			posYId++;
		}
		placesByVehicle = new ArrayList<Place>();
		home = app.loadImage("./resources/images/casa.png");
		mapa = app.loadImage("./resources/images/mapa.jpeg");
		vehicle = app.loadImage("./resources/images/vehicle.png");
		algorithmImp = 0;

		placesGraph = new LinkedList<Place>();
		lineG = new ArrayList<LineGraph>();
		posXVehicle = 790;
		posYVehicle = 440;
		callAlgorimt = false;
	}

	public void draw() {
		drawHeader();
		drawSubHeader();
		app.fill(245);
		app.noStroke();
		drawMap();
		drawVehiclesList();
		drawButtons();
		drawVehicles();
		drawLinesGraph();
		drawPlaces();
		drawAlogorithmName(algorithmImp);
		drawVehicle();

	}

	private void drawButtons() {
		for (int i = 0; i < NUMBER_BUTTONS; i++) {
			if (btns[i] != null) {
				btns[i].draw();
			}
		}
	}

	private void drawMap() {
		app.image(mapa, 226, 130, 645, 390);
	}

	private void drawVehiclesList() {
		app.rect(26, 130, 180, 390);
	}

	private void drawSubHeader() {
		fontC.draw("VehÝculos", "montserrat-semibold", 18, 168, 168, 168, 26, 100);
		fontC.draw("Ruta", "montserrat-semibold", 18, 168, 168, 168, 226, 100);
	}

	private void drawVehicles() {
		app.textAlign(PConstants.CENTER);
		for (int i = 0; i < idVehicleTList.size(); i++) {
			idVehicleTList.get(i).draw();
		}

	}

	public void selectVehicle() {
		for (int i = 0; i < idVehicleTList.size(); i++) {
			if (app.mouseX > idVehicleTList.get(i).getPosX() - 30 && app.mouseX < idVehicleTList.get(i).getPosX() + 30
					&& app.mouseY > idVehicleTList.get(i).getPosY() - 30
					&& app.mouseY < idVehicleTList.get(i).getPosY() + 30) {
				idVehicleTList.get(i).setSelected(true);
				placesByVehicle = mapC.getPlaces(idVehicleTList.get(i).getId());
				lineG.clear();
				posXVehicle = 790;
				posYVehicle = 440;
			} else if ((app.mouseX > 26 && app.mouseX < 140 && app.mouseY > 135 && app.mouseY < 390)) {
				idVehicleTList.get(i).setSelected(false);
			}
		}
	}

	private void drawPlaces() {
		for (int i = 0; i < placesByVehicle.size(); i++) {
			app.image(home, placesByVehicle.get(i).getLat() + 230, placesByVehicle.get(i).getLng() + 135);
		}
	}

	public void goInitScreen(int screen) {
		btns[1].click(new Callable<Void>() {
			public Void call() {
				Main.screen = screen;
				return null;
			}
		});
	}

	public void goRoutesScreen(int screen) {
		btns[0].click(new Callable<Void>() {
			public Void call() {
				Main.screen = screen;
				return null;
			}
		});
	}

	public void callAlgorithm() {

		btns[2].click(new Callable<Void>() {
			public Void call() {
				lineG.clear();
				algorithmImp = 1;
				placesGraph = mapC.callAlogorithm(algorithmImp, placesByVehicle);
				addLinesGraph();
				callAlgorimt = true;
				return null;
			}
		});

		btns[3].click(new Callable<Void>() {
			public Void call() {
				lineG.clear();
				algorithmImp = 2;
				placesGraph = mapC.callAlogorithm(algorithmImp, placesByVehicle);
				addLinesGraph();
				callAlgorimt = true;
				return null;
			}
		});

		btns[4].click(new Callable<Void>() {
			public Void call() {
				lineG.clear();
				algorithmImp = 3;
				placesGraph = mapC.callAlogorithm(algorithmImp, placesByVehicle);
				addLinesGraph();
				callAlgorimt = true;
				return null;
			}
		});

		btns[5].click(new Callable<Void>() {
			public Void call() {
				lineG.clear();
				algorithmImp = 4;
				placesGraph = mapC.callAlogorithm(algorithmImp, placesByVehicle);
				addLinesGraph();
				callAlgorimt = true;
				return null;
			}
		});
		
		if(callAlgorimt) {
			new Thread(this).start();
		}
	}

	private void addLinesGraph() {
		int posXOne = 0;
		int posYOne = 0;
		int posXTwo = 0;
		int posYTwo = 0;

		for (int i = 0; i < placesGraph.size(); i++) {
			posXOne = placesGraph.get(i).getLat() + 230;
			posYOne = placesGraph.get(i).getLng() + 135;
			posXTwo = i + 1 < placesGraph.size() ? placesGraph.get(i + 1).getLat() + 230
					: placesGraph.get(0).getLat() + 230;
			posYTwo = i + 1 < placesGraph.size() ? placesGraph.get(i + 1).getLng() + 135
					: placesGraph.get(0).getLng() + 135;
			lineG.add(new LineGraph(app, posXOne, posYOne, posXTwo, posYTwo));
		}
	}

	private void drawLinesGraph() {
		for (int i = 0; i < lineG.size(); i++) {
			lineG.get(i).draw();
		}
	}

	private void drawAlogorithmName(int algorithm) {
		String textA = "";
		switch (algorithm) {
		case 1:
			textA = "Kruskal con Lista";
			break;
		case 2:
			textA = "Kruskal con Matriz";
			break;
		case 3:
			textA = "Prim con Lista";
			break;
		case 4:
			textA = "Prim con Matriz";
			break;
		default:
			textA = "";
			break;
		}
		fontC.draw(textA, "montserrat-semibold", 15, 100, 100, 100, 795, 510);
	}

	private void drawVehicle() {
		app.image(vehicle, posXVehicle, posYVehicle);
	}


	public void run() {
		if(callAlgorimt) {
			for (int i = 0; i < placesGraph.size(); i++) {
				try {
					posXVehicle=placesGraph.get(i).getLat()+230;
					posYVehicle=placesGraph.get(i).getLng()+135;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			callAlgorimt= false;
		}
		
	}
	
	
}