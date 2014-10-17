package model;

public class AnimateController {
	private Boolean isStart = false;
	AnimateRandom animateRandom;
	AnimateSingle animateSingle;
	
	public AnimateController() {
		
	}
	
	public void startAnimateRandom(Seat[] seats){
		if(!isStart){
			animateRandom = new AnimateRandom(seats);
			animateRandom.start();
			stateModify();
		}
		else{
			animateRandom.stop();
			stateModify();
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
	
	private void stateModify(){
		if(isStart) isStart = false;
		else isStart = true;
	}
	
	public boolean getState(){
		return isStart;
	}
}