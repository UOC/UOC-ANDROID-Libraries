package com.uoc.openapilibrary.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;

public class FolderList {
	private ArrayList<Folder> folders;

    public FolderList() {
        //Default constructor
    }
	public ArrayList<Folder> getFolders() {
		return folders;
	}
	public void setFolders(ArrayList<Folder> folders) {
		this.folders = folders;
	}
	
	private static FolderList JSONToFolderList(String folderList) {
		return new Gson().fromJson(folderList, FolderList.class);
	}

    /*
    * Get the list of folders of a communication's resource (board, debate, forum) of the classroom.
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
    *   FolderList
    *
     */
	public static FolderList getClassroomsIdBoardsIdFolders(String token, String domain_id, String board_id) {
		String fl = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/boards/"+board_id+"/folders",
				token);

		return JSONToFolderList(fl);
	}

    /*
    * Get the folder list of the user that is using the application.
    * The name of the folder has the names of the uplevel folder. So, if there is a folder named “Test” inside the
    * folder named “Inbox”, the name of the resource that represent the folder “Test” will be “Inbox – Test”.
    * The user must have given the application the grant READ_MAIL to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * Response:
    *   FolderList
    *
     */
	public static FolderList getMailFolders(String token) {
		String fl = RESTMethod.Get(
				Constants.BASEURI +"mail/folders",
				token);

		return JSONToFolderList(fl);
	}

    /*
    * Get the list of folders of a communication's resource (board, debate, forum) of the subject.
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
    *   FolderList
    *
     */
	public static FolderList getSubjectsIdBoardsIdFolders(String token, String subject_id, String board_id) {
		String fl = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/boards/"+board_id+"/folders",
				token);

		return JSONToFolderList(fl);
	}
}
