import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class ServerMain {
	private HashMap clients;
	
	ServerMain(){
		clients = new HashMap();
		Collections.synchronizedMap(clients);					//동기화맵으로 clients 해시맵 구성
	}
	
	
}
