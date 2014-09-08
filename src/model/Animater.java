package model;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.GridPane;

public class Animater extends AnimationTimer {
	GridPane grid = null;
	Seat[] seats;
	int row = 0;
	int column = 0;
	
	public Animater(Seat[] seats){
		this.seats = seats;
	}
	public Animater(GridPane grid, Seat[] seats, int row, int column){
		this.grid = grid;
		this.seats = seats;
		this.row = row;
		this.column = column;
	}
	
	int count = 0;
	@Override
	public void handle(long now) {
		seats[count].setText(seats[count].getSeatNumStr());
		count++;
		if(seats[count].getSeatNum() == 0) this.stop();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		ObservableList<Node> childrens = grid.getChildren();
//		Seat result = null;
//		childrens = clearObservableList(childrens);	// remove child like HBox ...etc
//		for(Node node : childrens) {
//            if(GridPane.getRowIndex(node) == 0 && GridPane.getColumnIndex(node) == 0) {
//                result = (Seat) node;
//                //System.out.println(result.seatNum);
//                break;
//            }
//		}
	}
	
//	private ObservableList<Node> clearObservableList(ObservableList<Node> childrens) {
//		Stack<Node> removeList = new Stack<>();
//		for(Node node : childrens) {
//			if(node instanceof Seat) continue;
//			else removeList.push(node);
//		}
//		for(Node node : removeList) childrens.remove(node);
//		return childrens;
//	}
}
