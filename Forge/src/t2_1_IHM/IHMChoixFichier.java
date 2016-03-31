package t2_1_IHM;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import t1_1_Model_Principal.Parcours;
import t1_1_Model_Principal.Simulation;
import t1_1_Model_Principal.TypeSysteme;

/**
 * lance la sauvegarde ou le chargement d'un parcours ou d'une simulation
 * 
 * @author joris
 *
 */
public class IHMChoixFichier extends JFrame {

	private final PanelAPICarte panelAPICarte;

	private final Simulation simulation;

	private final String typeExtension;

	private String fenetre;

	private File directory;

	/**
	 * lance la sauvegarde ou le chargement du parcours
	 * 
	 * @param typeOperation
	 *            (sauvegarde ou chargement)
	 * @param parcours
	 */
	public IHMChoixFichier(String typeOperation, PanelAPICarte panelAPICarte, String fenetre) {
		this.panelAPICarte = panelAPICarte;
		this.fenetre = fenetre;
		this.simulation = null;

		this.directory = new File("./files");

		JFileChooser choixFichier = new JFileChooser();
		choixFichier.setMultiSelectionEnabled(false);
		choixFichier.setCurrentDirectory(this.directory);
		choixFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
		choixFichier.setAcceptAllFileFilterUsed(false);
		this.typeExtension = "fGF";
		choixFichier.setFileFilter(new FileNameExtensionFilter(
				"Fichiers parcours (*.fGF)", this.typeExtension));
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
	 * 
	 * @param typeOperation
	 *            (importation ou exportation)
	 * @param simulation
	 */
	public IHMChoixFichier(String typeOperation, PanelAPICarte panelAPICarte, Simulation simulation) {
		this.simulation = simulation;
		this.panelAPICarte = panelAPICarte;

		this.directory = new File("./files");

		JFileChooser choixFichier = new JFileChooser();
		choixFichier.setMultiSelectionEnabled(false);
		choixFichier.setCurrentDirectory(this.directory);
		choixFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
		choixFichier.setAcceptAllFileFilterUsed(false);
		this.typeExtension = "fS";
		choixFichier.setFileFilter(new FileNameExtensionFilter(
				"Fichiers simulation (*.fS)", this.typeExtension));
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
	 * ouvre la fenetre de sélection du fichier où enregistrer le parcours ou la
	 * simulation
	 * 
	 * @param choixFichier
	 */
	private void saveDialog(JFileChooser choixFichier) {
		int retour = choixFichier.showSaveDialog(this.getContentPane());
		String extension = "."+this.typeExtension;

		if (retour == JFileChooser.APPROVE_OPTION) { // un fichier a été choisi
			File fichier = choixFichier.getSelectedFile();

			// vérification de l'extension du fichier
			if (!fichier.getName().endsWith(extension))
				fichier = new File(fichier.getPath()+extension);

			String path = fichier.getAbsolutePath();

			// le fichier existe déjà
			if (fichier.exists()) {
				int reponse = JOptionPane.showConfirmDialog(null,
						"Êtes-vous sûr de vouloir écraser le fichier ?",
						"Fichier déjà existant", JOptionPane.YES_NO_OPTION);
				if (reponse == JOptionPane.YES_OPTION) { // écrasement du
					// fichier
					// existant
					// confirmé
					choixFichier.getSelectedFile().delete();
					if (this.simulation == null)
						this.panelAPICarte.getParcours().sauvegarderParcours(path);
					else
						this.simulation.exportSimulation(path);
				}
			}

			// sauvegarde dans un nouveau fichier
			else {
				if (this.simulation == null)
					this.panelAPICarte.getParcours().sauvegarderParcours(path);
				else
					this.simulation.exportSimulation(path);
			}

		} else if (retour == JFileChooser.ERROR_OPTION)
			JOptionPane.showMessageDialog(this, "Action impossible", "Erreur",
					JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * ouvre la fenetre de sélection du fichier à ouvrir (chargement parcours ou
	 * importation simulation)
	 * 
	 * @param choixFichier
	 */
	private void openDialog(JFileChooser choixFichier) {
		int retour = choixFichier.showOpenDialog(this.getContentPane());
		String extension = "."+this.typeExtension;

		if (retour == JFileChooser.APPROVE_OPTION) { // appui sur le bouton
			// Ouvrir
			File fichier = choixFichier.getSelectedFile();
			String path = choixFichier.getSelectedFile().getAbsolutePath();

			// vérification fichier OK
			if (fichier.exists() && fichier.getName().endsWith(extension)) {
				// lancer chargementParcours ou importationSimulation
				if (this.simulation == null)
					chargeParcours(path);
				else
					importeSimulation(path);					
			} else
				JOptionPane.showMessageDialog(this,
						"Fichier inexistant ou format incorrect",
						"Chargement impossible", JOptionPane.ERROR_MESSAGE);
		} else if (retour == JFileChooser.ERROR_OPTION)
			JOptionPane.showMessageDialog(this, "Action impossible", "Erreur",
					JOptionPane.ERROR_MESSAGE);
	}

	private void chargeParcours(String path) {
		this.panelAPICarte.removeSegments();
		this.panelAPICarte.removeAllMapMarkers();

		// cree parcours vide, puis charge le parcours par dessus
		this.panelAPICarte.setParcours(new Parcours());
		this.panelAPICarte.getParcours().chargerParcours(path);

		// lance depuis IHMSimulation ou IHMParcours
		if(this.fenetre == "creation")
			this.panelAPICarte.createMarkers();
		else
			this.panelAPICarte.createMarkerDebutFin();
		this.panelAPICarte.traceSegments();
	}

	private void importeSimulation(String path){
		this.panelAPICarte.removeSegments();
		this.panelAPICarte.removeAllMapMarkers();		
		this.simulation.importSimulation(path);	
		this.panelAPICarte.createMarkerDebutFin();
		this.panelAPICarte.traceSegments();
	}

	public Simulation getSimulation() 
	{
		return simulation;
	}

}
