package model;

public class Place {
	private String guide,nameClient,product;
	private int lat,lng;
	
	public Place(String guide,String nameClient,String product, int lat, int lng) {
		this.guide=guide;
		this.nameClient=nameClient;
		this.product=product;
		this.lat=lat;
		this.lng=lng;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLng() {
		return lng;
	}

	public void setLng(int lng) {
		this.lng = lng;
	}
	
}
