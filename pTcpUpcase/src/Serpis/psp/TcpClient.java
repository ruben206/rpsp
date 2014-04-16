package Serpis.psp;

import java.io.*;
import java.net.*;

class tcpClient {
    public static void main( String args[] ) throws IOException {
    	String sentencia;
    	String modifiedSentence;
    	
    	BufferedReader inUser = new BufferedReader(new InputStreamReader(System.in));
    	
    	Socket clientSocket = new Socket("hostname", 12346);
    	
    	DataOutputStream outServer = new DataOutputStream(clientSocket.getOutputStream());
    	
    	BufferedReader inServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    	
    	sentencia = inUser.readLine();
    	
    	outServer.writeBytes(sentencia + '\n');
    	
    	modifiedSentence = inServer.readLine();
    	
    	System.out.println("hola Tcp" + sentencia);
    	
    	clientSocket.close();
        }
    }