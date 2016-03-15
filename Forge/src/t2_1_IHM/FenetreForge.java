package t2_1_IHM;

import javax.swing.JFrame;
import java.awt.Dimension;

public class FenetreForge extends JFrame {

	public static FenetreForge fenetreForge;
	
	public static int height;
	
	public static int width;
	
	public FenetreForge() {

		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.height = (int)dimension.getHeight();	
		this.width  = (int)dimension.getWidth();
		
		this.height = (int) (this.height - (this.height*0.1));
		this.width = (int) (this.width - (this.width*0.1));
		
		this.setTitle("Forge");
		this.setSize(this.width,this.height);
	    this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
