package com.lesson.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;

import java.util.Map;
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

import com.coach.model.CoachService;
import com.member.model.MemberService;

@ServerEndpoint("/LessonWS/{myNo}")
public class LessonWS {
	
//private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
private static final Map<String,Set<Session>> map = new Hashtable<String,Set<Session>>();

	public static Map<String, Set<Session>> getMap() {
	  return map;
    }

	@OnOpen
	public void onOpen(@PathParam("myNo") String myNo, Session userSession) throws IOException {
		System.out.println("myNo="+myNo);
		Set<Session> myNo_allSessions = null;
		if(map.containsKey(myNo)) {
			myNo_allSessions = map.get(myNo);
			myNo_allSessions.add(userSession);
			map.put(myNo, myNo_allSessions);
		}else {
			myNo_allSessions = Collections.synchronizedSet(new HashSet<Session>());
			myNo_allSessions.add(userSession);
			map.put(myNo, myNo_allSessions);
		}
		for (Session session : myNo_allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText("count="+ (map.get(myNo).size()-1));
		}
		System.out.println("map.get(myNo).size()="+map.get(myNo).size());
	}

	
	@OnMessage
	public void onMessage(@PathParam("myNo") String myNo, Session userSession, String message) throws JSONException {
		JSONObject the_message = new JSONObject(message);
		String yourNo = the_message.get("yourNo").toString();	
		if(yourNo.trim().length()==0) {
			String action = the_message.get("action").toString();
			if("coachScheduleUpdate".equals(action)){
				for (String key: map.keySet()) {
				  Set<Session> thissession =map.get(key);				    
					for (Session session : thissession) {
						if (session.isOpen())
							session.getAsyncRemote().sendText(message);
					}	
				}	
			System.out.println("All---Message received: " + message);
			}
		}else {
			Set<Session> myNo_allSessions = map.get(yourNo);
			String action = the_message.get("action").toString();	
			if("sendAP".equals(action)||"chatwithCoach".equals(action)){
				MemberService memSvc = new MemberService();
				String memName = memSvc.findOneMember(myNo).getMember_name();
				String finalMesage= the_message.put("memName", memName).toString();
			for (Session session : myNo_allSessions) {
				if (session.isOpen())
					session.getAsyncRemote().sendText(finalMesage);
			}
			}
			if("changeDateSuccess".equals(action)||"quoteprice".equals(action)){
				CoachService coachService = new CoachService();
				String coachName = coachService.findOneCoach(myNo).getCoach_name();
				String finalMesage= the_message.put("coachName",coachName).toString();
			for (Session session : myNo_allSessions) {
				if (session.isOpen())
					session.getAsyncRemote().sendText(finalMesage);
			}
			}					
			System.out.println(yourNo+"---Message received: " + message);
		}
	
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
		System.out.print(e);
	}
	
	@OnClose
	public void onClose(@PathParam("myNo") String myNo, Session userSession, CloseReason reason) {
		Set<Session> myNo_allSessions = map.get(myNo);
		myNo_allSessions.remove(userSession);
		map.put(myNo, myNo_allSessions);
		for (Session session : myNo_allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText("count="+ (map.get(myNo).size()-1));
		}
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
		System.out.println("map.get(myNo).size()="+map.get(myNo).size());
	}
}