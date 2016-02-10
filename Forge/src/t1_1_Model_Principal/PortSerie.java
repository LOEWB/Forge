package t1_1_Model_Principal;

import java.util.Enumeration;
import gnu.io.CommPortIdentifier;

public class PortSerie {
private Enumeration ports;

public PortSerie() {
	this.ports = CommPortIdentifier.getPortIdentifiers();

	int i = 1;
	while (ports.hasMoreElements())
	{
	CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
	System.out.println("Port n°"+i++);
	System.out.println("\tNom\t:\t"+port.getName());
	String type = null;
	if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) type = "Serie";
	else type = "Parallèle";
	System.out.println("\tType\t:\t"+type);
	String etat = null;
	if (port.isCurrentlyOwned()) etat = "Possédé par "+port.getCurrentOwner();
	else etat = "Libre";
	System.out.println("\tEtat\t:\t"+etat+"\n");
	}
	
}

public void envoyer(String contenu) {
	
}


}
