package t2_1_IHM;

import java.awt.event.MouseEvent;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import t1_1_Model_Principal.Coordonnees;
import t1_1_Model_Principal.Parcours;
import t1_1_Model_Principal.Point;

public class PanelAPICarte extends JMapViewer {
	
	DefaultMapController mapController;
	
	private Parcours parcours;
	
	public PanelAPICarte(Parcours parcours) {
		super();
		
		this.parcours = parcours;
		
		this.mapController = new DefaultMapController(this){
			// événement click sur la map => création point de parcours
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	checkAddPoint(map.getPosition(e.getPoint()));
		    }
		};
	}
	
	/**
	 * vérifie si le point n'existe pas encore et l'ajoute ou alors ouvre IHM modification point
	 * @param coord
	 */
	private void checkAddPoint(ICoordinate coord){
		Coordinate pointCoord = new Coordinate(coord.getLat(),coord.getLon());
		// cas du click sur un Point déjà placé
		
		// cas d'un click ailleurs sur la carte => ajout point
		this.addPoint(pointCoord);
	}
	
	/**
	 * ajoute le point au parcours en cours
	 */
	private void addPoint(Coordinate coord){
		this.addMapMarker(new AffichagePoint(coord));
		
		// TODO ajout mini-IHM 'input box' pour le temps relatif et l'altitude 
		switch (this.parcours.getTypeSysteme()){
		case TERRESTRE:
			Point point = new Point(0,new Coordonnees(coord.getLat(), coord.getLon()),0);
			this.parcours.ajouterPoint(point);
			break;
		case AERIEN:
			// récuperer ces données via le mini-IHM 'input box'
			// this.parcours.ajouterPoint(new Point(tempsRelatif,coord,altitude));
			break;
		}
	}
}
