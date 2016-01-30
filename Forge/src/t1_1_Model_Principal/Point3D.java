package t1_1_Model_Principal;

public class Point3D extends Point2D
{
	private int altitude;
	
	public Point3D(int sec, Coordonnees coor,int altitude) 
	{
		super(sec, coor);
		this.altitude=altitude;		
	}

	public int getAltitude() 
	{
		return altitude;
	}
	
}
