package server.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;


//클라이언트의 접속을 대기하는 스레드
public class ServerSc implements Runnable{
	
	ServerSocket serverSocket;							//클라이언트와의 통신을 위한 서버 소켓
	Socket connection;										//클라이언트 접속시 생성되는 소켓
	
	public ServerSc(){
		try {
			serverSocket = new ServerSocket(6789);
			new Thread(this).start();										//소켓 전달 후 실행
				
		} catch (IOException e) {
			e.printStackTrace();
		}
					
	}
	
	@Override
	public void run() {				//스레드의 메인 실행 부
		while(true){
			try{
				connection = serverSocket.accept();							//클라이언트의 접속을 대기
				ScWithClient sc = new ScWithClient(connection);		//클라이언트 접속시 
																					//실행 스레드에게 소켓 전달
			}catch(Exception ex){
				System.out.println("일단 뭔가 잘못된거긴 함");
			}
		}
	}

}
