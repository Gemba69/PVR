package de.pvr.fish.simulation.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
		Label iterationlabel = new Label("Iterationen:");
		TextField iterationfield = new TextField();
		Label threads = new Label("Anzahl der Threads:");
		TextField threadscount = new TextField();
		Label fishlabel = new Label("Anzahl der Fische:");
		TextField fishfield = new TextField();

		Button saveButt = new Button("Start");
		Button resetButt = new Button("Reset");
		Button messButt = new Button("Messungen");
		Button visButt = new Button("Visualisierung");
		Button leer1 = new Button("Feldgröße");
		Button leer2 = new Button("600x600");
		Button leer3 = new Button("1000x1000");
		Button Aq = new Button("Aquarium");
		Label squarelabel = new Label("Square's");
		ChoiceBox squarelength = new ChoiceBox();
		ChoiceBox squarenumber = new ChoiceBox();

		ChoiceBox deadangle = new ChoiceBox();
		ChoiceBox neibor = new ChoiceBox();
		ChoiceBox fishlength = new ChoiceBox();
		Label deadanglelabel = new Label("Größe des toten Winkels:");
		Label neiborlabel = new Label("Anzahl der Nachbarn:");
		Label fishlengthlabel = new Label("Länge des Fisches:");

		// final ChoiceBox<String> toterwinkel = new
		// ChoiceBox<>(FXCollections.observableArrayList("First", "Second",
		// "Third"));

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
		GridPane.setHalignment(resetButt, HPos.CENTER);
		topGrid.add(resetButt, 3, 2);
		resetButt.setMinSize(105, 20);
		resetButt.setAlignment(Pos.BASELINE_CENTER);

		// MessButton
		GridPane.setHalignment(messButt, HPos.CENTER);
		topGrid.add(messButt, 3, 3);
		messButt.setMinSize(105, 20);
		messButt.setAlignment(Pos.BASELINE_CENTER);

		// VisButton
		GridPane.setHalignment(visButt, HPos.CENTER);
		topGrid.add(visButt, 3, 4);
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

		// Death Angle Choice
		deadangle.getItems().add("20");
		deadangle.getItems().add("30");
		deadangle.getItems().add("40");
		deadangle.getItems().add("50");
		GridPane.setHalignment(deadangle, HPos.LEFT);
		topGrid.add(deadangle, 1, 7);
		deadangle.setMinSize(60, 20);
		deadangle.getSelectionModel().select(1);
		// Reading the selected Value
		String deadanglestring = (String) deadangle.getValue();
		GridPane.setHalignment(deadanglelabel, HPos.RIGHT);
		topGrid.add(deadanglelabel, 0, 7);

		// Neibor Choice
		neibor.getItems().add("3");
		neibor.getItems().add("4");
		neibor.getItems().add("5");
		GridPane.setHalignment(neibor, HPos.LEFT);
		topGrid.add(neibor, 1, 8);
		neibor.setMinSize(60, 20);
		neibor.getSelectionModel().select(1);
		String neiborstring = (String) neibor.getValue();
		GridPane.setHalignment(neiborlabel, HPos.RIGHT);
		topGrid.add(neiborlabel, 0, 8);

		// Fish length Choice
		fishlength.getItems().add("1");
		fishlength.getItems().add("2");
		fishlength.getItems().add("3");
		GridPane.setHalignment(fishlength, HPos.LEFT);
		topGrid.add(fishlength, 1, 9);
		fishlength.setMinSize(60, 20);
		fishlength.getSelectionModel().select(0);
		String fishlengthstring = (String) fishlength.getValue();
		GridPane.setHalignment(fishlengthlabel, HPos.RIGHT);
		topGrid.add(fishlengthlabel, 0, 9);

		// Square length Choice + label
		squarelength.getItems().add("1");
		squarelength.getItems().add("2");
		squarelength.getItems().add("3");
		GridPane.setHalignment(squarelength, HPos.RIGHT);
		topGrid.add(squarelength, 3, 8);
		squarelength.setMinSize(60, 20);
		squarelength.getSelectionModel().select(2);
		String squarelengthstring = (String) squarelength.getValue();
		GridPane.setHalignment(squarelabel, HPos.RIGHT);
		topGrid.add(squarelabel, 3, 7);

		// Square length Choice 3/9next
		squarenumber.getItems().add("1");
		squarenumber.getItems().add("2");
		squarenumber.getItems().add("3");
		GridPane.setHalignment(squarenumber, HPos.RIGHT);
		topGrid.add(squarenumber, 3, 9);
		squarenumber.setMinSize(60, 20);
		squarenumber.getSelectionModel().select(2);
		String squarenumberstring = (String) squarenumber.getValue();

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

		// Anzeigen des Panes
		root.setTop(topGrid);
		root.setBottom(bottomGrid);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}