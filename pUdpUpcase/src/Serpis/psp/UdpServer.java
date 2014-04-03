package Serpis.psp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.TimeZone;

public class UdpServer {

	private static final int MaxPaquetSize = 2048; 
	private static final int PORT = 12345;
	private static final String CALENDAR_FORMAT = "%1$tF %1$tT UTC";
	public static void main(String[] args) throws IOException {
		

		DatagramSocket  socket = new DatagramSocket(12345);
		while (true) {

			byte[] inBuf = new byte[ MaxPaquetSize ];
			int inLength = inBuf.length;
			DatagramPacket inDatagramPacket = new DatagramPacket(inBuf, inLength);
			socket.receive(inDatagramPacket);
			String inMessage = new String(inBuf, 0, inDatagramPacket.getLength());
			
			String outMessage = CALENDAR_FORMAT;
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