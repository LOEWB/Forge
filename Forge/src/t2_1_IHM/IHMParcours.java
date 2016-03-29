package t2_1_IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import t1_1_Model_Principal.Coordonnees;
import t1_1_Model_Principal.Parcours;
import t1_1_Model_Principal.Point;
import t1_1_Model_Principal.TypeSysteme;

public class IHMParcours implements ActionListener {

	private JButton bsimulation = new JButton("LANCER LA SIMULATION");
	
	private JButton bmenu = new JButton("MENU");

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
	
    private String[] listeDebit = { "Débit : 1 trame/s","Débit : 2 trames/s","Débit : 3 trames/s" };
	
    private JComboBox<String> comboBoxDebitTrame = new JComboBox<String>(listeDebit);
    	
	private Parcours parcours;
	
	public IHMParcours(String typeDeSysteme) {
		
		this.initParcours(typeDeSysteme);
		
		PanelAPICarte api = new PanelAPICarte(this.parcours);
		api.setZoom(8, new java.awt.Point(-25,40));
		JPanel parametres = new JPanel();
		JPanel boutonSimulation = new JPanel();
		JPanel basDroit = new JPanel();
		JPanel carte = new JPanel();
		
		typeSystemeCalc.setText(typeDeSysteme);

		JSplitPane splitGauche = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				parametres, boutonSimulation);
		splitGauche.setDividerLocation((int)(FenetreForge.height*0.80));
		splitGauche.setDividerSize(0);

		JSplitPane splitDroit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				carte, basDroit);
		splitDroit.setDividerLocation((int)(FenetreForge.height*0.90));
		splitDroit.setDividerSize(0);

		JSplitPane splitForge = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				splitGauche, splitDroit);
		splitForge.setDividerLocation((int)(FenetreForge.height*0.35));
		splitForge.setDividerSize(0);

		splitForge.setContinuousLayout(true);
		splitGauche.setContinuousLayout(true);
		splitDroit.setContinuousLayout(true);
		
		bsimulation.addActionListener(this);
		bsimulation.setBackground(Color.WHITE);
		bsimulation.setActionCommand("simulation");
		bmenu.setBackground(Color.WHITE);
		bsauvegarder.addActionListener(this);
		bsauvegarder.setBackground(Color.WHITE);
		bsauvegarder.setActionCommand("sauvegarder");
		bcharger.addActionListener(this);
		bcharger.setBackground(Color.WHITE);
		bcharger.setActionCommand("charger");

		api.setBackground(Color.WHITE);
		parametres.setBackground(Color.WHITE);
		boutonSimulation.setBackground(Color.WHITE);
		carte.setBackground(Color.WHITE);
		basDroit.setBackground(Color.WHITE);
		comboBoxDebitTrame.setBackground(Color.WHITE);

		bsimulation.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27), (int)(FenetreForge.height*0.30/5)));
		bmenu.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27), (int)(FenetreForge.height*0.30/5)));
		bsauvegarder.setPreferredSize(new Dimension((int)(FenetreForge.height*0.6), (int)(FenetreForge.height*0.30/5)));
		bcharger.setPreferredSize(new Dimension((int)(FenetreForge.height*0.6), (int)(FenetreForge.height*0.30/5)));
		api.setPreferredSize(new Dimension((int)(FenetreForge.height*1.42), (int)(FenetreForge.height*1.25)));
		comboBoxDebitTrame.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27/2), (int)(FenetreForge.height*0.30/5)));
		typeSysteme.setPreferredSize(new Dimension(115, 30));
		typeSystemeCalc.setPreferredSize(new Dimension(115, 30));
		vitesseMoy.setPreferredSize(new Dimension(115, 30));
		vitesseMoyCalc.setPreferredSize(new Dimension(115, 30));
		altitudeMoy.setPreferredSize(new Dimension(115, 30));
		altitudeMoyCalc.setPreferredSize(new Dimension(115, 30));

		// panel en haut a gauche (parametres)
		parametres.setBorder(BorderFactory.createTitledBorder("Paramètres parcours"));
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
		gbcParametres.gridwidth = 2;
		gbcParametres.gridx = 0;
		gbcParametres.gridy = 16;
		parametres.add(comboBoxDebitTrame, gbcParametres);

		// panel en bas a gauche (bouton simulation & menu)
		boutonSimulation.setLayout(new GridBagLayout());
		GridBagConstraints gbcSimulation = new GridBagConstraints();
		gbcSimulation.gridx = 0;
		gbcSimulation.gridy = 0;
		boutonSimulation.add(bsimulation, gbcSimulation);
        gbcSimulation.gridy = 1;
        boutonSimulation.add(bmenu, gbcSimulation);

		// panel en haut a droite (api carte)
		carte.add(api);

		// panels en bas a droite (boutons sauvegarder/charger parcours)
		basDroit.setLayout(new GridBagLayout());
		GridBagConstraints gbcBasDroit = new GridBagConstraints();
		gbcBasDroit.gridx = 0;
		gbcBasDroit.gridy = 0;
		basDroit.add(comboBoxDebitTrame, gbcBasDroit);	
		gbcBasDroit.gridx = 1;
		gbcBasDroit.gridy = 0;
		basDroit.add(bsauvegarder, gbcBasDroit);		
		gbcBasDroit.gridx = 2;
		basDroit.add(bcharger, gbcBasDroit);

		FenetreForge.fenetreForge.getContentPane().removeAll();
		FenetreForge.fenetreForge.getContentPane().add(splitForge);
		FenetreForge.fenetreForge.setVisible(true);
		
		this.bsimulation.addActionListener(new ActionListener()
	    {

			@Override
			public void actionPerformed(ActionEvent e) 
			{				
				FenetreForge.fenetreForge.dispose();
				new IHMSimulation();				
			}
			 
	    });
		
		 this.bmenu.addActionListener(new ActionListener()
	     {

			@Override
			public void actionPerformed(ActionEvent e) 
			{				
				FenetreForge.fenetreForge.dispose();
				new IHMMenuPrincipal();				
			}
			 
	     });
	}
	
	/**
	 * initialisation du parcours au lancement de l'IHM
	 * @param typeDeSysteme
	 */
	private void initParcours(String typeDeSysteme) {
		switch(typeDeSysteme){
		case "terrestre":
			this.parcours = new Parcours(TypeSysteme.TERRESTRE);
			//test
			Point valence = new Point(0, new Coordonnees(44.933014, 4.890892), 0);
			Point saintay = new Point(3600, new Coordonnees(45.446958, 4.383396), 0);
			parcours.ajouterPoint(valence);
			parcours.ajouterPoint(saintay);
			break;
		case "aérien":
			this.parcours = new Parcours(TypeSysteme.AERIEN);
			//test
			valence = new Point(0, new Coordonnees(44.933014, 4.890892), 100);
			saintay = new Point(3600, new Coordonnees(45.446958, 4.383396), 100);
			parcours.ajouterPoint(valence);
			parcours.ajouterPoint(saintay);
		default:
			break;
		}
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
		parcours.ajouterPoint(valence);
		parcours.ajouterPoint(saintay);

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