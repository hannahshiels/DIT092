package tools;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;


public class GridTools {

    public static Button getButtonAtRow(int row, GridPane gridPane){
        Button btn = new Button();
        for(int i = 0; i < gridPane.getChildren().size(); i++){
            Node child = gridPane.getChildren().get(i);
            if(GridPane.getColumnIndex(child) == 0 && GridPane.getRowIndex(child) == row){
                btn = (Button) child;
            }
        }
        return btn;
    }

    public static ChoiceBox<String> getCBAtRow(int row, GridPane gridPane){
        ChoiceBox<String> cb = new ChoiceBox<String>();
        for(int i = 0; i < gridPane.getChildren().size(); i++){
            Node child = gridPane.getChildren().get(i);
            if(GridPane.getColumnIndex(child) == 1 && GridPane.getRowIndex(child) == row){
                cb = (ChoiceBox<String>) child;
            }
        }
        return cb;
    }

}
