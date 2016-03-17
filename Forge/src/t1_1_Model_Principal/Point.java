package t1_1_Model_Principal;

public class Point {
	private Coordonnees coordonnes;
	private double tempsPassageRelatif;
	private double altitude;

	public Point(double sec, Coordonnees coor, double altitude) {

		this.coordonnes = coor;
		this.tempsPassageRelatif = sec;
		this.altitude = altitude;
	}

	public Coordonnees getCoordonnes() {
		return this.coordonnes;
	}

	public double getTemps() {
		return this.tempsPassageRelatif;
	}

	public double getAltitude() {
		return this.altitude;
	}
	
	public String toString()
	{
		return "Temps : "+this.tempsPassageRelatif+" Longitude : "+this.coordonnes.getLongitude()+" Latitude : "+this.coordonnes.getLatitude()+" Altitude : "+this.altitude;
		
	}

}
