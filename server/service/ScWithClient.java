package server.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

//클라이언트가 접속시 호출되어서 실행되는 스레드
public class ScWithClient implements Runnable {
	Socket connection;										//클라이언트와 연결시 생성되는 소켓
	ObjectOutputStream output;							//소켓 통신시 메시지를 전달할 스트림 객체
	ObjectInputStream input;								//소켓 통신시 메시지를 전달받을 스트림 객체
	
	public ScWithClient(Socket socket) {
		this.connection = socket;
		new Thread(this).start(); 							//클라이언트와 연결된 소켓을 넘겨받고 run
	}
	@Override
	public void run() {
		System.out.println("실행이 되었습니다.");
		System.out.println(connection.getInetAddress());
		try {
			Thread.sleep(10000);
			System.out.println("종료가 되었습니다.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
