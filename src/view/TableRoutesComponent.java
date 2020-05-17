package view;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

public class TableRoutesComponent {
	private PApplet app;
	private ArrayList<String> placesDraw;
	private FontComponent fontC;
	private int posYInfoTable, posYInfo, posYRectScrool;

	public TableRoutesComponent(PApplet app) {
		this.app = app;
		placesDraw = new ArrayList<String>();
		fontC = new FontComponent(app);
		posYInfo = 0;
		posYRectScrool = 0;

	}

	public void draw() {
		app.noStroke();
		app.fill(245);
		app.rect(25, 120, 850, 400);
		app.fill(0);
		app.textSize(13);
		app.textAlign(PConstants.CENTER);

		
		//Drawing scrool bar
		float y2 = PApplet.map(posYInfoTable, 0, 200, 120, 100);
		app.rect(870, y2, 5, 30);
		
		
		//Drawing info table
		for (int i = 1; i < placesDraw.size(); i++) {
			String[] place = placesDraw.get(i).split(",");
			System.out.println(placesDraw.get(i));
			for (int j = 0; j < place.length; j++) {
				int totalWidthString = j == 0 ? 100 : (140 * j) + 100;
				fontC.draw(place[j], i == 0 ? "montserrat-semibold" : "montserrat-light", 13,
						50, 50, 50, totalWidthString, (i == 0 ? 150 : 150 + (i * 30) + posYInfoTable));
			}
		}

		app.fill(245);
		app.rect(26, 120, 800, 50);
		
		//Drawing the title
		if(placesDraw.size()>0) {
			String[] placeTitles = placesDraw.get(0).split(",");
			for (int j = 0; j < placeTitles.length; j++) {
				int totalWidthString = j == 0 ? 100 : (140 * j) + 100;
				fontC.draw(j == 0 ? "ID" : placeTitles[j], "montserrat-semibold", 13, 50, 50, 50, totalWidthString, 150);
			}
		}
		
		posYInfo = placesDraw.size() * 30;
	}
	


	public ArrayList<String> getPlacesDraw() {
		return placesDraw;
	}

	public void setPlacesDraw(ArrayList<String> placesDraw) {
		this.placesDraw = placesDraw;
	}

	public void setPosYInfoTable(int posYInfoTable) {
		this.posYInfoTable = posYInfoTable;
	}
	
	

}
