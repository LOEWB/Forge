package t1_1_Model_Principal;

import java.util.ArrayList;

public class Parcours 
{
	private TypeSysteme typeSysteme;
	private  ArrayList<Point> listePoints = new ArrayList<Point>(); 
	
	public Parcours(TypeSysteme typeSysteme, ArrayList<Point> listePoints) 
	{
		this.typeSysteme = typeSysteme;
		this.listePoints = listePoints;
	}
	
	public Parcours(TypeSysteme typeSysteme)
	{
		this.typeSysteme = typeSysteme;
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

	public ArrayList<Point> getListePoints() 
	{
		return listePoints;
	}

	public void ajouterPoint(Point p)
	{
		int index=0;
		
		if (this.listePoints.size()==0)
			this.listePoints.add(p);
		else
		{
			while(index<=this.listePoints.size()&&this.listePoints.get(index).getTemps()>p.getTemps())
			{
				index++;
			}
			this.listePoints.add(index, p);
		}
	}
}
