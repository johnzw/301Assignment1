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
import android.content.Context;
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
	private ArrayList<Counter> counterList;
	
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
		//Get the name from EditText, without considering the blank one, without name checking 
		editText = (EditText) findViewById(R.id.editName);
		String name = editText.getText().toString();
		
		//use this name to create a counter
		Counter counter = new Counter(name);
		this.saveInFile(counter);
		
		//Add counter to exsiting counter list
		counterList.add(counter);
		
		
	}
	
	private void saveInFile(Counter counter) {
		Gson gson = new Gson();
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			fos.write((gson.toJson(counter)+"\n").getBytes());
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
