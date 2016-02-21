package t2_1_IHM;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import t1_1_Model_Principal.Parcours;
import t1_1_Model_Principal.Simulation;

/**
 * lance la sauvegarde ou le chargement d'un parcours ou d'une simulation
 * @author joris
 *
 */
public class IHMChoixFichier extends JFrame {

	private final Parcours parcours;

	private final Simulation simulation;
	
	private final String extension;
	
	/**
	 * lance la sauvegarde ou le chargement du parcours
	 * @param typeOperation (sauvegarde ou chargement)
	 * @param parcours
	 */
	public IHMChoixFichier(String typeOperation, Parcours parcours) {
		this.parcours = parcours;
		this.simulation = null;
		
		File directory = new File("./files");
		
		JFileChooser choixFichier = new JFileChooser();
		choixFichier.setMultiSelectionEnabled(false);
		choixFichier.setCurrentDirectory(directory);
		choixFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
		choixFichier.setAcceptAllFileFilterUsed(false);
		this.extension = "fGF";
		choixFichier.setFileFilter(new FileNameExtensionFilter("Fichiers parcours (*.fGF)", this.extension));
			// filtre pour n'afficher que les fichiers de parcours (*.fGF)
		
		switch (typeOperation) {
		case "sauvegarder":
			saveDialog(choixFichier);
			break;
		case "charger":
			openDialog(choixFichier);
			break;
		}

		this.dispose();
	}
	
	/**
	 * Lance l'importation ou l'exportation de la simulation
	 * @param typeOperation (importation ou exportation)
	 * @param simulation
	 */
	public IHMChoixFichier(String typeOperation, Parcours parcours, Simulation simulation) {
		this.simulation = simulation;
		this.parcours = parcours;

		File directory = new File("./files");
		
		JFileChooser choixFichier = new JFileChooser();
		choixFichier.setMultiSelectionEnabled(false);
		choixFichier.setCurrentDirectory(directory);
		choixFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
		choixFichier.setAcceptAllFileFilterUsed(false);
		this.extension = "fS";
		choixFichier.setFileFilter(new FileNameExtensionFilter("Fichiers simulation (*.fS)", this.extension));
			// filtre pour n'afficher que les fichiers de simulation (*.fS)
		
		switch (typeOperation) {
		case "exporter":
			saveDialog(choixFichier);
			break;
		case "importer":
			openDialog(choixFichier);
			break;
		}

		this.dispose();
	}
	
	/**
	 * ouvre la fenetre de sélection du fichier où enregistrer le parcours ou la simulation
	 * @param choixFichier
	 */
	private void saveDialog(JFileChooser choixFichier) {
		int retour = choixFichier.showSaveDialog(this.getContentPane());
		
		if (retour == JFileChooser.APPROVE_OPTION) { // un fichier a été choisi
			File fichier = choixFichier.getSelectedFile();
			String path = choixFichier.getSelectedFile().getAbsolutePath();
			
			// vérification de l'extension du fichier
			if (fichier.getName().endsWith(this.extension)) {
				
				// le fichier existe déjà
				if (fichier.exists()){
					int reponse = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir écraser le fichier ?",
																"Fichier déjà existant", JOptionPane.YES_NO_OPTION);
					if (reponse == JOptionPane.YES_OPTION) { // écrasement du fichier existant confirmé
						choixFichier.getSelectedFile().delete();
						if (this.simulation == null)
							this.parcours.sauvegarderParcours(path);
						else
							this.simulation.exportSimulation(path);
					}
				}
				
				// sauvegarde dans un nouveau fichier
				else {
					if (this.simulation == null)
						this.parcours.sauvegarderParcours(path);
					else
						this.simulation.exportSimulation(path);
				}
			}
			// format du fichier incorrect
			else
				JOptionPane.showMessageDialog(this, "format du fichier incorrect",
															"Sauvegarde impossible", JOptionPane.ERROR_MESSAGE);
		}
		else if (retour == JFileChooser.ERROR_OPTION)
			JOptionPane.showMessageDialog(this, "Action impossible", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
	
	/**
	 * ouvre la fenetre de sélection du fichier à ouvrir (chargement parcours ou importation simulation)
	 * @param choixFichier
	 */
	private void openDialog(JFileChooser choixFichier) {
		int retour = choixFichier.showOpenDialog(this.getContentPane());
		
		if (retour == JFileChooser.APPROVE_OPTION) { // appui sur le bouton Ouvrir
			File fichier = choixFichier.getSelectedFile();
			String path = choixFichier.getSelectedFile().getAbsolutePath();
			
			// vérification fichier OK
			if (fichier.exists() && fichier.getName().endsWith(this.extension)){
				// lancer chargementParcours ou importationSimulation
				if (this.simulation == null)
					this.parcours.chargerParcours(path);
				else
					this.simulation.importSimulation(path);
			}
			else
				JOptionPane.showMessageDialog(this, "Fichier inexistant ou format incorrect",
																"Chargement impossible", JOptionPane.ERROR_MESSAGE);
		}
		else if (retour == JFileChooser.ERROR_OPTION)
			JOptionPane.showMessageDialog(this, "Action impossible", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
}
