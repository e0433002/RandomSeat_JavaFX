package model;

import javafx.animation.AnimationTimer;

public class AnimateController {
//	Seat[] seats;
	int count = 0;
	AnimationTimer animation;
	public AnimateController() {
//		new AnimateSequnce(seats).start();
		
	}
	
	public void startAnimate(Seat[] seats){
		if(animation == null){
			animation = new AnimateRandom(seats);
			animation.start();
		}
		else{
			animation.stop();
			animation = new AnimateRandom(seats);
			animation.start();
		}
	}
	
	public void stop(){
	}
}

