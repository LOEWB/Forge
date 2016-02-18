package t1_1_Model_Principal;

import java.io.*;
import javax.comm.*;
import java.util.*;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PortSerie
{
    private Enumeration ports = null;
	ArrayList<String> listePorts; 
    int debit;
    
    private OutputStream outStream = null;
    private SerialPort serialPort = null;
    private CommPort commPort;
    private CommPortIdentifier portIdentifier = null;

    
    public PortSerie() throws Exception{
    
        ports = CommPortIdentifier.getPortIdentifiers();
        
        while(ports.hasMoreElements())
        {
        	portIdentifier = (CommPortIdentifier)ports.nextElement();
            
            if (portIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL) listePorts.add(portIdentifier.getName());
        
        }
	}


public ArrayList<String> getListePorts() 
{
	return this.listePorts;
}

boolean setPort(String port, int Debit) throws PortInUseException, NoSuchPortException, UnsupportedCommOperationException {

	
	if(Debit == 0) this.debit = 2400;
	else this.debit = Debit;
	
    this.portIdentifier = CommPortIdentifier.getPortIdentifier(port);
    
    if (portIdentifier.isCurrentlyOwned() ) return false;
    
    this.commPort = portIdentifier.open("Forge Software on " + port,10000);
    
    if (!(commPort instanceof SerialPort)) return false;
    
    this.serialPort = (SerialPort) commPort;
    
    serialPort.setSerialPortParams(this.debit,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

	return true;
}

void envoyer(String donnees) throws IOException {
	
    OutputStream outstream = this.serialPort.getOutputStream();
    outstream.write(donnees.getBytes());

}
 
}