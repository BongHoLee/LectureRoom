package server.service;

public class ServerMain {
	ServerSc sc;										//클라이언트와 통신을 대기하는 소켓 스레드
	
	//생성자, main view 띄우기(로그인 성공시)
	public ServerMain(){
		//main view 팝업
		
		
		//멀티 스레드로 클라이언트 접속 대기
	}
	
	public static void main(String[] args) {
		Runnable sc = new ServerSc();
		System.out.println("메인은 끝남");
	}

}
