package t1_1_Model_Principal;

import java.util.ArrayList;

public class ThreadLecture extends Thread 
{
	private ArrayList<String> listeTrames;

	public ThreadLecture(ArrayList<String> listeTrames)
	{
		this.listeTrames=listeTrames;
	}

	public void run()
	{
		float tempsAttente=0;

		for(int i=0;i<this.listeTrames.size();i++)
		{
			//pour toutes les trames sauf la derniere
			if(i+1<this.listeTrames.size())
			{
				tempsAttente=Float.parseFloat(this.listeTrames.get(i+1).split(",")[1])-Float.parseFloat(this.listeTrames.get(i).split(",")[1]);
				System.out.println(this.listeTrames.get(i));
				
				try {
					Thread.sleep((long) tempsAttente*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//pour la dernière trame
			else
			{
				System.out.println(this.listeTrames.get(i));
			}
		}
	}
}
