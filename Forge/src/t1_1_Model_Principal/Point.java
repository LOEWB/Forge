package t1_1_Model_Principal;

public class Point
{
	private Coordonnees coordonnes;
	private int tempsPassageRelatif;
	private float altitude;
	
	public Point(int sec, Coordonnees coor,float altitude) {
	
		this.coordonnes = coor;
	    this.tempsPassageRelatif = sec;
		this.altitude=altitude;		
	}

	public Coordonnees getCoordonnes() 
	{
		return this.coordonnes;
	}
	

	public int getTemps() 
	{
		return this.tempsPassageRelatif;
	}

	public float getAltitude() 
	{
		return this.altitude;
	}
	
	
}
