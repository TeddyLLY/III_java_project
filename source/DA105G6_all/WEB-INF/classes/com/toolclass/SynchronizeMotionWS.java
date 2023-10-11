package com.toolclass;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;



@ServerEndpoint("/SynchronizeMotionWS/{memberNo}")
public class SynchronizeMotionWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();
		
	@OnOpen
	public void onOpen(@PathParam("memberNo") String memberNo, Session userSession) throws IOException {
		sessionsMap.put(memberNo, userSession);
		String text = String.format("Session ID = %s, connected; memberNo = %s", userSession.getId(),
				memberNo);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		JsonObject myMotionMsg = gson.fromJson(message, JsonObject.class);
		String memberNo = myMotionMsg.get("memberNo").getAsString();
		Session session = sessionsMap.get(memberNo);
//		abc xxx = new abc("M011", "4", "伏地挺身", "二頭肌", "三頭肌");
//		String json = new JSONObject(xxx).toString();
//		userSession.getAsyncRemote().sendObject(json);
		
		session.getAsyncRemote().sendText(message);
//		System.out.println(motion);
		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
	}
    	
}
