package com.uoc.openapilibrary.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;

public class MessageHistory {
	private String id;
	private ArrayList<MessageHistoryDetail> details;

    public MessageHistory(){

    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<MessageHistoryDetail> getDetails() {
		return details;
	}
	public void setDetails(ArrayList<MessageHistoryDetail> details) {
		this.details = details;
	}
	public void addDetail(MessageHistoryDetail detail) {
		this.details.add(detail);
	}
	public void removeDetail(MessageHistoryDetail detail) {
		this.details.remove(detail);
	}
	
	private static MessageHistory JSONToMessageHistory(String historyJSON) {
		Gson gson = new Gson();
		MessageHistory obj = gson.fromJson(historyJSON, MessageHistory.class);
		return obj;
	}
	
	public static MessageHistory getClassroomsIdBoardsIdFoldersIdMessagesIdHistory(String token, String domain_id, String board_id, String folder_id, String message_id) {
		String mh = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages/"+message_id+"/history",
				token);

		return JSONToMessageHistory(mh);
	}
	
	public static MessageHistory getMailMessagesIdHistory(String token, String message_id) {
		String mh = RESTMethod.Get(
				Constants.BASEURI +"mail/messages/"+message_id+"/history",
				token);

		return JSONToMessageHistory(mh);
	}
	
	public static MessageHistory getSubjectsIdBoardsIdFoldersIdMessagesIdHistory(String token, String subject_id, String board_id, String folder_id, String message_id) {
		String mh = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages/"+message_id+"/history",
				token);

		return JSONToMessageHistory(mh);
	}
}