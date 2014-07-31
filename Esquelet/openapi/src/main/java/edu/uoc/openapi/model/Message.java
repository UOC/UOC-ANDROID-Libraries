package edu.uoc.openapi.model;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;

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
    /*
    -parentMessage
    -previousMessage
    -nextMessage
     */

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
		return new Gson().fromJson(messageJSON, Message.class);
	}
	
	private static String toJSON(Message newMessage) {
		return new Gson().toJson(newMessage);
	}

    /*
    * Send a new mail message to a communication's resource (board, debate, forum) of the classroom.
    * The user must have given the application the grant SEND_BOARD to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - domain_id:      Clasroom's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * - newMessage:     The message object that you wish to create.
    *
    * Response:
    *   Message
    *
    *   If Post call to the server fails, it throws an exception
    *
     */
	public Message postClassroomsIdBoardsIdMessages(String token, String domain_id,
                                                           String board_id, Message newMessage,
                                                           String baseUri)
            throws Exception{

        return JSONToMessage(RESTMethod.Post(
                baseUri +"classrooms/"+domain_id+"/boards/"+board_id+"/messages",
                token, newMessage));
	}

    /*
    * Get message's metadata of a communication's resource (board, debate, forum) of the classroom.
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
    * Response:
    *   Message
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Message getClassroomsIdBoardsIdMessagesId(String token, String domain_id,
                                                            String board_id, String message_id,
                                                            String baseUri)
            throws Exception{

        return JSONToMessage(RESTMethod.Get(
                baseUri +"classrooms/"+domain_id+"/boards/"+board_id+"/messages/"+
                        message_id, token));
	}

    /*
    * Get the message's metadata from a folder of a communication's resource (board, debate, forum) of the
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
    * - folder_id:      Folder's identifier.
    *
    * - message_id:     Message's identifier.
    *
    * Response:
    *   Message
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Message getClassroomsIdBoardsIdFoldersIdMessagesId(String token, String domain_id,
                                                                     String board_id,
                                                                     String folder_id,
                                                                     String message_id,
                                                                     String baseUri)
            throws Exception{

        return JSONToMessage(RESTMethod.Get(
                baseUri +"classrooms/"+domain_id+"/boards/"+board_id+"/folders/"+
                        folder_id+"/messages/"+message_id, token));
	}

    /*
    * Send a new mail message.
    * The user must have given the application the grant SEND_MAIL to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - newMessage:     The Message object that you wish to send.
    *
    * Response:
    *   Message
    *
    *   If Post call to the server fails, it throws an exception
    *
     */
	public Message postMailMessages(String token, Message newMessage, String baseUri)
            throws Exception{
		return JSONToMessage(RESTMethod.Post(
                baseUri +"mail/messages", token, newMessage));
	}

    /*
    * Get message's metadata.
    * The user must have given the application the grant READ_MAIL to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - message_id:     Message identifier.
    *
    * Response:
    *   Message
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Message getMailMessagesId(String token, String message_id, String baseUri)
            throws Exception{
		return JSONToMessage(RESTMethod.Get(
                baseUri +"mail/messages/"+message_id,
                token));
	}

    /*
    * Send a new mail message to a communication's resource (board, debate, forum) of the subject.
    * The user must have given the application the grant SEND_BOARD to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - subject_id:     Subject's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * - newMessage:     The Message object that you wish to create.
    *
    * Response:
    *   Message
    *
    *   If Post call to the server fails, it throws an exception
    *
     */
	public Message postSubjectsIdBoardsIdMessages(String token, String subject_id,
                                                         String board_id, Message newMessage,
                                                         String baseUri)
            throws Exception{

		return JSONToMessage(RESTMethod.Post(
                baseUri +"subjects/"+subject_id+"/boards/"+board_id+"/messages",
                token, newMessage));
	}

    /*
    * Get message's metadata of a communication's resource (board, debate, forum) of the subject.
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
    * - message_id:     Message's identifier.
    *
    * Response:
    *   Message
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Message getSubjectsIdBoardsIdMessagesId(String token, String subject_id,
                                                          String board_id, String message_id,
                                                          String baseUri)
            throws Exception{
		return JSONToMessage(RESTMethod.Get(
                baseUri +"subjects/"+subject_id+"/boards/"+board_id+"/messages/"+message_id,
                token));
	}

    /*
    * Get the message's metadata from a folder of a communication's resource (board, debate, forum) of the
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
    *   Message
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Message getSubjectsIdBoardsIdFoldersIdMessagesId(String token, String subject_id,
                                                                   String board_id,
                                                                   String folder_id,
                                                                   String message_id, String baseUri)
            throws Exception{
		return JSONToMessage(RESTMethod.Get(
                baseUri +"subjects/"+subject_id+"/boards/"+board_id+"/folders/"+
                        folder_id+"/messages/"+message_id, token));
	}
}
