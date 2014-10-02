package model;

public class AnimateController {
	int count = 0;
	AnimateRandom animateRandom;
	AnimateSingle animateSingle;
	public AnimateController() {
//		new AnimateSequnce(seats).start();
		
	}
	
	public void startAnimateRandom(Seat[] seats){
		if(animateRandom == null){
			animateRandom = new AnimateRandom(seats);
			animateRandom.start();
		}
		else{
			animateRandom.stop();
			animateRandom = new AnimateRandom(seats);
			animateRandom.start();
		}
	}
	
	public void startAnimateSingle(Seat[] seats, int seatNum){
		if(animateSingle == null){
			animateSingle = new AnimateSingle(seats);
			animateSingle.start(seatNum);
		}
		else{
			animateSingle.start(seatNum);
		}
	}
}

