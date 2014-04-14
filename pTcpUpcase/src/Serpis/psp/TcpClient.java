package Serpis.psp;

import java.awt.*;
import java.net.*;
import java.io.*;

class minimoCliente {
    public static void main( String args[] ) throws IOException {
        int c;
        Socket s = new Socket();
        InputStream sIn;

        try {
            s = new Socket( "breogan",4321 );
        } catch( IOException e ) {
            System.out.println( e );
            }

        sIn = s.getInputStream();
        while( ( c = sIn.read() ) != -1 )
            System.out.print( (char)c );

        s.close();
        }
    }