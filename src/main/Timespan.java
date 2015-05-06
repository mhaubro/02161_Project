package main;

import java.util.Calendar;

import exceptions.OperationNotAllowedException;

public class Timespan {
	
	final Calendar startTime, finishTime;
	
	public Timespan(Calendar start, Calendar finish) throws OperationNotAllowedException{
		if (start.after(finish))
			throw new OperationNotAllowedException("cant make a negative timespan");
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
		if (t.finishTime.compareTo(this.finishTime) <= 0 && t.finishTime.compareTo(this.startTime) >= 0)
			return true;
		
		return false;
	}
	
	public double getTime(){
		long diff = finishTime.getTimeInMillis() - startTime.getTimeInMillis();
		double diffInHours = (double) diff / (1000 * 60 * 60);
		
		return diffInHours;
	}
	
	public String toString(){
		return "from " + getCalendarString(startTime) + " to " + getCalendarString(finishTime);
	}
	
	private String getCalendarString(Calendar cal){
		return "(" + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH)+1) + ") " + cal.get(Calendar.HOUR_OF_DAY) + ":"+ cal.get(Calendar.MINUTE);
	}
	
}
