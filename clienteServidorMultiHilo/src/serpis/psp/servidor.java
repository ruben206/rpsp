package serpis.psp;

import java.io.*;
import java.net.*;


public class servidor {

	 static class servidorHilo extends Thread {

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



	        try {
	            sc.close();
	        } catch (IOException ex) {
	        	System.out.println(ex);
	        }
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