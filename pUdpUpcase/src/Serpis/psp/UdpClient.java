package Serpis.psp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import javax.net.ssl.HttpsURLConnection;
 
public class UdpClient {

 
	public static void main(String[] args) throws Exception {
 
		UdpClient http = new UdpClient();
 
		http.sendGet();
 
	}
 
	private void sendGet() throws Exception {
 
		String url = "http://www.google.com";
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		con.setRequestMethod("GET");

 
		int responseCode = con.getResponseCode();
		System.out.println("Enviando direccion URL: " + url);
		System.out.println("Pagina web : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		System.out.println(response.toString());
 
	}
}