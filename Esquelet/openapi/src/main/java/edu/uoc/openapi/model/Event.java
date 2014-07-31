package edu.uoc.openapi.model;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;

//Decided to use string to represent dates just to make it easier to receive and send json objects.
public class Event {
	private String id;
	private String url;
	private String summary;
	private String start;
	private String end;
	
	public Event() {
		//Default constructor
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
	public Event(String id, String url, String summary, String start, String end) {
		this.id = id;
		this.url = url;
		this.summary = summary;
		this.start = start;
		this.end = end;
	}
	
	//Used when retrieving only one event
	private static Event JSONtoEvent(String event) {
		return new Gson().fromJson(event, Event.class);
	}

    /*
    * Retrieves an existing event.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:      Oauth access token.
    *
    * - id:  	    Id of the event you want to retrieve.
    *
    * Response:
    *   Event
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Event getCalendarEventsId(String token, String id, String baseUri) throws Exception{
		return JSONtoEvent(RESTMethod
				.Get(baseUri +"calendar/events/"+id,
						token));
	}

    /*
    * Creates a new event.
    * The user must have given the application the grant WRITE to use these operation.
    *
    * Parameters:
    *
    * - token:      Oauth access token.
    *
    * - newEvent:  	The new event that you wish to create.
    *
    * Response:
    *   Event
    *
    *   If Post call to the server fails, it throws an exception
    *
     */
	public Event postCalendarEvents(String token, Event newEvent, String baseUri) throws Exception{
		return JSONtoEvent(RESTMethod
				.Post(baseUri +"calendar/events",
						token, newEvent));
	}
}
