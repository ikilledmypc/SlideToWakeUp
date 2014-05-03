package nl.tim.slidetowakeup;

import java.util.ArrayList;

import models.Node;
import views.DisableAlarmSlide;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Alarm extends Activity {
	private DisableAlarmSlide disableAlarmSlide;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		disableAlarmSlide = (DisableAlarmSlide) findViewById(R.id.disableAlarmSlide);
		ArrayList<Node> nodes = getIntent().getParcelableArrayListExtra(MainActivity.NODES);
		disableAlarmSlide.setNodes(nodes);
	}
	public void displayMessage(){

	}
	
}
