package com.uoc.openapilibrary.model;

import com.google.gson.Gson;
import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;

public class Folder {
	private String id;
	private String name;
	private long unreadMessages;
	private long totalMessages;

    public Folder() {
        //Default constructor
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getUnreadMessages() {
		return unreadMessages;
	}
	public void setUnreadMessages(long unreadMessages) {
		this.unreadMessages = unreadMessages;
	}
	public long getTotalMessages() {
		return totalMessages;
	}
	public void setTotalMessages(long totalMessages) {
		this.totalMessages = totalMessages;
	}
	
	private static Folder JSONToFolder(String folderJSON) {
		Gson gson = new Gson();
		Folder obj = gson.fromJson(folderJSON, Folder.class);
		return obj;
	}
	
	private static String toJSON(Folder newFolder) {
		return new Gson().toJson(newFolder);
	}
	
	public static Folder getClassroomsIdBoardsIdFoldersInbox(String token, String domain_id, String board_id) {
		String f = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/inbox",
				token);

		return JSONToFolder(f);
	}
	
	public static Folder getClassroomsIdBoardsIdFoldersId(String token, String domain_id, String board_id, String folder_id) {
		String f = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+folder_id,
				token);

		return JSONToFolder(f);
	}
	
	public static Folder postClassroomsIdBoardsIdMessagesIdMoveId(String token, String domain_id, String board_id,String message_id, String folder_id) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/messages/"+message_id+"/move/"+folder_id,
				token);

		return JSONToFolder(f);
	}
	
	public static Folder postClassroomsIdBoardsIdFoldersIdMessagesIdMoveId(String token, String domain_id, String board_id, String sfolder_id, String message_id, String dfolder_id) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+sfolder_id+"/messages/"+message_id+"/move/"+dfolder_id,
				token);

		return JSONToFolder(f);
	}
	
	public static Folder postMailFolders(String token, Folder newFolder) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"mail/folders", toJSON(newFolder),
				token);

		return JSONToFolder(f);
	}
	
	public static Folder getMailFoldersInbox(String token) {
		String f = RESTMethod.Get(
				Constants.BASEURI +"mail/folders/inbox",
				token);

		return JSONToFolder(f);
	}
	
	public static Folder getMailFoldersId(String token, String folder_id) {
		String f = RESTMethod.Get(
				Constants.BASEURI +"mail/folders/"+folder_id,
				token);

		return JSONToFolder(f);
	}
	
	public static Folder postMailMessagesIdMoveId(String token, String message_id, String folder_id) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"mail/messages/"+message_id+"/move/"+folder_id,
				token);

		return JSONToFolder(f);
	}
	
	public static Folder postMailFoldersIdMessagesIdMoveId(String token, String sfolder_id, String message_id, String dfolder_id) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"mail/folders/"+sfolder_id+"/messages/"+message_id+"/move/"+dfolder_id,
				token);

		return JSONToFolder(f);
	}
	
	public static Folder getSubjectsIdBoardsIdFoldersInbox(String token, String subject_id, String board_id) {
		String f = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/inbox",
				token);

		return JSONToFolder(f);
	}
	
	public static Folder getSubjectsIdBoardsIdFoldersId(String token, String subject_id, String board_id, String folder_id) {
		String f = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+folder_id,
				token);

		return JSONToFolder(f);
	}
	
	public static Folder postSubjectsIdBoardsIdMessagesIdMoveId(String token, String subject_id, String board_id, String message_id, String folder_id) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/messages/"+message_id+"/move/"+folder_id,
				token);

		return JSONToFolder(f);
	}
	
	public static Folder postSubjectsIdBoardsIdFoldersIdMessagesIdMoveId(String token, String subject_id, String board_id,String sfolder_id,String message_id, String dfolder_id) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+sfolder_id+"/messages/"+message_id+"/move/"+dfolder_id,
				token);

		return JSONToFolder(f);
	}
}