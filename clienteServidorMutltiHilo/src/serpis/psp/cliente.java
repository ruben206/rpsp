package serpis.psp;

import java.io.*;
import java.net.Socket;
import java.util.*;



class Persona extends Thread {

    protected Socket sc;
    protected DataOutputStream enviado;
    protected DataInputStream recivido;
    private int id;

    public Persona(int id) {
        this.id = id;
    }
    
    /*Creamos una clase persona donde declararemos el socket de comunicacion y los DataImputStream y DataOutputstream
     * para la entrada y salida de mensages entre el cliente y servidor, tambien declararemos un id que se le asignara
     * a cada conexión.
	*/
    @Override
    public void run() {
    	/*En el metodo Run que desciende de Thread estableceremos los datos a las variables declaradas
    	 * (sc, enviado, recivido, respuesta(mensage del servidor)).
    	 * despues cerraremos el socket y los DataImput/OutputStream para que borren los datos despues de imprimirlos
    	 * pantalla.
    	 */
        try {
            sc = new Socket("localhost", 5432);
            enviado = new DataOutputStream(sc.getOutputStream());
            recivido = new DataInputStream(sc.getInputStream());

            System.out.println("El cliente " + id + " establece conexion");
            enviado.writeUTF("hola");
            String respuesta=recivido.readUTF();
            System.out.println(" Servidor contesta al cliente "+id+": " + respuesta);
            recivido.close();
            enviado.close();
            sc.close();
        } catch (IOException ex) {
        	System.out.println(ex);
        }
    }
}

public class cliente {
	/*
	 * En la clase cliente dentro del main crearemos un ArrayList de clientes
	 * donde guardaremos los clientes creados.
	 * tambien crearemos un bucle con el thread dnd estan ubicados los clientes.
	 * Para que se ejecute el metodo interno de la clase Persona.
	 */

    public static void main(String[] args) {
        ArrayList<Thread> clients = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            clients.add(new Persona(i));
        }
        for (Thread thread : clients) {
            thread.start();
        }
    }
}
