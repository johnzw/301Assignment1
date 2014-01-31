package cs.se.assignment1;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class CounterActivity extends Activity {
		private Counter theCounter;
		private TextView nameText;
		private TextView countsText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Intent intent = getIntent();
		Gson gson = new Gson();
		
		//get counter object from the string
		theCounter = gson.fromJson(intent.getStringExtra(MainActivity.EXTRA_MESSAGE), Counter.class);
		
		//find all these references
		nameText = (TextView)findViewById(R.id.name_counter);
		countsText = (TextView)findViewById(R.id.text_counts);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onStart(){
		super.onStart();
		nameText.setText(theCounter.getName());
		countsText.setText(""+theCounter.getTotalcounts());
	}
	
	//called when user click the "increment" button
	//and the total counts in the middle of screen would increment by 1
	public void increment(View view){
		theCounter.increment();
		countsText.setText(""+theCounter.getTotalcounts());
	}
	
	
}
