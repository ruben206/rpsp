package Serpis.psp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorWeb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int PUERTO = 5000;
		ServerSocket sc;
		Socket so;
		DataOutputStream salida;
		String mensajeRecibido;

			BufferedReader entrada;
			
			try{
				sc = new ServerSocket(PUERTO);
				so = new Socket();
				System.out.println("esperando conexión...");
				so = sc.accept();
				
				System.out.println("Un cliente esta conectado!");
				entrada = new BufferedReader(new InputStreamReader(so.getInputStream()));
				salida = new DataOutputStream(so.getOutputStream());
				salida.writeUTF("Mensaje de confirmacion!!");
				mensajeRecibido = entrada.readLine();
				System.out.println(mensajeRecibido);
				sc.close();

			}catch(Exception e ){
				System.out.println("Error: "+e.getMessage());
		}

	}

}
