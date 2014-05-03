package nl.tim.slidetowakeup;


import java.util.ArrayList;

import views.SlideScreenView;
import models.Node;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {
	private SlideScreenView slideScreen;
	public final static String NODES = "nl.tim.slidetowakeup.NODES";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	
}
