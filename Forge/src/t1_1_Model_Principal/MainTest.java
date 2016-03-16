package t1_1_Model_Principal;


public class MainTest {

	public static void main(String[] args) {
		System.out.println("\t\t-- Application de test en cmd de cr�ation,sauvegarde et chargement de parcours puis exportation, importation simu--\n\n");
		System.out.println("\t\tPARCOURS\n\n");
		System.out.println("Cr�ation d'un nouveau parcours avec deux points\n");
		Point valence = new Point(180823, new Coordonnees(4.890892, 44.933014),0);
		Point valence2 = new Point(180828, new Coordonnees(44.933014, 4.890892),150);
		Point saintay = new Point(180833, new Coordonnees(45.446958, 4.383396),0);
		Point p4 = new Point(180833, new Coordonnees(45.446958, 4.383396),0);
		Point p5 = new Point(180834, new Coordonnees(45.446958, 4.383396),0);
		Point p6 = new Point(180835, new Coordonnees(45.446958, 4.383396),0);
		Point p7 = new Point(180835.5, new Coordonnees(45.446958, 4.383396),0);
		Point p8 = new Point(180835.6, new Coordonnees(45.446958, 4.383396),0);
		Point p9 = new Point(180835.7, new Coordonnees(45.446958, 4.383396),0);
		Parcours parcours = new Parcours(TypeSysteme.TERRESTRE);
		parcours.ajouterPoint(valence);
		parcours.ajouterPoint(valence2);
		parcours.ajouterPoint(saintay);
		parcours.ajouterPoint(p4);
		parcours.ajouterPoint(p5);
		parcours.ajouterPoint(p6);
		parcours.ajouterPoint(p7);
		parcours.ajouterPoint(p8);
		parcours.ajouterPoint(p9);

		System.out.println(parcours.genererTrames());

		System.out.println("\nEnregistrement du parcours dans le fichier ParcoursStValence.fGF\n");
		parcours.sauvegarderParcours("ParcoursSteValence.fGF");

		System.out.println("\nCr�ation d'un nouveau parcours\nChargement du parcours depuis le fichier ParcoursStValence.fGF dans ce nouveau parcours\n");
		Parcours newParcours = new Parcours(TypeSysteme.TERRESTRE);
		newParcours.chargerParcours("ParcoursSteValence.fGF");
		System.out.println("\nPoints charg�es : \n");
		for(int i=0;i<parcours.getListePoints().size();i++)
		{
			Point pc=parcours.getListePoints().get(i);
			System.out.println("point num�ro "+i+"\t\tlongitude : "+pc.getCoordonnes().getLongitude()+"\t\tlatitude : "+pc.getCoordonnes().getLatitude()+"\t\taltitude : "+pc.getAltitude()+"\t\theure de passage : "+pc.getTemps());
		}
		
		System.out.println("\n\n\t\tSIMULATION\n\n");
		Simulation simu = new Simulation(parcours);
		System.out.println("\nExportation de la simulation dans le fichier SimuSteValence.nmea\nImportation de la simulation depuis le fichier SimuSteValence.nmea");
		simu.exportSimulation("SimuSteValence.nmea");
		simu.importSimulation("SimuSteValence.nmea");
		System.out.println("\n\nAffichage des trames import�es par ligne :\n");
		for (String trame : simu.getTramesArray())
		{
			System.out.println(trame);
		}
		
		System.out.println("\n\nD�but simulation :");
		simu.jouerSimulation();

	}
}
