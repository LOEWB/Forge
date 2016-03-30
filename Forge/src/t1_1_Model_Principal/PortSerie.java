package t1_1_Model_Principal;

import java.io.*; // IOException
import java.util.*; // Scanner
import jssc.*;

public class PortSerie {

    private static SerialPort serialPort;
    
    private String[] portNames = SerialPortList.getPortNames();
    ArrayList<String> listePorts;
  
	public ArrayList<String> getListePorts() {
        listePorts = new ArrayList<String>(Arrays.asList(portNames));
		return this.listePorts;
	}

	boolean setPort(String port, int Debit) {
    serialPort = new SerialPort(port);

try {
    serialPort.openPort();
	   serialPort.setParams(Integer.valueOf(Debit), // baudrate à mettre en dynamique après
               SerialPort.DATABITS_8,
               SerialPort.STOPBITS_1,
               SerialPort.PARITY_NONE);

serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
                        SerialPort.FLOWCONTROL_RTSCTS_OUT);

} catch (SerialPortException ex) {
    System.out.println("Error in writing data to port: " + ex);
}
		return true;
	}

	void envoyer(String donnees) throws IOException, SerialPortException {

        serialPort.writeString(donnees);

	}

}