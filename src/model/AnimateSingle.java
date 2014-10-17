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
	int gleamTimes = 40;
	int sleepMillis = 1;
	int defaultSleepMillis = 1;
	
	public AnimateSingle(Seat[] seats) {
		this.seats = seats;
	}
	
	public void start(int seatNum) {
		this.seatPos = getNumPos(seatNum);
		super.start();
	}
	
	@Override
	public void handle(long now) {
		if (!isRepeat()) {
			Random ran = new Random();
			AnimateFnSet.initGridPerFresh(seats, hasBeenSet);
			AnimateFnSet.sleep(sleepMillis += 10); // delay for animating
			if (count == gleamTimes) { // switch to other seat and set seat
				count = 0;
				sleepMillis = defaultSleepMillis;
				seats[seatPos].setText(seats[seatPos].getSeatNumStr());
				hasBeenSet.push(seatPos);
			}
			else {
				int randomSeatNum = AnimateFnSet.getRemainSeatPos(ran, seats,
						hasBeenSet);
				seats[randomSeatNum].setText(seats[seatPos].getSeatNumStr());
				count++;
			}

			if (count == 0) this.stop();
		}
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
	
	public boolean isRepeat(){
		for (Integer integer : hasBeenSet) {
			if(integer == seatPos)
				return true;	// if has been set, stopping this animation
		}
		return false;
	}
}