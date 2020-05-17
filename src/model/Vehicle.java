package model;

import java.util.ArrayList;

public class Vehicle {
	private String id;
	private ArrayList<Place> places;
	
	public Vehicle(String id) {
		this.id = id;
		places = new ArrayList<Place>();
	}
	
	public void addPlaceToDelivery(String guide,String nameClient,String product, int lat, int lng) {
		places.add(new Place(guide,nameClient,product,lat,lng));
	}
	
	public ArrayList<Place> getPlacesToDelivery() {
		return places; 
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
