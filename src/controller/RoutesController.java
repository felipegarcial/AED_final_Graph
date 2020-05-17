package controller;

import java.util.ArrayList;

import model.Prodigos;

public class RoutesController {
	
	private Prodigos prodigoApp;
	
	public RoutesController() {
		prodigoApp = new Prodigos();
	}
	
	public ArrayList <String> loadRoutes() {
		return prodigoApp.loadInfoCSVRoutes();
	}
}
