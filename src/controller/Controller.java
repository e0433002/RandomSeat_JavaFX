package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.AnimateController;
import model.Seat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable {
	@FXML private GridPane grid;
	@FXML private Button refBtn;
	@FXML private Button cnlBtn;
	@FXML private Button submit;
	@FXML private Button sglBtn;		// not implement
	@FXML private TextField numInput;
	@FXML private TextField sglInput;	// not implement
	@FXML private CheckBox sglChkBox; // not implement
	
	private int seatNum = 46;	// default number of member
	private int limitSeatNum = 48;
	private Seat[] seats = new Seat[limitSeatNum];
	int column = 6;
	int row = seatNum / column + 1;
	static AnimateController animateController = new AnimateController();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sglBtn.setVisible(false);
		sglInput.setVisible(false);
		
		numInput.setText(""+seatNum);
		
		for(int i = 0 ; i < row ; i++){
			for(int j = 0 ; j < column ; j++){
				int num = column*i+j;
				seats[num] = new Seat();
				seats[num].setXY(i, j);
				grid.add(seats[num], seats[num].y, seats[num].x);
			}
		}
		
		submit.setOnAction( (ActionEvent e) -> {
			String s = numInput.getText();
			seatNum = checkLegal(s);	// reset seaNum
			row = seatNum / column + 1;	// reset row 
		});
		
		sglBtn.setOnAction( (ActionEvent e) -> {
			String s = sglInput.getText();
			seatNum = checkLegal(s);
			animateController.startAnimateSingle(seats, seatNum);
		});
	}
	
	public void refreshSite(ActionEvent event) {	// refBtn trigger
		seatAssign();
		animateController.startAnimateRandom(seats);
		showInConsole();	// for debug
	}
	
	public void seatAssign(){
		ArrayList<Integer> iterateSeatList = new ArrayList<>();
		ArrayList<Integer> randomSeatList = new ArrayList<>();

		initSeat();
		for(int i = 1 ; i <= seatNum ; i++) iterateSeatList.add(i);
		// get iterateList random placing into randomList
		while(!iterateSeatList.isEmpty()){
			int num = iterateSeatList.size();
			int seat = (int)(Math.random() * num);
			randomSeatList.add(iterateSeatList.remove(seat));
		}
		// set seat number to string and integer
		for(int i = 0 ; !randomSeatList.isEmpty() ; i++){
			String insertNum = String.valueOf(randomSeatList.remove(0));
			if(insertNum.length() < 2) insertNum = " " + insertNum + " ";			
			seats[i].setSeatNumStrAndInt(insertNum);
		}
	}
	
	public void showItem(){
		if(sglChkBox.isSelected()){
			seatAssign();
			sglBtn.setVisible(true);
			sglInput.setVisible(true);
			refBtn.setVisible(false);
		}
		else{
			initSeat();
			sglBtn.setVisible(false);
			sglInput.setVisible(false);
			refBtn.setVisible(true);
		}
		showInConsole();
	}
	
	public int checkLegal(String s){
		char[] arr = s.toCharArray();
		int sum = 0;
		for(int i = arr.length-1, j = 0 ; i >= 0  ; i--){
			int ten = (int) Math.pow(10, j++);
			sum += (arr[i] - '0') * ten;
		}
		if(sum > 0 && sum <= limitSeatNum) return sum;
		else {
			numInput.setText(String.valueOf(seatNum));
			return seatNum;
		}
	}
	
	public void initSeat(){
		for(int i = 0 ; i < limitSeatNum ; i++){
			seats[i].setText(seats[i].initSeat);	// initialize panel
			seats[i].setSeatNumStrAndInt("0");		// initialize data
		}
	}
	
	public void stop(){		// cnlBtn trigger
		System.out.println("stop");
		System.exit(0);
	}
	
	public void showInConsole(){	// use to debug
		System.out.println("-------Grid-------");
		for(int i = 0 ; i < row ; i++){
			for(int j = 0 ; j < column ; j++){
				if(i*column+j == seatNum) break;
				System.out.printf("%2d ", seats[i*column+j].getSeatNum());
			}
			System.out.println();
		}
		System.out.println("-------____-------");
	}
}
