package t2_1_IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import t1_1_Model_Principal.Parcours;
import t1_1_Model_Principal.Point;
import t1_1_Model_Principal.PointComp;


public class IHMInformationsPoints extends JFrame implements ActionListener{

	private JButton boutonSupprimerCreerPoint = new JButton("");;
	private JButton boutonSortir = new JButton("");
	private JPanel panel;
	private JFormattedTextField passageHeure = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JFormattedTextField passageMinute = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JFormattedTextField altitude = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JLabel jlpassage = new JLabel("Passage (h/m) :");
	private JLabel jlaltitude = new JLabel("altitude (m) :");
	private Point point;
	private Coordinate coordonnee;	
	private String type;
	private PanelAPICarte panelAPICarte;
	private double dataAltitude;
	private double dataHeure;
	private double dataMinute;





	public IHMInformationsPoints(Point modifierPoint, PanelAPICarte panelAPICarte)
	{
		initializeWindow();
		this.setTitle("Modifier le point");
		this.panel = (JPanel) this.getContentPane();
		this.point = modifierPoint;
		this.panelAPICarte = panelAPICarte;
		addComponentsWindow();
		checkTypeSysteme();
		boutonSupprimerCreerPoint.setText("Supprimer");
		for (Point pointListe : this.panelAPICarte.getParcours().getListePoints())
		{
			if(pointListe == point)
			{
				dataAltitude = pointListe.getAltitude();
				dataHeure = pointListe.getTemps()/3600;
			}		
		}
		passageHeure.setValue(dataHeure);
		altitude.setValue(dataAltitude);
		this.type = "Modifier/Supprimer";		
		this.setVisible(true);


		passageHeure.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
				panelAPICarte.removeSegments();
				panelAPICarte.removeAllMapMarkers();
				panelAPICarte.changePoint((dataHeure+((double)passageMinute.getValue()/60))*3600, point, (double)altitude.getValue());
				panelAPICarte.createMarkers();	
				panelAPICarte.traceSegments();	
			}

			private void Data()
			{
				if(passageHeure.getValue() instanceof Double)
				{
					dataHeure = (double) passageHeure.getValue();
				}
				else
				{
					dataHeure = (double)((long) passageHeure.getValue());
				}
			}
		});
		
		passageMinute.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
				panelAPICarte.removeSegments();
				panelAPICarte.removeAllMapMarkers();
				panelAPICarte.changePoint(((double)passageHeure.getValue()+(dataMinute/60))*3600, point, (double)altitude.getValue());
				panelAPICarte.createMarkers();	
				panelAPICarte.traceSegments();	
			}

			private void Data()
			{
				if(passageMinute.getValue() instanceof Double)
				{
					dataMinute = (double) passageMinute.getValue();
				}
				else
				{
					dataMinute = (double)((long) passageMinute.getValue());
				}
			}
		});

		altitude.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
				panelAPICarte.removeSegments();
				panelAPICarte.removeAllMapMarkers();
				panelAPICarte.changePoint(((double)passageHeure.getValue()+((double)passageMinute.getValue()/60))*3600, point,dataAltitude);
				panelAPICarte.createMarkers();	
				panelAPICarte.traceSegments();	

			}

			private void Data()
			{
				if(altitude.getValue() instanceof Double)
				{
					dataAltitude = (double) altitude.getValue();
				}
				else
				{
					dataAltitude = (double)((long) altitude.getValue());
				}
			}
		});




	}

	public IHMInformationsPoints(Coordinate creationPoint, PanelAPICarte panelAPICarte)
	{
		initializeWindow();
		this.setTitle("Création du point");
		this.panel = (JPanel) this.getContentPane();
		this.coordonnee = creationPoint;
		this.panelAPICarte = panelAPICarte;
		addComponentsWindow();
		checkTypeSysteme();
		boutonSupprimerCreerPoint.setText("Créer");
		this.type = "Créer";
		this.setVisible(true);
	}


	private void initializeWindow() 
	{
		this.setSize(350, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void addComponentsWindow() 
	{

		// creating bouton 'supprimer'
		this.boutonSupprimerCreerPoint.addActionListener(this);
		boutonSortir.setText("Sortir");
		this.boutonSortir.addActionListener(this);

		this.boutonSupprimerCreerPoint.setActionCommand("Création/Suppression");
		this.boutonSortir.setActionCommand("Sortir");

		this.addGridBagLayout(passageHeure, passageMinute, altitude);

		// panel ajouté à la fenetre au debut avec getContentPane()		

	}

	private void addGridBagLayout(JFormattedTextField passageHeure, JFormattedTextField passageMinute, JFormattedTextField altitude) 
	{

		// création du layout (GridBagLayout)

		passageHeure.setPreferredSize(new Dimension(70,30));
		passageMinute.setPreferredSize(new Dimension(70,30));
		altitude.setPreferredSize(new Dimension(70,30));
		boutonSupprimerCreerPoint.setPreferredSize(new Dimension(95,30));
		boutonSortir.setPreferredSize(new Dimension(95,30));
		jlpassage.setPreferredSize(new Dimension(100,30));
		jlaltitude.setPreferredSize(new Dimension(100,30));


		JPanel cell1 = new JPanel();
		cell1.add(passageHeure);
		cell1.setPreferredSize(new Dimension(110,40));
		JPanel cell12 = new JPanel();
		cell12.add(passageMinute);
		cell12.setPreferredSize(new Dimension(110,40));
		JPanel cell2 = new JPanel();
		cell2.add(altitude);
		cell2.setPreferredSize(new Dimension(110,40));
		JPanel cell3 = new JPanel();
		cell3.add(this.boutonSupprimerCreerPoint);
		cell3.setPreferredSize(new Dimension(110,40));
		JPanel cell4 = new JPanel();
		cell4.add(this.boutonSortir);
		cell4.setPreferredSize(new Dimension(110,40));
		JPanel cell5 = new JPanel();
		cell5.add(this.jlpassage);
		cell5.setPreferredSize(new Dimension(110,40));
		JPanel cell6 = new JPanel();
		cell6.add(this.jlaltitude);
		cell6.setPreferredSize(new Dimension(110,40));

		this.panel.setPreferredSize(new Dimension(350, 200));
		this.panel.setLayout(new GridBagLayout());

		// application du layout
		GridBagConstraints gbc = new GridBagConstraints();

		// ---------------------------------------------

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		this.panel.add(cell5, gbc);
		// ---------------------------------------------

		gbc.gridx = 1;
		this.panel.add(cell1, gbc);
		// ---------------------------------------------
		
		gbc.gridx = 2;
		this.panel.add(cell12, gbc);
		// ---------------------------------------------

		gbc.gridx = 0;
		gbc.gridy = 1;
		this.panel.add(cell6, gbc);
		// ---------------------------------------------

		gbc.gridx = 1;
		this.panel.add(cell2, gbc);
		// ---------------------------------------------

		gbc.gridx = 0;
		gbc.gridy = 3;
		this.panel.add(cell4, gbc);
		gbc.gridx = 2;
		this.panel.add(cell3, gbc);


		this.passageHeure.setValue(0.0);
		this.passageMinute.setValue(0.0);
		this.altitude.setValue(0.0);

	}

	private void checkTypeSysteme() 
	{
		switch(this.panelAPICarte.getParcours().getTypeSysteme())
		{
		case TERRESTRE:
			this.jlaltitude.setVisible(false);
			this.altitude.setVisible(false);
			this.altitude.setValue(0l);
			break;
		case AERIEN:
		default:
			break;
		}
	}





	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Création/Suppression"))
		{
			if(this.type.equals("Créer"))
			{
				if(this.passageHeure.getText().equals("") || this.altitude.getText().equals(""))
				{
					JOptionPane.showMessageDialog(this,"Veuillez renseigner les champs demandés");
				}
				else
				{
					this.panelAPICarte.removeSegments();
					panelAPICarte.removeAllMapMarkers();
					if(this.passageHeure.getValue() instanceof Double && this.altitude.getValue() instanceof Double)
					{
						if(this.passageMinute.getValue() instanceof Double)
							panelAPICarte.addPoint((((double)this.passageHeure.getValue())+((double)this.passageMinute.getValue()/60))*3600, coordonnee, (double)this.altitude.getValue());
						else
							panelAPICarte.addPoint((((double)this.passageHeure.getValue())+(((double)((long)this.passageHeure.getValue())/60)))*3600, coordonnee, (double)this.altitude.getValue());
					}
					else if(this.passageHeure.getValue() instanceof Double && this.altitude.getValue() instanceof Long)
					{
						if(this.passageMinute.getValue() instanceof Double)
							panelAPICarte.addPoint((((double)this.passageHeure.getValue())+((double)this.passageMinute.getValue()/60))*3600, coordonnee, (double)((long)this.altitude.getValue()));
						else
							panelAPICarte.addPoint((((double)this.passageHeure.getValue())+((double)((long)this.passageHeure.getValue())/60))*3600, coordonnee, (double)((long)this.altitude.getValue()));
					}
					else if(this.passageHeure.getValue() instanceof Long && this.altitude.getValue() instanceof Double)
					{
						if(this.passageMinute.getValue() instanceof Double)
							panelAPICarte.addPoint((((double)((long) this.passageHeure.getValue()))+((double)this.passageMinute.getValue()/60))*3600, coordonnee, (double)this.altitude.getValue());
						else
							panelAPICarte.addPoint((((double)((long) this.passageHeure.getValue()))+((double)((long)this.passageHeure.getValue())/60))*3600, coordonnee, (double)this.altitude.getValue());						
					}
					else
					{
						if(this.passageMinute.getValue() instanceof Double)
							panelAPICarte.addPoint((((double)((long) this.passageHeure.getValue()))+((double)this.passageMinute.getValue()/60))*3600, coordonnee, (double)((long)this.altitude.getValue()));
						else
							panelAPICarte.addPoint((((double)((long) this.passageHeure.getValue()))+((double)((long)this.passageHeure.getValue())/60))*3600, coordonnee, (double)((long)this.altitude.getValue()));						
					}
					panelAPICarte.createMarkers();	
					panelAPICarte.traceSegments();			
					dispose();
				}
			}
			else
			{
				panelAPICarte.removeSegments();
				panelAPICarte.removeAllMapMarkers();
				panelAPICarte.deletePoint(this.point);
				panelAPICarte.removeMarker(this.point);
				panelAPICarte.createMarkers();	
				panelAPICarte.traceSegments();			
				JOptionPane.showMessageDialog(this,"Le point a été supprimé");
				dispose();
			}
		}
		else
		{

			dispose();		


		}


		//affiche parcours
		for(Point i:panelAPICarte.getParcours().getListePoints())
		{
			System.out.println("Point");
			System.out.println(i);

		}
		System.out.println("\n\n\n");

	}




}


