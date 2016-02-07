package t2_1_IHM;

import java.awt.Color;

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
public class IHMMenuPrincipal {
	
	private JFrame window;

	public IHMMenuPrincipal(){
		this.window = new JFrame();
		
		this.initGraphInterface();
	}

	private void initGraphInterface(){
		this.window.setTitle("Menu Principal");
		this.window.setSize(475, 550);
		this.window.setLayout(null);
		this.window.setResizable(false);
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();		
		panel.setBounds(113, 50, 300, 550);
		panel.setLayout(null); // afin de fixer manuellement les coordonnées des composants
		
		ImageIcon logoForge = new ImageIcon("./img/logoForge.PNG"); // ne pas oublier de mettre à jour le Logo
		JLabel labelForge = new JLabel(logoForge);
		labelForge.setBounds(-45, 0, 350, 200); // adapter manuellement la taille a l'image
	    JButton boutonParcours = new JButton("Créer un parcours");
	    boutonParcours.setBounds(25, 220, 200, 35);
	    JButton boutonSimulation = new JButton("Lancer une simulation");
	    boutonSimulation.setBounds(25, 330, 200, 35);
	    
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
}