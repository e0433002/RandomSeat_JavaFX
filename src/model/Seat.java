package model;

import javafx.scene.control.Label;

public class Seat extends Label{
	public int x;
	public int y;
	private int seatNum = 0;
	private String seatNumStr;
	public String initSeat = "    ";	// 4 spaces
	
	public Seat(){
		this.setText(initSeat);
		this.setStyle("-fx-margin: 0;"
				+ "-fx-padding: 5 10 5 10;"
				+ "-fx-font-size: 15px;"
				+ "-fx-text-fill: rgba(255,255,255,1);"
				+ "-fx-background-color: rgba(0,0,0,0.07), "
				+ "linear-gradient(#EBE0D6 0%, #B89470 10%, #996633 50%, #5C3D1F 65%, #8D7762 100%);");
	}
	
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setSeatNumStrAndInt(String seatNumStr){
		this.seatNumStr = seatNumStr;
		this.seatNum = Integer.parseInt(seatNumStr.trim());
	}
	
	public int getSeatNum(){
		return this.seatNum;
	}
	
	public String getSeatNumStr(){
		return seatNumStr;
	}
}
