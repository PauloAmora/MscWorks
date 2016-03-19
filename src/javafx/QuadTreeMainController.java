package javafxclass;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import model.Node;

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
		if (application.quadTree.treeAsList == null)
			application.quadTree.treeAsList = new ArrayList<Node>();
		application.quadTree.treeAsList.clear();
		application.quadTree.toList(application.quadTree.treeAsList, application.quadTree.root);
		for (int i = 0; i < application.quadTree.treeAsList.size(); i++) {
			Node tmp = application.quadTree.treeAsList.get(i);
			if (tmp.isLeaf()) {
				if (tmp.data != null) {
					Rectangle dot = new Rectangle(tmp.data.x, tmp.data.y, 1, 1);
					dot.setFill(null);
					dot.setStroke(Color.RED);
					dot.setStrokeWidth(2);
					QuadTreeMainWindow.getChildren().add(dot);
				}
			} else {
				drawBox(tmp.rect.originX, tmp.rect.originY, tmp.rect.width, tmp.rect.height);

			}
		}

	}

	void drawBox(int originX, int originY, int width, int height) {
		Line verticalLine = new Line(originX+(width / 2), originY, originX+(width / 2), originY + height);
		Line horizontalLine = new Line(originX, originY + (height / 2), originX+width, originY + (height / 2));

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
