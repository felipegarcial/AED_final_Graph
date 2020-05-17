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
	
	public Prodigos() {
		graph1 = new ListAdjacency<Place>();
		graph2 = new MatrixAdjacency<Place>();

		fileCSV = new File("./resources/file-csv/CSV_AED.csv");
		try {
			brFileCSV = new BufferedReader(new FileReader(fileCSV));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<String> loadInfoCSVRoutes(){
		String st;
		ArrayList<String> placesDraw = new ArrayList<String>();
		// ------------------------------
		try {
			while ((st = brFileCSV.readLine()) != null) {
				System.out.println(st);
				placesDraw.add(st);
				// -------------------
				String[] arrayInfoOwner = st.split(",");
				// -------------------
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return placesDraw;
	}
}
