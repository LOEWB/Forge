package t2_1_IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import t1_1_Model_Principal.Coordonnees;
import t1_1_Model_Principal.Parcours;
import t1_1_Model_Principal.Point;
import t1_1_Model_Principal.Simulation;
import t1_1_Model_Principal.ThreadLecture;
import t1_1_Model_Principal.TypeSysteme;

public class IHMSimulation implements ActionListener {

	private Parcours parcours;

	private JButton bcharger = new JButton("Charger");

	private JButton bimporter = new JButton("Importer");

	private JButton bexporter = new JButton("Exporter");

	private JButton bJouer = new JButton("JOUER");

	private JButton bMenu = new JButton("MENU");

	private JLabel vitActuelle = new JLabel("Vitesse actuelle");    
	private JLabel vitActuelleDisplay = new JLabel("");

	private JLabel vitMoyenne = new JLabel("Vitesse moyenne");
	private JLabel vitMoyenneDisplay = new JLabel("");

	private JLabel heureDepart = new JLabel("Heure de départ");
	private JLabel heureDepartDisplay = new JLabel("");

	private JLabel heureArrivee = new JLabel("Heure d'arrivée");
	private JLabel heureArriveeDisplay = new JLabel("");

	private JLabel heureActuelle = new JLabel("Heure actuelle");
	private JLabel heureActuelleDisplay = new JLabel("");

	private JLabel dateActuelle = new JLabel("Date actuelle");
	private JLabel dateActuelleDisplay = new JLabel("");

	private JLabel dateLabel = new JLabel("Date");
	private JLabel heureLabel = new JLabel("Heure");
	private JLabel dateLabel2 = new JLabel("Date");
	private JLabel heureLabel2 = new JLabel("Heure");
	private JLabel debutLabel = new JLabel("Début");
	private JLabel finLabel = new JLabel("Fin");

	private JFormattedTextField date = new JFormattedTextField(new SimpleDateFormat("MM/dd/yyyy"));

	private JFormattedTextField date2 = new JFormattedTextField(new SimpleDateFormat("MM/dd/yyyy"));

	private JFormattedTextField date3 = new JFormattedTextField(new SimpleDateFormat("MM/dd/yyyy"));

	private JFormattedTextField date4 = new JFormattedTextField(new SimpleDateFormat("MM/dd/yyyy"));

	private JFormattedTextField date5 = new JFormattedTextField(new SimpleDateFormat("MM/dd/yyyy"));

	private JFormattedTextField date6 = new JFormattedTextField(new SimpleDateFormat("MM/dd/yyyy"));

	private String[] listeLiaisonSerie = { "Liaison série 1", "Liaison série 2", "Liaison série 3" };

	private JComboBox<String> comboBoxliaisonSerie = new JComboBox<String>(listeLiaisonSerie);

	private String[] listeDebit = { "Débit : 1200 bit/s","Débit : 2400 bit/s","Débit : 3600 bit/s" };

	private JComboBox<String> comboBoxDebit = new JComboBox<String>(listeDebit);

	private PanelAPICarte panelAPICarte;




	public IHMSimulation(Parcours parcours) 
	{

		this.parcours = parcours;		
		creationComposant();
		panelAPICarte.createMarkerDebutFin();
		panelAPICarte.traceSegments();

	}

	public IHMSimulation() 
	{

		creationComposant();

	}


	void creationComposant() 
	{

		PanelAPICarte api = new PanelAPICarte(parcours,"simulation");
		this.panelAPICarte = api;
		JPanel informations = new JPanel();
		JPanel departArriveePlage = new JPanel();
		JPanel jouerMenu = new JPanel();
		JPanel bas = new JPanel();
		JPanel boutons = new JPanel();
		JPanel carte = new JPanel();      
		JPanel depart = new JPanel();
		JPanel arrivee = new JPanel();
		JPanel plage = new JPanel();
		JSlider vitesse = new JSlider(); 



		JSplitPane splitMilieuGauche = new JSplitPane(JSplitPane.VERTICAL_SPLIT, boutons, departArriveePlage);
		splitMilieuGauche.setDividerLocation((int)(FenetreForge.height*0.30));
		splitMilieuGauche.setDividerSize(0);

		JSplitPane splitBasGauche = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitMilieuGauche, jouerMenu);
		splitBasGauche.setDividerLocation((int)(FenetreForge.height*0.8));
		splitBasGauche.setDividerSize(0);

		JSplitPane splitMilieuDroit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, informations, carte);
		splitMilieuDroit.setDividerLocation((int)(FenetreForge.height*0.20));
		splitMilieuDroit.setDividerSize(0);

		JSplitPane splitBasDroit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitMilieuDroit, bas);
		splitBasDroit.setDividerLocation((int)(FenetreForge.height*0.90));
		splitBasDroit.setDividerSize(0);

		JSplitPane splitForge = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitBasGauche,splitBasDroit);
		splitForge.setDividerLocation((int)(FenetreForge.width*0.198));
		splitForge.setDividerSize(0);         

		splitForge.setContinuousLayout(true);
		splitMilieuGauche.setContinuousLayout(true);
		splitBasGauche.setContinuousLayout(true);
		splitMilieuDroit.setContinuousLayout(true);
		splitBasDroit.setContinuousLayout(true);         


		bcharger.setBackground(Color.WHITE);
		bcharger.addActionListener(this);
		bcharger.setActionCommand("charger");
		bimporter.setBackground(Color.WHITE);
		bimporter.addActionListener(this);
		bimporter.setActionCommand("importer");
		bexporter.setBackground(Color.WHITE);
		bexporter.addActionListener(this);
		bexporter.setActionCommand("exporter");
		comboBoxliaisonSerie.setBackground(Color.WHITE);
		comboBoxDebit.setBackground(Color.WHITE);
		bJouer.setBackground(Color.WHITE);
		bJouer.addActionListener(this);
		bJouer.setActionCommand("jouer");
		bMenu.setBackground(Color.WHITE);
		bMenu.addActionListener(this);
		bMenu.setActionCommand("menu");
		vitesse.setBackground(Color.WHITE);
		api.setBackground(Color.WHITE);

		informations.setBackground(Color.WHITE);
		departArriveePlage.setBackground(Color.WHITE);
		jouerMenu.setBackground(Color.WHITE);
		bas.setBackground(Color.WHITE);
		boutons.setBackground(Color.WHITE);
		carte.setBackground(Color.WHITE);
		depart.setBackground(Color.WHITE);
		arrivee.setBackground(Color.WHITE);
		plage.setBackground(Color.WHITE);

		Dimension dimensionAPI = new Dimension((int)(FenetreForge.width*0.8), (int)(FenetreForge.height*0.7));
		Dimension dimensionJSlider = new Dimension((int)(FenetreForge.width*0.25), (int)(FenetreForge.height*0.025));
		Dimension dimensionBoutonsHaut = new Dimension((int)(FenetreForge.width*0.15), (int)(FenetreForge.height*0.30/5));
		Dimension dimensionComboBox = new Dimension((int)(FenetreForge.width*0.15/2), (int)(FenetreForge.height*0.30/5));
		Dimension dimensionBoutonsBas = new Dimension((int)(FenetreForge.width*0.15), (int)(FenetreForge.height*0.18/3));
		Dimension dimensionDates = new Dimension((int)(FenetreForge.width*0.15/2), (int)(FenetreForge.height*0.50/3/4));
		Dimension dimensionInformations = new Dimension((int)(FenetreForge.width*0.25), (int)(FenetreForge.height*0.20/4));		

		api.setPreferredSize(dimensionAPI);
		vitesse.setPreferredSize(dimensionJSlider);
		bcharger.setPreferredSize(dimensionBoutonsHaut);
		bimporter.setPreferredSize(dimensionBoutonsHaut);
		bexporter.setPreferredSize(dimensionBoutonsHaut);
		comboBoxliaisonSerie.setPreferredSize(dimensionComboBox);
		comboBoxDebit.setPreferredSize(dimensionComboBox);
		bJouer.setPreferredSize(dimensionBoutonsBas);
		bMenu.setPreferredSize(dimensionBoutonsBas);
		date.setPreferredSize(dimensionDates);
		date2.setPreferredSize(dimensionDates);
		date3.setPreferredSize(dimensionDates);
		date4.setPreferredSize(dimensionDates);
		date5.setPreferredSize(dimensionDates);
		date6.setPreferredSize(dimensionDates);
		vitActuelleDisplay.setPreferredSize(dimensionInformations);
		vitMoyenneDisplay.setPreferredSize(dimensionInformations);
		heureDepartDisplay.setPreferredSize(dimensionInformations);
		heureArriveeDisplay.setPreferredSize(dimensionInformations);
		heureActuelleDisplay.setPreferredSize(dimensionInformations);
		dateActuelleDisplay.setPreferredSize(dimensionInformations);

		Font tailletexte = new Font(null, Font.BOLD, 12);
		vitActuelle.setFont(tailletexte);
		vitMoyenne.setFont(tailletexte);
		heureDepart.setFont(tailletexte);
		heureArrivee.setFont(tailletexte);
		heureActuelle.setFont(tailletexte);
		dateActuelle.setFont(tailletexte);
		vitActuelleDisplay.setFont(tailletexte);
		vitMoyenneDisplay.setFont(tailletexte);
		heureDepartDisplay.setFont(tailletexte);
		heureArriveeDisplay.setFont(tailletexte);
		heureActuelleDisplay.setFont(tailletexte);
		dateActuelleDisplay.setFont(tailletexte);
		dateLabel.setFont(tailletexte);
		heureLabel.setFont(tailletexte);
		dateLabel2.setFont(tailletexte);
		heureLabel2.setFont(tailletexte);
		debutLabel.setFont(tailletexte);
		finLabel.setFont(tailletexte);
		bJouer.setFont(tailletexte);
		bMenu.setFont(tailletexte);
		bcharger.setFont(tailletexte);
		bimporter.setFont(tailletexte);
		bexporter.setFont(tailletexte);
		comboBoxliaisonSerie.setFont(tailletexte);
		((JLabel)comboBoxliaisonSerie.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBoxDebit.setFont(tailletexte);
		((JLabel)comboBoxDebit.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);


		boutons.setLayout(new GridBagLayout());
		GridBagConstraints gbcBoutons = new GridBagConstraints();     
		gbcBoutons.insets = new Insets(1,1,1,1);
		gbcBoutons.gridwidth = 2;
		gbcBoutons.fill = GridBagConstraints.BOTH;
		gbcBoutons.gridx = 0;       
		gbcBoutons.gridy = 0;
		boutons.add(bcharger, gbcBoutons);              
		gbcBoutons.gridy = 1;
		boutons.add(bimporter, gbcBoutons);         
		gbcBoutons.gridy = 2;
		boutons.add(bexporter, gbcBoutons);
		gbcBoutons.gridy = 3;
		gbcBoutons.gridwidth = 1;
		boutons.add(comboBoxliaisonSerie, gbcBoutons);
		gbcBoutons.gridx = 1;
		boutons.add(comboBoxDebit, gbcBoutons);



		GridLayout departArriveePlageGL = new GridLayout(3, 1);
		departArriveePlageGL.setHgap(5); 
		departArriveePlageGL.setVgap(5);
		departArriveePlage.setLayout(departArriveePlageGL);         
		departArriveePlage.add(depart);
		departArriveePlage.add(arrivee);
		departArriveePlage.add(plage);         
		depart.setBorder(BorderFactory.createTitledBorder(null, "Départ",0, 0, tailletexte));
		arrivee.setBorder(BorderFactory.createTitledBorder(null, "Arrivée",0, 0, tailletexte));
		plage.setBorder(BorderFactory.createTitledBorder(null, "Plage Horaire relatif de la simulation (en heure)",0, 0, tailletexte));

		depart.setLayout(new GridBagLayout());
		GridBagConstraints gbcDepart = new GridBagConstraints();             
		gbcDepart.insets = new Insets(4,4,4,4);
		gbcDepart.anchor = GridBagConstraints.CENTER;
		gbcDepart.gridx = 0;       
		gbcDepart.gridy = 0;
		depart.add(dateLabel, gbcDepart);
		gbcDepart.gridx = 1;
		depart.add(heureLabel, gbcDepart);
		gbcDepart.gridx = 0;
		gbcDepart.gridy = 1;
		depart.add(date, gbcDepart);         
		gbcDepart.gridx = 1; 
		depart.add(date2, gbcDepart);

		arrivee.setLayout(new GridBagLayout());
		GridBagConstraints gbcArrivee = new GridBagConstraints();             
		gbcArrivee.insets = new Insets(4,4,4,4);
		gbcArrivee.anchor = GridBagConstraints.CENTER;
		gbcArrivee.gridx = 0;       
		gbcArrivee.gridy = 0;
		arrivee.add(dateLabel2, gbcArrivee);
		gbcArrivee.gridx = 1;
		arrivee.add(heureLabel2, gbcArrivee);
		gbcArrivee.gridx = 0;       
		gbcArrivee.gridy = 1;
		arrivee.add(date3, gbcArrivee);                
		gbcArrivee.gridx = 1; 
		arrivee.add(date4, gbcArrivee);

		plage.setLayout(new GridBagLayout());
		GridBagConstraints gbcPlage = new GridBagConstraints();      
		gbcPlage.insets = new Insets(4,4,4,4);
		gbcPlage.anchor = GridBagConstraints.CENTER;
		gbcPlage.gridx = 0;       
		gbcPlage.gridy = 0;
		plage.add(debutLabel, gbcPlage);
		gbcPlage.gridx = 1;
		plage.add(finLabel, gbcPlage);
		gbcPlage.gridx = 0;       
		gbcPlage.gridy = 1;
		plage.add(date5, gbcPlage);         
		gbcPlage.gridx = 1; 
		plage.add(date6, gbcPlage);



		jouerMenu.setLayout(new GridBagLayout());
		GridBagConstraints gbcJouerMenu = new GridBagConstraints();
		gbcJouerMenu.insets = new Insets(1,1,1,1);
		gbcJouerMenu.gridx = 0;
		gbcJouerMenu.gridy = 0;     
		jouerMenu.add(bJouer,gbcJouerMenu);
		gbcJouerMenu.gridy = 1;
		jouerMenu.add(bMenu,gbcJouerMenu);

		vitesse.setMaximum(3);
		vitesse.setMinimum(0);
		vitesse.setValue(1);
		vitesse.setPaintTicks(true);
		vitesse.setPaintLabels(true);
		vitesse.setMinorTickSpacing(0);
		vitesse.setMajorTickSpacing(1);
		bas.add(vitesse);

		api.setZoom(8, new java.awt.Point(-25,40));
		carte.add(api); 

		informations.setLayout(new GridBagLayout());
		GridBagConstraints gbcInformations = new GridBagConstraints();             
		gbcInformations.insets = new Insets(4,4,4,4);
		gbcInformations.anchor = GridBagConstraints.CENTER;
		gbcInformations.gridx = 0;       
		gbcInformations.gridy = 0;
		informations.add(vitActuelle, gbcInformations);
		gbcInformations.gridy = 2;
		informations.add(vitActuelleDisplay, gbcInformations);
		gbcInformations.gridy = 3;
		informations.add(vitMoyenne, gbcInformations);
		gbcInformations.gridy = 4;
		informations.add(vitMoyenneDisplay, gbcInformations);
		gbcInformations.gridx = 2; 
		gbcInformations.gridy = 0;
		informations.add(heureDepart, gbcInformations);
		gbcInformations.gridy = 2;
		informations.add(heureDepartDisplay, gbcInformations);
		gbcInformations.gridy = 3;
		informations.add(heureArrivee, gbcInformations);
		gbcInformations.gridy = 4;
		informations.add(heureArriveeDisplay, gbcInformations);
		gbcInformations.gridx = 3; 
		gbcInformations.gridy = 0;
		informations.add(heureActuelle, gbcInformations);
		gbcInformations.gridy = 2;
		informations.add(heureActuelleDisplay, gbcInformations);
		gbcInformations.gridy = 3;
		informations.add(dateActuelle, gbcInformations);
		gbcInformations.gridy = 4;
		informations.add(dateActuelleDisplay, gbcInformations);

		informations.setBorder(BorderFactory.createTitledBorder(null, "Informations",0, 0, tailletexte));
		vitActuelleDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		vitMoyenneDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		heureDepartDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		heureArriveeDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		heureActuelleDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		dateActuelleDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));



		FenetreForge.fenetreForge.getContentPane().removeAll();
		FenetreForge.fenetreForge.getContentPane().add(splitForge);
		FenetreForge.fenetreForge.setVisible(true);

	}


	void jouer()
	{		
		ArrayList<Point> listePoint2 = this.panelAPICarte.getParcours().getListePoints();
		for(int i=0;i<this.panelAPICarte.getParcours().getListePoints().size();i++)
		{
			this.panelAPICarte.addMapMarker(new AffichagePointInter(new Coordinate(listePoint2.get(i).getCoordonnes().getLatitude(),listePoint2.get(i).getCoordonnes().getLongitude()),"./img/MarqueurPoint.png"));
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "charger":
			new IHMChoixFichier(e.getActionCommand(), this.panelAPICarte, "simulation");
			break;
		case "importer":
			new IHMChoixFichier(e.getActionCommand(), this.panelAPICarte, new Simulation());
			break;
		case "exporter":
			Simulation simulation = new Simulation(this.panelAPICarte.getParcours());
			new IHMChoixFichier(e.getActionCommand(), this.panelAPICarte, simulation);
			break;
		case "jouer":
			if(this.panelAPICarte.getParcours() != null)
				if(this.panelAPICarte.getParcours().getListePoints().size()>0)
					jouer();
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

}
