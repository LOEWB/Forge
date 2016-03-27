package t1_1_Model_Principal;

import java.util.Comparator;

public class PointComp implements Comparator<Point>{


	@Override
	public int compare(Point point1, Point point2) {

		return Double.compare(point1.getTemps(), point2.getTemps());
	}
}
