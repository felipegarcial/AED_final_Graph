package model;

import java.util.ArrayList;

public class Vehicle{
	private String id;
	private ArrayList<Place> places;
	private int posX,posY;
	
	public Vehicle(String id) {
		this.id = id;
		places = new ArrayList<Place>();
		posX=0;
		posY=0;
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

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
