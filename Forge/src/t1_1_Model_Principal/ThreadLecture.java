package t1_1_Model_Principal;

import java.io.IOException;
import java.util.ArrayList;

import jssc.SerialPortException;

public class ThreadLecture extends Thread 
{
 private ArrayList<String> listeTrames;
 PortSerie portSerie;

 public ThreadLecture(ArrayList<String> listeTrames, String Port, int Debit)
 {
  this.listeTrames=listeTrames;
  this.portSerie = new PortSerie();
  this.portSerie.setPort(Port, Debit);
  
 }

 public void run()
 {
  float tempsAttente=0;

  for(int i=0;i<this.listeTrames.size();i++)
  {
   //pour toutes les trames sauf la derniere
   if(i+1<this.listeTrames.size())
   {
    tempsAttente=(Float.parseFloat(this.listeTrames.get(i+1).split(",")[1])-Float.parseFloat(this.listeTrames.get(i).split(",")[1]))*1000;
    System.out.println(this.listeTrames.get(i));
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
     Thread.sleep((long) tempsAttente);
    } catch (InterruptedException e) {
     e.printStackTrace();
    }
   }
   //pour la dernière trame
   else
   {
    System.out.println(this.listeTrames.get(i));
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