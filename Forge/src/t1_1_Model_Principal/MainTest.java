package t1_1_Model_Principal;

import java.util.ArrayList;
public class MainTest {

	public static void main(String[] args) {
		
		Point valence = new Point(0,new Coordonnees(44.933014, 4.890892),0);
		Point saintay = new Point(3600,new Coordonnees(45.446958, 4.383396),0);
		Parcours parcours = new Parcours(TypeSysteme.TERRESTRE);
		
		parcours.ajouterPoint(valence);
		parcours.ajouterPoint(saintay);
		Simulation simu = new Simulation(parcours);
		System.out.println(parcours.genererTrames());
	}
}

