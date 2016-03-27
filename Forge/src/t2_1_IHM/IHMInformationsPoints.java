package t2_1_IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	private JButton boutonSupprimerCreerPoint;
	private JPanel panel;
	private JFormattedTextField passage = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JFormattedTextField altitude = new JFormattedTextField(NumberFormat.getNumberInstance());
	private JLabel jlpassage = new JLabel("Passage (h) :");
	private JLabel jlaltitude = new JLabel("altitude (m) :");
	private Point point;
	private Coordinate coordonnee;	
	private String type;
	static PanelAPICarte panelAPICarte;
	static String dataAltitude;
	static String dataHeure;




	public IHMInformationsPoints(Point modifierPoint)
	{
		initializeWindow();
		this.setTitle("Modifier le point");
		this.panel = (JPanel) this.getContentPane();
		addComponentsWindow();
		boutonSupprimerCreerPoint.setText("Supprimer");
		dataAltitude = Double.toString(panelAPICarte.getParcours().getListePoints().get(2).getAltitude());
		dataHeure = Double.toString(panelAPICarte.getParcours().getListePoints().get(2).getTemps());
		passage.setText(dataAltitude);
		altitude.setText(dataHeure);
		this.point = modifierPoint;
		this.type = "Modifier/Supprimer";		
		this.setVisible(true);


		passage.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
				panelAPICarte.changePoint(Double.parseDouble(dataHeure), modifierPoint, Double.parseDouble(dataAltitude));	

			}

			private void Data()
			{
				IHMInformationsPoints.dataHeure = passage.getText();
			}
		});

		altitude.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}
			public void removeUpdate(DocumentEvent e) {

			}
			public void insertUpdate(DocumentEvent e) {
				Data();
				panelAPICarte.changePoint(Double.parseDouble(dataHeure), modifierPoint, Double.parseDouble(dataAltitude));

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
		this.setTitle("Cr�ation du point");
		this.panel = (JPanel) this.getContentPane();
		addComponentsWindow();
		boutonSupprimerCreerPoint.setText("Cr�er");
		this.coordonnee = creationPoint;
		this.panelAPICarte = panelAPICarte;
		this.type = "Cr�er";
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
		this.boutonSupprimerCreerPoint = new JButton("");
		this.boutonSupprimerCreerPoint.addActionListener(this);

		this.addGridBagLayout(passage, altitude);

		// panel ajout� � la fenetre au debut avec getContentPane()		

	}

	private void addGridBagLayout(JFormattedTextField passage, JFormattedTextField altitude) 
	{
		// cr�ation du layout (GridBagLayout)

		passage.setPreferredSize(new Dimension(70,30));
		altitude.setPreferredSize(new Dimension(70,30));
		boutonSupprimerCreerPoint.setPreferredSize(new Dimension(110,30));
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
		cell4.add(this.jlpassage);
		cell4.setPreferredSize(new Dimension(110,40));
		JPanel cell5 = new JPanel();
		cell5.add(this.jlaltitude);
		cell5.setPreferredSize(new Dimension(110,40));

		this.panel.setPreferredSize(new Dimension(350, 200));
		this.panel.setLayout(new GridBagLayout());

		// application du layout
		GridBagConstraints gbc = new GridBagConstraints();

		// ---------------------------------------------

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		this.panel.add(cell4, gbc);
		// ---------------------------------------------

		gbc.gridx = 1;
		this.panel.add(cell1, gbc);
		// ---------------------------------------------

		gbc.gridx = 0;
		gbc.gridy = 1;
		this.panel.add(cell5, gbc);
		// ---------------------------------------------

		gbc.gridx = 1;
		this.panel.add(cell2, gbc);
		// ---------------------------------------------

		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.panel.add(cell3, gbc);

		this.passage.setText(null);
		this.altitude.setText(null);

	}





	@Override
	public void actionPerformed(ActionEvent e)
	{


		if(this.type.equals("Cr�er"))
		{
			if(this.passage.getText().equals("") || this.altitude.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Veuillez renseigner les champs demand�s");
			}
			else
			{
				this.panelAPICarte.removeSegments();
				if(this.passage.getValue() instanceof Double)
				{
					panelAPICarte.addPoint((double)this.passage.getValue(), coordonnee, (double)this.altitude.getValue());
				}
				else
				{
					panelAPICarte.addPoint((double)((long) this.passage.getValue()), coordonnee, (double)((long)this.altitude.getValue()));
				}

				Collections.sort(panelAPICarte.getParcours().getListePoints(),new PointComp());
				this.panelAPICarte.traceSegments();
				this.dispose();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"");			
		}

		//affiche parcours
		for(Point i:IHMInformationsPoints.panelAPICarte.getParcours().getListePoints())
		{
			System.out.println("Point");
			System.out.println(i);

		}
		System.out.println("\n\n\n");

	}




}


