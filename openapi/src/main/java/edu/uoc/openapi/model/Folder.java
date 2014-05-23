package edu.uoc.openapi.model;

import com.google.gson.Gson;
import edu.uoc.openapi.Constants;
import edu.uoc.openapi.RESTMethod;

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

    /*
    * Get the data of the Inbox folder of a communication's resource (board, debate, forum) of the classroom.
    * The user must have given the application the grant READ_BOARD to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - domain_id:      Clasroom's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * Response:
    *   Folder
    *
     */
	public static Folder getClassroomsIdBoardsIdFoldersInbox(String token, String domain_id, String board_id) {
		String f = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/inbox",
				token);

		return JSONToFolder(f);
	}

    /*
    * Get the data of a folder of a communication's resource (board, debate, forum) of the classroom.
    * The user must have given the application the grant READ_BOARD to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - domain_id:      Clasroom's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * - folder_id:      Folder's identifier.
    *
    * Response:
    *   Folder
    *
     */
	public static Folder getClassroomsIdBoardsIdFoldersId(String token, String domain_id, String board_id, String folder_id) {
		String f = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+folder_id,
				token);

		return JSONToFolder(f);
	}

    /*
    * Move the message to a folder.
    * The user must have given the application the grant WRITE to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - domain_id:      Clasroom's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * - message_id:     Message's identifier.
    *
    * - folder_id:      Folder's identifier.
    *
    * Response:
    *   Folder
    *
     */
	public static Folder postClassroomsIdBoardsIdMessagesIdMoveId(String token, String domain_id, String board_id,String message_id, String folder_id) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/messages/"+message_id+"/move/"+folder_id,
				token);

		return JSONToFolder(f);
	}

    /*
    * Move the message to a folder.
    * The user must have given the application the grant WRITE to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - domain_id:      Clasroom's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * - message_id:     Message's identifier.
    *
    * - sfolder_id:     Folder identifier where the message is located.
    *
    * - dfolder_id:     Folder identifier to move the message to.
    *
    * Response:
    *   Folder
    *
     */
	public static Folder postClassroomsIdBoardsIdFoldersIdMessagesIdMoveId(String token, String domain_id, String board_id, String sfolder_id, String message_id, String dfolder_id) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+sfolder_id+"/messages/"+message_id+"/move/"+dfolder_id,
				token);

		return JSONToFolder(f);
	}

    /*
    * Create a new folder. The folder is created at the top level of folders.
    * The user must have given the application the grant WRITE to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - newFolder:      The Folder object that you wish to create.
    *
    * Response:
    *   Folder
    *
     */
	public static Folder postMailFolders(String token, Folder newFolder) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"mail/folders", toJSON(newFolder),
				token);

		return JSONToFolder(f);
	}

    /*
    * Get the data of the Inbox folder.
    * The user must have given the application the grant READ_MAIL to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * Response:
    *   Folder
    *
     */
	public static Folder getMailFoldersInbox(String token) {
		String f = RESTMethod.Get(
				Constants.BASEURI +"mail/folders/inbox",
				token);

		return JSONToFolder(f);
	}

    /*
    * Get info about the folder.
    * The name of the folder has the names of the uplevel folder. So, if there is a folder named “Test” inside the
    * folder named “Inbox”, the name of the resource that represent the folder “Test” will be “Inbox – Test”.
    * The user must have given the application the grant READ_MAIL to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - folder_id:      Folder's identifier
    *
    * Response:
    *   Folder
    *
     */
	public static Folder getMailFoldersId(String token, String folder_id) {
		String f = RESTMethod.Get(
				Constants.BASEURI +"mail/folders/"+folder_id,
				token);

		return JSONToFolder(f);
	}

    /*
    * Move the message to a folder.
    * The user must have given the application the grant WRITE to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - message_id:     Message identifier
    *
    * - folder_id:      Folder's identifier
    *
    * Response:
    *   Folder
    *
     */
	public static Folder postMailMessagesIdMoveId(String token, String message_id, String folder_id) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"mail/messages/"+message_id+"/move/"+folder_id,
				token);

		return JSONToFolder(f);
	}

    /*
    * Move the message to a folder.
    * The user must have given the application the grant WRITE to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - message_id:     Message identifier
    *
    * - sfolder_id:     Folder identifier where the message is located.
    *
    * - dfolder_id:     Folder identifier to move the message to.
    *
    * Response:
    *   Folder
    *
     */
	public static Folder postMailFoldersIdMessagesIdMoveId(String token, String sfolder_id, String message_id, String dfolder_id) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"mail/folders/"+sfolder_id+"/messages/"+message_id+"/move/"+dfolder_id,
				token);

		return JSONToFolder(f);
	}

    /*
    * Get the data of the Inbox folder of a communication's resource (board, debate, forum) of the subject.
    * The user must have given the application the grant READ_BOARD to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - subject_id:     Subject's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * Response:
    *   Folder
    *
     */
	public static Folder getSubjectsIdBoardsIdFoldersInbox(String token, String subject_id, String board_id) {
		String f = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/inbox",
				token);

		return JSONToFolder(f);
	}

    /*
    * Get the data of a folder of a communication's resource (board, debate, forum) of the subject.
    * The user must have given the application the grant READ_BOARD to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - subject_id:     Subject's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * - folder_id:      Folder's identifier.
    *
    * Response:
    *   Folder
    *
     */
	public static Folder getSubjectsIdBoardsIdFoldersId(String token, String subject_id, String board_id, String folder_id) {
		String f = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+folder_id,
				token);

		return JSONToFolder(f);
	}

    /*
    * Move the message to a folder.
    * The user must have given the application the grant WRITE to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - subject_id:     Subject's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * - message_id:     Message's identifier.
    *
    * - folder_id:      Folder's identifier.
    *
    * Response:
    *   Folder
    *
     */
	public static Folder postSubjectsIdBoardsIdMessagesIdMoveId(String token, String subject_id, String board_id, String message_id, String folder_id) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/messages/"+message_id+"/move/"+folder_id,
				token);

		return JSONToFolder(f);
	}

    /*
    * Move the message to a folder.
    * The user must have given the application the grant WRITE to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - subject_id:     Subject's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * - message_id:     Message's identifier.
    *
    * - sfolder_id:     Folder identifier where the message is located.
    *
    * - dfolder_id:     Folder identifier to move the message to.
    *
    * Response:
    *   Folder
    *
     */
	public static Folder postSubjectsIdBoardsIdFoldersIdMessagesIdMoveId(String token, String subject_id, String board_id,String sfolder_id,String message_id, String dfolder_id) {
		String f = RESTMethod.Post(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+sfolder_id+"/messages/"+message_id+"/move/"+dfolder_id,
				token);

		return JSONToFolder(f);
	}
}