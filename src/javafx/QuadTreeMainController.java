package javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class QuadTreeMainController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane QuadTreeMainWindow;

	private QuadTreeWindowApp application;

	public void setApp(QuadTreeWindowApp application) {
		this.application = application;
	}

	@FXML
	void drawDot(MouseEvent event) {
		if (application == null)
			return;
		if (event.getSceneX() < QuadTreeMainWindow.getWidth() && event.getSceneY() < QuadTreeMainWindow.getHeight()) {
			int prevHeight = application.quadTree.height;
			if (application.quadTree.insert((int) event.getSceneX(), (int) event.getSceneY(),
					application.quadTree.root)) {
				Rectangle dot = new Rectangle(event.getSceneX(), event.getSceneY(), 1, 1);
				dot.setFill(null);
				dot.setStroke(Color.RED);
				dot.setStrokeWidth(2);
				QuadTreeMainWindow.getChildren().add(dot);
				if (prevHeight != application.quadTree.height) {
					redrawTree();
					
				}
			}
		}

	}

	private void redrawTree() {
		QuadTreeMainWindow.getChildren().clear();
		for(int i = 0; i < application.quadTree.height; i++){
			for (int j = 0; j < Math.pow(2, 2*i); j++){
				
			}
		}
		Rectangle dot = new Rectangle(event.getSceneX(), event.getSceneY(), 1, 1);
		dot.setFill(null);
		dot.setStroke(Color.RED);
		dot.setStrokeWidth(2);
		QuadTreeMainWindow.getChildren().add(dot);
		
		drawBox(application.quadTree.root.rect.originX, application.quadTree.root.rect.originY,
				application.quadTree.root.rect.width, application.quadTree.root.rect.height);
		
	}

	void drawBox(int originX, int originY, int width, int height) {
		Line verticalLine = new Line(width/2, originY, width/2, height);
		Line horizontalLine = new Line(originX, height/2, width, height/2);

		// Line verticalLine = new Line(QuadTreeMainWindow.getWidth()/2, 0,
		// QuadTreeMainWindow.getWidth()/2, QuadTreeMainWindow.getHeight());
		QuadTreeMainWindow.getChildren().add(verticalLine);
		QuadTreeMainWindow.getChildren().add(horizontalLine);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert QuadTreeMainWindow != null : "fx:id=\"QuadTreeMainWindow\" was not injected: check your FXML file 'QuadTreeWindow.fxml'.";

	}

}
