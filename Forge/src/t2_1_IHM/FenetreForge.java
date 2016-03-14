package t2_1_IHM;

import javax.swing.JFrame;
import java.awt.Dimension;

public class FenetreForge extends JFrame {

	public static FenetreForge fenetreForge;

	public FenetreForge() {

		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();	
		int width  = (int)dimension.getWidth();
		
		height = (int) (height - (height*0.1));
		width = (int) (width - (width*0.1));
		
		this.setTitle("Forge");
		this.setSize(width,height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
