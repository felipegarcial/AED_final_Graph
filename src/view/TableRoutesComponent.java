package view;

import java.util.ArrayList;

import processing.core.PApplet;

public class TableRoutesComponent {
	private PApplet app;
	private ArrayList<String> placesDraw;

	public TableRoutesComponent(PApplet app) {
		this.app = app;
		placesDraw = new ArrayList<String>();
	}

	public void draw() {
		app.noStroke();
		app.fill(220);
		app.rect(25, 120, 850, 400);

		for (int i = 0; i < placesDraw.size(); i++) {
			String[] place = placesDraw.get(i).split(",");
			for (int j = 0; j < place.length; j++) {
				app.text(place[j], 50 * (i + 50), 200 * (j + 200));
			}
		}
	}

	public ArrayList<String> getPlacesDraw() {
		return placesDraw;
	}

	public void setPlacesDraw(ArrayList<String> placesDraw) {
		this.placesDraw = placesDraw;
	}

}
