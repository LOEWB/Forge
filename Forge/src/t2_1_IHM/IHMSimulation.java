package t2_1_IHM;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jssc.SerialPortException;

import org.openstreetmap.gui.jmapviewer.Coordinate;





import t1_1_Model_Principal.EtatSimu;
import t1_1_Model_Principal.Parcours;
import t1_1_Model_Principal.Point;
import t1_1_Model_Principal.PortSerie;
import t1_1_Model_Principal.Simulation;
import t1_1_Model_Principal.TypeSysteme;


public class IHMSimulation implements ActionListener {

	private JButton bcharger = new JButton("Charger");

	private JButton bimporter = new JButton("Importer");

	private JButton bexporter = new JButton("Exporter");

	private JButton bJouer = new JButton("JOUER");

	private JButton bMenu = new JButton("MENU");

	private JButton bPause = new JButton("");

	private JButton bLecture = new JButton("");

	private JButton bArret = new JButton("");

	private JLabel vitActuelle = new JLabel("Vitesse actuelle");    
	private JLabel vitActuelleDisplay = new JLabel("");

	private JLabel vitMoyenne = new JLabel("Vitesse moyenne");
	private JLabel vitMoyenneDisplay = new JLabel("");

	private JLabel altActuelle = new JLabel("Altitude actuelle");
	private JLabel altActuelleDisplay = new JLabel("");

	private JLabel altMoyenne = new JLabel("Altitude moyenne");
	private JLabel altMoyenneDisplay = new JLabel("");

	private JLabel heureActuelle = new JLabel("Heure actuelle");
	private JLabel heureActuelleDisplay = new JLabel("");

	private JLabel dateActuelle = new JLabel("Date actuelle");
	private JLabel dateActuelleDisplay = new JLabel("");

	private JLabel dateLabel = new JLabel("Date");
	private JLabel heureLabel = new JLabel("Heure");
	private JLabel dateLabel2 = new JLabel("Date");
	private JLabel heureLabel2 = new JLabel("Heure");
	private JLabel debutLabel = new JLabel("D�but");
	private JLabel finLabel = new JLabel("Fin");

	private JFormattedTextField date = new JFormattedTextField(new SimpleDateFormat("MM/dd/yyyy"));

	private JFormattedTextField date2 = new JFormattedTextField(NumberFormat.getNumberInstance());

	private JFormattedTextField date3 = new JFormattedTextField(new SimpleDateFormat("MM/dd/yyyy"));

	private JFormattedTextField date4 = new JFormattedTextField(NumberFormat.getNumberInstance());

	private JFormattedTextField date5 = new JFormattedTextField(NumberFormat.getNumberInstance());

	private JFormattedTextField date6 = new JFormattedTextField(NumberFormat.getNumberInstance());

	private JLabel tauxErreurLabel = new JLabel("Taux d'erreur :    ");

	private JFormattedTextField tauxErreur = new JFormattedTextField(NumberFormat.getPercentInstance());

	private PortSerie portSerie = new PortSerie();

	private String[] listeLiaisonSerie = portSerie.getListePorts().toArray(new String[portSerie.getListePorts().size()]);

	private JComboBox<String> comboBoxliaisonSerie = new JComboBox<String>(listeLiaisonSerie);

	private String[] listeDebit = { "1200","2400","3600" };

	private JComboBox<String> comboBoxDebit = new JComboBox<String>(listeDebit);

	JSlider vitesse = new JSlider();

	private Parcours parcours;

	private PanelAPICarte panelAPICarte;

	private Simulation simulation;

	private float dataTauxErreur;




	public IHMSimulation(Parcours parcours) 
	{

		this.parcours = parcours;


		creationComposant();
		panelAPICarte.createMarkerDebutFin();
		panelAPICarte.traceSegments();


		tauxErreur.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				if(simulation == null)
					Data();
				else
					if(simulation.getEtat() == EtatSimu.ARRET)
						Data();
			}

			private void Data()
			{

				if(tauxErreur.getValue() instanceof Float)
				{
					if((float) tauxErreur.getValue() <= 1f)
					{
						dataTauxErreur = (float) tauxErreur.getValue();					
					}
				}
				else
				{
					if((float)((double) tauxErreur.getValue()) <= 1f)
					{
						dataTauxErreur = (float)((double) tauxErreur.getValue());
					}
				}
			}
		});

		date.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
			}

			private void Data()
			{
				if(date2.getValue() == null)
					date3.setValue(date.getValue());
				else
				{
					int tempsSimuH = 0;
					Calendar cal = Calendar.getInstance();
					cal.setTime((Date) date.getValue());
					if(panelAPICarte.getParcours() != null)
					{
						tempsSimuH = (int)((double)panelAPICarte.getParcours().getListePoints().get(panelAPICarte.getParcours().getListePoints().size()-1).getTemps()/3600);
					}

					if(date2.getValue() instanceof Double)
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((double)date2.getValue()-(cal.get(Calendar.HOUR_OF_DAY)))));
					else
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((long)date2.getValue()-(cal.get(Calendar.HOUR_OF_DAY)))));

					date3.setValue(cal.getTime());
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					date4.setValue((double)(hour));

				}
			}
		});

		date2.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
			}

			private void Data()
			{
				if(date.getValue() != null)
				{
					int tempsSimuH = 0;
					Calendar cal = Calendar.getInstance();
					cal.setTime((Date) date.getValue());
					if(panelAPICarte.getParcours() != null)
					{
						tempsSimuH = (int)((double)panelAPICarte.getParcours().getListePoints().get(panelAPICarte.getParcours().getListePoints().size()-1).getTemps()/3600);
					}
					if(date2.getValue() instanceof Double)
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((double)date2.getValue()-(cal.get(Calendar.HOUR_OF_DAY)))));
					else
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((long)date2.getValue()-(cal.get(Calendar.HOUR_OF_DAY)))));


					date3.setValue(cal.getTime());
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					date4.setValue((double)(hour));


				}
			}
		});


		/*date3.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
			}

			private void Data()
			{
				if(date4.getValue() == null)
					date.setValue(date.getValue());
				else
				{
					int tempsSimuH = 0;
					int tempsSimuM = 0;
					Calendar cal = Calendar.getInstance();
					cal.setTime((Date) date3.getValue());
					if(panelAPICarte.getParcours() != null)
					{
						tempsSimuH = (int)((double)panelAPICarte.getParcours().getListePoints().get(panelAPICarte.getParcours().getListePoints().size()-1).getTemps()/3600);
						tempsSimuM = (int)((double)panelAPICarte.getParcours().getListePoints().get(panelAPICarte.getParcours().getListePoints().size()-1).getTemps()-(tempsSimuH*3600))/60;
					}
					if(date4.getValue() instanceof Double)
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((double)date4.getValue())));
					else
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((long)date4.getValue())));


					if(date4.getValue() instanceof Double)
						cal.add(Calendar.MINUTE, tempsSimuM + (int)((double)date4.getValue()*60));
					else
						cal.add(Calendar.MINUTE, tempsSimuM + (int)((long)date4.getValue()*60));

					Date dateA = cal.getTime();

					date.setValue(dateA);
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					int min = cal.get(Calendar.MINUTE);
					date2.setValue((double)(hour+(min/60)));


				}
			}
		});

		date4.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
			}

			private void Data()
			{
				if(date3.getValue() != null)
				{
					int tempsSimuH = 0;
					int tempsSimuM = 0;
					Calendar cal = Calendar.getInstance();
					cal.setTime((Date) date3.getValue());
					if(panelAPICarte.getParcours() != null)
					{
						tempsSimuH = (int)((double)panelAPICarte.getParcours().getListePoints().get(panelAPICarte.getParcours().getListePoints().size()-1).getTemps()/3600);
						tempsSimuM = (int)((double)panelAPICarte.getParcours().getListePoints().get(panelAPICarte.getParcours().getListePoints().size()-1).getTemps()-(tempsSimuH*3600))/60;
					}
					if(date4.getValue() instanceof Double)
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((double)date4.getValue())));
					else
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((long)date4.getValue())));


					if(date4.getValue() instanceof Double)
						cal.add(Calendar.MINUTE, tempsSimuM + (int)((double)date4.getValue()*60));
					else
						cal.add(Calendar.MINUTE, tempsSimuM + (int)((long)date4.getValue()*60));

					Date dateA = cal.getTime();

					date.setValue(dateA);
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					int min = cal.get(Calendar.MINUTE);
					date2.setValue((double)(hour+(min/60)));

				}
			}
		});*/

	}

	public IHMSimulation() 
	{


		//		for(int i=0; i<this.listeLiaisonSerie.length; i++) System.out.println(this.listeLiaisonSerie[i]);
		creationComposant();


		tauxErreur.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				if(simulation == null)
					Data();
				else
					if(simulation.getEtat() == EtatSimu.ARRET)
						Data();
			}

			private void Data()
			{

				if(tauxErreur.getValue() instanceof Float)
				{
					if((float) tauxErreur.getValue() <= 1f)
					{
						dataTauxErreur = (float) tauxErreur.getValue();					
					}
				}
				else
				{
					if((float)((double) tauxErreur.getValue()) <= 1f)
					{
						dataTauxErreur = (float)((double) tauxErreur.getValue());
					}
				}
			}
		});

		date.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
			}

			private void Data()
			{
				if(date2.getValue() == null)
					date3.setValue(date.getValue());
				else
				{
					int tempsSimuH = 0;
					Calendar cal = Calendar.getInstance();
					cal.setTime((Date) date.getValue());
					if(panelAPICarte.getParcours() != null)
					{
						tempsSimuH = (int)((double)panelAPICarte.getParcours().getListePoints().get(panelAPICarte.getParcours().getListePoints().size()-1).getTemps()/3600);
					}

					if(date2.getValue() instanceof Double)
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((double)date2.getValue()-(cal.get(Calendar.HOUR_OF_DAY)))));
					else
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((long)date2.getValue()-(cal.get(Calendar.HOUR_OF_DAY)))));

					date3.setValue(cal.getTime());
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					date4.setValue((double)(hour));

				}
			}
		});

		date2.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
			}

			private void Data()
			{
				if(date.getValue() != null)
				{
					int tempsSimuH = 0;
					Calendar cal = Calendar.getInstance();
					cal.setTime((Date) date.getValue());
					if(panelAPICarte.getParcours() != null)
					{
						tempsSimuH = (int)((double)panelAPICarte.getParcours().getListePoints().get(panelAPICarte.getParcours().getListePoints().size()-1).getTemps()/3600);
					}
					if(date2.getValue() instanceof Double)
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((double)date2.getValue()-(cal.get(Calendar.HOUR_OF_DAY)))));
					else
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((long)date2.getValue()-(cal.get(Calendar.HOUR_OF_DAY)))));


					date3.setValue(cal.getTime());
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					date4.setValue((double)(hour));


				}
			}
		});

		/*date3.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
			}

			private void Data()
			{
				if(date4.getValue() == null)
					date.setValue(date.getValue());
				else
				{
					int tempsSimuH = 0;
					int tempsSimuM = 0;
					Calendar cal = Calendar.getInstance();
					cal.setTime((Date) date3.getValue());
					if(panelAPICarte.getParcours() != null)
					{
						tempsSimuH = (int)((double)panelAPICarte.getParcours().getListePoints().get(panelAPICarte.getParcours().getListePoints().size()-1).getTemps()/3600);
						tempsSimuM = (int)((double)panelAPICarte.getParcours().getListePoints().get(panelAPICarte.getParcours().getListePoints().size()-1).getTemps()-(tempsSimuH*3600))/60;
					}
					if(date4.getValue() instanceof Double)
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((double)date4.getValue())));
					else
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((long)date4.getValue())));


					if(date4.getValue() instanceof Double)
						cal.add(Calendar.MINUTE, tempsSimuM + (int)((double)date4.getValue()*60));
					else
						cal.add(Calendar.MINUTE, tempsSimuM + (int)((long)date4.getValue()*60));

					date.setValue(cal.getTime());
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					int min = cal.get(Calendar.MINUTE);
					date2.setValue((double)(hour+(min/60)));


				}
			}
		});

		date4.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
			}

			private void Data()
			{
				if(date3.getValue() != null)
				{
					int tempsSimuH = 0;
					int tempsSimuM = 0;
					Calendar cal = Calendar.getInstance();
					cal.setTime((Date) date3.getValue());
					if(panelAPICarte.getParcours() != null)
					{
						tempsSimuH = (int)((double)panelAPICarte.getParcours().getListePoints().get(panelAPICarte.getParcours().getListePoints().size()-1).getTemps()/3600);
						tempsSimuM = (int)((double)panelAPICarte.getParcours().getListePoints().get(panelAPICarte.getParcours().getListePoints().size()-1).getTemps()-(tempsSimuH*3600))/60;
					}
					if(date4.getValue() instanceof Double)
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((double)date4.getValue())));
					else
						cal.add(Calendar.HOUR, (tempsSimuH + (int)((long)date4.getValue())));


					if(date4.getValue() instanceof Double)
						cal.add(Calendar.MINUTE, tempsSimuM + (int)((double)date4.getValue()*60));
					else
						cal.add(Calendar.MINUTE, tempsSimuM + (int)((long)date4.getValue()*60));

					date.setValue(cal.getTime());
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					int min = cal.get(Calendar.MINUTE);
					date2.setValue((double)(hour+(min/60)));

				}
			}
		});*/

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
		JPanel panelBoutonsPaLeAr = new JPanel();
		JPanel panelTauxErreur = new JPanel();





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
		splitBasDroit.setDividerLocation((int)(FenetreForge.height*0.85));
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
		panelTauxErreur.setBackground(Color.WHITE);
		panelBoutonsPaLeAr.setBackground(Color.WHITE);
		bPause.setBackground(Color.WHITE);
		bPause.addActionListener(this);
		bPause.setActionCommand("pause");
		bLecture.setBackground(Color.WHITE);
		bLecture.addActionListener(this);
		bLecture.setActionCommand("lecture");
		bArret.setBackground(Color.WHITE);
		bArret.addActionListener(this);
		bArret.setActionCommand("arret");
		bPause.setBorder(null);
		bLecture.setBorder(null);
		bArret.setBorder(null);
		ImageIcon imgPause = new ImageIcon("./img/Pause.png");
		ImageIcon imgLecture = new ImageIcon("./img/Lecture.png");
		ImageIcon imgArret = new ImageIcon("./img/Arret.png");
		bPause.setIcon(imgPause);
		bLecture.setIcon(imgLecture);
		bArret.setIcon(imgArret);

		Dimension dimensionAPI = new Dimension((int)(FenetreForge.width*0.8), (int)(FenetreForge.height*0.7));
		Dimension dimensionJSlider = new Dimension((int)(FenetreForge.width*0.25), (int)(FenetreForge.height*0.1));
		Dimension dimensionBoutonsHaut = new Dimension((int)(FenetreForge.width*0.15), (int)(FenetreForge.height*0.30/5));
		Dimension dimensionComboBox = new Dimension((int)(FenetreForge.width*0.15/2), (int)(FenetreForge.height*0.30/5));
		Dimension dimensionBoutonsBas = new Dimension((int)(FenetreForge.width*0.15), (int)(FenetreForge.height*0.18/3));
		Dimension dimensionBoutonsBasDroite = new Dimension((int)(FenetreForge.width*0.05), (int)(FenetreForge.height*0.18/3));
		Dimension dimensionDates = new Dimension((int)(FenetreForge.width*0.15/2), (int)(FenetreForge.height*0.50/3/4));
		Dimension dimensionInformations = new Dimension((int)(FenetreForge.width*0.25), (int)(FenetreForge.height*0.20/4));	
		Dimension dimensionTauxErreur = new Dimension((int)(FenetreForge.width*0.05), (int)(FenetreForge.height*0.02));	


		api.setPreferredSize(dimensionAPI);
		vitesse.setPreferredSize(dimensionJSlider);
		bcharger.setPreferredSize(dimensionBoutonsHaut);
		bimporter.setPreferredSize(dimensionBoutonsHaut);
		bexporter.setPreferredSize(dimensionBoutonsHaut);
		comboBoxliaisonSerie.setPreferredSize(dimensionComboBox);
		comboBoxDebit.setPreferredSize(dimensionComboBox);
		bJouer.setPreferredSize(dimensionBoutonsBas);
		bMenu.setPreferredSize(dimensionBoutonsBas);
		bPause.setPreferredSize(dimensionBoutonsBasDroite);
		bLecture.setPreferredSize(dimensionBoutonsBasDroite);
		bArret.setPreferredSize(dimensionBoutonsBasDroite);
		date.setPreferredSize(dimensionDates);
		date2.setPreferredSize(dimensionDates);
		date3.setPreferredSize(dimensionDates);
		date4.setPreferredSize(dimensionDates);
		date5.setPreferredSize(dimensionDates);
		date6.setPreferredSize(dimensionDates);
		vitActuelleDisplay.setPreferredSize(dimensionInformations);
		vitMoyenneDisplay.setPreferredSize(dimensionInformations);
		altActuelleDisplay.setPreferredSize(dimensionInformations);
		altMoyenneDisplay.setPreferredSize(dimensionInformations);
		heureActuelleDisplay.setPreferredSize(dimensionInformations);
		dateActuelleDisplay.setPreferredSize(dimensionInformations);  
		tauxErreur.setPreferredSize(dimensionTauxErreur);

		Font tailletexte = new Font(null, Font.BOLD, 12);
		vitActuelle.setFont(tailletexte);
		vitMoyenne.setFont(tailletexte);
		altActuelle.setFont(tailletexte);
		altMoyenne.setFont(tailletexte);
		heureActuelle.setFont(tailletexte);
		dateActuelle.setFont(tailletexte);
		vitActuelleDisplay.setFont(tailletexte);
		vitMoyenneDisplay.setFont(tailletexte);
		altActuelleDisplay.setFont(tailletexte);
		altMoyenneDisplay.setFont(tailletexte);
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
		tauxErreurLabel.setFont(tailletexte);

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
		depart.setBorder(BorderFactory.createTitledBorder(null, "D�part",0, 0, tailletexte));
		arrivee.setBorder(BorderFactory.createTitledBorder(null, "Arriv�e",0, 0, tailletexte));
		plage.setBorder(BorderFactory.createTitledBorder(null, "Plage Horaire relatif (en heure)",0, 0, tailletexte));

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

		vitesse.setMaximum(10);
		vitesse.setMinimum(1);
		vitesse.setValue(1);
		vitesse.setPaintTicks(true);
		vitesse.setPaintLabels(true);
		vitesse.setMinorTickSpacing(0);
		vitesse.setMajorTickSpacing(1);

		panelTauxErreur.setLayout(new GridBagLayout());
		GridBagConstraints gbcTauxErreur = new GridBagConstraints();
		gbcTauxErreur.insets = new Insets(4,4,4,4);
		gbcTauxErreur.gridx = 0;
		gbcTauxErreur.gridy = 0;
		panelTauxErreur.add(tauxErreurLabel,gbcTauxErreur);
		gbcTauxErreur.gridx = 1;
		panelTauxErreur.add(tauxErreur,gbcTauxErreur);


		panelBoutonsPaLeAr.setLayout(new GridBagLayout());
		GridBagConstraints gbcBoutonsPaLe = new GridBagConstraints();
		gbcBoutonsPaLe.gridx = 0;
		gbcBoutonsPaLe.gridy = 0;
		panelBoutonsPaLeAr.add(bPause,gbcBoutonsPaLe);
		gbcBoutonsPaLe.gridx = 1;
		panelBoutonsPaLeAr.add(bLecture,gbcBoutonsPaLe);
		gbcBoutonsPaLe.gridx = 2;
		panelBoutonsPaLeAr.add(bArret,gbcBoutonsPaLe);


		GridLayout glBas = new GridLayout(1, 3);
		glBas.setHgap(1); 
		bas.setLayout(new GridBagLayout());
		bas.setLayout(glBas); 
		bas.add(panelBoutonsPaLeAr);
		bas.add(vitesse);
		bas.add(panelTauxErreur);



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
		informations.add(altActuelle, gbcInformations);
		gbcInformations.gridy = 2;
		informations.add(altActuelleDisplay, gbcInformations);
		gbcInformations.gridy = 3;
		informations.add(altMoyenne, gbcInformations);
		gbcInformations.gridy = 4;
		informations.add(altMoyenneDisplay, gbcInformations);
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
		altActuelleDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		altMoyenneDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		heureActuelleDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		dateActuelleDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));


		java.util.Date dateactuelle = new java.util.Date();
		date.setValue(dateactuelle);
		date3.setValue(dateactuelle);
		date2.setValue(0l);

		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) date.getValue());
		if(panelAPICarte.getParcours() != null)
		{
			int tempsSimuH = (int)((double)panelAPICarte.getParcours().getListePoints().get(panelAPICarte.getParcours().getListePoints().size()-1).getTemps()/3600);
			cal.add(Calendar.HOUR, (tempsSimuH));		
		}
		date3.setValue(cal.getTime());
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		date4.setValue((double)(hour));


		tauxErreur.setValue(0.0f);
		dataTauxErreur = 0.0f;
		if(this.panelAPICarte.getParcours() != null)
		{
			date5.setValue(this.panelAPICarte.getParcours().getListePoints().get(0).getTemps()/3600);
			date6.setValue(this.panelAPICarte.getParcours().getListePoints().get(this.panelAPICarte.getParcours().getListePoints().size()-1).getTemps()/3600);
		}



		FenetreForge.fenetreForge.getContentPane().removeAll();
		FenetreForge.fenetreForge.getContentPane().add(splitForge);
		FenetreForge.fenetreForge.setVisible(true);

	}

	private void afficherLabels()
	{
		if (this.panelAPICarte.getParcours() != null)
		{
			NumberFormat format = NumberFormat.getInstance();
			format.setMinimumFractionDigits(2);
			this.vitMoyenneDisplay.setText(format.format(this.panelAPICarte.getParcours().vitesseMoyenne()) + "   km/h");
			this.altMoyenneDisplay.setText(format.format(this.panelAPICarte.getParcours().altitudeMoyenne())+ "   m");


		}
	}

	private String secToHoursMinSec(int t)
	{
		int h = t/3600;
		int m = (t%3600)/60;
		int s = t % 60;
		return h+":"+m+":"+s+" h:min:s";
	}

	private int hoursMinSecToSec(String str)
	{
		String[] time = (str.split(" "))[0].split(":");
		int h = Integer.parseInt(time[0]);
		int m = Integer.parseInt(time[1]);
		int s = Integer.parseInt(time[2]);
		return h*3600+m*60+s;
	}



	void jouer()
	{		
		this.simulation = new Simulation(this.panelAPICarte.getParcours());
		this.simulation.setEtat(EtatSimu.LECTURE);

		this.simulation.setlistePointsImportes(this.panelAPICarte.getParcours().genererListePointsIntermediaires(this.panelAPICarte.getParcours().getDebit(), this.panelAPICarte.getParcours().getListePoints()));
		this.panelAPICarte.getParcours().setListePoints(this.simulation.getlistePointsImportes());
		int monX2 = 0;
		int monY2 = 5000;
		Rectangle rect2 = new Rectangle(monX2,monY2);
		panelAPICarte.paintImmediately(rect2);


		new Thread(
				new Runnable()
				{
					private String boussierTrame(String trame){

						Simulation simulation = new Simulation();


						Point point = simulation.getPoint(trame);
						int dd = 0 + (int)(Math.random() * ((250-1 - 0) + 1));

						Point point2 = new Point(point.getTemps()-dd, point.getCoordonnes(), point.getAltitude()+dd); // modification avec conneries
						ArrayList<Point> listePoints = new ArrayList<Point>();
						listePoints.add(point2);

						Parcours parcours = new Parcours(TypeSysteme.AERIEN, listePoints); // AERIEN au pif pour satisfaire constructeur....

						String newTrame = parcours.genererTrames();

						return newTrame;

					}

					public void run()				
					{

						Float tempsAttente = (float) (panelAPICarte.getParcours().getListePoints().get(1).getTemps() - panelAPICarte.getParcours().getListePoints().get(0).getTemps());
						PortSerie portserie = new PortSerie();
						portserie.setPort(comboBoxliaisonSerie.getSelectedItem().toString(),Integer.valueOf(comboBoxDebit.getSelectedItem().toString()));
						int i = 0;


						if(dataTauxErreur < 1f && dataTauxErreur != 0f) {

							int nbTramesErronnees = (int) (simulation.getTramesArray().size()*dataTauxErreur);
							int nbTramesfaites = 0;
							ArrayList<Integer> faits = new ArrayList<Integer>();
							int j=0;
							int dd=0;

							while(nbTramesfaites != nbTramesErronnees) { // bon boussie d'abord la liste de trame selon taux d'erreur avant de commencer l'envoi
								dd = 0 + (int)(Math.random() * ((simulation.getTramesArray().size()-1 - 0) + 1));
								if(!faits.contains(dd)) {
									simulation.getTramesArray().set(dd, boussierTrame(simulation.getTramesArray().get(dd).toString()));
									faits.add(dd);
									nbTramesfaites++;

								}

							}



							while(true)
							{
								while(simulation.getEtat() == EtatSimu.PAUSE)
								{
									try {
										Thread.sleep(0l);
									} catch (InterruptedException e1) {
										throw new RuntimeException("Thread interrupted..."+e1);
									}
								}

								if(simulation.getEtat() == EtatSimu.ARRET)
								{
									Thread.currentThread().interrupt();
									break;
								}

								while(simulation.getEtat() == EtatSimu.PAUSE);

								for(;i<panelAPICarte.getParcours().getListePoints().size();i++)
								{
									if(panelAPICarte.getParcours().getListePoints().get(i).getTemps()>(double)date5.getValue()*3600)
										break;
								}

								for(;i<simulation.getTramesArray().size();i++)
								{

									System.out.println(simulation.getTramesArray().get(i));

									if(simulation.getEtat() == EtatSimu.ARRET)
									{
										Thread.currentThread().interrupt();
										break;
									}

									if(simulation.getEtat() == EtatSimu.PAUSE)
										break;

									//pour toutes les trames sauf la derniere
									if(i+1<simulation.getTramesArray().size())
									{
										try {
											System.out.println(simulation.getTramesArray().get(i));
											portserie.envoyer(simulation.getTramesArray().get(i));

										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (SerialPortException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

										try {
											Thread.sleep((long)(1000*tempsAttente/vitesse.getValue()));
										} catch (InterruptedException e) {
											throw new RuntimeException("Thread interrupted..."+e);
										}
									}
									//pour la derni�re trame
									else
									{
										try {
											portserie.envoyer(simulation.getTramesArray().get(i));											
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
					}
				}).start();






		new Thread(
				new Runnable() 
				{
					public void run() 
					{

						Float tempsAttente = (float) (panelAPICarte.getParcours().getListePoints().get(1).getTemps() - panelAPICarte.getParcours().getListePoints().get(0).getTemps());
						int i=0;

						while(true)
						{


							while(simulation.getEtat() == EtatSimu.PAUSE)
							{
								try {
									Thread.sleep(0l);
								} catch (InterruptedException e1) {
									throw new RuntimeException("Thread interrupted..."+e1);

								}
							}

							if(i>=panelAPICarte.getParcours().getListePoints().size())
							{
								bArret.doClick();
								break;
							}

							if(simulation.getEtat() == EtatSimu.ARRET)
							{
								Thread.currentThread().interrupt();
								break;
							}

							for(;i<panelAPICarte.getParcours().getListePoints().size();i++)
							{
								if(panelAPICarte.getParcours().getListePoints().get(i).getTemps()>(double)date5.getValue()*3600)
									break;
							}


							for(;i<panelAPICarte.getParcours().getListePoints().size();i++)
							{
								if(date5.getValue() instanceof Double)
								{
									if(panelAPICarte.getParcours().getListePoints().get(i).getTemps()>(double)date5.getValue()*3600)
										break;
								}
								else
								{
									if(panelAPICarte.getParcours().getListePoints().get(i).getTemps()>(long)date5.getValue()*3600)
										break;
								}
										
							}


							for(;i<panelAPICarte.getParcours().getListePoints().size();i++)
							{

								if(panelAPICarte.getParcours().getListePoints().get(i).getTemps()>((double)date6.getValue()*3600))
								{
									System.out.println(panelAPICarte.getParcours().getListePoints().get(i).getTemps());
									System.out.println((double)date6.getValue());
									bArret.doClick();
									break;
								}								

								if(simulation.getEtat() == EtatSimu.ARRET)
								{
									Thread.currentThread().interrupt();
									break;
								}

								if(simulation.getEtat() == EtatSimu.PAUSE)
									break;

								if(i+1<panelAPICarte.getParcours().getListePoints().size())
								{
									NumberFormat format = NumberFormat.getNumberInstance(); 
									format.setMinimumFractionDigits(2);
									vitActuelleDisplay.setText(format.format(((double)panelAPICarte.getParcours().vitesseSegments(panelAPICarte.getParcours().getListePoints().get(i), panelAPICarte.getParcours().getListePoints().get(i+1))*vitesse.getValue())) + "  km/h");
									altActuelleDisplay.setText(format.format(((double)panelAPICarte.getParcours().altitudeSegments(panelAPICarte.getParcours().getListePoints().get(i), panelAPICarte.getParcours().getListePoints().get(i+1)))) + "  m");
									
									SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
									int tempsSimuH = 0;
									Calendar cal = Calendar.getInstance();
									cal.setTime((Date) date.getValue());
									tempsSimuH = (int)((double)panelAPICarte.getParcours().getListePoints().get(i).getTemps()/3600);
									if(date2.getValue() instanceof Double)
										cal.add(Calendar.HOUR, (tempsSimuH + (int)((double)date2.getValue()-(cal.get(Calendar.HOUR_OF_DAY)))));
									else
										cal.add(Calendar.HOUR, (tempsSimuH + (int)((long)date2.getValue()-(cal.get(Calendar.HOUR_OF_DAY)))));
									int hour = cal.get(Calendar.HOUR_OF_DAY);									
									heureActuelleDisplay.setText(format.format((long)date2.getValue())+(panelAPICarte.getParcours().getListePoints().get(i).getTemps()*100));
									dateActuelleDisplay.setText(ft.format(date3.getValue()));
									
								}

								afficherLabels();

								panelAPICarte.addMapMarker(new AffichagePointInter(new Coordinate(simulation.getlistePointsImportes().get(i).getCoordonnes().getLongitude(),simulation.getlistePointsImportes().get(i).getCoordonnes().getLatitude()),"./img/MarqueurPoint.png"));
								if(panelAPICarte.getMapPosition(panelAPICarte.getMapMarkerList().get(panelAPICarte.getMapMarkerList().size()-1).getCoordinate()) != null)
								{
									int monX = ((int) panelAPICarte.getMapPosition(panelAPICarte.getMapMarkerList().get(panelAPICarte.getMapMarkerList().size()-1).getCoordinate()).getX()) - AffichagePoint.MARKER_SIZE*2;
									int monY = ((int) panelAPICarte.getMapPosition(panelAPICarte.getMapMarkerList().get(panelAPICarte.getMapMarkerList().size()-1).getCoordinate()).getY()) - AffichagePoint.MARKER_SIZE*3;
									Rectangle rect = new Rectangle(monX,monY,AffichagePoint.MARKER_SIZE*6,AffichagePoint.MARKER_SIZE*6);
									panelAPICarte.paintImmediately(rect);
									try {
										Thread.sleep((long)(1000*tempsAttente/vitesse.getValue()));
									} catch (InterruptedException e) {
										throw new RuntimeException("Thread interrupted..."+e);
									}	
								}


								panelAPICarte.removeMapMarker(panelAPICarte.getMapMarkerList().get(panelAPICarte.getMapMarkerList().size()-1));
								int monX2 = 0;
								int monY2 = 5000;
								Rectangle rect2 = new Rectangle(monX2,monY2);
								panelAPICarte.paintImmediately(rect2);
							}
						}
					}
				}).start();



	}


	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "charger":
			new IHMChoixFichier(e.getActionCommand(), this.panelAPICarte, "simulation");
			if(this.panelAPICarte.getParcours() != null)
			{
				date5.setValue(this.panelAPICarte.getParcours().getListePoints().get(0).getTemps()/3600);
				date6.setValue(this.panelAPICarte.getParcours().getListePoints().get(this.panelAPICarte.getParcours().getListePoints().size()-1).getTemps()/3600);
			}
			break;
		case "importer":
			IHMChoixFichier ihmChoixFichiernew = new IHMChoixFichier(e.getActionCommand(), this.panelAPICarte, new Simulation());
			this.simulation = ihmChoixFichiernew.getSimulation();
			this.panelAPICarte = ihmChoixFichiernew.getPanelAPICarte();
			System.out.println(this.panelAPICarte.getParcours().getListePoints());
			System.out.println(this.simulation.getlistePointsImportes());
			this.panelAPICarte.createMarkerDebutFin();
			this.panelAPICarte.traceSegments();
			break;
		case "exporter":
			Simulation simulation = new Simulation(this.panelAPICarte.getParcours());
			new IHMChoixFichier(e.getActionCommand(), this.panelAPICarte, simulation);
			break;
		case "jouer":
			if(this.panelAPICarte.getParcours() != null)
				if(this.panelAPICarte.getParcours().getListePoints().size()>1)
					if(this.simulation == null)
						jouer();
					else if(this.simulation.getEtat() == EtatSimu.ARRET)
						jouer();
			System.out.println(dataTauxErreur);
			break;
		case "pause":
			if(this.simulation != null)
				if(this.simulation.getEtat() != EtatSimu.PAUSE)
					this.simulation.setEtat(EtatSimu.PAUSE);
			break;
		case "lecture":
			if(this.simulation != null)
				if(this.simulation.getEtat() != EtatSimu.LECTURE)
					this.simulation.setEtat(EtatSimu.LECTURE);
			break;
		case "arret":
			if(this.simulation != null)
				if(this.simulation.getEtat() != EtatSimu.ARRET)					
				{
					this.simulation.setEtat(EtatSimu.ARRET);
					jouer();
					this.simulation.setEtat(EtatSimu.ARRET);
				}

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
