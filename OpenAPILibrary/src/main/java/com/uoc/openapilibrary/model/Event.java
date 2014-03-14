package com.uoc.openapilibrary.model;

import com.google.gson.Gson;
import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;

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
		super();
		this.id = id;
		this.url = url;
		this.summary = summary;
		this.start = start;
		this.end = end;
	}
	
	//Used when retrieving only one event
	private static Event JSONtoEvent(String event) {
		Gson gs = new Gson();
		Event aux = gs.fromJson(event, Event.class);
		return aux;
	}
	
	//Parse the Event newEvent and transform it into a string with the json format of the event.
	private static String toJSON(Event newEvent) {
		return new Gson().toJson(newEvent);
	}
	
	public static Event getCalendarEventsId(String token, String id) {
		return JSONtoEvent(RESTMethod
				.Get(Constants.BASEURI +"calendar/events/"+id,
						token));
	}
	
	//Makes a Post call to the OPEN API url for events. This call is used to create new events.
	public static Event postCalendarEvents(String token, Event newEvent) {
		return JSONtoEvent(RESTMethod
				.Post(Constants.BASEURI +"calendar/events",
						token, toJSON(newEvent)));
	}
}
