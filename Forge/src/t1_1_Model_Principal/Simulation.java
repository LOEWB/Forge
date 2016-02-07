package t1_1_Model_Principal;

import java.util.ArrayList;
import java.util.Iterator;

public class Simulation {
private EtatSimu etat;
private float vitesse;
private Parcours parcours;
private Trame trame;
private PortSerie portSerie;

public Simulation(Parcours parcours) {

this.parcours = parcours;
this.etat = etat;

}

String genererTrames() {
String trames = "";
String trame = "";
Point point;

ArrayList<Point> listePoints; 

listePoints = parcours.getListePoints();

for(int i = 0 ; i < listePoints.size(); i++) {
trame = "$GPGGA,";
point = listePoints.get(i);

trame += point.getDate() + "," + point.getCoordonnes().getLatitude() + ",N," 
+ point.getCoordonnes().getLongitude() + ",E,1,08,0.9," +  point.getAltitude() + ",M,"
+ "46.9,M, , ";

int checksum = 0;
for(int j = 0; j < trame.length(); j++) {
  checksum = checksum ^ Character.codePointAt(trame, j);
}

trame += "*" + checksum;

trames += trame;

}

return trames;
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


}
