package t1_1_Model_Principal;

public class Point
{
	private Coordonnees coordonnes;
	private int unixTimeStamp;
	private float altitude;
	
	public Point(int sec, Coordonnees coor,int altitude) {
	
		this.coordonnes = coor;
	    this.unixTimeStamp = sec;
		this.altitude=altitude;		
	}

	public Coordonnees getCoordonnes() 
	{
		return this.coordonnes;
	}
	

	public int getDate() 
	{
		return this.unixTimeStamp;
	}

	public float getAltitude() 
	{
		return this.altitude;
	}
	
	
}
