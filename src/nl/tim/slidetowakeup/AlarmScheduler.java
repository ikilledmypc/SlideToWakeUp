package nl.tim.slidetowakeup;

import java.util.ArrayList;
import java.util.Calendar;

import models.Node;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmScheduler {
	private static final AlarmScheduler INSTANCE = new AlarmScheduler(); 
	
	public static final AlarmScheduler getInstance() {
		return INSTANCE;
	}
	
	
	public void schedule(Calendar date,Context context, ArrayList<Node> nodes){
		AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmReceiver.class);
		intent.putParcelableArrayListExtra(MainActivity.NODES,nodes);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

		if(android.os.Build.VERSION.SDK_INT >19 ){
			Log.d("WARNING", "SDK > 19 USING SETEXACT");
			alarmMgr.setExact(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), alarmIntent);
		}
		else{
			Log.d("WARNING", "SDK < 19 USING SET");
			alarmMgr.set(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), alarmIntent);
		}
		
	}
}
