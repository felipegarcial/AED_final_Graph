package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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

	public Queue<Place> selectAlgorithm(int algorithmImp, ArrayList<Place> places) {
		Queue<Place> toReturn = new LinkedList<>();
		switch (algorithmImp) {
		case 1:
			chargeVertex(1, places);
			IGraph<VertexConected<Place>> r = graph1.kurskal();
			ArrayList<Vertex<VertexConected<Place>>> t = r.getVertex();
			for (int i = 0; i < t.size(); i++) {
				VertexConected<Place> f = t.get(i).getNode();
				toReturn.add(f.getVertexEnd().getNode());
			}
			break;
		case 2:
			chargeVertex(2, places);
			IGraph<VertexConected<Place>> r2 =graph2.kurskal();
			ArrayList<Vertex<VertexConected<Place>>> t2 = r2.getVertex();
			for (int i = 0; i <t2.size(); i++) {
				VertexConected<Place> f = t2.get(i).getNode();
				toReturn.add(f.getVertexEnd().getNode());
			}
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
		return null;
		
	}
	
	
	//agrega todos los vertices a la estructura correspondiente
	public void chargeVertex(int al , ArrayList<Place> places) {
		for (int i = 0; i < places.size(); i++) {
			if(al == 1) {
				graph1.addVertex(places.get(i));
			}
			else{
				graph2.addVertex(places.get(i));
			}
		}
	}
	
//	agrega todas las aristas
	public void chargeEdges(int al, Place p1, Place p2) {
		if(al == 1) {
			graph1.addEdge(p1, p2, getWeigth(p1, p2));
		}
		else{
			graph2.addEdge(p1, p2, getWeigth(p1, p2));
		}
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
