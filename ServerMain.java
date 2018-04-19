import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class ServerMain {
	private HashMap clients;

	ServerMain() {
		clients = new HashMap();
		Collections.synchronizedMap(clients); // 동기화맵으로 clients 해시맵 구성
	}

	void sendToAll(String msg) {
		Iterator it = clients.keySet().iterator();
		while (it.hasNext()) {

			try {
				DataOutputStream out = (DataOutputStream) clients.get(it.next());
				out.writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");

			while (true) {
				socket = serverSocket.accept(); // 클라이언트의 접속을 대기한다.
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "에서 접속했습니다");

				//클라이언트의 접속으로 인해 생성된 socket을 
				//ServerReceiver 스레드로 넘겨준뒤  스레드를 시작한다.
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		} catch (Exception ex) {

		}
	}

	public static void main(String[] args) {
		new ServerMain().start();
	}

	
	//ServerReceiver 스레드. 클라이언트가 접속하면 
	//소켓을 받아서 클라이언트 소켓과 통신을 해주는 스레드
	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;

		public ServerReceiver(Socket socket) {
			this.socket = socket;

			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (Exception ex) {
				System.out.println("뭔가 잘못되었다..");
			} // try,catch
		}// 생성자

		@Override
		public void run() {
			String name = "";
			try {
				name = in.readUTF();
				sendToAll("#" + name + "님이 들어오셨습니다.");
				clients.put(name, out);
				System.out.println("현재 서버의 접속자 수는 " + clients.size() + " 입니다.");

				while (in != null) {
					sendToAll(in.readUTF());
				}
			} catch (Exception ex) {
				System.out.println("뭔가 잘못되었다2");

			} // try, catch
		} // run

	} // ReceiverThread
}
