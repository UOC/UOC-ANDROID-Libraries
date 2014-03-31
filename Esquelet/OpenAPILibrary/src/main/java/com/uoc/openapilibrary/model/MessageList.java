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

    /*
    * Get the list of messages of the Inbox folder of a communication's resource (board, debate, forum) of the
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
    * - start:          First element to return.
    *
    * - end:            Last element to return (all elements to return if this field is greater than the total
    *                   number of elements).
    *
    * - so (optional):  MessageSearchOptions object with the options needed to filter your search
    *
    * Response:
    *   MessageList
    *
     */
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

    /*
    * Get the list of unread messages of the Inbox folder of a communication's resource (board, debate, forum) of
    * the classroom.
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
    * - start:          First element to return.
    *
    * - end:            Last element to return (all elements to return if this field is greater than the total
    *                   number of elements).
    *
    * - so (optional):  MessageSearchOptions object with the options needed to filter your search
    *
    * Response:
    *   MessageList
    *
     */
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

    /*
    * Get the list of messages of a folder from a communication's resource (board, debate, forum) of the
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
    * - start:          First element to return.
    *
    * - end:            Last element to return (all elements to return if this field is greater than the total
    *                   number of elements).
    *
    * - so (optional):  MessageSearchOptions object with the options needed to filter your search
    *
    * Response:
    *   MessageList
    *
     */
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

    /*
    * Get the list of unread messages of a folder from a communication's resource (board, debate, forum) of the
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
    * - start:          First element to return.
    *
    * - end:            Last element to return (all elements to return if this field is greater than the total
    *                   number of elements).
    *
    * - so (optional):  MessageSearchOptions object with the options needed to filter your search
    *
    * Response:
    *   MessageList
    *
     */
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

    /*
    * Get the messages of folder Inbox. The number of message to obtain can be limited via start and end
    * parameters.
    * The user must have given the application the grant READ_MAIL to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - start:          First element to return.
    *
    * - end:            Last element to return (all elements to return if this field is greater than the total
    *                   number of elements).
    *
    * - so (optional):  MessageSearchOptions object with the options needed to filter your search
    *
    * Response:
    *   MessageList
    *
     */
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

    /*
    * Get the unread messages of folder Inbox. The number of message to obtain can be limited via start and end
    * parameters.
    * The user must have given the application the grant READ_MAIL to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - start:          First element to return.
    *
    * - end:            Last element to return (all elements to return if this field is greater than the total
    *                   number of elements).
    *
    * - so (optional):  MessageSearchOptions object with the options needed to filter your search
    *
    * Response:
    *   MessageList
    *
     */
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

    /*
    * Get the messages of folder id. The number of message to obtain can be limited via start and end
    * parameters.
    * The user must have given the application the grant READ_MAIL to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - start:          First element to return.
    *
    * - end:            Last element to return (all elements to return if this field is greater than the total
    *                   number of elements).
    *
    * - folder_id:      Folder's identifier.
    *
    * - so (optional):  MessageSearchOptions object with the options needed to filter your search
    *
    * Response:
    *   MessageList
    *
     */
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

    /*
    * Get the unread messages of folder id. The number of message to obtain can be limited via start and end
    * parameters.
    * The user must have given the application the grant READ_MAIL to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - start:          First element to return.
    *
    * - end:            Last element to return (all elements to return if this field is greater than the total
    *                   number of elements).
    *
    * - folder_id:      Folder's identifier.
    *
    * - so (optional):  MessageSearchOptions object with the options needed to filter your search
    *
    * Response:
    *   MessageList
    *
     */
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

    /*
    * Get the list of messages of the Inbox folder of a communication's resource (board, debate, forum) of the
    * subject.
    * The user must have given the application the grant READ_BOARD to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - start:          First element to return.
    *
    * - end:            Last element to return (all elements to return if this field is greater than the total
    *                   number of elements).
    *
    * - subject_id:     Subject's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * - so (optional):  MessageSearchOptions object with the options needed to filter your search
    *
    * Response:
    *   MessageList
    *
     */
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

    /*
    * Get the list of unread messages of the Inbox folder of a communication's resource (board, debate, forum) of
    * the subject.
    * The user must have given the application the grant READ_BOARD to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - start:          First element to return.
    *
    * - end:            Last element to return (all elements to return if this field is greater than the total
    *                   number of elements).
    *
    * - subject_id:     Subject's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * - so (optional):  MessageSearchOptions object with the options needed to filter your search
    *
    * Response:
    *   MessageList
    *
     */
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

    /*
    * Get the list of messages of a folder from a communication's resource (board, debate, forum) of the subject.
    * The user must have given the application the grant READ_BOARD to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - start:          First element to return.
    *
    * - end:            Last element to return (all elements to return if this field is greater than the total
    *                   number of elements).
    *
    * - subject_id:     Subject's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * - folder_id:      Folder's identifier.
    *
    * - so (optional):  MessageSearchOptions object with the options needed to filter your search
    *
    * Response:
    *   MessageList
    *
     */
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

    /*
    * Get the list of unread messages of a folder from a communication's resource (board, debate, forum) of the
    * subject.
    * The user must have given the application the grant READ_BOARD to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - start:          First element to return.
    *
    * - end:            Last element to return (all elements to return if this field is greater than the total
    *                   number of elements).
    *
    * - subject_id:     Subject's identifier.
    *
    * - board_id:       Identifier of the communication's resource.
    *
    * - folder_id:      Folder's identifier.
    *
    * - so (optional):  MessageSearchOptions object with the options needed to filter your search
    *
    * Response:
    *   MessageList
    *
     */
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