package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

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
		fileCSV = new File("./resources/file-csv/csv2.csv");
		vehicles = new HashMap<String, Vehicle>();
	}

	public void loadInfoCSVRoutes() {
		String st;
		placesDraw.clear();

		// Read file csv
		try {
			brFileCSV = new BufferedReader(new FileReader(fileCSV));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// ------------------------------
		try {
			while ((st = brFileCSV.readLine()) != null) {
				if (st.length() > 8) {
					// ----------------
					// Adding elements to the arraylist to show routes
					placesDraw.add(st.trim());

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadInfoCSVRoutesByVehicle() {
		String st;
		// Read file csv
		try {
			brFileCSV = new BufferedReader(new FileReader(fileCSV));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// ------------------------------
		try {
			while ((st = brFileCSV.readLine()) != null) {
				if (st.length() > 8) {
					// ----------------
					// Creating elements vehicles with their routes
					String[] row = st.split(",");
					if (!st.contains("ID")) {
						String guide = row[0];
						String idVehicle = row[1];
						String nameClient = row[2];
						String product = row[3];
						int lat = Integer.parseInt(row[4]);
						int lng = Integer.parseInt(row[5]);

						if (vehicles.get(idVehicle) == null) {
							this.vehicles.put(idVehicle, new Vehicle(idVehicle));
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
	}

	public LinkedList<Place> selectAlgorithm(int algorithmImp, ArrayList<Place> places) {
		LinkedList<Place> placesQ = new LinkedList<Place>();
		
		switch (algorithmImp) {
		case 1:
			//kruscal lista
			break;
		case 2:
			//kruscal matriz
			break;
		case 3:
			//Prim lista
			break;
		case 4:
			//Prim matriz
			break;
		default:
			break;
		}
		
		
		for (Place place : places) {
			placesQ.add(place);
		}
		
		return placesQ;
	}

	public int getWeigth(Place p1, Place p2) {
		int a = (int) Math.pow(p1.getLng() - p2.getLng(), 2);
		int b = (int) Math.pow(p1.getLat() - p2.getLat(), 2);
		return (int) Math.sqrt(a - b);
	}

	public ArrayList<String> getPlacesDraw() {
		return placesDraw;
	}

	public HashMap<String, Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(HashMap<String, Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public ArrayList<Place> getPlacesByVehicles(String idVehcile) {
		return vehicles.get(idVehcile).getPlacesToDelivery();
	}
}
