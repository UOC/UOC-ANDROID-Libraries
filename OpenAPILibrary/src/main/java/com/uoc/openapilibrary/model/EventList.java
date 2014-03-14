package com.uoc.openapilibrary.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;

public class EventList {
	private ArrayList<Event> events;

    public EventList() {
        //Default constructor
    }
	public ArrayList<Event> getEvents() {
		return events;
	}
	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}

    private static EventList JSONtoEventList(String eventlist) {
        return new Gson().fromJson(eventlist, EventList.class);
    }
	
	public static EventList getCalendarEvents(String token) {
		String evl = RESTMethod.Get(
				Constants.BASEURI +"calendar/events/",
				token);

		return JSONtoEventList(evl);
	}
	
	public static EventList getCalendarEvents(String token, String start, String end) {
		String evl = RESTMethod.Get(
				Constants.BASEURI +"calendar/events/",
				token, "&start="+start+"&end="+end);

		return JSONtoEventList(evl);
	}
	
	public static EventList getCalendarEventsStart(String token, String start) {
		String evl = RESTMethod.Get(
				Constants.BASEURI +"calendar/events/",
				token, "&start="+start);

		return JSONtoEventList(evl);
	}
	
	public static EventList getCalendarEventsEnd(String token, String end) {
		String evl = RESTMethod.Get(
				Constants.BASEURI +"calendar/events/",
				token, "&end="+end);

		return JSONtoEventList(evl);
	}
}