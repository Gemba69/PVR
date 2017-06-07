package de.pvr.fish.simulation.view;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewControlerWindow extends Application {
	
	private GridPane topGrid;
	private GridPane bottomGrid;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

	
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 700, 700, Color.WHITE);
		primaryStage.setScene(scene);
		
		//TopGrid
		topGrid  = new GridPane();
		topGrid.setAlignment(Pos.TOP_LEFT);
	//	GridPane gridpane = new GridPane();
		topGrid.setPadding(new Insets(5));
		topGrid.setHgap(10);
		topGrid.setVgap(10);
		topGrid.setPadding(new Insets(25, 25, 25, 25));
		topGrid.setStyle("-fx-background-color: white");
		
		//Titel
		Text scenetitle = new Text("Willkommen zur Simulation ");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		topGrid.add(scenetitle, 1, 0);
		
		//Spalten
		ColumnConstraints column1 = new ColumnConstraints(120);
		ColumnConstraints column2 = new ColumnConstraints(120, 150, 110);
		ColumnConstraints column3 = new ColumnConstraints(105);
		ColumnConstraints column4 = new ColumnConstraints(120, 100, 100);
		column2.setHgrow(Priority.ALWAYS);
		topGrid.getColumnConstraints().addAll(column1, column2, column3, column4);


		//Fenstertitel
		primaryStage.setTitle("PVR - Simulation");
		primaryStage.show();

		//Label, Buttons, Textfields
		Label iterationlabel = new Label("Iterationen");
		TextField iterationfield = new TextField();
		Label threads = new Label("Anzahl der Threads");
		TextField threadscount = new TextField();
		Label fishlabel = new Label("Anzahl der Fische");
		TextField fishfield = new TextField();

		
		Button saveButt = new Button("Start");
		Button resetButt = new Button("Reset");
		Button messButt = new Button("Messungen");
		Button visButt = new Button("Visualisierung");
		Button leer1 = new Button("Leer1");
		Button leer2 = new Button("Leer2");
		Button leer3 = new Button("Leer3");
		Button Aq = new Button("Aquarium");


		// Iterationlabel
		GridPane.setHalignment(iterationlabel, HPos.RIGHT);
		topGrid.add(iterationlabel, 0, 2);

		// Timelabel
		GridPane.setHalignment(threads, HPos.RIGHT);
		topGrid.add(threads, 0, 3);

		// Fishlabel
		GridPane.setHalignment(fishlabel, HPos.RIGHT);
		topGrid.add(fishlabel, 0, 4);

		// Iterationfield
		GridPane.setHalignment(iterationfield, HPos.LEFT);
		topGrid.add(iterationfield, 1, 2);
		iterationfield.setText("1000");
		iterationfield.getText();

		// Timefield
		GridPane.setHalignment(threadscount, HPos.LEFT);
		topGrid.add(threadscount, 1, 3);
		threadscount.setText("4");
		threadscount.getText();

		// Fishfield
		GridPane.setHalignment(fishfield, HPos.LEFT);
		topGrid.add(fishfield, 1, 4);
		fishfield.setText("100");
		fishfield.getText();

		// StartButton
		GridPane.setHalignment(saveButt, HPos.LEFT);
		topGrid.add(saveButt, 2, 4);

		// ResetButton
		GridPane.setHalignment(resetButt, HPos.LEFT);
		topGrid.add(resetButt, 3, 7);
		resetButt.setMinSize(105, 20);
		resetButt.setAlignment(Pos.BASELINE_LEFT);

		// MessButton
		GridPane.setHalignment(messButt, HPos.LEFT);
		topGrid.add(messButt, 3, 8);
		messButt.setMinSize(105, 20);
		messButt.setAlignment(Pos.BASELINE_LEFT);

		// VisButton
		GridPane.setHalignment(visButt, HPos.LEFT);
		topGrid.add(visButt, 3, 9);
		visButt.setMinSize(105, 20);

		// ButtonLeer1
		GridPane.setHalignment(leer1, HPos.LEFT);
		topGrid.add(leer1, 2, 7);
		leer1.setMinSize(105, 20);
		leer1.setAlignment(Pos.BASELINE_CENTER);

		// ButtonLeer2
		GridPane.setHalignment(leer2, HPos.LEFT);
		topGrid.add(leer2, 2, 8);
		leer2.setMinSize(105, 20);
		leer2.setAlignment(Pos.BASELINE_CENTER);

		// ButtonLeer3
		GridPane.setHalignment(leer3, HPos.LEFT);
		topGrid.add(leer3, 2, 9);
		leer3.setMinSize(105, 20);
		leer3.setAlignment(Pos.BASELINE_CENTER);
		
		
		bottomGrid = new GridPane();
		bottomGrid.setAlignment(Pos.BOTTOM_LEFT);
		bottomGrid.setPadding(new Insets(5));
		bottomGrid.setHgap(10);
		bottomGrid.setVgap(10);
		bottomGrid.setPadding(new Insets(25, 25, 25, 25));
		bottomGrid.setStyle("-fx-background-color: grey");
		ColumnConstraints column5 = new ColumnConstraints(150);
		ColumnConstraints column6 = new ColumnConstraints(50, 150, 110);
		ColumnConstraints column7 = new ColumnConstraints(50);
		ColumnConstraints column8 = new ColumnConstraints(120, 100, 100);
		bottomGrid.getColumnConstraints().addAll(column5, column6, column7, column8);
		
		// Aquarium
		GridPane.setHalignment(Aq, HPos.CENTER);
		bottomGrid.add(Aq, 2, 2);
		Aq.setMinSize(600, 300);
		Aq.setAlignment(Pos.BASELINE_CENTER);
		
	
	
		
		//Anzeigen des Panes
		root.setTop(topGrid);
		root.setBottom(bottomGrid);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}