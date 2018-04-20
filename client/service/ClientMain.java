package client.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import client.view.*;

public class ClientMain {
	String serverIP;
	MainView mv;				//메인 뷰를 띄우기 위한 메인뷰 객체
	
	Socket connection;
	ObjectOutputStream output;
	ObjectInputStream input;
	
	public ClientMain(){}
	
	public ClientMain(String c_id){ 
		mv = new MainView();						//클라이언트 메인 뷰를 띄운다.
		
		try {
			Socket connection = new Socket("70.12.115.73",6789);
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();
			ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
			
			System.out.println("클라이언트 입니다. 스트림이 연결되었네요");
			output.writeObject(c_id);
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
