package Serpis.psp;

import java.awt.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

class minimoServidor {
	public static Date now = new Date();
	public static DateFormat df = DateFormat.getDateInstance();
	public static DateFormat hf = new SimpleDateFormat("HH:mm:ss");
	public static String dateNow = df.format(now);
	public static String hourNow = hf.format(now);
    public static void main( String args[] ) {
        ServerSocket s = (ServerSocket)null;
        Socket s1;
        String cadena = dateNow + " " + hourNow;
        int longCad;
        OutputStream s1out;
        
        try {
            s = new ServerSocket( 4321,300 );
        } catch( IOException e ) {
            System.out.println( e );
            }

        while( true ) {
            try {
                s1 = s.accept();

                s1out = s1.getOutputStream();

                longCad = cadena.length();
                for( int i=0; i < longCad; i++ )
                    s1out.write( (int)cadena.charAt( i ) );

                s1.close();
            } catch( IOException e ) {
                System.out.println( e );
                }
            }
        }
    }