package client.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import client.vo.*;

public class ScWithServer implements Runnable{
	Customer cus;											//클라이언트 정보를 담기 위한 vo객체
	Socket connection;
	ObjectOutputStream output;
	ObjectInputStream input;

	 public ScWithServer(String c_id) {
		 cus = new Customer(c_id);							//접속한 클라이언트의 c_id를 저장
		 try {
			connection = new Socket("70.12.115.73",6789);								//소켓연결 
			output = new ObjectOutputStream(connection.getOutputStream());		//스트림 연결
			output.flush();
			input = new ObjectInputStream(connection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		 new Thread(this).start();
	 }
	@Override
	public void run() {
		try {
			
			System.out.println("클라이언트 입니다. 스트림이 연결되었네요");
			output.writeObject(cus.getC_id());											//서버에 접속자 c_id를 전송
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
