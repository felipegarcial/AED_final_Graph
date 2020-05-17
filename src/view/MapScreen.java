package view;

import processing.core.PApplet;

public class MapScreen extends Screen {

	private PApplet app;
	private FontComponent fontC;
	private ButtonComponent[] btns;
	private final int NUMBER_BUTTONS;

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
		btns[9] = new ButtonComponent(app, "./resources/images/map-btn-9.png",btns[2].getPosX() + 255, 530);
		btns[10] = new ButtonComponent(app, "./resources/images/map-btn-10.png", btns[2].getPosX() + 360, 530);
		btns[11] = new ButtonComponent(app, "./resources/images/map-btn-11.png", btns[2].getPosX() + 465, 530);

	}

	public void draw() {
		drawHeader();

		drawSubHeader();

		app.fill(245);
		app.noStroke();
		drawMap();
		drawVehiclesList();
		drawButtons();
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

}
