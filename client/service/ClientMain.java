package client.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import client.view.*;

public class ClientMain {
	String serverIP;
	MainView mv;				//메인 뷰를 띄우기 위한 메인뷰 객체
	
	
	public ClientMain(){}
	
	public ClientMain(String c_id){ 
		mv = new MainView();						//클라이언트 메인 뷰를 띄운다.
		Runnable r = new ScWithServer(c_id);
		
		
	}
	

}
