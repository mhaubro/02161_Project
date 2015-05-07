package main;

import java.util.Calendar;

public class Timespan {
	
	final Calendar startTime, finishTime;
	
	public Timespan(Calendar start, Calendar finish){
		this.startTime = start;
		this.finishTime = finish;
	}
	
	public boolean overlap(Timespan t){
		
		
		if (this.startTime.compareTo(t.finishTime) <= 0 && this.startTime.compareTo(t.startTime) >= 0)
			return true;
		if (this.finishTime.compareTo(t.finishTime) <= 0 && this.finishTime.compareTo(t.startTime) >= 0)
			return true;
		
		if (t.startTime.compareTo(this.finishTime) <= 0 && t.startTime.compareTo(this.startTime) >= 0)
			return true;
		
		return false;
	}
	
	public double getTime(){
		long diff = finishTime.getTimeInMillis() - startTime.getTimeInMillis();
		double diffInHours = (double) diff / (1000 * 60 * 60);
		
		return diffInHours;
	}
	
}
