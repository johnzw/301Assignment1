package cs.se.assignment1;

import java.util.Calendar;
import java.util.Date;

//each count has timestamp;

/*
 * unfished
 * still need to calculate the day of the week
 * 
 */

/**
 * @author  wzhong3
 */
public class Count {
	/**
	 * @uml.property  name="timestamp"
	 */
	private Date timestamp;
	/**
	 * @uml.property  name="year"
	 */
	private int year;
	/**
	 * @uml.property  name="month"
	 */
	private int month;
	/**
	 * @uml.property  name="date"
	 */
	private int date;
	/**
	 * @uml.property  name="hour"
	 */
	private int hour;
	/**
	 * @uml.property  name="min"
	 */
	private int min;
	private int dayofweek;
	
	/**
	 * @return
	 * @uml.property  name="timestamp"
	 */
	public Date getTimestamp(){
		return timestamp;
	}
	
	/**
	 * @return
	 * @uml.property  name="year"
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @return
	 * @uml.property  name="month"
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @return
	 * @uml.property  name="hour"
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * @return
	 * @uml.property  name="min"
	 */
	public int getMin() {
		return min;
	}
	
	/**
	 * @return
	 * @uml.property  name="date"
	 */
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
