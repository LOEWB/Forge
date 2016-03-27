package t1_1_Model_Principal;

import java.util.Comparator;

public class PointComp implements Comparator<Point>{


	@Override
	public int compare(Point point1, Point point2) {

		// Le temps du point1 est plus élevé que le temps du point 2
		if(Double.compare(point1.getTemps(), point1.getTemps()) > 0)
		{
			return 1;
		}
		// Le temps du point1 est plus petit que le temps du point 2
		else if(Double.compare(point1.getTemps(), point1.getTemps()) < 0)
		{
			return -1;
		}
		// Le temps du point1 est égal au temps du point 2
		else
		{
			return 0;
		}
	}
}
