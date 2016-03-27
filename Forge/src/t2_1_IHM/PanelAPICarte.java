package t2_1_IHM;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JOptionPane;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import t1_1_Model_Principal.Coordonnees;
import t1_1_Model_Principal.Parcours;
import t1_1_Model_Principal.Point;
import t1_1_Model_Principal.PointComp;

public class PanelAPICarte extends JMapViewer {

	DefaultMapController mapController;

	private Parcours parcours;

	private PanelAPICarte panelAPICarte = this;

	private Hashtable<MapMarker,Point> pointMarker = new Hashtable<MapMarker,Point>();

	public PanelAPICarte(Parcours parcours) {
		super();

		this.parcours = parcours;

		this.mapController = new DefaultMapController(this){
			// événement click sur la map => création point de parcours
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkAddPoint(map.getPosition(e.getPoint())))
				{
					new IHMInformationsPoints((Coordinate)map.getPosition(e.getPoint()), panelAPICarte);		    		
				}
				else
				{
					// TODO --> cas du click sur un marker
					for (MapMarker marker : mapMarkerList)
					{
						// point sur l'écran

						int monX = ((int) getMapPosition(marker.getCoordinate()).getX()) - AffichagePoint.MARKER_SIZE*3;
						int monY = ((int) getMapPosition(marker.getCoordinate()).getY()) - AffichagePoint.MARKER_SIZE*5;
						Rectangle rect = new Rectangle(monX,monY,AffichagePoint.MARKER_SIZE*6,AffichagePoint.MARKER_SIZE*6);
						if(rect.contains(getMapPosition((Coordinate)map.getPosition(e.getPoint())).getX(), getMapPosition((Coordinate)map.getPosition(e.getPoint())).getY()))
						{
							new IHMInformationsPoints(pointMarker.get(marker),panelAPICarte);
						}


					}
				}
			}
		};
	}



	/**
	 * vérifie si l'emplacement clické sur la carte est vide (true) ou s'il s'y trouve un marqueur (false)
	 * @param coord
	 * @return true si emplacement vide ; false sinon
	 */
	private boolean checkAddPoint(ICoordinate coord)
	{
		Coordinate pointCoord = new Coordinate(coord.getLat(),coord.getLon());
		// cas du click sur un Point déjà placé
		for (MapMarker marker : this.mapMarkerList)
		{
			// point sur l'écran
			if (this.getMapPosition(marker.getCoordinate()) != null)
			{
				int monX = ((int) this.getMapPosition(marker.getCoordinate()).getX()) - AffichagePoint.MARKER_SIZE*3;
				int monY = ((int) this.getMapPosition(marker.getCoordinate()).getY()) - AffichagePoint.MARKER_SIZE*5;
				Rectangle rect = new Rectangle(monX,monY,AffichagePoint.MARKER_SIZE*6,AffichagePoint.MARKER_SIZE*6);
				if (rect.contains(this.getMapPosition(pointCoord).getX(),this.getMapPosition(pointCoord).getY()))
				{
					// on a clické sur le point 'marker'
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
	void addPoint(double sec, Coordinate coord, double altitude)
	{

		Point point = new Point(sec,new Coordonnees(coord.getLat(), coord.getLon()),altitude);
		this.parcours.ajouterPoint(point);

	}

	void changePoint(double sec, Point point, double altitude)
	{		

		for(int i=0;i<this.parcours.getListePoints().size();i++)
		{
			if(this.parcours.getListePoints().get(i).getCoordonnes() == point.getCoordonnes())
			{
				this.parcours.getListePoints().get(i).setAltitude(altitude);
				this.parcours.getListePoints().get(i).setTempsPassageRelatif(sec);
			}
		}

	}

	void deletePoint(Point point)
	{		
		this.parcours.supprimerPoint(point);		
	}

	public Parcours getParcours() {
		return parcours;
	}

	void traceSegments()
	{		
		for(int i=0;i<this.parcours.getListePoints().size()-1;i++)
		{
			Coordinate point1 = new Coordinate(this.parcours.getListePoints().get(i).getCoordonnes().getLatitude(),this.parcours.getListePoints().get(i).getCoordonnes().getLongitude());
			Coordinate point2 = new Coordinate(this.parcours.getListePoints().get(i+1).getCoordonnes().getLatitude(),this.parcours.getListePoints().get(i+1).getCoordonnes().getLongitude());
			List<Coordinate> route = new ArrayList<Coordinate>(Arrays.asList(point1,point2,point2));;
			this.panelAPICarte.addMapPolygon(new MapPolygonImpl(route));
			this.panelAPICarte.removeMapPolygon(new MapPolygonImpl(route));
		}				
	}

	void removeSegments()
	{		
		if(!(this.mapPolygonList.isEmpty()))
		{
			this.panelAPICarte.removeAllMapPolygons();
			
		}
	}

	void createMarker()
	{		
		Collections.sort(panelAPICarte.getParcours().getListePoints(),new PointComp());
		this.addMapMarker(new AffichagePoint(new Coordinate(this.parcours.getListePoints().get(0).getCoordonnes().getLatitude(),this.parcours.getListePoints().get(0).getCoordonnes().getLongitude()),"./img/MarqueurDepart.png"));

		for(int i=1;i<this.parcours.getListePoints().size()-1;i++)
		{		
			this.addMapMarker(new AffichagePoint(new Coordinate(this.parcours.getListePoints().get(i).getCoordonnes().getLatitude(),this.parcours.getListePoints().get(i).getCoordonnes().getLongitude()),"./img/MarqueurPoint.png"));
		}

		this.addMapMarker(new AffichagePoint(new Coordinate(this.parcours.getListePoints().get(this.parcours.getListePoints().size()-1).getCoordonnes().getLatitude(),this.parcours.getListePoints().get(this.parcours.getListePoints().size()-1).getCoordonnes().getLongitude()),"./img/MarqueurArrivee.png"));

		this.pointMarker = new Hashtable<MapMarker,Point>();
		
		for (int j=0;j<this.mapMarkerList.size();j++)
		{
			this.pointMarker.put(this.mapMarkerList.get(j), this.parcours.getListePoints().get(j));
		}
		
	}
	
	void removeMarker(Point point)
	{
		for(MapMarker marker : this.mapMarkerList)
		{
			if(this.pointMarker.get(marker)==point)
			{
				this.pointMarker.remove(marker);
			}
		}
		
	}

}
