package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

import controller.Controller;
import controller.MapController;
import model.Vehicle;
import model.Place;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class MapScreen extends Screen {

	private PApplet app;
	private FontComponent fontC;
	private ButtonComponent[] btns;
	private final int NUMBER_BUTTONS;
	private Controller mapC;
	private HashMap<String, Vehicle> vehicles;
	private int posYId;
	private ArrayList<Place> placesByVehicle;
	private ArrayList<IdVehicleText> idVehicleTList;
	private PImage home;

	public MapScreen(PApplet app) {
		super("Mapa Enrutador", app);
		fontC = new FontComponent(app);
		NUMBER_BUTTONS = 12;
		btns = new ButtonComponent[NUMBER_BUTTONS];
		this.app = app;

		btns[0] = new ButtonComponent(app, "./resources/images/map-btn-0.png", 615, 70);
		btns[1] = new ButtonComponent(app, "./resources/images/map-btn-1.png", 745, 70);

		btns[2] = new ButtonComponent(app, "./resources/images/map-btn-2.png", 305, 530);
		btns[3] = new ButtonComponent(app, "./resources/images/map-btn-3.png", btns[2].getPosX() + 150, 530);
		btns[4] = new ButtonComponent(app, "./resources/images/map-btn-4.png", btns[2].getPosX() + 255, 530);
		btns[5] = new ButtonComponent(app, "./resources/images/map-btn-5.png", btns[2].getPosX() + 360, 530);
		btns[6] = new ButtonComponent(app, "./resources/images/map-btn-6.png", btns[2].getPosX() + 465, 530);
		btns[7] = new ButtonComponent(app, "./resources/images/map-btn-7.png", 305, 530);
		btns[8] = new ButtonComponent(app, "./resources/images/map-btn-8.png", btns[2].getPosX() + 150, 530);
		btns[9] = new ButtonComponent(app, "./resources/images/map-btn-9.png", btns[2].getPosX() + 255, 530);
		btns[10] = new ButtonComponent(app, "./resources/images/map-btn-10.png", btns[2].getPosX() + 360, 530);
		btns[11] = new ButtonComponent(app, "./resources/images/map-btn-11.png", btns[2].getPosX() + 465, 530);

		mapC = new MapController();
		vehicles = mapC.loadInfoRoutesByVehicle();
		posYId = 0;
		idVehicleTList = new ArrayList<IdVehicleText>();

		for (Vehicle element : vehicles.values()) {
			idVehicleTList.add(new IdVehicleText(app, element.getId(), 110, 170 + (60 * posYId)));
			posYId++;
		}
		placesByVehicle = new ArrayList<Place>();
		home = app.loadImage("./resources/images/casa.png");
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
		drawPlaces();

	}

	private void drawButtons() {

		for (int i = 0; i < NUMBER_BUTTONS; i++) {
			if (btns[i] != null) {
				btns[i].draw();
			}
		}
	}

	private void drawMap() {
		app.rect(226, 130, 645, 390);
	}

	private void drawVehiclesList() {
		app.rect(26, 130, 180, 390);
	}

	private void drawSubHeader() {
		fontC.draw("Vehículos", "montserrat-semibold", 18, 168, 168, 168, 26, 100);
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
			System.out.println(app.mouseX + " " + app.mouseY);
			if (app.mouseX > idVehicleTList.get(i).getPosX() - 30 && app.mouseX < idVehicleTList.get(i).getPosX() + 30
					&& app.mouseY > idVehicleTList.get(i).getPosY() - 30
					&& app.mouseY < idVehicleTList.get(i).getPosY() + 30) {
				System.out.println("Entro");
				System.out.println(app.mouseX + " " + idVehicleTList.get(i).getPosX());
				System.out.println(app.mouseY + " " + idVehicleTList.get(i).getPosY());
				idVehicleTList.get(i).setSelected(true);
				
				placesByVehicle = mapC.getPlaces(idVehicleTList.get(i).getId());
			} else if ((app.mouseX > 26 && app.mouseX < 140 && app.mouseY > 135 && app.mouseY <390)
				) {
				idVehicleTList.get(i).setSelected(false);
			}
		}
	}
	
	
	private void drawPlaces() {
		for (int i = 0; i < placesByVehicle.size(); i++) {
			app.image(home, placesByVehicle.get(i).getLat()+230, placesByVehicle.get(i).getLng()+135);
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

}