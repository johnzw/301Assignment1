package cs.se.assignment1;
import java.util.ArrayList;
import java.util.Date;

public class Counter {
	private String name;
	private int totalcounts;
	private ArrayList<Count> counts;
	private int tempcounts;
	private boolean isDeleted = false;
	private String [] MONTH = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", 
								"JUL", "AUG", "SEPT", "OCT", "NOV", "DEC"};
	
	//Constructor
	public Counter(String name){
		this.name = name;
		this.totalcounts = 0;
		this.tempcounts = 0;
		counts = new ArrayList<Count>();
	}
	
	public void delete(){
		isDeleted = true;
	}
	
	public boolean isDeleted(){
		return this.isDeleted;
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
	
	//the following are statistic part
	public ArrayList<String> countsPerMonth(){
		ArrayList<String> list = new ArrayList<String>();
		int numbers = 1;
		
		if(counts.size() == 0){
			return list;
		}
		else if(counts.size() == 1){
			addInfoMonth(list, 0, numbers);
			return list;
		}
		else{
			int i;
			for(i= 1;i< counts.size(); i++){
				if(counts.get(i-1).getYear() == counts.get(i).getYear()){
					if(counts.get(i-1).getMonth() == counts.get(i).getMonth()){
						numbers++;
					}
					else{
						addInfoMonth(list, i-1, numbers);
						numbers =1;
					}
				}
				else{
					addInfoMonth(list, i-1, numbers);
					numbers =1;
				}
			}
			
			addInfoMonth(list, i-1, numbers);
			
			return list;	
		}	
	}
	
	public ArrayList<String> countsPerWeek(){
		ArrayList<String> list = new ArrayList<String>();
		int numbers = 1;
		
		if(counts.size() == 0){
			return list;
		}
		else if(counts.size() == 1){
			addInfoWeek(list, 0, numbers);
			return list;
		}
		else{
			int i;
			for(i= 1;i< counts.size(); i++){
				Count count = counts.get(i);
				if(counts.get(i-1).inSameWeek(count)){
					numbers++;
				}
				else{
					addInfoWeek(list, i-1, numbers);
					numbers =1;
				}
				
				
			}
			
			addInfoWeek(list, i-1, numbers);
			
			return list;	
		}	
	}
	
	public ArrayList<String> countsPerDay(){
		ArrayList<String> list = new ArrayList<String>();
		int numbers = 1;
		
		if(counts.size() == 0){
			return list;
		}
		else if(counts.size() == 1){
			addInfoDay(list, 0, numbers);
			return list;
		}
		else{
			int i;
			for(i= 1;i< counts.size(); i++){
				if(counts.get(i-1).getYear() == counts.get(i).getYear()){
					if(counts.get(i-1).getMonth() == counts.get(i).getMonth()){
						if(counts.get(i-1).getDate() == counts.get(i).getDate())
							numbers++;
						else{
							addInfoDay(list, i-1, numbers);
							numbers =1;
						}
					}
					else{
						addInfoDay(list, i-1, numbers);
						numbers =1;
					}
				}
				else{
					addInfoDay(list, i-1, numbers);
					numbers =1;
				}
			}
			
			addInfoDay(list, i-1, numbers);
			//list.add("TESTING");
			//System.out.println("Here");
			return list;	
		}
		
	}
	
	public ArrayList<String> countsPerHour(){
		ArrayList<String> list = new ArrayList<String>();
		int numbers = 1;
		
		if(counts.size() == 0){
			return list;
		}
		else if(counts.size() == 1){
			addInfoHour(list, 0, numbers);
			return list;
		}
		else{
			int i;
			for(i= 1;i< counts.size(); i++){
				if(counts.get(i-1).getYear() == counts.get(i).getYear()){
					if(counts.get(i-1).getMonth() == counts.get(i).getMonth()){
						if(counts.get(i-1).getDate() == counts.get(i).getDate())
							if(counts.get(i-1).getHour() == counts.get(i).getHour())
								numbers++;
							else{
								addInfoHour(list, i-1, numbers);								
								numbers =1;
							}
						else{
							addInfoHour(list, i-1, numbers);
							numbers =1;
						}
					}
					else{
						addInfoHour(list, i-1, numbers);						
						numbers =1;
					}
				}
				else{
					addInfoHour(list, i-1, numbers);
					numbers =1;
				}
			}
			
			addInfoHour(list, i-1, numbers);			
			//list.add("TESTING");
			//System.out.println("Here");
			return list;	
		}
		
	}
	
	public ArrayList<String> countsPerMin(){
		ArrayList<String> list = new ArrayList<String>();
		int numbers = 1;
		
		if(counts.size() == 0){
			return list;
		}
		else if(counts.size() == 1){
			addInfoMin(list, 0, numbers);
			return list;
		}
		else{
			int i;
			for(i= 1;i< counts.size(); i++){
				if(counts.get(i-1).getYear() == counts.get(i).getYear()){
					if(counts.get(i-1).getMonth() == counts.get(i).getMonth()){
						if(counts.get(i-1).getDate() == counts.get(i).getDate())
							if(counts.get(i-1).getHour() == counts.get(i).getHour())
								if(counts.get(i-1).getMin() == counts.get(i).getMin())
									numbers++;
								else{
									addInfoMin(list, i-1, numbers);								
									numbers =1;
								}
							else{
								addInfoMin(list, i-1, numbers);									
								numbers =1;
							}
						else{
							addInfoMin(list, i-1, numbers);	
							numbers =1;
						}
					}
					else{
						addInfoMin(list, i-1, numbers);							
						numbers =1;
					}
				}
				else{
					addInfoMin(list, i-1, numbers);	
					numbers =1;
				}
			}
			
			addInfoMin(list, i-1, numbers);			
			//list.add("TESTING");
			//System.out.println("Here");
			return list;	
		}
		
	}
	
	public void addInfoHour(ArrayList<String> list, int i, int numbers){
		list.add("Hour of "+MONTH[counts.get(i).getMonth()]+" "+counts.get(i).getDate()+" "+counts.get(i).getHour()+":00"+" -- "+ numbers);
	}
	
	public void addInfoDay(ArrayList<String> list, int i, int numbers){
		list.add("Day of "+MONTH[counts.get(i).getMonth()]+" "+counts.get(i).getDate()+" "+counts.get(i).getYear()+" -- "+ numbers);
	}
	
	public void addInfoWeek(ArrayList<String> list, int i, int numbers){
		list.add("Week of "+MONTH[counts.get(i).getMonth()]+" "+counts.get(i).getDate()+" "+counts.get(i).getYear()+" -- "+ numbers);
	}
	
	public void addInfoMonth(ArrayList<String> list, int i, int numbers){
		list.add("Month of "+MONTH[counts.get(i).getMonth()]+" "+counts.get(i).getYear()+" -- "+ numbers);
	}
	
	public void addInfoMin(ArrayList<String> list, int i, int numbers){
		list.add("Minute of "+MONTH[counts.get(i).getMonth()]+" "+counts.get(i).getDate()+" "+counts.get(i).getHour()+":"+counts.get(i).getMin()+" -- "+ numbers);
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
		return "Name: "+this.getName()+"  Total counts: " + this.getTotalcounts();
	}
	
	
	
}
