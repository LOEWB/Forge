package t1_1_Model_Principal;


public class MainTest {

	public static void main(String[] args) throws Exception {
		System.out.println("\t\t-- Application de test en cmd de création,sauvegarde et chargement de parcours puis exportation, importation simu--\n\n");
		System.out.println("\t\tPARCOURS\n\n");
		Point valence = new Point(180820, new Coordonnees(4.890892,44.933014),0);
		Point valence2 = new Point(180822, new Coordonnees(4.950892,45.933014),150);
		Point saintay = new Point(180824, new Coordonnees( 4.383396,45.446958),0);
		Parcours parcours = new Parcours(TypeSysteme.TERRESTRE);
		parcours.ajouterPoint(valence);
		parcours.ajouterPoint(valence2);
		parcours.ajouterPoint(saintay);

		parcours.setDebit(1);

		//		System.out.println("\nEnregistrement du parcours dans le fichier ParcoursStValence.fGF\n");
		//		parcours.sauvegarderParcours("ParcoursSteValence.fGF");

		//		System.out.println("\nCréation d'un nouveau parcours\nChargement du parcours depuis le fichier ParcoursStValence.fGF dans ce nouveau parcours\n");
		//		Parcours newParcours = new Parcours(TypeSysteme.TERRESTRE);
		//		newParcours.chargerParcours("ParcoursSteValence.fGF");

		System.out.println("\n\n\t\tSIMULATION\n\n");
		Simulation simu = new Simulation(parcours);
		System.out.println("\nExportation de la simulation dans le fichier SimuSteValence.nmea\nImportation de la simulation depuis le fichier SimuSteValence.nmea");
		simu.exportSimulation("SimuSteValence.nmea");
		simu.importSimulation("SimuSteValence.nmea");

		System.out.println("\n\nAffichage des trames importées par ligne :\n");
		for (String trame : simu.getTramesArray())
		{
			System.out.println(trame);
		}
		System.out.println("\nVitesse moyenne : "+parcours.vitesseMoyenne()+"km/h");

		
		PortSerie testPortSerie = new PortSerie();
		testPortSerie.setPort("COM8", 96000);
		while(true) {
			testPortSerie.envoyer("Debut Test Fin");
		}
		
		//		System.out.println("\n\nDébut simulation :");
		//		simu.jouerSimulation();
	}
}
