package model;

public class AnimateController {
	public AnimateController(Seat[] seats) {
		new Animater(seats).start();
	}
}
