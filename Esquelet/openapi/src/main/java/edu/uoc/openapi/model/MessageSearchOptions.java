package edu.uoc.openapi.model;


public class MessageSearchOptions {
	private String to;
	private String from;
	private String subject;
	private String before;
	private String after;
	private String recurse;

	public MessageSearchOptions() {
		this.to = "";
		this.from = "";
		this.subject = "";
		this.before = "";
		this.after = "";
		this.recurse = "";
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	public String getAfter() {
		return after;
	}
	public void setAfter(String after) {
		this.after = after;
	}
	public String getRecurse() {
		return recurse;
	}
	public void setRecurse(String recurse) {
		this.recurse = recurse;
	}
	
	public String toUrlParameter(){
		String result ="&q=";
		if(!this.to.equals("")) result = result+"to:"+this.to+" ";
		if(!this.from.equals("")) result = result+"from:"+this.from+" ";
		if(!this.subject.equals("")) result = result+"subject:"+this.subject+" ";
		if(!this.before.equals("")) result = result+"before:"+this.before+" ";
		if(!this.after.equals("")) result = result+"after:"+this.after+" ";
		if(!this.recurse.equals("")) result = result+"recurse:"+this.recurse;
		if(result.equals("&q=")) result = "";
		return result;
	}
}