package cs.se.assignment1;

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
