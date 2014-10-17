package controller;

import model.Seat;

public class Cheater {
	
	public static Seat[] startCheats(Seat[] seats, int cheatNum){
		int beChangeSeat = seats.length - 5;
		
		for (Seat seat : seats) {
			if(seat.getSeatNum() == cheatNum){
				seat.setSeatNumStrAndInt(seats[beChangeSeat].getSeatNumStr());
				seats[beChangeSeat].setSeatNumStrAndInt(""+cheatNum);
				return seats;
			}
		}
		return seats;
	}
}
