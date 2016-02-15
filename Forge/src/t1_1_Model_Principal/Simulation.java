package t1_1_Model_Principal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Simulation {
private EtatSimu etat;
private float vitesse;
private Parcours parcours;
private Trame trame;
private PortSerie portSerie;

public Simulation(Parcours parcours) {
	
this.parcours = parcours;
this.etat=EtatSimu.ARRET;

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

void importSimulation(String cheminFichier) {
	
this.parcours.chargerParcours(cheminFichier + ".fGF");

ConteneurFichier conteneurFichier = new ConteneurFichier();

String parcoursBrut = conteneurFichier.lire(cheminFichier + ".fS");
String temporaire = "";

int i = 0;

i += parcoursBrut.indexOf("$STATE",i)+1;

for(int j=i; j<parcoursBrut.indexOf("$VELOCITY",i); j++) {
temporaire += parcoursBrut.charAt(j);
}
this.etat = EtatSimu.valueOf(temporaire);

i += parcoursBrut.indexOf("$VELOCITY",i)+1;

temporaire = "";

for(int j=i; j<parcoursBrut.indexOf("$GPGGA",i); j++) {
temporaire += parcoursBrut.charAt(j);
}
this.vitesse = Float.parseFloat(temporaire);

}

void exportSimulation(String cheminFichier) {
	
	ConteneurFichier conteneurFichier = new ConteneurFichier();
	
	String simulationBrut = "";
	
	simulationBrut += "$STATE" + this.etat + "$VELOCITY" + this.vitesse + parcours.genererTrames();
	
	conteneurFichier.ecrire(cheminFichier, simulationBrut);
	
}


}
