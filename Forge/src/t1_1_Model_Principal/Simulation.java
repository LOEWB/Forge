package t1_1_Model_Principal;

import java.util.ArrayList;
import java.util.Arrays;


public class Simulation {
	private EtatSimu etat;
	private float vitesse;
	private Parcours parcours;
	private ArrayList<String> tramesArray;
	
	public Simulation(Parcours parcours) {

		this.parcours = parcours;
		this.etat = EtatSimu.ARRET;
		this.tramesArray=new ArrayList<String>();
		this.chargerTramesBrutes(parcours.genererTrames());
	}

	public Simulation() {
	}

	public ArrayList<String> getTramesArray() {
		return tramesArray;
	}
	
	float getVitesse() {
		return this.vitesse;
	}

	void augmenterVitesse() {
		this.vitesse += 0.5;
	}

	void diminuerVitesse() {
		this.vitesse -= 0.5;
	}

	EtatSimu getEtat() {
		return this.etat;
	}
	/*
	public void chargerParcours(String cheminFichier) {
		ConteneurFichier conteneurFichier = new ConteneurFichier();
		String parcoursBrut = conteneurFichier.lire(cheminFichier);
		
		String[] attributsTable=parcoursBrut.split("#");
		//type systeme
		if(attributsTable[0]!="1")
			this.typeSysteme=TypeSysteme.TERRESTRE;
		else
			this.typeSysteme=TypeSysteme.AERIEN;
		
		//debitGPS
		this.GPSdebit=Float.valueOf(attributsTable[1]);
		//liste points
		String[] listePoints=attributsTable[2].split("\\$");
		
		for(int i=0;i<listePoints.length;i++)
		{
			String[] tabPt=listePoints[i].split(",");
			this.listePoints.add(new Point(Double.valueOf(tabPt[0]),new Coordonnees(Double.valueOf(tabPt[2]),Double.valueOf(tabPt[3])),Double.valueOf(tabPt[1])));
		}
		

			

	} */
	// $GPGGA,180820.0,4455.9806,N,00453.4530,E,1,6,14.6,0.0,M,47.9,M,,*57
	  
	 
	
	Point getPoint(String trame) {
		 Coordonnees coordonnees;
		 double altitude;
		 double latitude;
		 double longitude;
		 
		 
		String[] attributsTable=trame.split(",");
		altitude = Double.valueOf(attributsTable[1]);
		latitude = Double.valueOf(attributsTable[2]);
		longitude = Double.valueOf(attributsTable[4]);
		
		coordonnees = new Coordonnees(latitude, longitude);
		
		return new Point(0, coordonnees,  altitude);
		
		
	}
	
	
	public void importSimulation(String cheminFichier)
	{
		ConteneurFichier conteneurFichier = new ConteneurFichier();
		String simulationBrut = conteneurFichier.lire(cheminFichier);
		
		chargerTramesBrutes(simulationBrut);
	}
	
	public void exportSimulation(String cheminFichier)
	{
		ConteneurFichier conteneurFichier = new ConteneurFichier();
		conteneurFichier.ecrire(cheminFichier, this.parcours.genererTrames());
	}
	
	public void chargerTramesBrutes(String tramesBrutes)
	{
		String[] tramesTable = tramesBrutes.split("(?=\\$)");
		
		this.tramesArray=new ArrayList<String>(Arrays.asList(tramesTable));
	}
	
	public void jouerSimulation()
	{
		ThreadLecture threadLecture = new ThreadLecture(this.tramesArray);
		threadLecture.start();
	}
	
}
