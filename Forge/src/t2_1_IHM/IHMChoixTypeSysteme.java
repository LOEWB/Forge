package t2_1_IHM;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

/**
 * IHM permettant � l'utilisateur de selectionner le type de parcours
 * 
 * @author joris
 *
 */
public class IHMChoixTypeSysteme extends JFrame implements ActionListener {

	private JButton boutonLancerParcours;
	private JPanel panel;
	private JRadioButton rboutonTerrestre = new JRadioButton("Terrestre");
	private JRadioButton rboutonAerien = new JRadioButton("Aerien");

	public IHMChoixTypeSysteme() {
		initializeWindow();
		this.panel = (JPanel) this.getContentPane();
		addComponentsWindow();
		this.setVisible(true);
	}

	/**
	 * initializes the default window settings
	 */
	private void initializeWindow() {
		this.setTitle("Choix du syst�me");
		this.setSize(350, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/**
	 * initialize the components
	 */
	private void addComponentsWindow() {
		// creation boutons radio
		ButtonGroup bg = new ButtonGroup();

		bg.add(rboutonTerrestre);
		bg.add(rboutonAerien);

		rboutonTerrestre.setSelected(true);

		// creating bouton 'cr�er'
		this.boutonLancerParcours = new JButton("Cr�er");
		this.boutonLancerParcours.addActionListener(this);

		this.addGridBagLayout(rboutonTerrestre, rboutonAerien);

		// panel ajout� � la fenetre au debut avec getContentPane()
	}

	private void addGridBagLayout(JRadioButton boutonTerrestre,
			JRadioButton boutonAerien) {
		// cr�ation du layout (GridBagLayout)
		JPanel cell1 = new JPanel();
		cell1.add(boutonTerrestre);
		cell1.setPreferredSize(new Dimension(90, 40));
		JPanel cell2 = new JPanel();
		cell2.add(boutonAerien);
		cell2.setPreferredSize(new Dimension(90, 40));
		JPanel cell3 = new JPanel();
		cell3.add(this.boutonLancerParcours);
		cell3.setPreferredSize(new Dimension(90, 40));

		this.panel.setPreferredSize(new Dimension(350, 200));
		this.panel.setLayout(new GridBagLayout());

		// application du layout
		GridBagConstraints gbc = new GridBagConstraints();

		// ---------------------------------------------
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		this.panel.add(cell1, gbc);
		// ---------------------------------------------
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 2;
		this.panel.add(cell2, gbc);
		// ---------------------------------------------
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		this.panel.add(cell3, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (rboutonTerrestre.isSelected())
			new IHMParcours("terrestre");
		else
			new IHMParcours("a�rien");
		this.dispose();
	}
}
