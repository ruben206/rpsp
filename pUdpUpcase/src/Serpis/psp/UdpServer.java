package Serpis.psp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
import java.text.*;

public class UdpServer {

	private static final int MaxPaquetSize = 2048; 
	private static final int PORT = 12345;
	public static Date now = new Date();
	public static DateFormat df = DateFormat.getDateInstance();
	public static DateFormat hf = new SimpleDateFormat("HH:mm:ss");
	public static String dateNow = df.format(now);
	public static String hourNow = hf.format(now);
	public static void main(String[] args) throws IOException {
		

		DatagramSocket  socket = new DatagramSocket(12345);
		while (true) {

			byte[] inBuf = new byte[ MaxPaquetSize ];
			int inLength = inBuf.length;
			DatagramPacket inDatagramPacket = new DatagramPacket(inBuf, inLength);
			socket.receive(inDatagramPacket);
			String inMessage = new String(inBuf, 0, inDatagramPacket.getLength());
			
			String outMessage = dateNow + " " + hourNow;
			byte[] outBuf = outMessage.getBytes();
			int outLength = outBuf.length;
			InetAddress outInetAddress = inDatagramPacket.getAddress();
			int outPort = inDatagramPacket.getPort(); 
			DatagramPacket outDatagramPacket = new DatagramPacket(outBuf, outLength, outInetAddress, outPort);
			socket.send(outDatagramPacket);
			
			System.out.println("UdpServer end.");
		}
	}
}