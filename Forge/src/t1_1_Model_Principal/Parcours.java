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
	
	public Parcours() {
		this.typeSysteme = TypeSysteme.TERRESTRE;
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
	
	public void setListePoints(ArrayList<Point> listePoints) {
		this.listePoints = listePoints;
	}

	public void ajouterPoint(Point p) {

		this.listePoints.add(p);

	}
	
	public void supprimerPoint(Point p)
	{
		this.listePoints.remove(p);
	}

	public int randomInteger(int min, int max) {

		Random rand = new Random();

		
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
			
			 
			 			trame = "$GPGGA,";
			point = listePoints.get(i);
			h=0;
			tempString = "";
			tempString2 = "";
			if(point.getCoordonnes().getLongitude() < 0) lat = "S";
			else lat = "N";
			if(point.getCoordonnes().getLatitude() < 0) longi = "W";
			else longi = "E";
			temp = Math.abs(point.getCoordonnes().getLongitude());

		
			latitude = "" + temp + "";
			while(latitude.charAt(h) != '.') h++; // recherche partie int
			integerPart = latitude.substring(0, h);
			intPart = Integer.parseInt(integerPart);
			integerPart = "0." + latitude.substring(h+1, latitude.length()-1);
			doublePart = Double.parseDouble(integerPart);
			latitude = "" + intPart + doublePart*60 + "";
			if(latitude.length() > 9) latitude = latitude.substring(0, 9); // on coupe si trop long
			
			while(latitude.charAt(h) != '.') h++;
			tempString2 = latitude.substring(0, h);
			
			if(tempString2.length() != 4) for(int m = tempString2.length(); m<4; m++) tempString += "0";
			tempString += tempString2;

			tempString2 = latitude.substring(h+1, latitude.length());
			if(tempString2.length() > 4) tempString2 = tempString2.substring(0, 3); 
			tempString += ".";
			tempString += tempString2;
			if(tempString2.length() != 4) for(int m = tempString2.length(); m<4; m++) tempString += "0";
			latitude = tempString;

			temp = Math.abs(point.getCoordonnes().getLatitude());
			
			longitude = "" + temp + "";
			
			h=0;
			tempString = "";
			tempString2 = "";
			while(longitude.charAt(h) != '.') h++; // recherche partie int
			integerPart = longitude.substring(0, h);
			intPart = Integer.parseInt(integerPart); // d
			integerPart = "0." + longitude.substring(h+1, longitude.length()-1);
			doublePart = Double.parseDouble(integerPart);
			longitude = "" + intPart + doublePart*60 + "";
			
			while(longitude.charAt(h) != '.') h++;
			tempString2 = longitude.substring(0, h);
			if(tempString2.length() != 5) for(int m = tempString2.length(); m<5; m++) tempString += "0";
			tempString += tempString2;

			tempString2 = longitude.substring(h+1, longitude.length());
			if(tempString2.length() > 4) tempString2 = tempString2.substring(0, 3); 
			tempString += ".";
			tempString += tempString2;
			if(tempString2.length() != 4) for(int m = tempString2.length(); m<4; m++) tempString += "0";
			longitude = tempString;

			temps = ""+ point.getTemps() +"";

			 long days=0;
			 long hours=0;
			 long mins=0;
			 long secs=0;
			 double dd = Double.parseDouble(temps);
			 secs = Math.round(dd);
			// System.out.println("Secs" + secs);
			   hours = Math.round(secs / 3600);
			  
			   mins = Math.round((secs % 3600) / 60);
			   secs = Math.round(secs % 60);
			   temps = Long.toString(hours)+Long.toString(mins)+Long.toString(secs);
			 String temp26 = "";
			
		//	 System.out.println("Temps " + temps + "Longueur" + temps.length());
			 
			 if(temps.length() < 6) {
				 int gg=temps.length(); 
				 while(gg < 6) { temp26 += "0"; gg++; }
				 temp26 += temps;
				 temps = temp26;
				 
			 }
		
			
				trame += temps + "," 
							
					
						
					+ latitude + "," + lat + ","
					+  longitude + "," + longi + ",1,"
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
		String parcoursBrut="";
		String typeSys;
		ConteneurFichier conteneurFichier = new ConteneurFichier();
		
		//type systeme
		if (this.typeSysteme==TypeSysteme.TERRESTRE)
			typeSys="0";
		else 
			typeSys="1";
		parcoursBrut+=typeSys;
		//debitGPS
		parcoursBrut+="#"+this.GPSdebit;
		//liste points
		parcoursBrut+="#";
		for(int i=0;i<this.listePoints.size();i++)
		{
			parcoursBrut+=this.listePoints.get(i).getTemps();
			parcoursBrut+=",";
			parcoursBrut+=this.listePoints.get(i).getAltitude();
			parcoursBrut+=",";
			parcoursBrut+=this.listePoints.get(i).getCoordonnes().getLatitude();
			parcoursBrut+=",";
			parcoursBrut+=this.listePoints.get(i).getCoordonnes().getLongitude();
			parcoursBrut+="$";
		}
		conteneurFichier.ecrire(cheminFuturFichier, parcoursBrut);
	}

	public void chargerParcours(String cheminFichier) {
		ConteneurFichier conteneurFichier = new ConteneurFichier();
		String parcoursBrut = conteneurFichier.lire(cheminFichier);
		
		String[] attributsTable=parcoursBrut.split("#");
		//type systeme
		if(!attributsTable[0].equals("1"))
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
	
	public double vitesseSegments(Point p1, Point p2)
	{
		double eQuatorialEarthRadius = 6378.1370D;
		double d2r = (Math.PI / 180D);
		
		double long1=p1.getCoordonnes().getLongitude();
		double long2=p2.getCoordonnes().getLongitude();
		double lat1=p1.getCoordonnes().getLatitude();
		double lat2=p2.getCoordonnes().getLatitude();


	    double dlong = (long2 - long1) * d2r;
	    double dlat = (lat2 - lat1) * d2r;
	    double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(lat1 * d2r) * Math.cos(lat2 * d2r) * Math.pow(Math.sin(dlong / 2D), 2D);
	    double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
	    double d = eQuatorialEarthRadius * c;

		return d/(((p2.getTemps()-p1.getTemps()))/3600);
	}
	
	
	public double altitudeSegments(Point p1, Point p2)
	{
				
		double alt1=p1.getAltitude();
		double alt2=p2.getAltitude();

		return (alt2+alt1)/2l;
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