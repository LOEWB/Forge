package t2_1_IHM;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

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
			// �v�nement click sur la map => cr�ation point de parcours
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if (checkAddPoint(map.getPosition(e.getPoint())))
		    	{
		    		new IHMInformationsPoints("Cr�ation du point");
		    		addPoint((Coordinate)map.getPosition(e.getPoint()));
		    	}
		    	else{
		    		// TODO --> cas du click sur un marker
		    		Point point = new Point(0,new Coordonnees(((Coordinate)map.getPosition(e.getPoint())).getLat(), ((Coordinate)map.getPosition(e.getPoint())).getLon()),0);
		    		new IHMInformationsPoints(point);
		    	}
		    }
		};
	}
	
	/**
	 * v�rifie si l'emplacement click� sur la carte est vide (true) ou s'il s'y trouve un marqueur (false)
	 * @param coord
	 * @return true si emplacement vide ; false sinon
	 */
	private boolean checkAddPoint(ICoordinate coord){
		Coordinate pointCoord = new Coordinate(coord.getLat(),coord.getLon());
		// cas du click sur un Point d�j� plac�
		for (MapMarker marker : this.mapMarkerList){
			// point sur l'�cran
			if (this.getMapPosition(marker.getCoordinate()) != null){
				int monX = ((int) this.getMapPosition(marker.getCoordinate()).getX()) - AffichagePoint.MARKER_SIZE*3;
				int monY = ((int) this.getMapPosition(marker.getCoordinate()).getY()) - AffichagePoint.MARKER_SIZE*5;
				Rectangle rect = new Rectangle(monX,monY,AffichagePoint.MARKER_SIZE*6,AffichagePoint.MARKER_SIZE*6);
				if (rect.contains(this.getMapPosition(pointCoord).getX(),this.getMapPosition(pointCoord).getY())){
					// on a click� sur le point 'marker'
					return false;
				}
			}
		}
		// cas d'un click ailleurs sur la carte => ajout point
		return true;
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
			// r�cuperer ces donn�es via le mini-IHM 'input box'
			// this.parcours.ajouterPoint(new Point(tempsRelatif,coord,altitude));
			break;
		}
	}
}
