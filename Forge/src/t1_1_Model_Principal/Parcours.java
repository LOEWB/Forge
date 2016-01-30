package t1_1_Model_Principal;

import java.util.ArrayList;

public class Parcours 
{
	private TypeSysteme typeSysteme;
	private  ArrayList<Point2D> listePoints; 
	
	public Parcours(TypeSysteme typeSysteme, ArrayList<Point2D> listePoints) 
	{
		this.typeSysteme = typeSysteme;
		this.listePoints = listePoints;
	}
	
	public boolean verifierParcours()
	{
		if (this.listePoints.size()==0)
			return false;
		else
			return true;			
	}

	public TypeSysteme getTypeSysteme() 
	{
		return typeSysteme;
	}

	public ArrayList<Point2D> getListePoints() 
	{
		return listePoints;
	}

	
}
