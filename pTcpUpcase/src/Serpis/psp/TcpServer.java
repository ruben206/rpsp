package Serpis.psp;

import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

class tcpServidor {
	public static Date now = new Date();
	public static String sentenciaFinal;
	public static DateFormat df = DateFormat.getDateInstance();
	public static DateFormat hf = new SimpleDateFormat("HH:mm:ss");
	public static String dateNow = df.format(now);
	public static String hourNow = hf.format(now);
    public static void main( String args[] ) throws IOException {
    	ServerSocket socket = new ServerSocket(12347);
    	
    	while(true){
    		Socket connectionSocket = socket.accept();
    		
    		BufferedReader fromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
    		
    		DataOutputStream outClient = new DataOutputStream(connectionSocket.getOutputStream());
    		
    		dateNow = fromClient.readLine();
    		
    		sentenciaFinal = dateNow + hourNow;
    		outClient.writeBytes(sentenciaFinal);
    	}
    	
        }
    }