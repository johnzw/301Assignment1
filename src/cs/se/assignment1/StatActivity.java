package cs.se.assignment1;

import java.util.ArrayList;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

/**
 * @author  wzhong3
 */
public class StatActivity extends Activity {
	private ListView listview;
	private ArrayAdapter<String> adapter;
	/**
	 * @uml.property  name="counter"
	 * @uml.associationEnd  
	 */
	private Counter counter;
	private ArrayList<String> statList =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stat);
		// Show the Up button in the action bar.
		setupActionBar();
		listview = (ListView)findViewById(R.id.listview_stat);
		
		
		// Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(CounterActivity.EXTRA_MESSAGE);
	    
	    Gson gson = new Gson();
	    counter = gson.fromJson(message, Counter.class);
	    
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stat, menu);
		return true;
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
		statList = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, statList);
		listview.setAdapter(adapter);
	}
	
	public void OrderByMin(View view){
		ArrayList<String> temp = counter.countsPerMin();
		statList.clear();
		for(int i=0; i< temp.size(); i++){
			statList.add(temp.get(i));
		}
		adapter.notifyDataSetChanged();
	}
	
	public void OrderByHour(View view){
		ArrayList<String> temp = counter.countsPerHour();
		statList.clear();
		for(int i=0; i< temp.size(); i++){
			statList.add(temp.get(i));
		};
		adapter.notifyDataSetChanged();
	}
	
	public void OrderByDay(View view){
		ArrayList<String> temp = counter.countsPerDay();
		statList.clear();
		for(int i=0; i< temp.size(); i++){
			statList.add(temp.get(i));
		}
		adapter.notifyDataSetChanged();
	}
	
	public void OrderByWeek(View view){
		ArrayList<String> temp = counter.countsPerWeek();
		statList.clear();
		for(int i=0; i< temp.size(); i++){
			statList.add(temp.get(i));
		}
		adapter.notifyDataSetChanged();
	}
	
	public void OrderByMonth(View view){
		ArrayList<String> temp = counter.countsPerMonth();
		statList.clear();
		for(int i=0; i< temp.size(); i++){
			statList.add(temp.get(i));
		}
		adapter.notifyDataSetChanged();
	}
	

}
