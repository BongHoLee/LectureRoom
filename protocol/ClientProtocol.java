package protocol;
import java.io.Serializable;


public class ClientProtocol implements Serializable {
	static final long serialVersionUID = 100L;
	public static final int		Chatting_Start		 = 210;	 // 채팅시작시
	public static final int		Chatting_Message	 = 220; 	 // 채팅 메시지 전송시
	public static final int		Chatting_End		 = 230; 	 // 채팅 종료시
	
	public static final int      Order_Send			= 300;		 // 음식 주문시
	public static final int		EXIT 					=  400;	 // 사용 종료시
	
	

	Object 						data;
	int                          state;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public static int getChattingStart() {
		return Chatting_Start;
	}
	public static int getChattingMessage() {
		return Chatting_Message;
	}
	public static int getChattingEnd() {
		return Chatting_End;
	}
	public static int getOrderSend() {
		return Order_Send;
	}
	public static int getExit() {
		return EXIT;
	}
	
	
	

}
