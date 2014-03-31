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

    /*
    * Get the events of the calendar. If there isn't any parameter it return all the events of the current week.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token: Oauth access token.
    *
    * - Start (optional):  	Start date in ISO8601 format (optional). If end parameter is not informed it
    *                       returns the events of the next 30 days.
    *
    * - End (optional):     End date in IS08601 format (optional). If start parameter is not informed it returns
    *                       the events of the previous 30 days.
    *
    * Response:
    *   EventList
    *
     */
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