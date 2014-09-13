package model;

import javafx.animation.AnimationTimer;

public class AnimateSequnce extends AnimationTimer {
	Seat[] seats;
	int row = 0;
	int column = 0;
	
	public AnimateSequnce(Seat[] seats){
		this.seats = seats;
	}
	
	int count = 0;
	@Override
	public void handle(long now) {
		seats[count].setText(seats[count].getSeatNumStr());
		count++;
		if(seats[count].getSeatNum() == 0) this.stop();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
