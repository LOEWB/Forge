package t1_1_Model_Principal;

import java.util.ArrayList;
import java.util.Random;

public class Parcours {
	private TypeSysteme typeSysteme;
	private ArrayList<Point> listePoints = new ArrayList<Point>();
	private float GPSdebit;

	public Parcours(TypeSysteme typeSysteme, ArrayList<Point> listePoints) {
		this.typeSysteme = typeSysteme;
		this.listePoints = listePoints;
		this.GPSdebit=1;
	}
	
	public Parcours(TypeSysteme typeSysteme, ArrayList<Point> listePoints, float debit) {
		this.typeSysteme = typeSysteme;
		this.listePoints = listePoints;
		this.GPSdebit=debit;
	}

	public Parcours(TypeSysteme typeSysteme) {
		this.typeSysteme = typeSysteme;
	}

	public boolean verifierParcours() {
		if (this.listePoints.size() == 0)
			return false;
		else
			return true;
	}

	public TypeSysteme getTypeSysteme() {
		return typeSysteme;
	}

	public ArrayList<Point> getListePoints() {
		return listePoints;
	}

	public void ajouterPoint(Point p) {
//		int index = 0;
//
//		if (this.listePoints.size() == 0)
//			this.listePoints.add(p);
//		else {
//			while (index <= this.listePoints.size()
//					&& this.listePoints.get(index).getTemps() > p.getTemps()) {
//				index++;
//			}
//			this.listePoints.add(index + 1, p);
//		}
		this.listePoints.add(p);

	}
	
	public void supprimerPoint(Point p)
	{
		this.listePoints.remove(p);
	}

	public int randomInteger(int min, int max) {

		Random rand = new Random();

		// nextInt excludes the top value so we have to add 1 to include the top
		// value
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public String genererTrames() {
		String trames = "";
		String trame = "";
		Point point;
		String lat = "";
		String longi = "";
		String latitude = "";
		String longitude = "";
		String tempString = "";
		String checksumString = "";
		ArrayList<Point> listePoints;
		double temp;
		listePoints = this.genererListePointsIntermediaires(this.GPSdebit,this.getListePoints());
		int h = 0;
		int longueur = 0;
		for (int i = 0; i < listePoints.size(); i++) {
			trame = "$GPGGA,";
			point = listePoints.get(i);
			h=0;
			tempString = "";
			if(point.getCoordonnes().getLatitude() < 0) lat = "S";
			else lat = "N";
			if(point.getCoordonnes().getLongitude() < 0) longi = "W";
			else longi = "E";
			temp = Math.abs(point.getCoordonnes().getLatitude());
			
			// latitude 4, 6
			// longitude 5, 6
			latitude = "" + temp + "";
		//	System.out.println(latitude);
			while(latitude.charAt(h) != '.') h++;
			h++;
			//System.out.println(h);
			latitude = latitude.substring(0, h);
			//System.out.println("Latitude 1 " + latitude);
			for(int m = latitude.length(); m<4+1; m++) tempString += "0";
			tempString += latitude;
			temp = Math.abs(point.getCoordonnes().getLatitude());
			latitude = "" + temp + "";
			latitude = latitude.substring(h, latitude.length()-1);
			
			//latitude = latitude.substring(h, h+7);
			//tempString += ".";
			for(int m = latitude.length(); m<6+1; m++) tempString += "0";
			tempString += latitude;
			//tempString = tempString.substring(12, tempString.length());
			latitude = tempString;
			latitude = latitude.substring(0, 11);
			//latitude = latitude.substring(5, 11)
		//	System.out.println("Latitude brute " + point.getCoordonnes().getLatitude() + " Trame " + latitude);
			temp = Math.abs(point.getCoordonnes().getLongitude());

			longitude = "" + temp + "";
			h=0;
			tempString = "";
			while(longitude.charAt(h) != '.') h++;
			h++;
			longitude = longitude.substring(0, h);
			for(int m = longitude.length(); m<5+1; m++) tempString += "0";
			tempString += longitude;
			temp = Math.abs(point.getCoordonnes().getLongitude());
			longitude = "" + temp + "";
			longitude = longitude.substring(h, longitude.length()-1);
			
			//tempString += ".";
			for(int m = longitude.length(); m<6+1; m++) tempString += "0";
			tempString += longitude;
			longitude = tempString;
			longitude= longitude.substring(0, 12);
			
				trame += Math.round(point.getTemps()) + "," 
					
					+ latitude + "," + lat + ","
					+ longitude + "," + longi + ",1,"
					+ randomInteger(3, 12) + "," + (0.6 + randomInteger(0, 19))
					+ "," + point.getAltitude() + ",M,"
					+ (46.9 + randomInteger(1, 3)) + "46.9,M, , ";
					int checksum = 0;
			for (int j = 0; j < trame.length(); j++) {
				checksum = checksum ^ Character.codePointAt(trame, j);
				
			}
			checksumString = "" + Integer.toHexString(checksum) + "";
			trame += "*" + checksumString.toUpperCase();
			trames += trame;

		}

		return trames;
	}

	public void sauvegarderParcours(String cheminFuturFichier) {

		// cheminFuturFichier += ".fGF"; a voir...

		ConteneurFichier conteneurFichier = new ConteneurFichier();

		conteneurFichier.ecrire(cheminFuturFichier, genererTrames());

	}

	public void chargerParcours(String cheminFichier) {

		double tempsPremierPoint = 0;
		double tempsPoint = 0;
		int i = 0;
		double longitude;
		double latitude;
		float altitude = 0;

		ConteneurFichier conteneurFichier = new ConteneurFichier();

		String parcoursBrut = conteneurFichier.lire(cheminFichier);
		String temporaire = "";

		i = 7;

		while (parcoursBrut.charAt(i) != ',') {
			temporaire += parcoursBrut.charAt(i);
			i++;
		}
		i++;

		tempsPremierPoint = Double.parseDouble(temporaire);

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
		}

		listePoints.add(new Point(tempsPremierPoint, new Coordonnees(longitude,
				latitude), altitude));

		while (parcoursBrut.indexOf("$GPGGA", 0) != -1) {

			temporaire = "";

			i = 7;

			while (parcoursBrut.charAt(i) != ',') {
				temporaire += parcoursBrut.charAt(i);
				i++;
			}
			i++;

			tempsPoint = Double.parseDouble(temporaire);

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

			this.listePoints.add(new Point(tempsPoint - tempsPremierPoint,
					new Coordonnees(longitude, latitude), altitude));
		}
	}
	
	/**
	 * 
	 * @param debit en trame/seconde
	 * @param listePoints placés par l'utilisateur sur la carte
	 * @return la liste de points avec tous les points intermédiaires générés
	 */
	public ArrayList<Point> genererListePointsIntermediaires(float debit,ArrayList<Point> listePoints)
	{
		ArrayList<Point> listeFinale=new ArrayList<Point>();
		double periodeTrame = 1/(double)debit;
		//traitement pour tous les points sauf le dernier
		for (int i=0;i+1<listePoints.size();i++)
		{
			double nbPeriodeIntermediaire = (listePoints.get(i+1).getTemps()-listePoints.get(i).getTemps())*debit;
			//correspond à la translation de la longitude en une periode 
			double transLongitudePeriode = (listePoints.get(i+1).getCoordonnes().getLongitude()-listePoints.get(i).getCoordonnes().getLongitude())/nbPeriodeIntermediaire;
			double transLatitudePeriode = (listePoints.get(i+1).getCoordonnes().getLatitude()-listePoints.get(i).getCoordonnes().getLatitude())/nbPeriodeIntermediaire;
			double transAltitudePeriode = (listePoints.get(i+1).getAltitude()-listePoints.get(i).getAltitude())/nbPeriodeIntermediaire;

			for(int p=0;p<nbPeriodeIntermediaire;p++)
			{
				Point pointCourant = new Point(listePoints.get(i).getTemps()+p*periodeTrame, new Coordonnees(listePoints.get(i).getCoordonnes().getLongitude()+p*transLongitudePeriode, listePoints.get(i).getCoordonnes().getLatitude()+p*transLatitudePeriode), listePoints.get(i).getAltitude()+p*transAltitudePeriode);
				listeFinale.add(pointCourant);
			}
		}
		listeFinale.add(listePoints.get(listePoints.size()-1));
		return listeFinale;
	}
	
	public void setDebit(float debit)
	{
		this.GPSdebit=debit;
	}
	
	public float getDebit()
	{
		return this.GPSdebit;
	}

}