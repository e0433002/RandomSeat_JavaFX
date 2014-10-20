package controller;

import java.util.ArrayList;

public class Cheater {
	public static void startCheats(ArrayList<Integer> randomSeatList, int... cheatNum){
		for(int i = 0 ; i < cheatNum.length ; i++){
			for (int j = 0 ; j < randomSeatList.size() ; j++) {
				Integer seat = randomSeatList.get(j);
				if(seat == cheatNum[i]){
					int random = getEnableSeat(randomSeatList, cheatNum);	// array number
					int temp = randomSeatList.get(random);
					randomSeatList.set(random, seat);
					randomSeatList.set(j, temp);
				}
			}
		}
	}
	
	private static int getEnableSeat(ArrayList<Integer> randomSeatList, int[] cheatNum){
		int random = 0;
		while(true){
			random = (int)(Math.random() * (45-32+1)) + 32;
			if(isCheatNum(randomSeatList.get(random), cheatNum)) continue;
			else return random;
		}
	}
	
	private static boolean isCheatNum(int randomNum, int[] cheatNum){
		for (int i : cheatNum) 
			if(randomNum == i) return true;
		return false;
	}
}
