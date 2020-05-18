package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Prodigos {
	private IGraph<Place> graph1;
	private IGraph<Place> graph2;
	private File fileCSV;
	private BufferedReader brFileCSV;
	private ArrayList<String> placesDraw;
	private HashMap<String, Vehicle> vehicles;

	public Prodigos() {
		graph1 = new ListAdjacency<Place>();
		graph1.setDirected(false);
		graph2 = new MatrixAdjacency<Place>();
		graph2.setDirected(false);
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
						int adj = Integer.parseInt(row[6]);// trae adjacente
						if (vehicles.get(idVehicle) == null) {
							this.vehicles.put(idVehicle, new Vehicle(idVehicle));
						}

						vehicles.forEach((key, value) -> {
							if (key.equals(idVehicle)) {
								vehicles.get(idVehicle).addPlaceToDelivery(guide, nameClient, product, lat, lng, adj);
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
			chargeVertex(1, places);
			chargeEdges(1, places);
			IGraph<VertexConected<Place>> r = graph1.kurskal();
			ArrayList<Vertex<VertexConected<Place>>> t = r.getVertex();
			for (int i = 0; i < t.size(); i++) {
				VertexConected<Place> f = t.get(i).getNode();
				placesQ.add(f.getVertexEnd().getNode());
			}
			break;
		case 2:
			chargeVertex(2, places);
			chargeEdges(2, places);
			IGraph<VertexConected<Place>> r2 = graph2.kurskal();
			ArrayList<Vertex<VertexConected<Place>>> t2 = r2.getVertex();
			for (int i = 0; i < t2.size(); i++) {
				VertexConected<Place> f = t2.get(i).getNode();
				placesQ.add(f.getVertexEnd().getNode());
			}
			break;
		case 3:
			// Prim lista
			chargeVertex(1, places);
			chargeEdges(1, places);
			IGraph<VertexConected<Place>> r3 = graph1.prim(places.get(0));
			ArrayList<Vertex<VertexConected<Place>>> t3 = r3.getVertex();
			for (int i = 0; i < t3.size(); i++) {
				VertexConected<Place> f = t3.get(i).getNode();
				placesQ.add(f.getVertexEnd().getNode());
			}
			break;
		case 4:
			// Prim matriz
			chargeVertex(2, places);
			chargeEdges(2, places);
			IGraph<VertexConected<Place>> r4 = graph2.prim(places.get(0));
			ArrayList<Vertex<VertexConected<Place>>> t4 = r4.getVertex();
			for (int i = 0; i < t4.size(); i++) {
				VertexConected<Place> f = t4.get(i).getNode();
				placesQ.add(f.getVertexEnd().getNode());
			}
			break;
		default:
			break;
		}
		return placesQ;

	}

	// agrega todos los vertices a la estructura correspondiente
	public void chargeVertex(int al, ArrayList<Place> places) {
		for (int i = 0; i < places.size(); i++) {
			if (al == 1) {
				graph1.addVertex(places.get(i));
			} else {
				graph2.addVertex(places.get(i));
			}
		}
	}

	public Place searchPlace(int id, ArrayList<Place> places) {
		Place toReturn = null;
		for (int j = 0; j < places.size(); j++) {
			Place pl = places.get(j);
			int idP = Integer.parseInt(pl.getGuide());
			if (idP == id) {
				toReturn = pl;
			}
		}
		return toReturn;
	}

	public void chargeE(int al, Place p1, Place p2) {
		if (al == 1) {
			graph1.addEdge(p1, p2, getWeigth(p1, p2));
		} else {
			graph2.addEdge(p1, p2, getWeigth(p1, p2));
		}
	}

//	agrega todas las aristas
	public void chargeEdges(int al, ArrayList<Place> places) {
		for (int j = 0; j < places.size(); j++) {
			int adj = places.get(j).getAdjacent();
			Place found = searchPlace(adj, places);
			chargeE(al, places.get(j), found);
		}
	}

	public int getWeigth(Place p1, Place p2) {
		int a = (int) Math.pow(p1.getLng() - p2.getLng(), 2);
		int b = (int) Math.pow(p1.getLat() - p2.getLat(), 2);
		return (int) Math.sqrt(a + b);
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
