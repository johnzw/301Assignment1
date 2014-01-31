package cs.se.assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;

public class CounterActivity extends Activity {
		private static final String FILENAME2 = "tempcounterfile.sav";
		private static final String FILENAME1 = "countersfile.sav";
		public final static String EXTRA_MESSAGE = "se.assignment1.CounterActivity.MESSAGE";

		private Counter theCounter;
		private TextView nameText;
		private TextView countsText;
		private ArrayList<Counter> counterList = null;
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
		
		//every time increment clicked, the fixed counter is saved into file
		saveInFile(theCounter);
	}
	
	public void reset(View view){
		theCounter.reset();
		countsText.setText(""+theCounter.getTotalcounts());
		
		saveInFile(theCounter);
	}
	
	public void rename(View view){
		// 1. Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final EditText input = new EditText(this);
		builder.setView(input);
		builder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   String value = input.getText().toString();
		            counterList = new ArrayList<Counter>();
		            loadFromFile();
		            
		            //check if changed name has existed before

		    		for(int i=0; i<counterList.size(); i++){
		    			if(counterList.get(i).getName().equals(value)){
		    				alertName();
		    				return;
		    			}
		    		}
		    		
		    		for(int i=0; i<counterList.size(); i++){
		    			if(counterList.get(i).getName().equals(theCounter.getName())){
		    				counterList.get(i).editName(value);
		    				break;
		    			}
		    		}
		    		saveInFile();
		    		theCounter.editName(value);
		    		nameText.setText(theCounter.getName());
	           }
	    });
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User cancelled the dialog
	           }
	    });

		AlertDialog alert = builder.create();	
		alert.show();
	}
	
	public void alertName(){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Sorry");
		alertDialog.setMessage("the name has already existed, please change a name.");
		alertDialog.show();
	}
	public void remove(View view){
		theCounter.delete();
		saveInFile(theCounter);
		
		finish();
	}
	
	public void summarize(View view){
		Gson gson = new Gson();
		Intent intent = new Intent(this, StatActivity.class);
	    String message = gson.toJson(theCounter);
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
	
	
	private void saveInFile(Counter counter) {
		Gson gson = new Gson();
		try {
			FileOutputStream fos = openFileOutput(FILENAME2,
					Context.MODE_PRIVATE);
			fos.write((gson.toJson(counter)).getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadFromFile(){
		Gson gson = new Gson();
		Counter counter;
		
		try{
			FileInputStream fis = openFileInput(FILENAME1);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			//each line is a json object
			String line = in.readLine();
			while(line != null){
				counter = gson.fromJson(line, Counter.class);
				counterList.add(counter);
				line = in.readLine();
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//very similar to saveinFile, but this time it overwrite the file with the counterList
	private void saveInFile() {
		Gson gson = new Gson();
		try {
			FileOutputStream fos = openFileOutput(FILENAME1,
					Context.MODE_PRIVATE);
			for(int i=0; i<counterList.size(); i++){
				fos.write((gson.toJson(counterList.get(i))+"\n").getBytes());
			}
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
