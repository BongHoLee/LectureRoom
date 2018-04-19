package server.service;

import server.view.MainView;

public class ServerMain {
	ServerSc sc;										//클라이언트와 통신을 대기하는 소켓 스레드
	MainView mv;									//main view 객체
	
	//생성자, main view 띄우기(로그인 성공시)
	public ServerMain(){
		System.out.println("서버메인스레드 시작");
		//main view 팝업
		mv = new MainView();
		
		
		//멀티 스레드로 클라이언트 접속 대기
		Runnable sc = new ServerSc();
		System.out.println("메인스레드 종료");
	}

}
