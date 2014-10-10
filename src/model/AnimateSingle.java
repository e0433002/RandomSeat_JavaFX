package model;

import java.util.Random;
import java.util.Stack;

import javafx.animation.AnimationTimer;
import model.AnimateFnSet;

public class AnimateSingle extends AnimationTimer{
	Stack<Integer> hasBeenSet = new Stack<>();
	Seat[] seats;
	int seatPos;
	int count = 0;
	int gleamTimes = 5;
	int sleepMillis = 100;
	
	public AnimateSingle(Seat[] seats) {
		this.seats = seats;
	}
	
	public void start(int seatNum) {
		this.seatPos = getNumPos(seatNum);
		super.start();
	}
	
	@Override
	public void handle(long now) {
		Random ran = new Random();
		
		//if(count == 0) this.seatPos = this.showNum;
		AnimateFnSet.initGridPerFresh(seats, hasBeenSet);
		
		if(count == gleamTimes) {	// switch to other seat and set seat
			count = 0;
			seats[seatPos].setText(seats[seatPos].getSeatNumStr());
			hasBeenSet.push(seatPos);
		}
		else {
			int randomSeatNum = AnimateFnSet.getRemainSeatPos(ran, seats, hasBeenSet);
			seats[randomSeatNum].setText(seats[seatPos].getSeatNumStr());
			count++;
		}
		
		AnimateFnSet.sleep(sleepMillis);
		if(count == 0) this.stop();
	}
	
	public int getNumPos(int num){
		int seatPos = 0;
		for(Seat s : seats){
			if(s.getSeatNum() == num){
				return seatPos;
			}
			seatPos++;
		}
		return -1;
	}
}
