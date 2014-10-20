package nl.tim.slidetowakeup;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


import views.SlideScreenView;
import models.Node;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TimePicker;

public class MainActivity extends Activity {
	private SlideScreenView slideScreen;
	private static Context context;
	public final static String NODES = "nl.tim.slidetowakeup.NODES";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		setContentView(R.layout.activity_main);
		slideScreen = (SlideScreenView) findViewById(R.id.slideScreenview);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent me){
		super.onTouchEvent(me);
		return true;
	}
	
	public void clearPattern(View view){
		slideScreen.clearNodes();
	}
	
	public void savePattern(View view){
		Intent savePaternIntent = new Intent(this,Alarm.class);
		savePaternIntent.putParcelableArrayListExtra(NODES,slideScreen.getNodes());
		startActivity(savePaternIntent);
	}
	
	
	public void setAlarm(View view) {
		DialogFragment newFragment = new TimePickerFragment(slideScreen.getNodes());
		newFragment.show(getFragmentManager(), "timePicker");
	}

	public static class TimePickerFragment extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {
		private int callCount;
		private ArrayList<Node> nodes;

		public TimePickerFragment(ArrayList<Node> nodes) {
			// TODO Auto-generated constructor stub
			this.nodes = nodes;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);

			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
					DateFormat.is24HourFormat(getActivity()));
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// Do something with the time chosen by the user
			if (callCount < 1) {
				AlarmScheduler as = AlarmScheduler.getInstance();
				Calendar c = new GregorianCalendar();
				c.set(Calendar.HOUR_OF_DAY, hourOfDay);
				c.set(Calendar.MINUTE, minute);
				c.set(Calendar.SECOND, 0);
				as.schedule(c, context,nodes);
			}
			callCount++;
		}
	}
	
}
