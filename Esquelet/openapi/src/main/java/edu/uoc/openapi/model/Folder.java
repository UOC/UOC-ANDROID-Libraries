package edu.uoc.openapi.model;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;

public class Folder {
	private String id;
	private String name;
	private long unreadMessages;
	private long totalMessages;

    public Folder() {
        //Default constructor
    }

    public Folder(String id, String name, long unreadMessages, long totalMessages) {
        this.id = id;
        this.name = name;
        this.unreadMessages = unreadMessages;
        this.totalMessages = totalMessages;
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
		return new Gson().fromJson(folderJSON, Folder.class);
	}


    /*
    * Get the data of the Inbox folder of a communication's resource (board, debate, forum) of the
    * classroom. The user must have given the application the grant READ_BOARD to use these
    * operation.
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Folder getClassroomsIdBoardsIdFoldersInbox(String token, String domain_id,
                                                             String board_id, String baseUri)
            throws Exception{
		return JSONToFolder(RESTMethod.Get(
                baseUri +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/inbox",
                token));
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Folder getClassroomsIdBoardsIdFoldersId(String token, String domain_id,
                                                          String board_id, String folder_id,
                                                          String baseUri)
            throws Exception{
		return JSONToFolder(RESTMethod.Get(
                baseUri +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+folder_id,
                token));
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
    *   If Post call to the server fails, it throws an exception
    *
     */
	public Folder postClassroomsIdBoardsIdMessagesIdMoveId(String token, String domain_id,
                                                                  String board_id,String message_id,
                                                                  String folder_id, String baseUri)
            throws Exception{
		return JSONToFolder(RESTMethod.Post(
                baseUri +"classrooms/"+domain_id+"/boards/"+board_id+"/messages/"+
                        message_id+"/move/"+folder_id,
                token));
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
    *   If Post call to the server fails, it throws an exception
    *
     */
	public Folder postClassroomsIdBoardsIdFoldersIdMessagesIdMoveId(String token,
                                                                           String domain_id,
                                                                           String board_id,
                                                                           String sfolder_id,
                                                                           String message_id,
                                                                           String dfolder_id,
                                                                           String baseUri)
            throws Exception{
		return JSONToFolder(RESTMethod.Post(
                baseUri +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+
                        sfolder_id+"/messages/"+message_id+"/move/"+dfolder_id,
                token));
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
    *   If Post call to the server fails, it throws an exception
    *
     */
	public Folder postMailFolders(String token, Folder newFolder, String baseUri) throws Exception{
		return JSONToFolder(RESTMethod.Post(
                baseUri +"mail/folders", token, newFolder));
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Folder getMailFoldersInbox(String token, String baseUri) throws Exception{
		return JSONToFolder(RESTMethod.Get(
                baseUri +"mail/folders/inbox", token));
	}

    /*
    * Get info about the folder.
    * The name of the folder has the names of the uplevel folder. So, if there is a folder named
    * “Test” inside the folder named “Inbox”, the name of the resource that represent the folder
    * “Test” will be “Inbox – Test”. The user must have given the application the grant READ_MAIL to
    * use these operation.
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Folder getMailFoldersId(String token, String folder_id, String baseUri) throws Exception{
		return JSONToFolder(RESTMethod.Get(
                baseUri +"mail/folders/"+folder_id, token));
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
    *   If Post call to the server fails, it throws an exception
    *
     */
	public Folder postMailMessagesIdMoveId(String token, String message_id, String folder_id,
                                           String baseUri)
            throws Exception{
		return JSONToFolder(RESTMethod.Post(
                baseUri +"mail/messages/"+message_id+"/move/"+folder_id, token));
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
    *   If Post call to the server fails, it throws an exception
    *
     */
	public Folder postMailFoldersIdMessagesIdMoveId(String token, String sfolder_id,
                                                           String message_id, String dfolder_id,
                                                           String baseUri)
            throws Exception{
		return JSONToFolder(RESTMethod.Post(
                baseUri +"mail/folders/"+sfolder_id+"/messages/"+message_id+"/move/"+
                        dfolder_id, token));
	}

    /*
    * Get the data of the Inbox folder of a communication's resource (board, debate, forum) of the
    * subject. The user must have given the application the grant READ_BOARD to use these operation.
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Folder getSubjectsIdBoardsIdFoldersInbox(String token, String subject_id,
                                                           String board_id, String baseUri)
            throws Exception{
		String f = RESTMethod.Get(
                baseUri +"subjects/"+subject_id+"/boards/"+board_id+"/folders/inbox",
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Folder getSubjectsIdBoardsIdFoldersId(String token, String subject_id,
                                                        String board_id, String folder_id,
                                                        String baseUri)
            throws Exception{
		return JSONToFolder(RESTMethod.Get(
                baseUri +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+folder_id,
                token));
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
    *   If Post call to the server fails, it throws an exception
    *
     */
	public Folder postSubjectsIdBoardsIdMessagesIdMoveId(String token, String subject_id,
                                                                String board_id, String message_id,
                                                                String folder_id, String baseUri)
            throws Exception{
		return JSONToFolder(RESTMethod.Post(
                baseUri +"subjects/"+subject_id+"/boards/"+board_id+"/messages/"+
                        message_id+"/move/"+folder_id, token));
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
    *   If Post call to the server fails, it throws an exception
    *
     */
	public Folder postSubjectsIdBoardsIdFoldersIdMessagesIdMoveId(String token,
                                                                         String subject_id,
                                                                         String board_id,
                                                                         String sfolder_id,
                                                                         String message_id,
                                                                         String dfolder_id,
                                                                         String baseUri)
            throws Exception{
		return JSONToFolder(RESTMethod.Post(
                baseUri +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+
                        sfolder_id+"/messages/"+message_id+"/move/"+dfolder_id, token));
	}
}