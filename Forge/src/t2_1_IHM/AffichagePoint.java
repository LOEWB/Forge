package t2_1_IHM;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

public class AffichagePoint extends MapMarkerDot implements MapMarker {
	
	public AffichagePoint(Coordinate coord) {
		super(coord);
	}

	@Override
	public void paint(Graphics graph, Point pos, int arg2) {
        int size = 12;
        ImageIcon img = new ImageIcon("./img/Marqueur.png");
        Image image = img.getImage();
        // ajout du marqueur (image) sur la carte
        graph.drawImage(image, pos.x - size - 6, pos.y - size*2 - 6, size*3, size*3, null);
	}

}
