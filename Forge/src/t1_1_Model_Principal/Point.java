package t1_1_Model_Principal;

public class Point {
	private Coordonnees coordonnes;
	private double tempsPassageRelatif;
	private float altitude;

	public Point(double sec, Coordonnees coor, float altitude) {

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

	public float getAltitude() {
		return this.altitude;
	}

}
