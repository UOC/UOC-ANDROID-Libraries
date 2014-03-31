package com.uoc.esquelet.app;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

public class DialogTime extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
	
	// "button" is the id of the button used to call this function. This way, we can differentiate the button that
	// started this and set its text to the chosen value after we are done.
	private static Button button;
	
	  public static DialogTime newInstance(Button bstartend) {
	    // Create a new Fragment instance with the specified 
	    // parameters.
		button = bstartend;
		DialogTime fragment = new DialogTime();
	    return fragment;
	  }
	  
	  @Override
	  public Dialog onCreateDialog(Bundle savedInstanceState) {
	    // Create the new Dialog using the AlertBuilder.
		final Calendar c = Calendar.getInstance();
		int hour;
		int minute;
		TimePickerDialog  dP;
		hour = c.get(Calendar.YEAR);
		minute = c.get(Calendar.MONTH);
		dP = new TimePickerDialog(getActivity(),this,hour,minute,DateFormat.is24HourFormat(getActivity()));
	    // Configure the Dialog UI.
	    dP.setTitle("Choose the time");

	    // Return the configured Dialog.
	    return dP;
	  }
	  
	  // We want 2 digits both for hours and minutes, so we have to add a 0 character if the 
	  // selected time has a one digit field. Then we set the text of the button with the hour the user has chosen.
	public void onTimeSet(TimePicker view, int hour, int minute) {
		String auxhour = String.valueOf(hour);
		String auxminute = String.valueOf(minute);
		if (hour < 10) auxhour = '0'+ auxhour;
		if (minute < 10) auxminute = '0'+ auxminute;
		button.setText(auxhour+":"+auxminute);
	}


}
