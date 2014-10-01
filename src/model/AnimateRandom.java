package model;

import java.util.Random;
import java.util.Stack;

import javafx.animation.AnimationTimer;

class AnimateRandom extends AnimationTimer {
	Stack<Integer> hasBeenSet = new Stack<>();
	Seat[] seats;
	int seatPos;
	int count = 0;
	int gleamTimes = 5;
	int sleepMillis = 0;
	
	public AnimateRandom(Seat[] seats) {
		this.seats = seats;
	}
	@Override
	public void handle(long now) {
		Random ran = new Random();
		
		if(count == 0) this.seatPos = getRemainSeatPos(ran);	// element first parameter initial
		initGridPerFresh();
		if(count == gleamTimes) {	// switch to other seat and set seat
			count = 0;
			seats[seatPos].setText(seats[seatPos].getSeatNumStr());
			hasBeenSet.push(seatPos);
		}
		else {
			int randomSeatNum = getRemainSeatPos(ran);
			seats[randomSeatNum].setText(seats[seatPos].getSeatNumStr());
			count++;
		}
		sleep(sleepMillis);
		if(hasBeenSet.size() == this.getSeatsSize()) this.stop();	// finish animate
	}
	
	private int getRemainSeatPos(Random ran) {
		while(true) {
			int seatPos = ran.nextInt(this.getSeatsSize());
			Boolean notBeSet = true;
			for(Integer s : hasBeenSet) {
				if(seatPos == s) {
					notBeSet = false;
					break;
				}
			}
			if(notBeSet) return seatPos;
		}
	}
	private void initGridPerFresh() {
		for(int i = 0 ; i < this.getSeatsSize() ; i++){
			Boolean initThisSeatOrNot = true;
			for(Integer s : hasBeenSet){
				if(i == s) {
					initThisSeatOrNot = false;
					break;
				}
			}
			if(initThisSeatOrNot)
				seats[i].setText(""+seats[i].initSeat);
		}
	}
	private int getSeatsSize() {
		int count = 0;
		for(Seat node : seats){
			if(node.getSeatNum() == 0) return count;
			count++;
		}
		return seats.length;
	}
	
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}