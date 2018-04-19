package client.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class ClientSc {
	String serverIP;
	 
	 
	public static void main(String[] args) throws InterruptedException {
		try {
			Socket connection = new Socket("70.12.115.73",6789);
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();
			ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
			
			System.out.println("클라이언트 입니다. 스트림이 연결되었네요");
			output.writeObject("Lee");
			output.flush();
			System.out.println("클라이언트 : C_id 전송 완료");
			System.out.println(connection.getInetAddress().toString());
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
