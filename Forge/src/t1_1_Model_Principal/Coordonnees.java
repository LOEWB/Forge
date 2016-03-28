package t1_1_Model_Principal;

public class Coordonnees {
	double longitude;
	double latitude;

	public Coordonnees(double lat, double longi) {
		this.longitude = longi;
		this.latitude = lat;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}
}
