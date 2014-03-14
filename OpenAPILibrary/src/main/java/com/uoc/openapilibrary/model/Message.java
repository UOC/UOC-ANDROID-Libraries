package com.uoc.openapilibrary.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.style.ClickableSpan;

import com.google.gson.Gson;
import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;

public class Message {
	private String id;
	private String subject;
	private String snippet;
	private String date;
	private long color;
	private long status;
	private String from;
	private String to;
    private String cc;
    private String body;

    public Message(){

    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getColor() {
		return color;
	}
	public void setColor(long color) {
		this.color = color;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
    public String getCc() {
        return cc;
    }
    public void setCc(String cc) {
        this.cc = cc;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
	
	private static Message JSONToMessage(String messageJSON) {
		Gson gson = new Gson();
		Message obj = gson.fromJson(messageJSON, Message.class);
		return obj;
	}
	
	private static String toJSON(Message newMessage) {
		return new Gson().toJson(newMessage);
	}
	
	public static Message postClassroomsIdBoardsIdMessages(String token, String domain_id, String board_id, Message newMessage) {
		String m = RESTMethod.Post(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/messages",toJSON(newMessage),
				token);

		return JSONToMessage(m);
	}
	
	public static Message getClassroomsIdBoardsIdMessagesId(String token, String domain_id, String board_id, String message_id) {
		String m = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/messages/"+message_id,
				token);

		return JSONToMessage(m);
	}
	
	public static Message getClassroomsIdBoardsIdFoldersIdMessagesId(String token, String domain_id, String board_id, String folder_id, String message_id) {
		String m = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages/"+message_id,
				token);

		return JSONToMessage(m);
	}
	
	public static Message postMailMessages(String token, Message newMessage) {
		String m = RESTMethod.Post(
				Constants.BASEURI +"mail/messages",toJSON(newMessage),
				token);

		return JSONToMessage(m);
	}
	
	public static Message getMailMessagesId(String token, String message_id) {
		String m = RESTMethod.Get(
				Constants.BASEURI +"mail/messages/"+message_id,
				token);

		return JSONToMessage(m);
	}
	
	public static Message postSubjectsIdBoardsIdMessages(String token, String subject_id, String board_id, Message newMessage) {
		String m = RESTMethod.Post(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/messages",toJSON(newMessage),
				token);

		return JSONToMessage(m);
	}
	
	public static Message getSubjectsIdBoardsIdMessagesId(String token, String subject_id, String board_id, String message_id) {
		String m = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/messages/"+message_id,
				token);

		return JSONToMessage(m);
	}
	
	public static Message getSubjectsIdBoardsIdFoldersIdMessagesId(String token, String subject_id, String board_id, String folder_id, String message_id) {
		String m = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages/"+message_id,
				token);

		return JSONToMessage(m);
	}
}
