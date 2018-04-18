import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("대화명 입력하셈");
			System.exit(0);
		}
		
		try{
			String ip = "127.0.0.1";
			//소켓을 생성하여 연결을 요청
			
			Socket socket = new Socket(ip, 7777);
			System.out.println("서버에 연결이 되었습니다");
			
			Thread sender = new Thread(new ClientSender(socket, args[0]));
			Thread receiver = new Thread(new ClientReceiver(socket));
			
			sender.start();
			receiver.start();
			
		}catch(Exception ex){
			System.out.println("뭔가 잘못되었다.");
		}
	}//main
	
	static class ClientSender extends Thread{
		Socket socket;
		DataOutputStream out;
		String name;
		
		public ClientSender(Socket socket, String name) {
			this.socket = socket;
			try{
				out = new DataOutputStream(socket.getOutputStream());
				this.name = name;
			}catch(Exception ex){
				System.out.println("뭔가 잘못되었다2.");
			}
		}//생성자
		
		public void run() {
			Scanner scn = new Scanner(System.in);
			
			try{
				if(out != null){
					out.writeUTF(name);
				}
				while(out!=null){
					out.writeUTF("["+name+"]"+scn.nextLine());
				}
			}catch(Exception ex){
				System.out.println("뭔가 잘못되었다2.");
			}
		}//sender run()
	}//ClientSender class end

	
	static class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			
			try{
				in = new DataInputStream(socket.getInputStream());
			}catch(Exception ex){
				System.out.println("뭔가 잘못되었다3");
			}
		}
		
		public void run(){
			while(in != null){
				try{
					System.out.println(in.readUTF());
				}catch(Exception ex){
					
				}
			}
		}
	}
}
