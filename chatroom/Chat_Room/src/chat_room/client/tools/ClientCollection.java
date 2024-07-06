package chat_room.client.tools;

import java.util.HashMap;

import chat_room.client.backstage.ClientServerTogether;


/**
 * 客户端与服务器端继续通信的线程集合类
 * @author Administrator
 * Client_Connect_Server_Thread_Collection
 */
public class ClientCollection {
	private	static HashMap<String, ClientServerTogether> hm = new HashMap<String, ClientServerTogether>();
	
	/**
	 * 添加客户端与服务器端继续通信的线程的方法
	 * @param Name 用户名
	 * @param clientConServer 客户端与服务器端继续通信的线程
	 */
	public static void addClientThreadCollection(String Name, ClientServerTogether clientConServer ){
		hm.put(Name, clientConServer);
	}
	
	/**
	 * 根据用户名返回客户端与服务器端继续通信的线程的方法
	 * @param Name 用户名
	 * @return	返回客户端与服务器端继续通信的线程
	 */
	public static ClientServerTogether getClientThreadCollection(String Name){
		return hm.get(Name);
	}
}
