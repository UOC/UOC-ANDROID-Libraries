package edu.uoc.openapi.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import edu.uoc.openapi.Constants;
import edu.uoc.openapi.RESTMethod;

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

    /*
    * Get message's historic actions from a folder of a communication's resource (board, debate, forum) of the
    * classroom.
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
    *   MessageHistory
    *
     */
	public static MessageHistory getClassroomsIdBoardsIdFoldersIdMessagesIdHistory(String token, String domain_id, String board_id, String folder_id, String message_id) {
		String mh = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages/"+message_id+"/history",
				token);

		return JSONToMessageHistory(mh);
	}

    /*
    * Get message's historic actions.
    * The user must have given the application the grant READ_MAIL to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - message_id:     Message's identifier.
    *
    * Response:
    *   MessageHistory
    *
     */
	public static MessageHistory getMailMessagesIdHistory(String token, String message_id) {
		String mh = RESTMethod.Get(
				Constants.BASEURI +"mail/messages/"+message_id+"/history",
				token);

		return JSONToMessageHistory(mh);
	}

    /*
    * Get message's historic actions from a folder of a communication's resource (board, debate, forum) of the
    * subject.
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
    * - message_id:     Message's identifier.
    *
    * Response:
    *   MessageHistory
    *
     */
	public static MessageHistory getSubjectsIdBoardsIdFoldersIdMessagesIdHistory(String token, String subject_id, String board_id, String folder_id, String message_id) {
		String mh = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+folder_id+"/messages/"+message_id+"/history",
				token);

		return JSONToMessageHistory(mh);
	}
}