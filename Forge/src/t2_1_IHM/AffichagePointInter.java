package t2_1_IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

public class AffichagePointInter extends MapMarkerDot implements MapMarker {
	
	public final static int MARKER_SIZE = 3;
	public String marker;

	
	public AffichagePointInter(Coordinate coord,String marker) {
		super(coord);
		this.marker = marker;
	}

	@Override
	public void paint(Graphics graph, Point pos, int arg2) {		
        ImageIcon img = new ImageIcon(marker);
        Image image = img.getImage();
        // ajout du marqueur (image) sur la carte
        graph.drawImage(image, pos.x - MARKER_SIZE*3, pos.y - MARKER_SIZE*3, MARKER_SIZE*6, MARKER_SIZE*6, null);
        
        // debuggage : permet d'afficher la "zone effective" d'un marqueur
        /*graph.setColor(Color.BLUE);
        graph.drawRect(pos.x - MARKER_SIZE*3, pos.y - MARKER_SIZE*5, MARKER_SIZE*6, MARKER_SIZE*6);*/
	}

}
