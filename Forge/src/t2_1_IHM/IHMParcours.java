package t2_1_IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

import t1_1_Model_Principal.Coordonnees;
import t1_1_Model_Principal.Parcours;
import t1_1_Model_Principal.Point;
import t1_1_Model_Principal.TypeSysteme;

public class IHMParcours implements ActionListener {

	private JButton bsimulation = new JButton("LANCER LA SIMULATION");

	private JButton bsauvegarder = new JButton("SAUVEGARDER LE PARCOURS");

	private JButton bcharger = new JButton("CHARGER LE PARCOURS");

	private JLabel typeSysteme = new JLabel("Type de Système :");

	private JLabel typeSystemeCalc = new JLabel();

	private JLabel vitesseMoy = new JLabel("Vitesse moyenne :");

	private JLabel vitesseMoyCalc = new JLabel("10km/h(test)"); // texte par
																// défaut à
																// supprimer

	private JLabel altitudeMoy = new JLabel("Altitude moyenne :");

	private JLabel altitudeMoyCalc = new JLabel("7500m(test)"); // texte par
																// défaut à
																// supprimer

	public IHMParcours(String typeDeSysteme) {

		JMapViewer api = new JMapViewer();
		JPanel parametres = new JPanel();
		JPanel boutonSimulation = new JPanel();
		JPanel boutonSauvegarder = new JPanel();
		JPanel boutonCharger = new JPanel();
		JPanel carte = new JPanel();

		typeSystemeCalc.setText(typeDeSysteme);

		JSplitPane splitGauche = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				parametres, boutonSimulation);
		splitGauche.setDividerLocation(400);
		splitGauche.setDividerSize(0);

		JSplitPane splitBasDroit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				boutonSauvegarder, boutonCharger);
		splitBasDroit.setDividerLocation(400);
		splitBasDroit.setDividerSize(0);

		JSplitPane splitDroit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				carte, splitBasDroit);
		splitDroit.setDividerLocation(600);
		splitDroit.setDividerSize(0);

		JSplitPane splitForge = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				splitGauche, splitDroit);
		splitForge.setDividerLocation(400);
		splitForge.setDividerSize(0);

		splitForge.setContinuousLayout(true);
		splitGauche.setContinuousLayout(true);
		splitDroit.setContinuousLayout(true);

		bsimulation.addActionListener(this);
		bsimulation.setBackground(Color.WHITE);
		bsimulation.setActionCommand("simulation");
		bsauvegarder.addActionListener(this);
		bsauvegarder.setBackground(Color.WHITE);
		bsauvegarder.setActionCommand("sauvegarder");
		bcharger.addActionListener(this);
		bcharger.setBackground(Color.WHITE);
		bcharger.setActionCommand("charger");

		api.setBackground(Color.WHITE);
		parametres.setBackground(Color.WHITE);
		boutonSimulation.setBackground(Color.WHITE);
		boutonSauvegarder.setBackground(Color.WHITE);
		boutonCharger.setBackground(Color.WHITE);
		carte.setBackground(Color.WHITE);

		bsimulation.setPreferredSize(new Dimension(210, 40));
		bsauvegarder.setPreferredSize(new Dimension(210, 40));
		bcharger.setPreferredSize(new Dimension(210, 40));
		api.setPreferredSize(new Dimension(775, 580));
		typeSysteme.setPreferredSize(new Dimension(115, 30));
		typeSystemeCalc.setPreferredSize(new Dimension(115, 30));
		vitesseMoy.setPreferredSize(new Dimension(115, 30));
		vitesseMoyCalc.setPreferredSize(new Dimension(115, 30));
		altitudeMoy.setPreferredSize(new Dimension(115, 30));
		altitudeMoyCalc.setPreferredSize(new Dimension(115, 30));

		// panel en haut a gauche (parametres)
		parametres.setBorder(BorderFactory
				.createTitledBorder("Paramètres parcours"));
		parametres.setLayout(new GridBagLayout());
		GridBagConstraints gbcParametres = new GridBagConstraints();
		gbcParametres.gridx = 0;
		gbcParametres.gridy = 0;
		gbcParametres.gridwidth = 1;
		gbcParametres.gridheight = 1;
		parametres.add(typeSysteme, gbcParametres);
		gbcParametres.gridx = 1;
		parametres.add(typeSystemeCalc, gbcParametres);
		gbcParametres.gridx = 0;
		gbcParametres.gridy = 2;
		parametres.add(vitesseMoy, gbcParametres);
		gbcParametres.gridx = 1;
		parametres.add(vitesseMoyCalc, gbcParametres);
		gbcParametres.gridx = 0;
		gbcParametres.gridy = 4;
		parametres.add(altitudeMoy, gbcParametres);
		gbcParametres.gridx = 1;
		parametres.add(altitudeMoyCalc, gbcParametres);

		// panel en bas a gauche (bouton simulation)
		boutonSimulation.setLayout(new GridBagLayout());
		GridBagConstraints gbcSimulation = new GridBagConstraints();
		gbcSimulation.gridx = 0;
		gbcSimulation.gridy = 0;
		boutonSimulation.add(bsimulation, gbcSimulation);

		// panel en haut a droite (api carte)
		carte.add(api);

		// panels en bas a droite (boutons sauvegarder/charger parcours)
		boutonSauvegarder.setLayout(new GridBagLayout());
		GridBagConstraints gbcSauvegarder = new GridBagConstraints();
		gbcSauvegarder.gridx = 0;
		gbcSauvegarder.gridy = 0;
		boutonSauvegarder.add(bsauvegarder, gbcSauvegarder);
		boutonCharger.setLayout(new GridBagLayout());
		GridBagConstraints gbcCharger = new GridBagConstraints();
		gbcCharger.gridx = 0;
		gbcCharger.gridy = 0;
		boutonCharger.add(bcharger, gbcCharger);

		FenetreForge.fenetreForge.getContentPane().removeAll();
		FenetreForge.fenetreForge.getContentPane().add(splitForge);
		FenetreForge.fenetreForge.setVisible(true);
	}

	public void traceParcours() {

	}

	public void obtenirNouveauPoint() {

	}

	public void supprimePoint() {

	}

	public void afficheParametres() {

	}

	public void controleCarte() {

	}

	public void sauvegardeParcoursBouton() {

	}

	public void chargementParcoursBouton() {

	}

	public void lancerSimulationBouton() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// test :
		Point valence = new Point(0, new Coordonnees(44.933014, 4.890892), 0);
		Point saintay = new Point(3600, new Coordonnees(45.446958, 4.383396), 0);
		Parcours parcours = new Parcours(TypeSysteme.TERRESTRE);

		switch (e.getActionCommand()) {
		case "sauvegarder":
		case "charger":
			new IHMChoixFichier(e.getActionCommand(), parcours);
			break;
		default:
			// les autres boutons
			break;
		}

	}
}
