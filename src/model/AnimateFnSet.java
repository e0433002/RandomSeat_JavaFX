package model;

import java.util.Random;
import java.util.Stack;

public abstract class AnimateFnSet {
	
	protected static int getRemainSeatPos(Random ran, Seat[] seats, Stack<Integer> hasBeenSet) {
		while(true) {
			int seatPos = ran.nextInt(getSeatsSize(seats));
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
	protected static void initGridPerFresh(Seat[] seats, Stack<Integer> hasBeenSet) {
		for(int i = 0 ; i < getSeatsSize(seats) ; i++){
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
	protected static int getSeatsSize(Seat[] seats) {
		int count = 0;
		for(Seat node : seats){
			if(node.getSeatNum() == 0) return count;
			count++;
		}
		return seats.length;
	}
	protected static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}