package main;

import java.util.Calendar;

public class Timespan {
	
	Calendar startTime, finishTime;
	
	public Timespan(Calendar start, Calendar finish){
		this.startTime = start;
		this.finishTime = finish;
	}
	
	public Calendar getStart(){
		return (Calendar) startTime.clone();
	}
	
	public Calendar getFinish(){
		return (Calendar) finishTime.clone();
	}
	
	public boolean overlap(Timespan t){
		return false;
	}
	
	public int getTime(){
		return 0;
	}

	public Timespan copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
