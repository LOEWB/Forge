package t1_1_Model_Principal;

import java.util.ArrayList;
import java.util.Arrays;


public class Simulation {
	private EtatSimu etat;
	private float vitesse;
	private Parcours parcours;
	private ArrayList<String> tramesArray;
	
	public Simulation(Parcours parcours) {

		this.parcours = parcours;
		this.etat = EtatSimu.ARRET;
		this.tramesArray=new ArrayList<String>();

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

	EtatSimu getEtat() {
		return this.etat;
	}
	
	public void importSimulation(String cheminFichier)
	{
		ConteneurFichier conteneurFichier = new ConteneurFichier();
		String simulationBrut = conteneurFichier.lire(cheminFichier);
		
		String[] tramesTable = simulationBrut.split("(?=\\$)");
		
		this.tramesArray=new ArrayList<String>(Arrays.asList(tramesTable));
	}
	
	public void exportSimulation(String cheminFichier)
	{
		ConteneurFichier conteneurFichier = new ConteneurFichier();
		conteneurFichier.ecrire(cheminFichier, this.parcours.genererTrames());
	}
	
}
