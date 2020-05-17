package view;

import processing.core.PApplet;

public class IdVehicleText {

	private int posX,posY;
	private String id;
	private boolean selected;
	private FontComponent fontC;
	private PApplet app;
	
	public IdVehicleText(PApplet app,String id,int posX,int posY) {
		this.app = app;
		fontC = new FontComponent(app);
		selected = false;
		this.id = id;
		this.posX = posX;
		this.posY = posY;
	}
	
	public void draw() {
		fontC.draw(id, "montserrat-semibold", 16,!selected?120:239, !selected?120:168, !selected?120:5, posX, posY);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	

}
