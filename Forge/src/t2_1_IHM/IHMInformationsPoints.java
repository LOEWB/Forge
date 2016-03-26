package t2_1_IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DateFormat;
import java.text.NumberFormat;

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


public class IHMInformationsPoints extends JFrame implements ActionListener{

	private JButton boutonSupprimerCreerPoint;
	private JPanel panel;
	private JFormattedTextField passage = new JFormattedTextField(NumberFormat.getInstance());
	private JFormattedTextField altitude = new JFormattedTextField(NumberFormat.getIntegerInstance());
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
		boutonSupprimerCreerPoint.setLabel("Supprimer");
		this.point = modifierPoint;
		this.type = "Modifier/Supprimer";		
		this.setVisible(true);


		passage.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				Data();
				IHMInformationsPoints.panelAPICarte.changePoint(Double.parseDouble(dataHeure), modifierPoint, Double.parseDouble(dataAltitude));
			}
			public void removeUpdate(DocumentEvent e) {
				Data();
				IHMInformationsPoints.panelAPICarte.changePoint(Double.parseDouble(dataHeure), modifierPoint, Double.parseDouble(dataAltitude));
			}
			public void insertUpdate(DocumentEvent e) {
				Data();
				IHMInformationsPoints.panelAPICarte.changePoint(Double.parseDouble(dataHeure), modifierPoint, Double.parseDouble(dataAltitude));
			}

			private void Data()
			{
				IHMInformationsPoints.dataHeure = passage.getText();
			}
		});
		
		altitude.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				Data();
				IHMInformationsPoints.panelAPICarte.changePoint(Double.parseDouble(dataHeure), modifierPoint, Double.parseDouble(dataAltitude));
			}
			public void removeUpdate(DocumentEvent e) {
				Data();
				IHMInformationsPoints.panelAPICarte.changePoint(Double.parseDouble(dataHeure), modifierPoint, Double.parseDouble(dataAltitude));
			}
			public void insertUpdate(DocumentEvent e) {
				Data();
				IHMInformationsPoints.panelAPICarte.changePoint(Double.parseDouble(dataHeure), modifierPoint, Double.parseDouble(dataAltitude));
			}

			private void Data()
			{
				IHMInformationsPoints.dataAltitude = altitude.getText();
			}
		});




	}

	public IHMInformationsPoints(Coordinate creationPoint, PanelAPICarte panelAPICarte)
	{
		initializeWindow();
		this.setTitle("Création du point");
		this.panel = (JPanel) this.getContentPane();
		addComponentsWindow();
		boutonSupprimerCreerPoint.setLabel("Créer");
		this.coordonnee = creationPoint;
		this.panelAPICarte = panelAPICarte;
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
		this.boutonSupprimerCreerPoint = new JButton("");
		this.boutonSupprimerCreerPoint.addActionListener(this);

		this.addGridBagLayout(passage, altitude);

		// panel ajouté à la fenetre au debut avec getContentPane()		

	}

	private void addGridBagLayout(JFormattedTextField passage, JFormattedTextField altitude) 
	{
		// création du layout (GridBagLayout)

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


		if(this.type.equals("Créer"))
		{
			if(this.passage.getText().equals("") || this.altitude.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Veuillez renseigner les champs demandés");
			}
			else
			{
				IHMInformationsPoints.panelAPICarte.addPoint(Double.parseDouble(this.passage.getText()), coordonnee, Double.parseDouble(this.altitude.getText()));
				this.dispose();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"");			
		}
		
		//affiche parcours
		for(Point i:this.panelAPICarte.getParcours().getListePoints())
		{
			System.out.println("Point");
			System.out.println(i);			
		}
		System.out.println("\n\n\n");

	}




}


