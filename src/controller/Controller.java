package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.Cheater;
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
	@FXML private Button sglBtn;
	@FXML private TextField numInput;
	@FXML private TextField sglInput;
	@FXML private CheckBox sglChkBox;
	
	int toolbarRowSpan = 2;
	int column;
	int row;
	private int seatNum = 46;	// default number of member
	private int limitSeatNum;
	private Seat[] seats;
	
	static AnimateController animateController = new AnimateController();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sglBtn.setVisible(false);
		sglInput.setVisible(false);
		column = grid.getColumnConstraints().size();
		row = grid.getRowConstraints().size() - toolbarRowSpan;
		limitSeatNum = row * column;
		seats = new Seat[seatNum];
		
		for(int i = 0, num = 0 ; i < row ; i++){
			for(int j = 0 ; j < column ; j++){
				if(isVacancy(i, j)) continue;
				seats[num] = new Seat();
				seats[num].setXY(i, j);
				grid.add(seats[num], seats[num].y, seats[num].x);
				num++;
			}
		}
		
		iconEnable();
	}
	
	public void iconEnable(){
		numInput.setText(""+seatNum);	// member number textField
		
		submit.setOnAction( (ActionEvent e) -> {	// submit button
			String s = numInput.getText();
			seatNum = checkLegal(s);	// reset seaNum
			row = seatNum / column + 1;	// reset row 
		});
		
		sglBtn.setOnAction( (ActionEvent e) -> {	// single flush button
			String s = sglInput.getText();
			seatNum = checkLegal(s);
			animateController.startAnimateSingle(seats, seatNum);
		});
	}
	
	public void refreshSite(ActionEvent event) {	// refBtn trigger
		seatAssign();
		animateController.startAnimateRandom(seats);
		
		if(animateController.getState()){
			refBtn.setText("Stop");
			sglChkBox.setVisible(false);
		}
		else{
			refBtn.setText("Refresh");
			sglChkBox.setVisible(true);
		}
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
		
		// **cheater part**
		Cheater.startCheats(randomSeatList, 2, 4, 5, 7, 8, 9, 38);
		// ****************
		
		// checking the number whether all be setting, not important
		if(!isAllSet(randomSeatList.size(), randomSeatList)) System.out.println("not all setting");
		
		// set seat number to string and integer
		for(int i = 0 ; !randomSeatList.isEmpty() ; i++){
			String insertNum = String.valueOf(randomSeatList.remove(0));
			seats[i].setSeatNum(insertNum);
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
	
	public boolean isAllSet(int size, ArrayList<Integer> checkList){
		int[] checkArr = new int[size];
		for(int i = 0 ; i < size; i++) checkArr[i] = 0;
		for(Integer seat : checkList) checkArr[seat-1] = 1;
		for(Integer i : checkArr) if(i == 0) return false;
		return true;
	}
	
	public boolean isVacancy(int x, int y){
		int[][] vacancy = {{2, 5}, {3, 5}, {4, 0}, {4, 5}, {8, 1}, {8, 2}, {8, 3}, {8, 4}};	// the seat which is empty
		for(int i = 0 ; i < vacancy.length ; i++)
			for(int j = 0 ; j < 2 ; j++){
				if(x == vacancy[i][0] && y == vacancy[i][1]) return true;
			}
		return false;
	}
	
	public void initSeat(){
		for(int i = 0 ; i < seats.length ; i++){
			seats[i].setText(seats[i].initSeat);	// initialize panel
			seats[i].setSeatNum("0");		// initialize data
		}
	}
	
	public void stop(){		// cnlBtn trigger
		System.exit(0);
	}
	
	public void showInConsole(){	// use to debug
		System.out.println("-------Grid-------");
		for(int i = 0, num = 0 ; i < row ; i++){
			for(int j = 0 ; j < column ; j++){
				if(isVacancy(i, j)) System.out.print("   ");
				else System.out.printf("%2d ", seats[num++].getSeatNum());
			}
			System.out.println();
		}
		System.out.println("-------____-------");
	}
}
