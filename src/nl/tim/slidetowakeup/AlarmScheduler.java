package nl.tim.slidetowakeup;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmScheduler {
	private static final AlarmScheduler INSTANCE = new AlarmScheduler(); 
	
	public static final AlarmScheduler getInstance() {
		return INSTANCE;
	}
	
	
	public void schedule(Calendar date,Context context){
		AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, Alarm.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

		// Set the alarm to start at 8:30 a.m.
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(System.currentTimeMillis());
//		calendar.set(Calendar.HOUR_OF_DAY, 8);
//		calendar.set(Calendar.MINUTE, 30);

		// setRepeating() lets you specify a precise custom interval--in this case,
		// 20 minutes.
		if(android.os.Build.VERSION.SDK_INT >19 ){
			alarmMgr.setExact(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), alarmIntent);
		}
		else{
			alarmMgr.set(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), alarmIntent);
		}
		
	}
}
