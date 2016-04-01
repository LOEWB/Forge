package t1_1_Model_Principal;

import java.io.IOException;
import java.util.ArrayList;

import jssc.SerialPortException;

public class ThreadLecture extends Thread 
{
	private ArrayList<String> listeTrames;
	PortSerie portSerie;
	float tauxErreur;
	float vitesse;

	public ThreadLecture(ArrayList<String> listeTrames, String Port, int Debit, float TauxErreur, float Vitesse)
	{
		this.listeTrames=listeTrames;
		this.portSerie = new PortSerie();
		this.portSerie.setPort(Port, Debit);
		this.tauxErreur = TauxErreur;
		this.vitesse = Vitesse;
	}

	private String boussierTrame(String trame){

		Simulation simulation = new Simulation();

		Point point = simulation.getPoint(trame);

		Point point2 = new Point(point.getTemps()-10, point.getCoordonnes(), point.getAltitude()+90); // modification avec conneries
		ArrayList<Point> listePoints = new ArrayList<Point>();
		listePoints.add(point2);

		Parcours parcours = new Parcours(TypeSysteme.AERIEN, listePoints); // AERIEN au pif pour satisfaire constructeur....

		String newTrame = parcours.genererTrames();

		return newTrame;

	}
	public void run()
	{
		float tempsAttente=0;

		int nbTramesErronnees = (int) (this.listeTrames.size()/this.tauxErreur);
		int nbTramesfaites=0;
		ArrayList<Integer> faits = new ArrayList<Integer>();
		int j=0;
		int dd=0;

		while(nbTramesfaites != nbTramesErronnees) { // on boussie d'abord la liste de trame selon taux d'erreur avant de commencer l'envoi
			dd = 0 + (int)(Math.random() * ((this.listeTrames.size()-1 - 0) + 1));
			if(!faits.contains(dd)) {
				this.listeTrames.set(dd, boussierTrame(this.listeTrames.get(dd).toString()));
				faits.add(dd);
				nbTramesfaites++;
			}
		}

		for(int i=0;i<this.listeTrames.size();i++)
		{
			//pour toutes les trames sauf la derniere
			if(i+1<this.listeTrames.size())
			{
				tempsAttente=(Float.parseFloat(this.listeTrames.get(i+1).split(",")[1])-Float.parseFloat(this.listeTrames.get(i).split(",")[1]))*1000;
				try {
					this.portSerie.envoyer(this.listeTrames.get(i));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					Thread.sleep((long) (tempsAttente/this.vitesse));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//pour la dernière trame
			else
			{
				try {
					this.portSerie.envoyer(this.listeTrames.get(i));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}