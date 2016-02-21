package t1_1_Model_Principal;

import java.util.ArrayList;
public class MainTest {

	public static void main(String[] args) {
		System.out.println("\t\t-- Application de test en cmd de création,sauvegarde et chargement de parcours --\n\n");
		System.out.println("Création d'un nouveau parcours avec deux points\n");
		Point valence = new Point(180823,new Coordonnees(44.933014, 4.890892),0);
		Point saintay = new Point(205203,new Coordonnees(45.446958, 4.383396),0);
		Parcours parcours = new Parcours(TypeSysteme.TERRESTRE);		
		parcours.ajouterPoint(valence);
		parcours.ajouterPoint(saintay);
		Simulation simu = new Simulation(parcours);
		System.out.println(parcours.genererTrames());
		
		System.out.println("\nEnregistrement du parcours dans le fichier ParcoursStValence.fGF\n");
		parcours.sauvegarderParcours("ParcoursSteValence.fGF");
		
		System.out.println("\nCréation d'un nouveau parcours\nChargement du parcours depuis le fichier ParcoursStValence.fGF dans ce nouveau parcours\n");
		Parcours newParcours = new Parcours(TypeSysteme.TERRESTRE);
		newParcours.chargerParcours("ParcoursSteValence.fGF");
		System.out.println("\nTrames chargées : \n");
		System.out.println(newParcours.genererTrames());
		
		
		
	}
}

