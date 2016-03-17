package t1_1_Model_Principal;

import java.util.ArrayList;
import java.util.Random;

public class Parcours {
	private TypeSysteme typeSysteme;
	private ArrayList<Point> listePoints = new ArrayList<Point>();

	public Parcours(TypeSysteme typeSysteme, ArrayList<Point> listePoints) {
		this.typeSysteme = typeSysteme;
		this.listePoints = listePoints;
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

		ArrayList<Point> listePoints;

		listePoints = this.getListePoints();

		for (int i = 0; i < listePoints.size(); i++) {
			trame = "$GPGGA,";
			point = listePoints.get(i);
			if(point.getCoordonnes().getLatitude() < 0) {
			trame += point.getTemps() + ","
					+ Math.abs(point.getCoordonnes().getLatitude()) + ",S,"
					+ point.getCoordonnes().getLongitude() + ",E,1,"
					+ randomInteger(3, 12) + "," + (0.6 + randomInteger(0, 19))
					+ "," + point.getAltitude() + ",M,"
					+ (46.9 + randomInteger(1, 3)) + "46.9,M, , ";
			}
			else {
				trame += point.getTemps() + ","
						+ point.getCoordonnes().getLatitude() + ",N,"
						+ point.getCoordonnes().getLongitude() + ",E,1,"
						+ randomInteger(3, 12) + "," + (0.6 + randomInteger(0, 19))
						+ "," + point.getAltitude() + ",M,"
						+ (46.9 + randomInteger(1, 3)) + "46.9,M, , ";

			}
			int checksum = 0;
			for (int j = 0; j < trame.length(); j++) {
				checksum = checksum ^ Character.codePointAt(trame, j);
			}

			trame += "*" + checksum;

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

}
