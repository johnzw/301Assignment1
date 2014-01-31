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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private static final String FILENAME = "countersfile.sav";
	private EditText editText;
	private Button addButton;
	private ListView listCounter;
	private ArrayList<Counter> counterList = null;
	private ArrayAdapter<Counter> adapter;
	public final static String EXTRA_MESSAGE = "assignment1.MainActivity.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//let ListCounter get reference
		listCounter = (ListView) findViewById(R.id.counterList);
		editText = (EditText) findViewById(R.id.editName);
	}                     

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void onStart(){
		super.onStart();
		counterList = new ArrayList<Counter>();
		
		//load every Counter into counterList
		loadFromFile();
		
		adapter = new ArrayAdapter<Counter>(this, android.R.layout.simple_list_item_1, counterList);
		listCounter.setAdapter(adapter);
		
		//Add Listener to listView, such that user can see more information about the counter when he click on it
		listCounter.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Counter counter = adapter.getItem(position);
				createAnotherActivity(counter);
			}
		});
		
	}
	
	//called when user clicks the button
	//Save the EditText's text as counter's name
	//create a new activity for counter
	public void addCounter(View view){
		
		
		//Get the name from EditText, without considering the blank one, without name checking 
		String name = editText.getText().toString();
		
		//use this name to create a counter
		Counter counter = new Counter(name);
		this.saveInFile(counter);
		//Add counter to exsiting counter list
		counterList.add(counter);
		adapter.notifyDataSetChanged();
		editText.setText("");
		
		this.createAnotherActivity(counter);
	}
	
	//send counter to another activity, and start that activity
	private void createAnotherActivity(Counter counter){
		Intent intent = new Intent(this, CounterActivity.class);
		Gson gson = new Gson();
		intent.putExtra(this.EXTRA_MESSAGE, gson.toJson(counter));
		startActivity(intent); 
	}
	
	//Load everything from counterlist file, and store it in memory, and make it ready to present to screen
	private void loadFromFile(){
		Gson gson = new Gson();
		Counter counter;
		
		try{
			FileInputStream fis = openFileInput(FILENAME);
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
	//using gson to convert counter into Json, and add it to the end of file
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
