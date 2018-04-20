package client.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import client.view.*;

public class ClientMain {
	String serverIP;
	MainView mv;				//메인 뷰를 띄우기 위한 메인뷰 객체
	public static String c_id;			//접속한 클라이언트의 ID를 저장, 공유변수
	
	
	public ClientMain(){}
	
	public ClientMain(String c_id){ 					//접속한 클라이언트의 ID를 가져온다.
		this.c_id = c_id;
		Runnable r = new ScWithServer(this.c_id);		//접속한 클라이언트의ID를 넘겨주고 스레드 실행
		mv = new MainView();						//클라이언트 메인 뷰를 띄운다.
		
		
		
		
	}
	

}
