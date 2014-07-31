package edu.uoc.esquelet.app;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class DialogDate extends DialogFragment implements OnDateSetListener {
	// "button" is the id of the button used to call this function. This way, we can differentiate the button that
    // started this and set its text to the chosen value after we are done.
	private static Button button; 
	
	  public static DialogDate newInstance(Button bstartend) {
	    // Create a new Fragment instance with the specified 
	    // parameters.
		button = bstartend;
	    return new DialogDate();
	  }
	  
	  @Override
	  public Dialog onCreateDialog(Bundle savedInstanceState) {
	    // Create the new Dialog using the AlertBuilder.
		Calendar c = Calendar.getInstance();
		int year;
		int month;
		int day;
		DatePickerDialog  dP;
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		dP = new DatePickerDialog(getActivity(),this,year,month,day);
		dP.getDatePicker().setMinDate(c.getTimeInMillis());
	    // Configure the Dialog UI.
	    dP.setTitle("Choose a date");

	    // Return the configured Dialog.
	    return dP;
	  }
	  
	  // We want 2 digits both for month and day, so we have to add a 0 character if the 
	  // selected date has a one digit field. Then we set the text of the button with the date the user has chosen.
	  public void onDateSet(DatePicker view, int year, int month, int day) {
		  String auxday = String.valueOf(day);
		  String auxmonth = String.valueOf(month+1);
		  if (month < 10) auxmonth = '0'+ auxmonth;
		  if (day < 10) auxday = '0'+ auxday;
		  button.setText(String.valueOf(year)+"-"+auxmonth+"-"+auxday);
			
	  }


}
