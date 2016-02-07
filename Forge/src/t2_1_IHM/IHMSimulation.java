package t2_1_IHM;


import java.awt.BorderLayout;
import java.awt.Color; 
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.DateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

public class IHMSimulation {
	
    private JButton bcharger = new JButton("Charger");

    private JButton bimporter = new JButton("Importer");

    private JButton bexporter = new JButton("Exporter");
    
    private JButton bjouer = new JButton("JOUER");
    
    private JFormattedTextField date = new JFormattedTextField(DateFormat.getDateInstance());
	
    private JFormattedTextField date2 = new JFormattedTextField(DateFormat.getDateInstance());

    private JFormattedTextField date3 = new JFormattedTextField(DateFormat.getDateInstance());
    
    private JFormattedTextField date4 = new JFormattedTextField(DateFormat.getDateInstance());
    
    private JFormattedTextField date5 = new JFormattedTextField(DateFormat.getDateInstance());
    
    private JFormattedTextField date6 = new JFormattedTextField(DateFormat.getDateInstance());

	public IHMSimulation() {
		
		
		
		 JMapViewer api = new JMapViewer();		
		 JPanel informations = new JPanel();
         JPanel departArriveePlage = new JPanel();
         JPanel jouer = new JPanel();
         JPanel bas = new JPanel();
         JPanel boutons = new JPanel();
         JPanel carte = new JPanel();      
         JPanel depart = new JPanel();
         JPanel arrivee = new JPanel();
         JPanel plage = new JPanel();
         JSlider vitesse = new JSlider(); 
         

         
         JSplitPane splitMilieuGauche = new JSplitPane(JSplitPane.VERTICAL_SPLIT, boutons, departArriveePlage);
         splitMilieuGauche.setDividerLocation(200);
         splitMilieuGauche.setDividerSize(0);
         
         JSplitPane splitBasGauche = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitMilieuGauche, jouer);
         splitBasGauche.setDividerLocation(600);
         splitBasGauche.setDividerSize(0);
         
         JSplitPane splitMilieuDroit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, informations, carte);
         splitMilieuDroit.setDividerLocation(200);
         splitMilieuDroit.setDividerSize(0);
         
         JSplitPane splitBasDroit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitMilieuDroit, bas);
         splitBasDroit.setDividerLocation(680);
         splitBasDroit.setDividerSize(0);

         JSplitPane splitForge = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitBasGauche,splitBasDroit);
         splitForge.setDividerLocation(400);
         splitForge.setDividerSize(0);         
         
         splitForge.setContinuousLayout(true);
         splitMilieuGauche.setContinuousLayout(true);
         splitBasGauche.setContinuousLayout(true);
         splitMilieuDroit.setContinuousLayout(true);
         splitBasDroit.setContinuousLayout(true);         
         
         
         bcharger.setBackground(Color.WHITE);
         bimporter.setBackground(Color.WHITE);
         bexporter.setBackground(Color.WHITE);
         bjouer.setBackground(Color.WHITE);
         vitesse.setBackground(Color.WHITE);
         api.setBackground(Color.WHITE);
         
         informations.setBackground(Color.WHITE);
         departArriveePlage.setBackground(Color.WHITE);
         jouer.setBackground(Color.WHITE);
         bas.setBackground(Color.WHITE);
         boutons.setBackground(Color.WHITE);
         carte.setBackground(Color.WHITE);
         depart.setBackground(Color.WHITE);
         arrivee.setBackground(Color.WHITE);
         plage.setBackground(Color.WHITE);
         
         
         bcharger.setPreferredSize(new Dimension(150, 40));
         bimporter.setPreferredSize(new Dimension(150, 40));
         bexporter.setPreferredSize(new Dimension(150, 40));
         bjouer.setPreferredSize(new Dimension(150, 40));
         date.setPreferredSize(new Dimension(150, 30));
         date2.setPreferredSize(new Dimension(150, 30));
         date3.setPreferredSize(new Dimension(150, 30));
         date4.setPreferredSize(new Dimension(150, 30));
         date5.setPreferredSize(new Dimension(150, 30));
         date6.setPreferredSize(new Dimension(150, 30));
         api.setPreferredSize(new Dimension(775,480));
         
         
         boutons.setLayout(new GridBagLayout());
         GridBagConstraints gbcBoutons = new GridBagConstraints();     
         gbcBoutons.insets = new Insets(1,1,1,1);
         gbcBoutons.gridx = 0;       
         gbcBoutons.gridy = 0;
         boutons.add(bcharger, gbcBoutons);              
         gbcBoutons.gridy = 2;
         boutons.add(bimporter, gbcBoutons);         
         gbcBoutons.gridy = 3;
         boutons.add(bexporter, gbcBoutons);          

         
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
         depart.add(date, gbcDepart);         
         gbcDepart.gridx = 2; 
         depart.add(date2, gbcDepart);
         
         arrivee.setLayout(new GridBagLayout());
         GridBagConstraints gbcArrivee = new GridBagConstraints();             
         gbcArrivee.insets = new Insets(4,4,4,4);
         gbcArrivee.gridx = 0;       
         gbcArrivee.gridy = 0;
         arrivee.add(date3, gbcArrivee);                
         gbcArrivee.gridx = 2; 
         arrivee.add(date4, gbcArrivee);
         
         plage.setLayout(new GridBagLayout());
         GridBagConstraints gbcPlage = new GridBagConstraints();      
         gbcPlage.insets = new Insets(4,4,4,4);
         gbcPlage.gridx = 0;       
         gbcPlage.gridy = 0;
         plage.add(date5, gbcPlage);         
         gbcPlage.gridx = 2; 
         plage.add(date6, gbcPlage);

         
         
         jouer.setLayout(new GridBagLayout());
         GridBagConstraints gbcJouer = new GridBagConstraints();  
         gbcJouer.gridx = 1;
         gbcJouer.gridy = 1;     
         jouer.add(bjouer);
         
         
                 
         vitesse.setMaximum(3);
         vitesse.setMinimum(0);
         vitesse.setValue(1);
         vitesse.setPaintTicks(true);
         vitesse.setPaintLabels(true);
         vitesse.setMinorTickSpacing(0);
         vitesse.setMajorTickSpacing(1);
         bas.add(vitesse);
         
         
         carte.add(api); 
         
//         informations.setLayout(new GridBagLayout());
//         GridBagConstraints gbcInformations = new GridBagConstraints();
//             
//         gbcInformations.insets = new Insets(4,4,4,4);
//         gbcInformations.gridx = 0;       
//         gbcInformations.gridy = 0;
//         informations.add(date, gbcInformations);         
//         
//         gbcInformations.gridx = 2; 
//         gbcInformations.gridy = 2;
//         informations.add(date2, gbcInformations);         
//
//         gbcInformations.gridx = 3; 
//         gbcInformations.gridy = 3;
//         informations.add(date3, gbcInformations);
         informations.setBorder(BorderFactory.createTitledBorder("Informations"));
         

         bas.setPreferredSize(new Dimension(800,120));
         bas.setMaximumSize(new Dimension(800,120));
         bas.setMinimumSize(new Dimension(200,30));         
         jouer.setPreferredSize(new Dimension(400,200));
         carte.setPreferredSize(new Dimension(800,480));
         boutons.setPreferredSize(new Dimension(400,200));
         informations.setPreferredSize(new Dimension(800,200));
         departArriveePlage.setPreferredSize(new Dimension(400,200));
         

        
         
         
         FenetreForge.fenetreForge.getContentPane().add(splitForge);
		 FenetreForge.fenetreForge.setVisible(true);
		 //FenetreForge.fenetreForge.pack();
         

		
	}

	
	
	
	

}
