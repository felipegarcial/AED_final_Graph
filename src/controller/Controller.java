package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import model.Place;
import model.Prodigos;
import model.Vehicle;

public class Controller {
	
	protected Prodigos prodigos;
	
	public Controller() {
		prodigos = new Prodigos();
	}
	
	public ArrayList <String> loadRoutes() {
		prodigos.loadInfoCSVRoutes();
		return prodigos.getPlacesDraw();
	}
	
	
	public HashMap<String, Vehicle> loadInfoRoutesByVehicle() {
		prodigos.loadInfoCSVRoutesByVehicle();
		return prodigos.getVehicles();
	}
	
	public void setMapVehicles(HashMap<String, Vehicle> vehicles) {
		prodigos.setVehicles(vehicles);
	}
	
	public HashMap<String, Vehicle> getVehicles() {
		return prodigos.getVehicles();
	}
	
	public ArrayList<Place> getPlaces(String idVehcile ){
		return prodigos.getPlacesByVehicles(idVehcile);
	}
	
	
	public LinkedList<Place> callAlogorithm(int algorithmImp, ArrayList <Place> places){
		return prodigos.selectAlgorithm(algorithmImp, places);
	}

}
