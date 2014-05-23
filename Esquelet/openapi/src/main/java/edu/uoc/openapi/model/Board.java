package edu.uoc.openapi.model;

import com.google.gson.Gson;
import edu.uoc.openapi.Constants;
import edu.uoc.openapi.RESTMethod;

public class Board {
	private String id;
	private String subtype;
	private String title;
	private String code;
	private String domainId;
    private String unreadMessages;
    private String totalMessages;
	
	public Board() {
		//Default constructor
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
    public String getUnreadMessages() {
        return unreadMessages;
    }
    public void setUnreadMessages(String unreadMessages) {
        this.unreadMessages = unreadMessages;
    }
    public String getTotalMessages() {
        return totalMessages;
    }
    public void setTotalMessages(String totalMessages) {
        this.totalMessages = totalMessages;
    }
	
	private static Board JSONToBoard(String boardJSON) {
		Gson gson = new Gson();
		Board obj = gson.fromJson(boardJSON, Board.class);
		return obj;
	}

    /*
    * Get the data of a communication's resource (board, debate, forum) of the classroom.
    * The user must have given the application the grant READ to use these operation.
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
    *   Board
    *
     */
	public static Board getClassroomsIdBoardsId(String token, String domain_id, String board_id) {
		String b = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id,
				token);

		return JSONToBoard(b);
	}

    /*
    * Get the data of a communication's resource (board, debate, forum) of the subject.
    * The user must have given the application the grant READ to use these operation.
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
    *   Board
    *
     */
	public static Board getSubjectsIdBoardsId(String token, String subject_id, String board_id) {
		String b = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id,
				token);

		return JSONToBoard(b);
	}
}
