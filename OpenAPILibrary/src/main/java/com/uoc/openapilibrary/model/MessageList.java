package com.uoc.openapilibrary.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;

public class MessageList {
	private ArrayList<Message> messages;

    public MessageList(){

    }
	public ArrayList<Message> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
	private static MessageList JSONToMessageList(String messageList) {
		return new Gson().fromJson(messageList, MessageList.class);
	}
	
	public static MessageList getClassroomsIdBoardsIdMessages(String token, String domain_id, String board_id, String start, String end) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/messages",
				token, "&start="+start+"&end="+end);

		return JSONToMessageList(ml);
	}
	
	public static MessageList getClassroomsIdBoardsIdMessages(String token, String domain_id, String board_id, String start, String end, MessageSearchOptions so) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/messages",
				token, "&start="+start+"&end="+end+so.toUrlParameter());

		return JSONToMessageList(ml);
	}
	
	public static MessageList getClassroomsIdBoardsIdMessagesUnread(String token, String domain_id, String board_id, String start, String end) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/messages/unread",
				token, "&start="+start+"&end="+end);

		return JSONToMessageList(ml);
	}
	
	public static MessageList getClassroomsIdBoardsIdMessagesUnread(String token, String domain_id, String board_id, String start, String end, MessageSearchOptions so) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/messages/unread",
				token, "&start="+start+"&end="+end+so.toUrlParameter());

		return JSONToMessageList(ml);
	}
	
	public static MessageList getClassroomsIdBoardsIdFoldersIdMessages(String token, String domain_id, String board_id, String folder_id, String start, String end) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages",
				token, "&start="+start+"&end="+end);

		return JSONToMessageList(ml);
	}
	
	public static MessageList getClassroomsIdBoardsIdFoldersIdMessages(String token, String domain_id, String board_id, String folder_id, String start, String end, MessageSearchOptions so) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages",
				token, "&start="+start+"&end="+end+so.toUrlParameter());

		return JSONToMessageList(ml);
	}
	
	public static MessageList getClassroomsIdBoardsIdFoldersIdMessagesUnread(String token, String domain_id, String board_id, String folder_id, String start, String end) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages/unread",
				token, "&start="+start+"&end="+end);

		return JSONToMessageList(ml);
	}
	
	public static MessageList getClassroomsIdBoardsIdFoldersIdMessagesUnread(String token, String domain_id, String board_id, String folder_id, String start, String end, MessageSearchOptions so) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages/unread",
				token, "&start="+start+"&end="+end+so.toUrlParameter());

		return JSONToMessageList(ml);
	}
	
	public static MessageList getMailMessages(String token, String start, String end) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"mail/messages",
				token, "&start="+start+"&end="+end);

		return JSONToMessageList(ml);
	}
	
	public static MessageList getMailMessages(String token, String start, String end, MessageSearchOptions so) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"mail/messages",
				token, "&start="+start+"&end="+end+so.toUrlParameter());

		return JSONToMessageList(ml);
	}
	
	public static MessageList getMailMessagesUnread(String token, String start, String end) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"mail/messages/unread",
				token, "&start="+start+"&end="+end);

		return JSONToMessageList(ml);
	}
	
	public static MessageList getMailMessagesUnread(String token, String start, String end, MessageSearchOptions so) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"mail/messages/unread",
				token, "&start="+start+"&end="+end+so.toUrlParameter());

		return JSONToMessageList(ml);
	}

	public static MessageList getMailFoldersIdMessages(String token, String folder_id, String start, String end) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"mail/folders/"+folder_id+"/messages",
				token, "&start="+start+"&end="+end);

		return JSONToMessageList(ml);
	}
	
	public static MessageList getMailFoldersIdMessages(String token, String folder_id, String start, String end, MessageSearchOptions so) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"mail/folders/"+folder_id+"/messages",
				token, "&start="+start+"&end="+end+so.toUrlParameter());

		return JSONToMessageList(ml);
	}
	
	public static MessageList getMailFoldersIdMessagesUnread(String token, String folder_id, String start, String end) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"mail/folders/"+folder_id+"/messages/unread",
				token, "&start="+start+"&end="+end);

		return JSONToMessageList(ml);
	}
	
	public static MessageList getMailFoldersIdMessagesUnread(String token, String folder_id, String start, String end, MessageSearchOptions so) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"mail/folders/"+folder_id+"/messages/unread",
				token, "&start="+start+"&end="+end+so.toUrlParameter());

		return JSONToMessageList(ml);
	}
	
	public static MessageList getSubjectsIdBoardsIdMessages(String token, String subject_id, String board_id, String start, String end) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/messages",
				token, "&start="+start+"&end="+end);

		return JSONToMessageList(ml);
	}
	
	public static MessageList getSubjectsIdBoradsIdMessages(String token, String subject_id, String board_id, String start, String end, MessageSearchOptions so) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/messages",
				token, "&start="+start+"&end="+end+so.toUrlParameter());

		return JSONToMessageList(ml);
	}
	
	public static MessageList getSubjectsIdBoardsIdMessagesUnread(String token, String subject_id, String board_id, String start, String end) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/messages/unread",
				token, "&start="+start+"&end="+end);

		return JSONToMessageList(ml);
	}
	
	public static MessageList getSubjectsIdBoardsIdMessagesUnread(String token, String subject_id, String board_id, String start, String end, MessageSearchOptions so) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/messages/unread",
				token, "&start="+start+"&end="+end+so.toUrlParameter());

		return JSONToMessageList(ml);
	}
	
	public static MessageList getSubjectsIdBoardsIdFoldersIdMessages(String token, String subject_id, String board_id, String folder_id, String start, String end) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages",
				token, "&start="+start+"&end="+end);

		return JSONToMessageList(ml);
	}
	
	public static MessageList getSubjectsIdBoardsIdFoldersIdMessages(String token, String subject_id, String board_id, String folder_id, String start, String end, MessageSearchOptions so) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages",
				token, "&start="+start+"&end="+end+so.toUrlParameter());

		return JSONToMessageList(ml);
	}
	
	public static MessageList getSubjectsIdBoardsIdFoldersIdMessagesUnread(String token, String subject_id, String board_id, String folder_id, String start, String end) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages/unread",
				token, "&start="+start+"&end="+end);

		return JSONToMessageList(ml);
	}
	
	public static MessageList getSubjectsIdBoardsIdFoldersIdMessagesUnread(String token, String subject_id, String board_id, String folder_id, String start, String end, MessageSearchOptions so) {
		String ml = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages/unread",
				token, "&start="+start+"&end="+end+so.toUrlParameter());

		return JSONToMessageList(ml);
	}
}