package client.view;

public class AccessChat {
	private static ChatView cv;
	
	//singletone 패턴을 이용한 ChatView 호출
	private AccessChat(){
		
	}
	
	public static ChatView chat() {
		if(cv == null)
			cv = new ChatView();
		return cv;
	}

}
