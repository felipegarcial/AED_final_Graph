package model;

import java.util.ArrayList;

public class Vehicle {
	private String id;
	private ArrayList<Place> places;
	
	public Vehicle(String id) {
		this.id = id;
	}
	
	public void addPlaceToDelivery(String guide,String nameClient,String product,String address, int lat, int lng) {
		places.add(new Place(guide,nameClient,product,address,lat,lng));
	}
	
	public ArrayList<Place> getPlacesToDelivery() {
		return places; 
	}
}
