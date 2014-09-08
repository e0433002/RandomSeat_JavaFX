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
		this.setStyle("-fx-padding: 5 10 5 10;"
				+ "-fx-font-size: 15px;"
				+ "-fx-background-color: rgba(0,0,0,0.07), "
				+ "linear-gradient(#5a61af, #51536d), "
				+ "linear-gradient(#e4fbff 0%, #cee6fb 10%, #a5d3fb 50%, #88c6fb 51%, #d5faff 100%);");
	}
	
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setSeatNumStr(String seatNumStr){
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
