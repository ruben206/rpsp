package Serpis.psp;

import java.io.*;
import java.net.Socket;

public class clienteWeb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String HOST = "localhost";
		final int PUERTO = 5000;
		Socket sc;
		DataOutputStream mensaje;
		DataInputStream entrada;
	
			try{
				sc = new Socket(HOST, PUERTO);
				mensaje = new DataOutputStream(sc.getOutputStream());
				mensaje.writeUTF("Hola al Servidor");
				sc.close();
			}catch(Exception e){
				System.out.println("Error: "+e.getMessage());
		}
	}

}
