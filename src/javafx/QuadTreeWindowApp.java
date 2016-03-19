package javafxclass;

import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Node;
import model.Rect;
import quadtree.QuadTree;



public class QuadTreeWindowApp extends Application {

	public QuadTree quadTree;
	private Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("QuadTree");
		quadTree = new QuadTree();
		quadTree.root = new Node();
		quadTree.root.rect = new Rect(0, 0, 500, 500);
		QuadTreeMainController profile = (QuadTreeMainController) replaceSceneContent("QuadTreeWindow.fxml");
		profile.setApp(this);
		primaryStage.show();
	}

	private Initializable replaceSceneContent(String fxml) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		InputStream in = QuadTreeWindowApp.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(QuadTreeWindowApp.class.getResource(fxml));
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load(in);
		} finally {
			in.close();
		}
		Scene scene = new Scene(page, 500, 500);
		stage.setScene(scene);
		stage.sizeToScene();
		return (Initializable) loader.getController();
	}

	public static void main(String[] args) {
		launch(args);
	}
}