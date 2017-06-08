package de.pvr.fish.simulation.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
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
		Scene scene = new Scene(root, 700, 800, Color.WHITE);

		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		root.setTop(menuBar);

		Menu fileMenu = new Menu("File");
		MenuItem exitMenuItem = new MenuItem("Exit");
		fileMenu.getItems().add(exitMenuItem);
		exitMenuItem.setOnAction(actionEvent -> Platform.exit());

		Menu Visualisierung = new Menu("Visualisierung");
		MenuItem Fischschwarm = new MenuItem("Fischschwarm");
		Visualisierung.getItems().add(Fischschwarm);
		Fischschwarm.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				Stage stage = new Stage();
				Scene scene = new Scene(new VBox());
				stage.setTitle("Aquarium");
				stage.setScene(scene);
				stage.show();
			}
		});

		menuBar.getMenus().addAll(fileMenu, Visualisierung);

		// TopGrid
		topGrid = new GridPane();
		topGrid.setAlignment(Pos.TOP_LEFT);
		// GridPane gridpane = new GridPane();
		topGrid.setPadding(new Insets(5));
		topGrid.setHgap(10);
		topGrid.setVgap(10);
		topGrid.setPadding(new Insets(25, 25, 25, 25));
		topGrid.setStyle("-fx-background-color: white");

		// Titel
		Text scenetitle = new Text("Willkommen zur Simulation ");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		topGrid.add(scenetitle, 1, 0);

		// Spalten
		ColumnConstraints column1 = new ColumnConstraints(200);
		ColumnConstraints column2 = new ColumnConstraints(120, 150, 110);
		ColumnConstraints column3 = new ColumnConstraints(105);
		ColumnConstraints column4 = new ColumnConstraints(105, 100, 100);
		column2.setHgrow(Priority.ALWAYS);
		topGrid.getColumnConstraints().addAll(column1, column2, column3, column4);

		// Fenstertitel
		primaryStage.setTitle("PVR - Simulation");
		primaryStage.show();

		// Label, Buttons, Textfields
		Label iterationLabel = new Label("Iterationen:");
		TextField iterationField = new TextField();
		Label threads = new Label("Anzahl der Threads:");
		TextField threadCount = new TextField();
		Label fishLabel = new Label("Anzahl der Fische:");
		TextField fishField = new TextField();

		Button startButton = new Button("Start");
		Button resetButton = new Button("Reset");
		Button messButton = new Button("Messungen");
		Button visButton = new Button("Visualisierung");
		Button fieldSize = new Button("Feldgröße");
		Button size600 = new Button("600x600");
		Button size1000 = new Button("1000x1000");
		Button fishTankLabel = new Button("Aquarium");
		Label squareLabel = new Label("Square's");
		ChoiceBox squareLength = new ChoiceBox();
		ChoiceBox squareNumber = new ChoiceBox();

		ChoiceBox deadAngle = new ChoiceBox();
		ChoiceBox neighbor = new ChoiceBox();
		ChoiceBox fishLength = new ChoiceBox();
		Label deadAngleLabel = new Label("Größe des toten Winkels:");
		Label neighborLabel = new Label("Anzahl der Nachbarn:");
		Label fishLengthLabel = new Label("Länge des Fisches:");

		// final ChoiceBox<String> toterwinkel = new
		// ChoiceBox<>(FXCollections.observableArrayList("First", "Second",
		// "Third"));

		// Iterationlabel
		GridPane.setHalignment(iterationLabel, HPos.RIGHT);
		topGrid.add(iterationLabel, 0, 2);

		// Timelabel
		GridPane.setHalignment(threads, HPos.RIGHT);
		topGrid.add(threads, 0, 3);

		// Fishlabel
		GridPane.setHalignment(fishLabel, HPos.RIGHT);
		topGrid.add(fishLabel, 0, 4);

		// Iterationfield
		GridPane.setHalignment(iterationField, HPos.LEFT);
		topGrid.add(iterationField, 1, 2);
		iterationField.setText("1000");
		iterationField.getText();

		// Timefield
		GridPane.setHalignment(threadCount, HPos.LEFT);
		topGrid.add(threadCount, 1, 3);
		threadCount.setText("4");
		threadCount.getText();

		// Fishfield
		GridPane.setHalignment(fishField, HPos.LEFT);
		topGrid.add(fishField, 1, 4);
		fishField.setText("100");
		fishField.getText();

		// StartButton
		GridPane.setHalignment(startButton, HPos.LEFT);
		topGrid.add(startButton, 2, 4);

		// ResetButton
		GridPane.setHalignment(resetButton, HPos.CENTER);
		topGrid.add(resetButton, 3, 2);
		resetButton.setMinSize(105, 20);
		resetButton.setAlignment(Pos.BASELINE_CENTER);

		// MessButton
		GridPane.setHalignment(messButton, HPos.CENTER);
		topGrid.add(messButton, 3, 3);
		messButton.setMinSize(105, 20);
		messButton.setAlignment(Pos.BASELINE_CENTER);

		// VisButton
		GridPane.setHalignment(visButton, HPos.CENTER);
		topGrid.add(visButton, 3, 4);
		visButton.setMinSize(105, 20);

		// ButtonLeer1
		GridPane.setHalignment(fieldSize, HPos.LEFT);
		topGrid.add(fieldSize, 2, 7);
		fieldSize.setMinSize(105, 20);
		fieldSize.setAlignment(Pos.BASELINE_CENTER);

		// ButtonLeer2
		GridPane.setHalignment(size600, HPos.LEFT);
		topGrid.add(size600, 2, 8);
		size600.setMinSize(105, 20);
		size600.setAlignment(Pos.BASELINE_CENTER);

		// ButtonLeer3
		GridPane.setHalignment(size1000, HPos.LEFT);
		topGrid.add(size1000, 2, 9);
		size1000.setMinSize(105, 20);
		size1000.setAlignment(Pos.BASELINE_CENTER);

		// Death Angle Choice
		deadAngle.getItems().add("20");
		deadAngle.getItems().add("30");
		deadAngle.getItems().add("40");
		deadAngle.getItems().add("50");
		GridPane.setHalignment(deadAngle, HPos.LEFT);
		topGrid.add(deadAngle, 1, 7);
		deadAngle.setMinSize(60, 20);
		deadAngle.getSelectionModel().select(1);
		// Reading the selected Value
		// String deadanglestring = (String) deadAngle.getValue();
		GridPane.setHalignment(deadAngleLabel, HPos.RIGHT);
		topGrid.add(deadAngleLabel, 0, 7);

		// Neibor Choice
		neighbor.getItems().add("3");
		neighbor.getItems().add("4");
		neighbor.getItems().add("5");
		GridPane.setHalignment(neighbor, HPos.LEFT);
		topGrid.add(neighbor, 1, 8);
		neighbor.setMinSize(60, 20);
		neighbor.getSelectionModel().select(1);
		// String neiborstring = (String) neighbor.getValue();
		GridPane.setHalignment(neighborLabel, HPos.RIGHT);
		topGrid.add(neighborLabel, 0, 8);

		// Fish length Choice
		fishLength.getItems().add("1");
		fishLength.getItems().add("2");
		fishLength.getItems().add("3");
		GridPane.setHalignment(fishLength, HPos.LEFT);
		topGrid.add(fishLength, 1, 9);
		fishLength.setMinSize(60, 20);
		fishLength.getSelectionModel().select(0);
		// String fishlengthstring = (String) fishLength.getValue();
		GridPane.setHalignment(fishLengthLabel, HPos.RIGHT);
		topGrid.add(fishLengthLabel, 0, 9);

		// Square length Choice + label
		squareLength.getItems().add("1");
		squareLength.getItems().add("2");
		squareLength.getItems().add("3");
		GridPane.setHalignment(squareLength, HPos.RIGHT);
		topGrid.add(squareLength, 3, 8);
		squareLength.setMinSize(60, 20);
		squareLength.getSelectionModel().select(2);
		// String squarelengthstring = (String) squareLength.getValue();
		GridPane.setHalignment(squareLabel, HPos.RIGHT);
		topGrid.add(squareLabel, 3, 7);

		// Square length Choice 3/9next
		squareNumber.getItems().add("1");
		squareNumber.getItems().add("2");
		squareNumber.getItems().add("3");
		GridPane.setHalignment(squareNumber, HPos.RIGHT);
		topGrid.add(squareNumber, 3, 9);
		squareNumber.setMinSize(60, 20);
		squareNumber.getSelectionModel().select(2);
		// String squarenumberstring = (String) squareNumber.getValue();

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
		GridPane.setHalignment(fishTankLabel, HPos.CENTER);
		bottomGrid.add(fishTankLabel, 2, 2);
		fishTankLabel.setMinSize(600, 300);
		fishTankLabel.setAlignment(Pos.BASELINE_CENTER);

		// Anzeigen des Panes
		root.setTop(menuBar);
		root.setCenter(topGrid);
		root.setBottom(bottomGrid);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}