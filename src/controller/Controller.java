package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.Seat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable {
	@FXML
	private GridPane grid;
	@FXML
	private Button clrBtn;
	@FXML
	private Button refBtn;
	@FXML
	private Button cnlBtn;
	@FXML
	private Button submit;
	@FXML
	private TextField numInput;
	
	private int seatNum = 46;	// default number of member
	private Seat[] seats = new Seat[50];
	
	int column = 8;
	int row = seatNum / column + 1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		int column = 8;
//		int row = seatNum / column + 1;
		numInput.setText(""+seatNum);
		
		for(int i = 0 ; i < row ; i++){
			for(int j = 0 ; j < column ; j++){
				int num = column*i+j;
				seats[num] = new Seat();
				seats[num].setXY(i, j);
				grid.add(seats[num], seats[num].y, seats[num].x);
			}
		}
		
		clrBtn.setVisible(false);
		clrBtn.setOnAction( (ActionEvent e) -> {	// test
		});
		
		submit.setOnAction( (ActionEvent e) -> {
			String s = numInput.getText();
			seatNum = checkLegal(s);
		});
	}
	
	public void refreshSite(ActionEvent event) throws InterruptedException{
		ArrayList<Integer> seatList = seatAssign();
		initSeat();
		for(int i = 0 ; !seatList.isEmpty() ; i++){
			String insertNum = String.valueOf(seatList.remove(0) + 1);
			if(insertNum.length() < 2) insertNum = " " + insertNum + " ";
			seats[i].setText(insertNum);
		}
	}
	
	public ArrayList<Integer> seatAssign(){
		ArrayList<Integer> seatList = new ArrayList<>();
		ArrayList<Integer> random = new ArrayList<>();
		
		for(int i = 0 ; i < seatNum ; i++)
			seatList.add(i);
		
		while(!seatList.isEmpty()){
			int num = seatList.size();
			int seat = (int)(Math.random() * num);
			
			random.add(seatList.remove(seat));
		}
		
		return random;
	}
	
	public int checkLegal(String s){
		char[] arr = s.toCharArray();
		int sum = 0;
		for(int i = arr.length-1, j = 0 ; i >= 0  ; i--){
			int ten = (int) Math.pow(10, j++);
			sum += (arr[i] - '0') * ten;
		}
		if(sum > 0 && sum <= 46) return sum;
		else {
			numInput.setText(String.valueOf(seatNum));
			return seatNum;
		}
	}
	
	public void initSeat(){
		for(int i = 0 ; i < row * column ; i++) seats[i].setText(seats[i].initSeat);
	}
	
	public void stop(){
		System.out.println("stop");
		System.exit(0);
	}
}
