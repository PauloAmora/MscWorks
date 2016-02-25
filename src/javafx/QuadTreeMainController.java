package javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;


public class QuadTreeMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane QuadTreeMainWindow;


    @FXML
    void drawDot(MouseEvent event) {
    	if (event.getSceneX() < QuadTreeMainWindow.getWidth() && event.getSceneY() < QuadTreeMainWindow.getHeight()) {
            Rectangle dot = new Rectangle(event.getSceneX(), event.getSceneY(), 1 , 1);
            dot.setFill(null);
            dot.setStroke(Color.RED);
            dot.setStrokeWidth(2);
            QuadTreeMainWindow.getChildren().add(dot);
            drawLine();
        }

        
    }
    
    void drawLine(){
    	Line verticalLine = new Line(QuadTreeMainWindow.getWidth()/2, 0, QuadTreeMainWindow.getWidth()/2, QuadTreeMainWindow.getHeight());
    	QuadTreeMainWindow.getChildren().add(verticalLine);
    }

    @FXML
    void initialize() {
        assert QuadTreeMainWindow != null : "fx:id=\"QuadTreeMainWindow\" was not injected: check your FXML file 'QuadTreeWindow.fxml'.";
        

    }

}
