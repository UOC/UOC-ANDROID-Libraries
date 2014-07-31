package edu.uoc.openapi.model;

import java.util.ArrayList;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;

public class BoardList {
	private ArrayList<Board> boards;

    public BoardList() {
        //Default constructor
    }

    public BoardList(ArrayList<Board> boards) {
        this.boards = boards;
    }

    public ArrayList<Board> getBoards() {
		return boards;
	}
	public void setBoards(ArrayList<Board> boards) {
		this.boards = boards;
	}

    private static BoardList JSONtoBoardList(String boardlist) {
        return new Gson().fromJson(boardlist, BoardList.class);
    }

    /*
    * Get the list of communication's resources (board, debate, forum) of the classroom.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:  Oauth access token.
    *
    * - id:     Clasroom's identifier
    *
    * Response:
    *   BoardList
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public BoardList getClassroomsIdBoards(String token, String id, String baseUri) throws Exception{
		return JSONtoBoardList(RESTMethod.Get(
                baseUri +"classrooms/"+id+"/boards",
                token));
	}

    /*
    * Get the list of communication's resources (board, debate, forum) of the subject.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - subject_id:     Subject's identifier
    *
    * Response:
    *   BoardList
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public BoardList getSubjectsIdBoards(String token, String subject_id,
                                         String baseUri) throws Exception{
		return JSONtoBoardList(RESTMethod.Get(
                baseUri +"subjects/"+subject_id+"/boards",
                token));
	}
}