package t1_1_Model_Principal;

public class Coordonnees 
{
	float longitude;
	float latitude;
	
	public Coordonnees(float longitude,float latitude)
	{
		this.longitude=longitude;
		this.latitude=latitude;
	}

	public float getLongitude() 
	{
		return longitude;
	}

	public float getLatitude() 
	{
		return latitude;
	}
}
