package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Prodigos {
	private IGraph<Place> graph1;
	private IGraph<Place> graph2;
	private File fileCSV;
	private BufferedReader brFileCSV;
	ArrayList<String> placesDraw; 
	
	public Prodigos() {
		graph1 = new ListAdjacency<Place>();
		graph2 = new MatrixAdjacency<Place>();
		placesDraw = new ArrayList<String>();
		fileCSV = new File("./resources/file-csv/CSV_AED.csv");
		
	}
	
	
	public ArrayList<String> loadInfoCSVRoutes(){
		//Read file csv
		try {
			brFileCSV = new BufferedReader(new FileReader(fileCSV));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String st;
		placesDraw.clear();
		// ------------------------------
		try {
			while ((st = brFileCSV.readLine()) != null) {
				placesDraw.add(st.trim());
				// -------------------
				// -------------------
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return placesDraw;
	}
}
