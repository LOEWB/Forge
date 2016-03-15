package t2_1_IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

import t1_1_Model_Principal.Coordonnees;
import t1_1_Model_Principal.Parcours;
import t1_1_Model_Principal.Point;
import t1_1_Model_Principal.Simulation;
import t1_1_Model_Principal.TypeSysteme;

public class IHMSimulation implements ActionListener {

    private JButton bcharger = new JButton("Charger");

    private JButton bimporter = new JButton("Importer");

    private JButton bexporter = new JButton("Exporter");
        
    private JButton bjouer = new JButton("JOUER");
    
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
    
    private JFormattedTextField date = new JFormattedTextField(DateFormat.getDateInstance());
	
    private JFormattedTextField date2 = new JFormattedTextField(DateFormat.getDateInstance());

    private JFormattedTextField date3 = new JFormattedTextField(DateFormat.getDateInstance());
    
    private JFormattedTextField date4 = new JFormattedTextField(DateFormat.getDateInstance());
    
    private JFormattedTextField date5 = new JFormattedTextField(DateFormat.getDateInstance());
    
    private JFormattedTextField date6 = new JFormattedTextField(DateFormat.getDateInstance());
    
    private String[] listeLiaisonSerie = { "Liaison série 1", "Liaison série 2", "Liaison série 3" };
    
    private JComboBox<String> comboBoxliaisonSerie = new JComboBox<String>(listeLiaisonSerie);
    
    private String[] listeDebit = { "Débit : 1200 bit/s","Débit : 2400 bit/s","Débit : 3600 bit/s" };
    
    private JComboBox<String> comboBoxDebit = new JComboBox<String>(listeDebit);

	public IHMSimulation() {

		JMapViewer api = new JMapViewer();	
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
        splitForge.setDividerLocation((int)(FenetreForge.height*0.35));
        splitForge.setDividerSize(0);         
        
        splitForge.setContinuousLayout(true);
        splitMilieuGauche.setContinuousLayout(true);
        splitBasGauche.setContinuousLayout(true);
        splitMilieuDroit.setContinuousLayout(true);
        splitBasDroit.setContinuousLayout(true);         
        
        
        bcharger.setBackground(Color.WHITE);
        bimporter.setBackground(Color.WHITE);
        bexporter.setBackground(Color.WHITE);
        comboBoxliaisonSerie.setBackground(Color.WHITE);
        comboBoxDebit.setBackground(Color.WHITE);
        bjouer.setBackground(Color.WHITE);
        bMenu.setBackground(Color.WHITE);
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
        
        api.setPreferredSize(new Dimension((int)(FenetreForge.height*1.42), (int)(FenetreForge.height*0.7)));
        bcharger.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27), (int)(FenetreForge.height*0.30/5)));
        bimporter.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27), (int)(FenetreForge.height*0.30/5)));
        bexporter.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27), (int)(FenetreForge.height*0.30/5)));
        comboBoxliaisonSerie.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27/2), (int)(FenetreForge.height*0.30/5)));
        comboBoxDebit.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27/2), (int)(FenetreForge.height*0.30/5)));
        bjouer.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27), (int)(FenetreForge.height*0.20/2)));
        bMenu.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27), (int)(FenetreForge.height*0.20/2)));
        date.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27)/2, 30));
        date2.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27/2), 30));
        date3.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27/2), 30));
        date4.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27/2), 30));
        date5.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27/2), 30));
        date6.setPreferredSize(new Dimension((int)(FenetreForge.height*0.27/2), 30));
        vitActuelle.setPreferredSize(new Dimension(150, 30));
        vitMoyenne.setPreferredSize(new Dimension(150, 30));
        heureDepart.setPreferredSize(new Dimension(150, 30));
        heureArrivee.setPreferredSize(new Dimension(150, 30));
        heureActuelle.setPreferredSize(new Dimension(150, 30));
        dateActuelle.setPreferredSize(new Dimension(150, 30));
        vitActuelleDisplay.setPreferredSize(new Dimension(150, 30));
        vitMoyenneDisplay.setPreferredSize(new Dimension(150, 30));
        heureDepartDisplay.setPreferredSize(new Dimension(150, 30));
        heureArriveeDisplay.setPreferredSize(new Dimension(150, 30));
        heureActuelleDisplay.setPreferredSize(new Dimension(150, 30));
        dateActuelleDisplay.setPreferredSize(new Dimension(150, 30));
        
        
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
        depart.setBorder(BorderFactory.createTitledBorder("Départ"));
        arrivee.setBorder(BorderFactory.createTitledBorder("Arrivée"));
        plage.setBorder(BorderFactory.createTitledBorder("Plage Horaire relatif de la simulation (en heure)"));
        
        depart.setLayout(new GridBagLayout());
        GridBagConstraints gbcDepart = new GridBagConstraints();             
        gbcDepart.insets = new Insets(4,4,4,4);
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
        jouerMenu.add(bjouer,gbcJouerMenu);
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
        
        informations.setBorder(BorderFactory.createTitledBorder("Informations"));
        vitActuelleDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
        vitMoyenneDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
        heureDepartDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
        heureArriveeDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
        heureActuelleDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
        dateActuelleDisplay.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
        
     
        
        FenetreForge.fenetreForge.getContentPane().removeAll();
        FenetreForge.fenetreForge.getContentPane().add(splitForge);
		 FenetreForge.fenetreForge.setVisible(true);
		 //FenetreForge.fenetreForge.pack();
        

		 this.bMenu.addActionListener(new ActionListener()
        {

			@Override
			public void actionPerformed(ActionEvent e) 
			{				
				FenetreForge.fenetreForge.dispose();
				new IHMMenuPrincipal();				
			}
			 
        });

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// test :
		Point valence = new Point(0, new Coordonnees(44.933014, 4.890892), 0);
		Point saintay = new Point(3600, new Coordonnees(45.446958, 4.383396), 0);
		Parcours parcours = new Parcours(TypeSysteme.TERRESTRE);

		switch (e.getActionCommand()) {
		case "charger":
			new IHMChoixFichier(e.getActionCommand(), parcours);
			break;
		case "importer":
		case "exporter":
			Simulation simulation = new Simulation(parcours);
			new IHMChoixFichier(e.getActionCommand(), parcours, simulation);
			break;
		default:
			// les autres boutons
			break;
		}

	}

}
