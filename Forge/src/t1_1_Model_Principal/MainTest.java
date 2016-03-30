package t1_1_Model_Principal;

import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) throws Exception {
		System.out.println("\t\t-- Application de test en cmd de création,sauvegarde et chargement de parcours puis exportation, importation simu--\n\n");
		System.out.println("\t\tPARCOURS\n\n");
		Point valence = new Point(180820, new Coordonnees(44.933014,4.890892),0);
		Point valence2 = new Point(180822, new Coordonnees(45.933014,4.950892),150);
		Point saintay = new Point(180824, new Coordonnees(45.446958,4.383396),0);
		Parcours parcours = new Parcours(TypeSysteme.TERRESTRE);
		parcours.ajouterPoint(valence);
		parcours.ajouterPoint(valence2);
		parcours.ajouterPoint(saintay);

		parcours.setDebit(1);

		//		System.out.println("\nEnregistrement du parcours dans le fichier ParcoursStValence.fGF\n");
				parcours.sauvegarderParcours("ParcoursSteValence.fGF");

		//		System.out.println("\nCréation d'un nouveau parcours\nChargement du parcours depuis le fichier ParcoursStValence.fGF dans ce nouveau parcours\n");
				Parcours newParcours = new Parcours(TypeSysteme.AERIEN);
				newParcours.chargerParcours("ParcoursSteValence.fGF");

		System.out.println("\n\n\t\tSIMULATION\n\n");
		Simulation simu = new Simulation(parcours);
		System.out.println("\nExportation de la simulation dans le fichier SimuSteValence.nmea\nImportation de la simulation depuis le fichier SimuSteValence.nmea");
		simu.exportSimulation("SimuSteValence.nmea");
		Simulation simu2 = new Simulation();
		simu2.importSimulation("SimuSteValence.nmea");

		System.out.println("\n\nAffichage des trames importées par ligne :\n");
		for (String trame : simu.getTramesArray())
		{
			System.out.println(trame);
		}
		System.out.println("\nVitesse moyenne : "+parcours.vitesseMoyenne()+"km/h");

		for(Point p:newParcours.getListePoints())
		{
			System.out.println(p+"");
		}
		
		PortSerie testPortSerie = new PortSerie();
		testPortSerie.setPort("COM8", 9600);
		
		testPortSerie.envoyer("Putain its work");
		
		
		
		
		Point dd = simu.getPoint("$GPGGA,180820.0,4455.9806,N,00453.4530,E,1,6,14.6,0.0,M,47.9,M,,*57");
		
		System.out.println(dd.toString()); // les 0 qui sont pas dans l'affichage sont du à la conversion en double, mais c'est normal...

		System.out.println("\n\nTest getPoints :\n");

		 ArrayList<Point> listePoints = simu.getPoints("$GPGGA,180820.0,4455.9806,N,00453.4530,E,1,6,12.6,0.0,M,48.9,M,,*5E$GPGGA,180821.0,4525.9806,N,00455.2534,E,1,10,19.6,75.0,M,47.9,M,,*5C$GPGGA,180822.0,4555.9806,N,00457.0534,E,1,6,4.6,150.0,M,47.9,M,,*65$GPGGA,180823.0,4541.3991,N,00440.0284,E,1,12,10.6,75.0,M,47.9,M,,*58$GPGGA,180824.0,4526.8170,N,00423.0034,E,1,6,6.6,0.0,M,47.9,M,,*6E");

		 	for(int i=0; i<listePoints.size(); i++) System.out.println(listePoints.get(i).toString());
		
		//		System.out.println("\n\nDébut simulation :");
		//		simu.jouerSimulation();
	}
}
