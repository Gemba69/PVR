package de.pvr.fish.simulation.view;


import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javafx.animation.PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT;

import de.pvr.fish.simulation.model.Fish;

public class ViewControler extends Application
{
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) 
	{
		// Create the Rectangle
		Fish [][] Fishes;
		Rectangle rect = new Rectangle(6, 2, Color.BLUE);
		
		// Create the path
		Circle path = new Circle(50);
		path.setFill(null);
		path.setStroke(Color.WHITE);
		
		// Create the VBox
		VBox root = new VBox(rect, path);
		// Set the Size of the VBox
		root.setPrefSize(500, 500);
		// Set the Style-properties of the VBox
		root.setStyle("-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: blue;");
		
		// Create the Scene
		Scene scene = new Scene(root);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title of the Stage
		stage.setTitle("Aquarium");
		// Display the Stage
		stage.show();


		// Set up a Path Transition
		PathTransition pathTransition = new PathTransition(Duration.seconds(2), path);
		pathTransition.setOrientation(ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(PathTransition.INDEFINITE);

		// Create a sequential transition
		SequentialTransition sequTransition = new SequentialTransition();
		// Rectangle is the node for all animations
		sequTransition.setNode(rect);
		// Add animations to the list
		sequTransition.getChildren().addAll(pathTransition);
		// Let the animation run forever
		sequTransition.setCycleCount(PathTransition.INDEFINITE);
		// Play the Animation
		sequTransition.play();		
	} 
}