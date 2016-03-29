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
		String temps = "";
		String integerPart = "";
		String decimalPart = "";
		String tempString2 = "";
		ArrayList<Point> listePoints;
		double temp;
		listePoints = this.genererListePointsIntermediaires(this.GPSdebit,this.getListePoints());
		int h = 0;
		long longueur = 0;
		long intPart;
		long minutes;
		long secondes;
		double doublePart;
		for (int i = 0; i < listePoints.size(); i++) {
			/*
			trame = "$GPGGA,";
			point = listePoints.get(i);
			h=0;
			tempString = "";
			tempString2 = "";
			
			if(point.getCoordonnes().getLatitude() < 0) lat = "S";
			else lat = "N";
			if(point.getCoordonnes().getLongitude() < 0) longi = "W";
			else longi = "E";
			temp = Math.abs(point.getCoordonnes().getLatitude());

			// latitude 4, 4
			// longitude 5, 4
			latitude = "" + temp + "";
			while(latitude.charAt(h) != '.') h++; // recherche partie int
			integerPart = latitude.substring(0, h);
			intPart = Integer.parseInt(integerPart); // d
			integerPart = "0." + latitude.substring(h+1, latitude.length()-1);
			doublePart = Double.parseDouble(integerPart);
			latitude = "" + intPart + doublePart*60 + "";
			//System.out.println("Latitude pre trame : " + latitude);
			if(latitude.length() > 9) latitude = latitude.substring(0, 9); // on coupe si trop long
			//System.out.println("Latitude pre trame 1 : " + latitude);
			
			while(latitude.charAt(h) != '.') h++;
			tempString2 = latitude.substring(0, h);
			//System.out.println("Latitude pre trame 2 : " + tempString2);
		//	if(latitude.length() > 4) latitude = latitude.substring(4, latitude.length()-1);
		//	if(tempString2.length() != 4) for(int m = tempString2.length(); m<4; m++) tempString += "0";
			tempString += tempString2;
			//System.out.println("Latitude : " + tempString);

			tempString2 = latitude.substring(h+1, latitude.length());
			if(tempString2.length() > 4) tempString2 = tempString2.substring(0, 3); 
			//System.out.println("Latitude : " + tempString);
			tempString += ".";
			//System.out.println("Longueur : " + tempString2.length());
		//	if(tempString2.length() != 4) for(int m = tempString2.length(); m<5; m++) tempString += "0";
			tempString += tempString2;
			latitude = tempString;
			//System.out.println("Latitude 2 " + latitude); 

			temp = Math.abs(point.getCoordonnes().getLongitude());
			
			longitude = "" + temp + "";
			h=0;
			tempString = "";
			tempString2 = "";
			//System.out.println("Longitude brut : " + longitude);
			while(longitude.charAt(h) != '.') h++; // recherche partie int
			integerPart = longitude.substring(0, h);
			intPart = Integer.parseInt(integerPart); // d
			integerPart = "0." + longitude.substring(h+1, longitude.length()-1);
			doublePart = Double.parseDouble(integerPart);
			longitude = "" + intPart + doublePart*60 + "";
			//System.out.println("Longitude pre trame : " + longitude);
//			if(longitude.length() > 10) longitude = longitude.substring(0, 9); // on coupe si trop long
			//System.out.println("Longitude pre trame 1 : " + longitude);
			
			while(longitude.charAt(h) != '.') h++;
			tempString2 = longitude.substring(0, h);
			//System.out.println("Longitude pre trame 2 : " + tempString2);
		//	if(latitude.length() > 4) latitude = latitude.substring(4, latitude.length()-1);
			//if(tempString2.length() != 5) for(int m = tempString2.length(); m<5; m++) tempString += "0";
			tempString += tempString2;
			//System.out.println("Longi : " + tempString);

			tempString2 = longitude.substring(h+1, longitude.length());
			if(tempString2.length() > 4) tempString2 = tempString2.substring(0, 3); 
			//System.out.println("Longi : " + tempString);
			tempString += ".";
			//System.out.println("Longueur : " + tempString2.length());
		//	if(tempString2.length() != 4) for(int m = tempString2.length(); m<4; m++) tempString += "0";
			tempString += tempString2;
			longitude = tempString;
			//System.out.println("Longitude 2 " + longitude); 


			
			temps = ""+ point.getTemps() +"";
		//	double dd = Math.point.getTemps();
			
				trame += temps + "," 
						
					
					+ latitude + "," + lat + ","
					+ longitude + "," + longi + ",1,"
					+ randomInteger(3, 12) + "," + (0.6 + randomInteger(0, 19))
					+ "," + point.getAltitude() + ",M,"
					+ (46.9 + randomInteger(1, 3)) + "46.9,M, , ";
				trame = trame.toString();
					int checksum = 0;
			for (int j = 1; j < trame.length(); j++) {
				checksum = checksum ^ Character.codePointAt(trame, j);
				
			}
			checksumString = "" + Integer.toHexString(checksum) + "";
			trame += "*" + checksumString.toUpperCase();
			trames += trame;

			*/
			
			 
			 			trame = "$GPGGA,";
			point = listePoints.get(i);
			h=0;
			tempString = "";
			tempString2 = "";
			if(point.getCoordonnes().getLatitude() < 0) lat = "S";
			else lat = "N";
			if(point.getCoordonnes().getLongitude() < 0) longi = "W";
			else longi = "E";
			temp = Math.abs(point.getCoordonnes().getLatitude());

			// latitude 4, 4
			// longitude 5, 4
			latitude = "" + temp + "";
			while(latitude.charAt(h) != '.') h++; // recherche partie int
			integerPart = latitude.substring(0, h);
			intPart = Integer.parseInt(integerPart); // d
			integerPart = "0." + latitude.substring(h+1, latitude.length()-1);
			doublePart = Double.parseDouble(integerPart);
			latitude = "" + intPart + doublePart*60 + "";
			//System.out.println("Latitude pre trame : " + latitude);
			if(latitude.length() > 9) latitude = latitude.substring(0, 9); // on coupe si trop long
			//System.out.println("Latitude pre trame 1 : " + latitude);
			
			while(latitude.charAt(h) != '.') h++;
			tempString2 = latitude.substring(0, h);
			//System.out.println("Latitude pre trame 2 : " + tempString2);
		//	if(latitude.length() > 4) latitude = latitude.substring(4, latitude.length()-1);
			if(tempString2.length() != 4) for(int m = tempString2.length(); m<4; m++) tempString += "0";
			tempString += tempString2;
			//System.out.println("Latitude : " + tempString);

			tempString2 = latitude.substring(h+1, latitude.length());
			if(tempString2.length() > 4) tempString2 = tempString2.substring(0, 3); 
			//System.out.println("Latitude : " + tempString);
			tempString += ".";
			//System.out.println("Longueur : " + tempString2.length());
			tempString += tempString2;
			if(tempString2.length() != 4) for(int m = tempString2.length(); m<4; m++) tempString += "0";
			latitude = tempString;
			//System.out.println("Latitude 2 " + latitude); 

			temp = Math.abs(point.getCoordonnes().getLongitude());
			
			longitude = "" + temp + "";
			h=0;
			tempString = "";
			tempString2 = "";
			//System.out.println("Longitude brut : " + longitude);
			while(longitude.charAt(h) != '.') h++; // recherche partie int
			integerPart = longitude.substring(0, h);
			intPart = Integer.parseInt(integerPart); // d
			integerPart = "0." + longitude.substring(h+1, longitude.length()-1);
			doublePart = Double.parseDouble(integerPart);
			longitude = "" + intPart + doublePart*60 + "";
			//System.out.println("Longitude pre trame : " + longitude);
//			if(longitude.length() > 10) longitude = longitude.substring(0, 9); // on coupe si trop long
			//System.out.println("Longitude pre trame 1 : " + longitude);
			
			while(longitude.charAt(h) != '.') h++;
			tempString2 = longitude.substring(0, h);
			//System.out.println("Longitude pre trame 2 : " + tempString2);
		//	if(latitude.length() > 4) latitude = latitude.substring(4, latitude.length()-1);
			if(tempString2.length() != 5) for(int m = tempString2.length(); m<5; m++) tempString += "0";
			tempString += tempString2;
			//System.out.println("Longi : " + tempString);

			tempString2 = longitude.substring(h+1, longitude.length());
			if(tempString2.length() > 4) tempString2 = tempString2.substring(0, 3); 
			//System.out.println("Longi : " + tempString);
			tempString += ".";
			tempString += tempString2;
			//System.out.println("Longueur : " + tempString2.length());
			if(tempString2.length() != 4) for(int m = tempString2.length(); m<4; m++) tempString += "0";
			longitude = tempString;
			//System.out.println("Longitude 2 " + longitude); 

			temps = ""+ point.getTemps() +"";
		//	double dd = Math.point.getTemps();
			
				trame += temps + "," 
						
					
					+ latitude + "," + lat + ","
					+ longitude + "," + longi + ",1,"
					+ randomInteger(3, 12) + "," + (0.6 + randomInteger(0, 19))
					+ "," + point.getAltitude() + ",M,"
					+ (46.9 + randomInteger(1, 3)) + ",M,,";
				trame = trame.toString();
					int checksum = 0;
			for (int j = 1; j < trame.length(); j++) {
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
		Point pointFinal=listePoints.get(listePoints.size()-1);
		listeFinale.add(new Point(pointFinal.getTemps(),new Coordonnees(pointFinal.getCoordonnes().getLongitude(),pointFinal.getCoordonnes().getLatitude()),pointFinal.getAltitude()));
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
	
	/**
	 * 
	 * @return double vitesse moyenne du parcours total en km/h
	 */
	public double vitesseMoyenne()
	{
		double eQuatorialEarthRadius = 6378.1370D;
		double d2r = (Math.PI / 180D);
		
		double long1=this.listePoints.get(0).getCoordonnes().getLongitude();
		double long2=this.listePoints.get(this.listePoints.size()-1).getCoordonnes().getLongitude();
		double lat1=this.listePoints.get(0).getCoordonnes().getLatitude();
		double lat2=this.listePoints.get(this.listePoints.size()-1).getCoordonnes().getLatitude();


	    double dlong = (long2 - long1) * d2r;
	    double dlat = (lat2 - lat1) * d2r;
	    double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(lat1 * d2r) * Math.cos(lat2 * d2r) * Math.pow(Math.sin(dlong / 2D), 2D);
	    double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
	    double d = eQuatorialEarthRadius * c;

		return d/(((this.listePoints.get(this.listePoints.size()-1).getTemps()-this.listePoints.get(0).getTemps()))/3600);
	}


	public double altitudeMoyenne() 
	{
		double altMoy = 0;
		for(Point point: this.listePoints)
		{
			altMoy+=point.getAltitude();
		}
		altMoy = altMoy / this.listePoints.size();
		return altMoy;
	}

}