package t2_1_IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import org.openstreetmap.gui.jmapviewer.DefaultMapController;

import t1_1_Model_Principal.Coordonnees;
import t1_1_Model_Principal.Parcours;
import t1_1_Model_Principal.Point;
import t1_1_Model_Principal.TypeSysteme;

public class IHMParcours implements ActionListener,MouseListener {

	private JButton bsimulation = new JButton("LANCER LA SIMULATION");

	private JButton bmenu = new JButton("MENU");

	private JButton bsauvegarder = new JButton("SAUVEGARDER LE PARCOURS");

	private JButton bcharger = new JButton("CHARGER LE PARCOURS");

	private JLabel typeSysteme = new JLabel("Type de Système :");

	private JLabel typeSystemeCalc = new JLabel();

	private JLabel vitesseMoy = new JLabel("Vitesse moyenne :");

	private JLabel vitesseMoyCalc = new JLabel(""); // texte par
	// défaut à
	// supprimer

	private JLabel altitudeMoy = new JLabel("Altitude moyenne :");

	private JLabel altitudeMoyCalc = new JLabel(""); // texte par
	// défaut à
	// supprimer

	private String[] listeDebit = { "Débit : 1 trame/s","Débit : 1.5 trames/s","Débit : 2 trames/s" };

	private JComboBox<String> comboBoxDebitTrame = new JComboBox<String>(listeDebit);

	private Parcours parcours;

	private PanelAPICarte panelAPICarte;

	public IHMParcours(String typeDeSysteme) {

		this.initParcours(typeDeSysteme);

		PanelAPICarte api = new PanelAPICarte(this.parcours);
		this.panelAPICarte = api;
		api.setZoom(8, new java.awt.Point(-25,40));

		panelAPICarte.createMarkers();	
		panelAPICarte.traceSegments();

		vitesseMoyCalc.setText(Double.toString(panelAPICarte.getParcours().vitesseMoyenne()));
		altitudeMoyCalc.setText(Double.toString(panelAPICarte.getParcours().altitudeMoyenne()));		

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
		bmenu.addActionListener(this);
		bmenu.setActionCommand("menu");
		bsauvegarder.addActionListener(this);
		bsauvegarder.setBackground(Color.WHITE);
		bsauvegarder.setActionCommand("sauvegarder");
		bcharger.addActionListener(this);
		bcharger.setBackground(Color.WHITE);
		bcharger.setActionCommand("charger");
		comboBoxDebitTrame.setBackground(Color.WHITE);
		comboBoxDebitTrame.addActionListener(this);
		comboBoxDebitTrame.setActionCommand("debitTrame");


		api.setBackground(Color.WHITE);
		api.addMouseListener(this);
		parametres.setBackground(Color.WHITE);
		boutonSimulation.setBackground(Color.WHITE);
		carte.setBackground(Color.WHITE);
		basDroit.setBackground(Color.WHITE);


		Dimension dimensionAPI = new Dimension((int)(FenetreForge.width*0.8), (int)(FenetreForge.height*0.9));
		Dimension dimensionBoutonsBasGauche = new Dimension((int)(FenetreForge.width*0.15), (int)(FenetreForge.height*0.30/5));
		Dimension dimensionBoutonsBasDroite = new Dimension((int)(FenetreForge.width*0.3), (int)(FenetreForge.height*0.30/5));
		Dimension dimensionComboBox = new Dimension((int)(FenetreForge.width*0.3/2), (int)(FenetreForge.height*0.30/5));
		Dimension dimensionLabelsInformation = new Dimension((int)(FenetreForge.width*0.06), (int)(FenetreForge.height*0.10));

		
		api.setPreferredSize(dimensionAPI);
		bsimulation.setPreferredSize(dimensionBoutonsBasGauche);
		bmenu.setPreferredSize(dimensionBoutonsBasGauche);
		bsauvegarder.setPreferredSize(dimensionBoutonsBasDroite);
		bcharger.setPreferredSize(dimensionBoutonsBasDroite);
		comboBoxDebitTrame.setPreferredSize(dimensionComboBox);
		typeSysteme.setPreferredSize(dimensionLabelsInformation);
		typeSystemeCalc.setPreferredSize(dimensionLabelsInformation);
		vitesseMoy.setPreferredSize(dimensionLabelsInformation);
		vitesseMoyCalc.setPreferredSize(dimensionLabelsInformation);
		altitudeMoy.setPreferredSize(dimensionLabelsInformation);
		altitudeMoyCalc.setPreferredSize(dimensionLabelsInformation);
		
		Font tailletexte = new Font(null, Font.BOLD, 20);
		typeSysteme.setFont(tailletexte);
		typeSystemeCalc.setFont(tailletexte);
		vitesseMoy.setFont(tailletexte);
		vitesseMoyCalc.setFont(tailletexte);
		altitudeMoy.setFont(tailletexte);
		altitudeMoyCalc.setFont(tailletexte);
		bsimulation.setFont(tailletexte);
		bmenu.setFont(tailletexte);
		comboBoxDebitTrame.setFont(tailletexte);
		((JLabel)comboBoxDebitTrame.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		bsauvegarder.setFont(tailletexte);
		bcharger.setFont(tailletexte);
		

		// panel en haut a gauche (parametres)
		parametres.setBorder(BorderFactory.createTitledBorder(null, "Paramètres parcours", 0, 0, tailletexte));
		parametres.setLayout(new GridBagLayout());
		GridBagConstraints gbcParametres = new GridBagConstraints();
		gbcParametres.anchor = GridBagConstraints.LINE_START;
		gbcParametres.gridx = 0;
		gbcParametres.gridy = 0;
		gbcParametres.gridwidth = 1;
		gbcParametres.gridheight = 1;
		parametres.add(typeSysteme, gbcParametres);
		gbcParametres.anchor = GridBagConstraints.LINE_END;
		gbcParametres.gridx = 1;
		parametres.add(typeSystemeCalc, gbcParametres);
		gbcParametres.anchor = GridBagConstraints.LINE_START;
		gbcParametres.gridx = 0;
		gbcParametres.gridy = 2;
		parametres.add(vitesseMoy, gbcParametres);
		gbcParametres.anchor = GridBagConstraints.LINE_END;
		gbcParametres.gridx = 1;
		parametres.add(vitesseMoyCalc, gbcParametres);
		gbcParametres.anchor = GridBagConstraints.LINE_START;
		gbcParametres.gridx = 0;
		gbcParametres.gridy = 4;
		parametres.add(altitudeMoy, gbcParametres);
		gbcParametres.anchor = GridBagConstraints.LINE_END;
		gbcParametres.gridx = 1;
		parametres.add(altitudeMoyCalc, gbcParametres);


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

		switch (e.getActionCommand()) {
		case "debitTrame":
			switch (comboBoxDebitTrame.getSelectedIndex())
			{
			case 0:
				panelAPICarte.getParcours().setDebit(1.0f);
				break;
			case 1:
				panelAPICarte.getParcours().setDebit(1.5f);
				break;
			case 2:
				panelAPICarte.getParcours().setDebit(2f);
				break;
			}			
			break;
		case "sauvegarder":
		case "charger":
			new IHMChoixFichier(e.getActionCommand(), this.panelAPICarte);
			break;
		case "simulation":
			FenetreForge.fenetreForge.dispose();
			new IHMSimulation(panelAPICarte.getParcours());	
			break;
		case "menu":
			FenetreForge.fenetreForge.dispose();
			new IHMMenuPrincipal();	
			break;			
		default:
			// les autres boutons
			break;
		}

	}

	void setInformationsText()
	{
		NumberFormat format = NumberFormat.getInstance(); 
		format.setMinimumFractionDigits(2);
		if(panelAPICarte.getParcours().getListePoints().size() > 0)
		{
			String vitMoy=format.format(panelAPICarte.getParcours().vitesseMoyenne());
			String altMoy=format.format(panelAPICarte.getParcours().altitudeMoyenne());
			vitesseMoyCalc.setText(vitMoy + "  km/h");
			altitudeMoyCalc.setText(altMoy + "  m");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		setInformationsText();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setInformationsText();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		setInformationsText();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		setInformationsText();

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		setInformationsText();
	}	

}