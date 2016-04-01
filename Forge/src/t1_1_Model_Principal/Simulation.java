package t1_1_Model_Principal;

import java.util.ArrayList;
import java.util.Arrays;


public class Simulation {
	private EtatSimu etat;
	private float vitesse;
	private Parcours parcours;
	private ArrayList<String> tramesArray;
	private ArrayList<Point> listePointsImportes = new ArrayList<Point>();  

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

	public EtatSimu getEtat() {
		return this.etat;
	}


	public Point getPoint(String trame) {
		Coordonnees coordonnees;
		double temps;
		double altitude;
		double latitude;
		double longitude;
		double doublePart;
		
		String integerPart = "";
		String temporaire2 = "";
		String temps2 = "";
		
		int h=0;
		int intPart=0;
		
		String[] attributsTable=trame.split(",");
		temps2 = attributsTable[1];
		latitude = Double.valueOf(attributsTable[2]);
		longitude = Double.valueOf(attributsTable[4]);
		altitude =  Double.valueOf(attributsTable[7]);


		
		long secs = Integer.parseInt(temps2.substring(4, 6));
		long mins =  Integer.parseInt(temps2.substring(2, 4));
		long hours = Integer.parseInt(temps2.substring(0, 2));

		
		temps = secs+(mins*60)+hours*(3600);
		 

		integerPart = "";
		temporaire2 = ""+latitude+"";

		h=0;
		while(temporaire2.charAt(h) != '.') h++; // recherche partie int
		integerPart = temporaire2.substring(0, 2);

		intPart = Integer.parseInt(integerPart); // d
		integerPart = temporaire2.substring(2, temporaire2.length());

		doublePart = Double.parseDouble(integerPart);
		doublePart = intPart + doublePart/60;
		temporaire2 = "" + doublePart + "";
		latitude = Double.valueOf(temporaire2);
		integerPart = "";
		temporaire2 = ""+longitude+"";

		h=0;
		while(temporaire2.charAt(h) != '.') h++; // recherche partie int
		integerPart = temporaire2.substring(0, 2);

		intPart = Integer.parseInt(integerPart); // d
		integerPart = temporaire2.substring(2, temporaire2.length());

		doublePart = Double.parseDouble(integerPart);
		doublePart = intPart + doublePart/60;
		temporaire2 = "" + doublePart + "";
		longitude = Double.valueOf(temporaire2);
	//	System.out.println("Longitude get23 " + longitude);

		coordonnees = new Coordonnees(latitude, longitude);

		return new Point(temps, coordonnees,  altitude);


	}

	ArrayList<Point> getPoints(String trames) {

		ArrayList<Point> listePoints = new ArrayList<Point>();

		double tempsPremierPoint = 0;
		double tempsPoint = 0;
		double doublePart;
		int i = 0;
		int h=0;
		int intPart;
		double longitude;
		double latitude;
		float altitude = 0;
		double doubleTemp;

		String parcoursBrut = trames;
		String temporaire = "";
		String temporaire2 = "";
		String integerPart = "";
		String temps2 = "";
		
		i = 7;

		while (parcoursBrut.charAt(i) != ',') {
			temporaire += parcoursBrut.charAt(i);
			i++;
		}
		i++;

		
		
		temps2 = temporaire;

		long secs = Integer.parseInt(temps2.substring(4, 6));
		long mins =  Integer.parseInt(temps2.substring(2, 4));
		long hours = Integer.parseInt(temps2.substring(0, 2));

		tempsPremierPoint = secs+(mins*60)+hours*(3600);

		
		temporaire = "";

		while (parcoursBrut.charAt(i) != ',') {
			temporaire += parcoursBrut.charAt(i);
			i++;
		}

		i++;
		latitude = Double.parseDouble(temporaire);
		i += 2;
		temporaire = "";
		while (parcoursBrut.charAt(i) != ',') {
			temporaire += parcoursBrut.charAt(i);
			i++;
		}
		i++;
		longitude = Double.parseDouble(temporaire);
		i += 2;

		while (parcoursBrut.charAt(i) != ',')
			i++; // saut du fix
		i++;

		while (parcoursBrut.charAt(i) != ',')
			i++; // saut satellites
		i++;

		while (parcoursBrut.charAt(i) != ',')
			i++; // saut DOP
		i++;

		temporaire = "";

		while (parcoursBrut.charAt(i) != ',') {
			temporaire += parcoursBrut.charAt(i);
			i++;
		}

		altitude = Float.parseFloat(temporaire);

		if ((parcoursBrut.indexOf("$GPGGA", i)) != -1) {
			i = parcoursBrut.indexOf("$GPGGA", i);
			parcoursBrut = parcoursBrut.substring(i);


			listePoints.add(new Point(tempsPremierPoint, new Coordonnees(latitude,
					longitude), altitude));

			while (parcoursBrut.indexOf("$GPGGA", 0) != -1) {

				temporaire = "";

				i = 7;

				while (parcoursBrut.charAt(i) != ',') {
					temporaire += parcoursBrut.charAt(i);
					i++;
				}
				i++;


				temps2 = temporaire;
				secs = Integer.parseInt(temps2.substring(4, 6));
				mins =  Integer.parseInt(temps2.substring(2, 4));
				hours = Integer.parseInt(temps2.substring(0, 2));
	
				tempsPoint = secs+(mins*60)+hours*(3600);

				temporaire = "";

				while (parcoursBrut.charAt(i) != ',') {
					temporaire += parcoursBrut.charAt(i);
					i++;
				}
				i++;
				latitude = Double.parseDouble(temporaire);
				i += 2;
				temporaire = "";
				while (parcoursBrut.charAt(i) != ',') {
					temporaire += parcoursBrut.charAt(i);
					i++;
				}
				i++;
				longitude = Double.parseDouble(temporaire);
				i += 2;

				while (parcoursBrut.charAt(i) != ',')
					i++; // saut du fix
				i++;

				while (parcoursBrut.charAt(i) != ',')
					i++; // saut satellites
				i++;

				while (parcoursBrut.charAt(i) != ',')
					i++; // saut DOP
				i++;

				temporaire = "";

				while (parcoursBrut.charAt(i) != ',') { // offset to go to the next
					// frame : c'est plus facile
					// à dire en anglais :)
					temporaire += parcoursBrut.charAt(i);
					i++;
				}

				altitude = Float.parseFloat(temporaire);

				if ((parcoursBrut.indexOf("$GPGGA", i)) != -1)
					i = parcoursBrut.indexOf("$GPGGA", i);

				parcoursBrut = parcoursBrut.substring(i);

				integerPart = "";
				temporaire2 = ""+latitude+"";

				h=0;
				while(temporaire2.charAt(h) != '.') h++; // recherche partie int
				integerPart = temporaire2.substring(0, 2);

				intPart = Integer.parseInt(integerPart); // d
				integerPart = temporaire2.substring(2, temporaire2.length());

				doublePart = Double.parseDouble(integerPart);
				doublePart = intPart + doublePart/60;
				temporaire2 = "" + doublePart + "";
				latitude = Double.valueOf(temporaire2);
				integerPart = "";
				temporaire2 = ""+longitude+"";

				h=0;
				while(temporaire2.charAt(h) != '.') h++; // recherche partie int
				integerPart = temporaire2.substring(0, 2);

				intPart = Integer.parseInt(integerPart); // d
				integerPart = temporaire2.substring(2, temporaire2.length());

				doublePart = Double.parseDouble(integerPart);
				doublePart = intPart + doublePart/60;
				temporaire2 = "" + doublePart + "";
				longitude = Double.valueOf(temporaire2);


				listePoints.add(new Point(tempsPoint - tempsPremierPoint,
						new Coordonnees(latitude, longitude), altitude));
			}
		}

		return listePoints;
	}


	public void importSimulation(String cheminFichier)
	{
		ConteneurFichier conteneurFichier = new ConteneurFichier();
		String simulationBrut = conteneurFichier.lire(cheminFichier);

		chargerTramesBrutes(simulationBrut);

		this.listePointsImportes = this.getPoints(simulationBrut);
		System.out.println( "Points importés " + this.listePointsImportes.toString());
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

	public void jouerSimulation(String port, int debit, float TauxErreur, float vitesse)
	{
		Thread t1 = new ThreadLecture(this.tramesArray, port, debit, TauxErreur, vitesse);
		t1.start();
	}

	public void setEtat(EtatSimu etat) {
		this.etat = etat;
		
	}
	
	public ArrayList<Point> getlistePointsImportes()
	{
		return this.listePointsImportes;
		
	}
	
	public void setlistePointsImportes(ArrayList<Point> listePointsImportes) 
	{
		this.listePointsImportes = listePointsImportes;		
	}

}