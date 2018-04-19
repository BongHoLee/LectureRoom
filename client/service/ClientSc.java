package client.service;

import java.io.IOException;
import java.net.*;

public class ClientSc {
	String serverIP;
	 
	 
	public static void main(String[] args) throws InterruptedException {
		try {
			Socket connection = new Socket("70.12.115.73",6789);
			System.out.println("클라이언트 입니다.");
			Thread.sleep(100000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
