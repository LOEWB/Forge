package t1_1_Model_Principal;

public class Coordonnees {
	double longitude;
	double latitude;

	public Coordonnees(double d, double e) {
		this.longitude = d;
		this.latitude = e;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}
}
