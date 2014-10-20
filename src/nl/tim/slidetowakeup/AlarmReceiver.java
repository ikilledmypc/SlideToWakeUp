package nl.tim.slidetowakeup;

import java.util.ArrayList;
import models.Node;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("ALARM", "ALARM FIRED!");
		Intent alarmIntent = new Intent(context,Alarm.class);
		alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		ArrayList<Node> nodes = intent.getParcelableArrayListExtra(MainActivity.NODES);
		alarmIntent.putParcelableArrayListExtra(MainActivity.NODES,nodes);
		context.startActivity(alarmIntent);
	}
	

}
