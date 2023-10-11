package com.jedis_pool;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@ServerEndpoint("/WebSocketChat/{myName}/{myRoom}")
public class WebSocketChat {
	// 連線 jedis
	private static JedisPool pool = null;
	Jedis jedis = null;
	// 取得送過來的值
	String myName = null;
	String myRoom = null;
	private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void onOpen(@PathParam("myName") String myName, @PathParam("myRoom") String myRoom, Session userSession)
			throws IOException {
		this.myName = myName;
		this.myRoom = myRoom;
		allSessions.add(userSession); // 存值
		System.out.println(userSession.getId() + " : 已連線");
		System.out.println(myName + " : 已連線");
		System.out.println(myRoom + " : chat room no ");
		for(Session session : allSessions) {
			int i = 1;
			System.out.println(i++ + "個連線:" + session.toString() +"\n\n");
		}
		
		// 開啟連線 to jedis
		pool = JedisUtil.getJedisPool();
		jedis = pool.getResource();
		jedis.auth("123456");

		try {
			// 迴圈歷史訊息
			if (jedis.llen("room:" + myRoom) == 0 || jedis.llen("room:" + myRoom) == null) {
			} else {
					for (int j = 0; j < jedis.llen("room:" + myRoom); j++) {
						JSONObject oneJsonHistory = new JSONObject(jedis.lindex("room:" + myRoom, j).toString());
						userSession.getBasicRemote().sendText(oneJsonHistory.toString()); // 送歷史訊息
					}
					// 上線訊息
						JSONObject helloObj = new JSONObject();
						helloObj.put("userName", myRoom);
						helloObj.put("room", myRoom);
						helloObj.put("message", myName + "已上線");
						for (Session session : allSessions) {
							if (session != userSession) {
								session.getBasicRemote().sendText(helloObj.toString());
							}
						}
			}
		} catch (NullPointerException e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();//
		}
		
	}

	@OnMessage
	public void onMessage(Session userSession, String message) throws JSONException {
		int i = 0;
		for (Session session : allSessions) {// 使用者此次連線scan訊息
			if (session.isOpen()) {
				i++;
				
				//換行處理
				JSONObject sendNowNews = new JSONObject(message);
				System.out.println(sendNowNews.get("message").toString());
				String str = sendNowNews.get("message").toString();
				StringBuilder sb = new StringBuilder();
				sb.append(str);
				if(sb.length() >= 20  && !sb.toString().contains("<img")) {
					for(int j = 0 ; j<sb.length() ; j++) {
						if( j % 20 == 0) {
							sb.insert(j,"\n");
						}
					}
				}
				sendNowNews.remove("message"); sendNowNews.put("message", sb.toString());
				
				
				session.getAsyncRemote().sendText(sendNowNews.toString()); // to jsp
				if (i / 2 == 0) {
					JSONObject jo = new JSONObject(sendNowNews.toString());
					System.out.println("Message received" + sendNowNews.toString()); // test
					try {
						jedis.rpush("room:" + myRoom, jo.toString());
					} catch (Exception e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		} // for end
	}// on message end

	@OnError
	public void onError(Session userSession, Throwable e) {
		
		jedis.close();
		for(Session session : allSessions) {
			try {
				allSessions.remove(session);
				session.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				
			}
		}
		
		e.printStackTrace();
		System.out.println(e.getMessage());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		try {
			// 離線訊息
			JSONObject excObj = new JSONObject();
			excObj.put("userName", myRoom);
			excObj.put("room", myRoom );
			excObj.put("message", myName + "已離線");
			for (Session session : allSessions) {
				if (session != userSession) {
					session.getBasicRemote().sendText(excObj.toString());
				}
			}

			System.out.println(userSession + "已離線");
			allSessions.remove(userSession);
			
			userSession.close();
			jedis.close();
		} catch (IOException | JSONException  e) {
			e.printStackTrace();
		}
		System.out.println(userSession.getId() + " : Disconnected" + Integer.toString(reason.getCloseCode().getCode()));
	}

}
