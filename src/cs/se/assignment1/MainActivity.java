package cs.se.assignment1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private static final String FILENAME = "countersfile.sav";
	private EditText editText;
	private Button addButton;
	private ListView listCounter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//called when user clicks the button
	//Save the EditText's text as counter's name
	//create a new activity for counter
	public void addCounter(View view){
		
		Intent intent = new Intent(this, CounterActivity.class);
		editText = (EditText) findViewById(R.id.editName);

	}

}
