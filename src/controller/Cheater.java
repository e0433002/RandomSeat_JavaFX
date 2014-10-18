package controller;

import java.util.ArrayList;

public class Cheater {
	
	public static void startCheats(ArrayList<Integer> randomSeatList, int cheatNum){
		int beChangeSeat = 0;
		
		for (int i = 0 ; i < randomSeatList.size() ; i++) {
			Integer seat = randomSeatList.get(i);
			if(seat == cheatNum){
				beChangeSeat = (int)(Math.random() * (45-32+1)) + 32;
				Integer temp = randomSeatList.get(beChangeSeat);
				randomSeatList.set(beChangeSeat, cheatNum);
				randomSeatList.set(i, temp);
			}
		}
	}
}
