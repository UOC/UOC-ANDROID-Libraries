package edu.uoc.openapi.model;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;

public class Classroom {
	private String id;
	private String title;
	private String color;
	private String fatherId;
	private String[] assignments;
    private String code;
    private String shortTitle;

	public Classroom() {
		//Default constructor
	}

    public Classroom(String id, String title, String color, String fatherId, String[] assignments,
                     String code, String shortTitle) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.fatherId = fatherId;
        this.assignments = assignments;
        this.code = code;
        this.shortTitle = shortTitle;
        /*falta una lista que contiene
            - contentType
            - format
            - url
         */
    }

    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	public String[] getAssignments() {
		return assignments;
	}
	public void setAssignments(String[] assignments) {
		this.assignments = assignments;
	}
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getShorttitle() {
        return shortTitle;
    }
    public void setShorttitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    private static Classroom JSONToClassRoom(String classRoomJSON) {
		return new Gson().fromJson(classRoomJSON, Classroom.class);
	}

    /*
    * Get classroom's data.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:  Oauth access token.
    *
    * - id:     Clasroom's identifier
    *
    * Response:
    *   Classroom
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Classroom getClassroomsId(String token, String id, String baseUri) throws Exception{
		return JSONToClassRoom(RESTMethod
				.Get(baseUri +"classrooms/"+id,
						token));
	}

    /*
    * Get subject's data.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - subject_id:     Subject's identifier
    *
    * Response:
    *   Classroom
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Classroom getSubjectsId(String token, String subject_id, String baseUri) throws Exception{
		return JSONToClassRoom(RESTMethod
				.Get(baseUri +"subjects/"+subject_id,
						token));
	}
}
