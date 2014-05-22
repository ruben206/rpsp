package Serpis.psp;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;

public class cliente {

    public static void main(String[] args) {
    	protected Socket sc;
        protected DataOutputStream enviado;
        protected DataInputStream recivido;
        private int id;
        
    	sc = new Socket("localhost", 5432);
        enviado = new DataOutputStream(sc.getOutputStream());
        recivido = new DataInputStream(sc.getInputStream());

        System.out.println("Cliente nº" + id + " comprueba la conexión");
        enviado.writeUTF("Hola servidor");
        String respuesta = recivido.readUTF();
        System.out.println(id + " respuesta del servidor: " + respuesta);
        recivido.close();
        enviado.close();
        sc.close();

        ArrayList<Thread> clients = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            clients.add(new cliente(i));
        }
        for (Thread thread : clients) {
            thread.start();
        }
    }
}