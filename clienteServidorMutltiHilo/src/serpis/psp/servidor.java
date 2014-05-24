package serpis.psp;

import java.io.*;
import java.net.*;


public class servidor {

	 static class servidorHilo extends Thread {
		 
		/*
		 * En el servidor haremos exactamente lo mismo que en el cliente creando variables similares.
		 */

	    private Socket sc;
	    private DataOutputStream enviado;
	    private DataInputStream recivido;
	    private int sesion;

	    public servidorHilo(Socket socket, int id) {
	        this.sc = socket;
	        this.sesion = id;
	        try {
	            enviado = new DataOutputStream(socket.getOutputStream());
	            recivido = new DataInputStream(socket.getInputStream());
	        } catch (IOException ex) {
	            System.out.println(ex);
	        }
	    }

	    @Override
	    public void run() {
	        String accion = "";
	        try {
	            accion = recivido.readUTF();
	            if(accion.equals("hola")){
	                System.out.println("El cliente con sesion "+this.sesion+" se conectó con exito!!!");
	                enviado.writeUTF("Conexion Finalizada");
	            }

	        } catch (IOException ex) {
	        	System.out.println(ex);
	        }
	        /*
	         * Tambien tenemos el metodo Run de la clase Thread. Este metodo se encarga de contestar al
	         * cliente el mensage de conexion.
	         */



	        try {
	            sc.close();
	        } catch (IOException ex) {
	        	System.out.println(ex);
	        }
	        /*
	         * Cerramos conexion.
	         */
	    }
	

    public static void main(String args[]) throws IOException {

        ServerSocket ss;
        try {
            ss = new ServerSocket(5432);
            System.out.print("Servidor Iniciado");
            int sesion = 0;
            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Nueva conexión "+socket + " establecida");
                ((servidorHilo) new servidorHilo(socket, sesion)).start();
                sesion++;
            }

        } catch (IOException ex) {
        	System.out.println(ex);
        }
      }
   }
}