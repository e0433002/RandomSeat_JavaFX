package model;

import java.util.Random;
import java.util.Stack;

import javafx.animation.AnimationTimer;
import model.AnimateFnSet;

class AnimateRandom extends AnimationTimer {
	Stack<Integer> hasBeenSet = new Stack<>();
	Seat[] seats;
	int seatPos;
	int count = 0;
	int gleamTimes = 5;
	int sleepMillis = 100;
	
	public AnimateRandom(Seat[] seats) {
		this.seats = seats;
	}
	@Override
	public void handle(long now) {
		Random ran = new Random();
		
		// get the arbitrary position's element in first time
		if(count == 0) this.seatPos = AnimateFnSet.getRemainSeatPos(ran, seats, hasBeenSet);
		
		AnimateFnSet.initGridPerFresh(seats, hasBeenSet);
		if(count == gleamTimes) {	// switch to other seat and set seat
			count = 0;
			seats[seatPos].setText(seats[seatPos].getSeatNumStr());
			hasBeenSet.push(seatPos);
		}
		else {
			int randomSeatNum =  AnimateFnSet.getRemainSeatPos(ran, seats, hasBeenSet);
			seats[randomSeatNum].setText(seats[seatPos].getSeatNumStr());
			count++;
		}
		
		AnimateFnSet.sleep(sleepMillis);
		if(hasBeenSet.size() == AnimateFnSet.getSeatsSize(seats)) this.stop();	// finish animate
	}
}