package t2_1_IHM;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.JPanel;

/**
 * Task managing the interface (creating, displaying...)
 * 
 * @author joris
 *
 */
public class IHMMenuPrincipal implements ActionListener {

	private JFrame window;

	public IHMMenuPrincipal() {
		this.window = new JFrame();

		this.initGraphInterface();
	}

	private void initGraphInterface() {
		this.window.setTitle("Menu Principal");
		this.window.setSize(400, 450);
		this.window.setLayout(null);
		this.window.setResizable(false);
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBounds(0, 50, 1000, 550);
		panel.setLayout(null); // afin de fixer manuellement les coordonnées des
								// composants

		ImageIcon logoForge = new ImageIcon("./img/logoForge.PNG");
		JLabel labelForge = new JLabel(logoForge);
		labelForge.setBounds(-300, 0, 1000, 200); // adapter manuellement la
												// taille a l'image
		JButton boutonParcours = new JButton("Créer un parcours");
		boutonParcours.setBounds(97, 220, 200, 35);
		boutonParcours.addActionListener(this);
		boutonParcours.setActionCommand("Parcours");
		JButton boutonSimulation = new JButton("Lancer une simulation");
		boutonSimulation.setBounds(97, 290, 200, 35);
		boutonSimulation.addActionListener(this);
		boutonSimulation.setActionCommand("Simulation");

		panel.add(labelForge);
		panel.add(boutonParcours);
		panel.add(boutonSimulation);

		boutonParcours.setBackground(Color.WHITE);
		boutonSimulation.setBackground(Color.WHITE);
		this.window.getContentPane().setBackground(Color.WHITE);
		panel.setBackground(Color.WHITE);

		this.window.add(panel);
		this.window.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "") {
			System.out.println("ah...");
			return;
		}
		FenetreForge.fenetreForge = new FenetreForge();
		if (e.getActionCommand().equals("Parcours"))
			new IHMChoixTypeSysteme();
		else
			new IHMSimulation();
		this.window.dispose();
	}
}