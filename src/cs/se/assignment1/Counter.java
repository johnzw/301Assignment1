package cs.se.assignment1;
import java.util.ArrayList;
import java.util.Date;

public class Counter {
	private String name;
	private int totalcounts;
	private ArrayList<Count> counts;
	private int tempcounts;
	
	//Constructor
	public Counter(String name){
		this.name = name;
		this.totalcounts = 0;
		this.tempcounts = 0;
		counts = new ArrayList<Count>();
	}
	
	public void increment(){
		this.tempcounts++;
		this.totalcounts++;
		this.counts.add(new Count(new Date(System.currentTimeMillis())));
	}
	
	public void editName(String name){
		this.name = name;
	}
	
	public void reset(){
		this.tempcounts = 0;
		this.totalcounts = 0;
		counts = new ArrayList<Count>();
	}
	
	public void restart(){
		this.tempcounts = 0;
	}
	
	//the following four method are getters
	public String getName() {
		return name;
	}

	public int getTotalcounts() {
		return totalcounts;
	}

	public ArrayList<Count> getCounts() {
		return counts;
	}
	
	public int getTempcounts(){
		return tempcounts;
	}
	
	public String toString(){
		return this.getName()+"\t" + this.getTotalcounts();
	}
	
}
