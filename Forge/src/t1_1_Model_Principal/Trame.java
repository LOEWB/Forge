package t1_1_Model_Principal;

public class Trame {
private float latitude;
private float longitude;
private int acquisition;

public Trame(float lat, float longi, int acq) {
this.latitude = lat;
this.longitude = longi;
this.acquisition = acq;

}

float getLatitude() {
	return this.latitude;
}

float getLongitude() {
	return this.longitude;
}

int getAcquisition() {
	return this.acquisition;
}

}
