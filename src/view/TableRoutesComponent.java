package view;

import java.util.ArrayList;

import processing.core.PApplet;

public class TableRoutesComponent {
	private PApplet app;
	private ArrayList<String> placesDraw;
	private FontComponent fontC;
	public TableRoutesComponent(PApplet app) {
		this.app = app;
		placesDraw = new ArrayList<String>();
		fontC = new FontComponent(app);
		
	}

	public void draw() {
		app.noStroke();
		app.fill(245);
		app.rect(25, 120, 850, 400);
		app.fill(0);
		app.textSize(13);
		app.textAlign(app.CENTER);
		

		for (int i = 0; i < placesDraw.size(); i++) {
			String[] place = placesDraw.get(i).split(";");
			for (int j = 0; j < place.length; j++) {
				int totalWidthString = j == 0?100:(140*j)+100;
				fontC.draw(i==0 && j==0 ? "ID":place[j], i==0 ? "montserrat-semibold":"montserrat-light", 13, 50, 50, 50,totalWidthString, i==0?150:150+(i*30));
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
