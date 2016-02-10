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
	    System.out.println ("Le fichier n'a pas été trouvé");
	}
		return fichier;
	}
	
	public void ecrire(String cheminFichier, String contenu) {
		// Création du fichier texte pour le programme
		File fichierTexte = new File (cheminFichier);
		// Création de "l'écrivain"
		FileWriter ecrireFichier;
		
		try{
			// Instanciation de l'objet ecrireFichier qui va écrire dans fichierTexte.txt
			ecrireFichier = new FileWriter(fichierTexte);
			// Écriture d'une chaîne de caractères dans le fichier texte
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
