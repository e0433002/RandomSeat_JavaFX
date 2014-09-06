package model;

public class SeatPlace {
	public int rowNum;
	public int columnNum = 10;
	public Seat[] seat;
	
	public SeatPlace(int memNum){
		seat = new Seat[memNum];
		this.rowNum = memNum / columnNum;
		
		for(int i = 0 ; i < 5 ; i++){
			for(int j = 0 ; j < 10 ; j++){
				int num = 10*i+j;
				seat[num] = new Seat();
				seat[num].setXY(i, j);
				//grid.add(seat[num], seat[num].y, seat[num].x);
			}
		}
	}
}
