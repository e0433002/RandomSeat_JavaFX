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
	int sleepMillis = 250;
	
	public AnimateRandom(Seat[] seats) {
		this.seats = seats;
	}
	@Override
	public void handle(long now) {
		Random ran = new Random();
		
		if(count == 0) this.seatPos = getRemainSeatPos(ran);	// element first parameter initial
		initGrid();
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
		if(hasBeenSet.size() == seatSize()) this.stop();	// finish animate
	}
	
	private int getRemainSeatPos(Random ran) {
		while(true) {
			int seatPos = ran.nextInt(seatSize());
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
	private void initGrid() {
		for(int i = 0 ; i < seatSize() ; i++){
			Boolean initSeatOrNot = true;
			for(Integer s : hasBeenSet){
				if(i == s) {
					initSeatOrNot = false;
					break;
				}
			}
			if(initSeatOrNot)
				seats[i].setText(""+seats[i].initSeat);
		}
	}
	private int seatSize() {
		int count = 0;
		for(Seat node : seats){
			if(node.getSeatNum() == 0) return count;
			count++;
		}
		return 0;
	}
	
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}