package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Prodigos {
	private IGraph<Place> graph1;
	private IGraph<Place> graph2;
	private File fileCSV;
	private BufferedReader brFileCSV;
	private ArrayList<String> placesDraw;
	private HashMap<String, Vehicle> vehicles;

	public Prodigos() {
		graph1 = new ListAdjacency<Place>();
		graph2 = new MatrixAdjacency<Place>();
		placesDraw = new ArrayList<String>();
		fileCSV = new File("./resources/file-csv/CSV_AED.csv");
		vehicles = new HashMap<String, Vehicle>();
	}

	public ArrayList<String> loadInfoCSVRoutes() { 
		// Read file csv
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

				if (st.length() > 8) {
					//----------------
					//Adding elements to the arraylist to show routes
					placesDraw.add(st.trim());
					//----------------
					//Creating elements vehicles with their routes
					String [] row = st.split(";");
					if (!st.contains("ID")) {
						String guide = row[0];
						String idVehicle = row[1];
						String nameClient = row[2];
						String product = row[3];
						int lat = Integer.parseInt(row[4]);
						int lng = Integer.parseInt(row[5]);

						if (vehicles.get(idVehicle) == null) {
							vehicles.put(idVehicle, new Vehicle(idVehicle));
						}

						vehicles.forEach((key, value) -> {
							if (key.equals(idVehicle)) {
								vehicles.get(idVehicle).addPlaceToDelivery(guide, nameClient, product, lat, lng);
							}
						});
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		if (vehicles.get("TDQ34D") != null) {
			System.out.println(vehicles.get("TDQ34D").getPlacesToDelivery().size());
		}

		return placesDraw;
	}
}
