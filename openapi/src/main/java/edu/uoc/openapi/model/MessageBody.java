package edu.uoc.openapi.model;

import com.google.gson.Gson;
import edu.uoc.openapi.Constants;
import edu.uoc.openapi.RESTMethod;

public class MessageBody {
	private String id;
	private String body;

    public MessageBody(){

    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	private static MessageBody JSONToMessageBody(String messageBodyJSON) {
		Gson gson = new Gson();
		MessageBody obj = gson.fromJson(messageBodyJSON, MessageBody.class);
		return obj;
	}

    /*
    * Get body of a message from a folder of a communication's resource (board, debate, forum) of the subject.
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
    * - message_id:     Message's identifier.
    *
    * - folder_id:      Folder's identifier.
    *
    * Response:
    *   MessageBody
    *
     */
	public static MessageBody getClassroomsIdBoardsIdFoldersIdMessagesIdBody(String token, String domain_id, String board_id,String folder_id, String message_id) {
		String m = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages/"+message_id+"/body",
				token);

		return JSONToMessageBody(m);
	}

    /*
    * Get the body of the message with id as identifier.
    * The user must have given the application the grant READ_MAIL to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - message_id:     Message's identifier.
    *
    * Response:
    *   MessageBody
    *
     */
	public static MessageBody getMailMessagesBody(String token, String message_id) {
		String m = RESTMethod.Get(
				Constants.BASEURI +"mail/messages/"+message_id+"/body",
				token);

		return JSONToMessageBody(m);
	}

    /*
    * Get body of a message from a folder of a communication's resource (board, debate, forum) of the subject.
    * The user must have given the application the grant READ_MAIL to use these operation.
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
    * - message_id:     Message's identifier.
    *
    * Response:
    *   MessageBody
    *
     */
	public static MessageBody getSubjectsIdBoardsIdFoldersIdMessagesIdBody(String token, String subject_id, String board_id, String folder_id,String message_id) {
		String m = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages/"+message_id+"/body",
				token);

		return JSONToMessageBody(m);
	}
}