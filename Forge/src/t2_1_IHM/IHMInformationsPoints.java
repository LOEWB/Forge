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
	private JFormattedTextField passage = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JFormattedTextField altitude = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JLabel jlpassage = new JLabel("Passage (h) :");
	private JLabel jlaltitude = new JLabel("altitude (m) :");
	private Point point;
	private Coordinate coordonnee;	
	private String type;
	private PanelAPICarte panelAPICarte;
	private String dataAltitude;
	private String dataHeure;





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
				dataAltitude = Double.toString(pointListe.getAltitude());
				dataHeure = Double.toString(pointListe.getTemps());
			}		
		}
		passage.setText(dataAltitude);
		altitude.setText(dataHeure);
		this.type = "Modifier/Supprimer";		
		this.setVisible(true);


		passage.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
				panelAPICarte.changePoint(Double.parseDouble(dataHeure), point, Double.parseDouble(dataAltitude));	

			}

			private void Data()
			{
				dataHeure = passage.getText();
			}
		});

		altitude.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
				panelAPICarte.changePoint(Double.parseDouble(dataHeure), point, Double.parseDouble(dataAltitude));

			}

			private void Data()
			{
				dataAltitude = altitude.getText();
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
		
		this.addGridBagLayout(passage, altitude);

		// panel ajouté à la fenetre au debut avec getContentPane()		

	}

	private void addGridBagLayout(JFormattedTextField passage, JFormattedTextField altitude) 
	{

		// création du layout (GridBagLayout)

		passage.setPreferredSize(new Dimension(70,30));
		altitude.setPreferredSize(new Dimension(70,30));
		boutonSupprimerCreerPoint.setPreferredSize(new Dimension(95,30));
		boutonSortir.setPreferredSize(new Dimension(95,30));
		jlpassage.setPreferredSize(new Dimension(100,30));
		jlaltitude.setPreferredSize(new Dimension(100,30));


		JPanel cell1 = new JPanel();
		cell1.add(passage);
		cell1.setPreferredSize(new Dimension(110,40));
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
		gbc.gridx = 1;
		this.panel.add(cell3, gbc);


		this.passage.setText(null);
		this.altitude.setText(null);

	}

	private void checkTypeSysteme() 
	{
		switch(this.panelAPICarte.getParcours().getTypeSysteme())
		{
		case TERRESTRE:
			this.jlaltitude.setVisible(false);
			this.altitude.setVisible(false);
			this.altitude.setValue((long)0);
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
				if(this.passage.getText().equals("") || this.altitude.getText().equals(""))
				{
					JOptionPane.showMessageDialog(this,"Veuillez renseigner les champs demandés");
				}
				else
				{
					this.panelAPICarte.removeSegments();
					panelAPICarte.removeAllMapMarkers();
					if(this.passage.getValue() instanceof Double)
					{
						panelAPICarte.addPoint((double)this.passage.getValue(), coordonnee, (double)this.altitude.getValue());
					}
					else
					{
						panelAPICarte.addPoint((double)((long) this.passage.getValue()), coordonnee, (double)((long)this.altitude.getValue()));
					}
					panelAPICarte.createMarker();	
					panelAPICarte.traceSegments();			
					this.dispose();
				}
			}
			else
			{
				this.panelAPICarte.removeSegments();
				panelAPICarte.removeAllMapMarkers();
				this.panelAPICarte.deletePoint(this.point);
				this.panelAPICarte.removeMarker(this.point);
				panelAPICarte.createMarker();	
				panelAPICarte.traceSegments();			
				JOptionPane.showMessageDialog(this,"Le point a été supprimé");
				this.dispose();
			}
		}
		else
		{
			
			this.dispose();		
			
			
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


