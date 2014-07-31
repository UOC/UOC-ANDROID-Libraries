package edu.uoc.openapi.model;

import com.google.gson.Gson;

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

    public Board(String id, String subtype, String title, String code, String domainId,
                 String unreadMessages, String totalMessages) {
        this.id = id;
        this.subtype = subtype;
        this.title = title;
        this.code = code;
        this.domainId = domainId;
        this.unreadMessages = unreadMessages;
        this.totalMessages = totalMessages;
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
		return new Gson().fromJson(boardJSON, Board.class);
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Board getClassroomsIdBoardsId(String token, String domain_id, String board_id,
                                         String baseUri)
            throws Exception{
        return JSONToBoard(RESTMethod.Get(
                baseUri + "classrooms/" + domain_id + "/boards/" + board_id,
                token));
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Board getSubjectsIdBoardsId(String token, String subject_id, String board_id,
                                       String baseUri)
            throws Exception{
        return JSONToBoard(RESTMethod.Get(
                baseUri +"subjects/"+subject_id+"/boards/"+board_id,
                token));
	}
}
