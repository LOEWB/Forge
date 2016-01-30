package t1_1_Model_Principal;

public class Point2D 
{
	private int secondesRelatives;
	private Coordonnees coordonnees;
	
	public Point2D(int sec, Coordonnees coor)
	{
		this.secondesRelatives=sec;
		this.coordonnees=coor;
	}

	public int getSecondesRelatives() 
	{
		return secondesRelatives;
	}

	public Coordonnees getCoordonnees() 
	{
		return coordonnees;
	}
	
}
