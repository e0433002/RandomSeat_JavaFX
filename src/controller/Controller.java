package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable {
	@FXML
	private GridPane grid;
	@FXML
	private Button refBtn;
	@FXML
	private Button cnlBtn;
	@FXML
	private Button submit;
	@FXML
	private TextField numInput;
	
	private int seatNum = 46;
	private Label[] label = new Label[50];
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		submit.setOnAction( (ActionEvent e) -> {
			String s = numInput.getText();
			seatNum = checkLegal(s);
		});
		
		for(int i = 0 ; i < 5 ; i++){
			for(int j = 0 ; j < 10 ; j++){
				int num = 10*i+j;
				label[num] = new Label("   ");
				label[num].setStyle("-fx-padding: 0 10 10 10;"
						+ "-fx-font-size: 15px;"
						+ "-fx-background-color: rgba(0,0,0,0.07), linear-gradient(#5a61af, #51536d), linear-gradient(#e4fbff 0%, #cee6fb 10%, #a5d3fb 50%, #88c6fb 51%, #d5faff 100%);");
				
				grid.add(label[num], j, i);
			}
		}
	}
	
	public void refreshSite(ActionEvent event){
		ArrayList<Integer> seatList = seatAssign();
		initSeat();
		for(int i = 0 ; !seatList.isEmpty() ; i++){
			if(i / 10 == 0 && i % 10 == 0) continue;
			if(i / 10 == 0 && i % 10 > 7) continue;
			label[i].setText(String.valueOf(seatList.remove(0) + 1));
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
		for(int i = 0 ; i < 50 ; i++) label[i].setText("   ");
	}
	
	public void stop(){
		System.out.println("stop");
		System.exit(0);
	}
}
