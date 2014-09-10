package model;

public class AnimateController {
	Seat[] seats;
	public AnimateController(Seat[] seats) {
//		new AnimateSequnce(seats).start();
		new AnimateRandom(seats).start();
	}
}

