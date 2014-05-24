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

    @Override
    public void run() {
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
