package cs.se.assignment1;

import java.util.Calendar;
import java.util.Date;

//each count has timestamp;

/*
 * unfished
 * still need to calculate the day of the week
 * 
 */

public class Count {
	private Date timestamp;
	private int year;
	private int month;
	private int date;
	private int hour;
	private int min;
	private int dayofweek;
	
	public Date getTimestamp(){
		return timestamp;
	}
	
	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getHour() {
		return hour;
	}

	public int getMin() {
		return min;
	}
	
	public int getDate() {
		return date;
	}

	public int getDayOfWeek(){
		return this.dayofweek;
	}
	
	//some bugs with this method
	public boolean inSameWeek(Count count){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(count.getTimestamp());
		
		Calendar c2 = Calendar.getInstance();
		c2.setTime(this.getTimestamp());
		
		int week1 = c1.WEEK_OF_YEAR;
		int week2 = c2.WEEK_OF_YEAR;
		
		if(c1.YEAR == c2.YEAR){
			if(week1 == week2)
				return true;
			else
				return false;
		}
		else{
			return false;
		}
	}
	
	
	public Count(Date date){
		this.timestamp = date;
		this.year = this.timestamp.getYear()+1900;
		this.month = this.timestamp.getMonth();
		this.date = this.timestamp.getDate();
		this.hour = this.timestamp.getHours();
		this.min = this.timestamp.getMinutes();
		this.dayofweek = this.timestamp.getDay();
		
	}
	
	
}
