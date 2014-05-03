package nl.tim.slidetowakeup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainMenu extends Activity {
	private Button patternButton;
	private Button setTimeButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		patternButton = (Button) findViewById(R.id.btn_setPattern);
		setTimeButton = (Button) findViewById(R.id.btn_setTime);
	}
	
	public void editPatern(View view){
		Intent editPaternIntent = new Intent(this,MainActivity.class);
		startActivity(editPaternIntent);
	}
	public void setTime(View view){
		Intent editPaternIntent = new Intent(this,SetTime.class);
		startActivity(editPaternIntent);
	}
}
