package t1_1_Model_Principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class ConteneurFichier {

	
	public String lire(String cheminFichier) {
	String fichier = "";
	try
	{
	    File f = new File (cheminFichier);
	    FileReader fr = new FileReader (f);
	    BufferedReader br = new BufferedReader (fr);
	 
	    try
	    {
	        String line = br.readLine();
	 
	        while (line != null)
	        {
	            fichier += line.toString();
	            line = br.readLine();
	        }
	 
	        br.close();
	        fr.close();
	    }
	    catch (IOException exception)
	    {
	        System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
	    }
	}
	catch (FileNotFoundException exception)
	{
	    System.out.println ("Le fichier n'a pas �t� trouv�");
	}
		return fichier;
	}
	
	public void ecrire(String cheminFichier, String contenu) {
		// Cr�ation du fichier texte pour le programme
		File fichierTexte = new File (cheminFichier);
		// Cr�ation de "l'�crivain"
		FileWriter ecrireFichier;
		
		try{
			// Instanciation de l'objet ecrireFichier qui va �crire dans fichierTexte.txt
			ecrireFichier = new FileWriter(fichierTexte);
			// �criture d'une cha�ne de caract�res dans le fichier texte
			ecrireFichier.write(contenu);
			// "Fermeture" du FileWriter
			ecrireFichier.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
}
