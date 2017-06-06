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
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

	
    BorderPane root = new BorderPane();
    Scene scene = new Scene(root, 500, 500, Color.WHITE);
    primaryStage.setScene(scene);

    GridPane gridpane = new GridPane();
    gridpane.setPadding(new Insets(5));
    gridpane.setHgap(10);
    gridpane.setVgap(10);
    gridpane.setPadding(new Insets(25, 25, 25, 25));
    Text scenetitle = new Text("Willkommen zur Simulation ");
    scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    gridpane.add(scenetitle, 1,0);
    ColumnConstraints column1 = new ColumnConstraints(150);
    ColumnConstraints column2 = new ColumnConstraints(50, 150, 110);
    ColumnConstraints column3 = new ColumnConstraints(50);
    column2.setHgrow(Priority.ALWAYS);
    gridpane.getColumnConstraints().addAll(column1, column2, column3);
    
	primaryStage.setTitle("PVR - Simulation");
	primaryStage.show();

    Label iterationlabel = new Label("Iterationen");
    TextField iterationfield = new TextField();
    Label timelabel = new Label("Zeitdauer");
    TextField timefield = new TextField();
    Label fishlabel = new Label("Anzahl der Fische");
    TextField fishfield = new TextField();

    Button saveButt = new Button("Start");
    Button resetButt = new Button("Reset");
    Button messButt = new Button("Messungen");
    Button visButt = new Button("Visualisierung");
    Button leer1 = new Button("Leer1");
    Button leer2 = new Button("Leer2");
    Button leer3 = new Button("Leer3");

    // Iterationlabel
    GridPane.setHalignment(iterationlabel, HPos.RIGHT);
    gridpane.add(iterationlabel, 0, 2);

    // Timelabel
    GridPane.setHalignment(timelabel, HPos.RIGHT);
    gridpane.add(timelabel, 0, 3);
    
    // Fishlabel
    GridPane.setHalignment(fishlabel, HPos.RIGHT);
    gridpane.add(fishlabel, 0, 4);
    
    // Iterationfield
    GridPane.setHalignment(iterationfield, HPos.LEFT);
    gridpane.add(iterationfield, 1, 2);
    iterationfield.setText("1");
    iterationfield.getText();

    // Timefield
    GridPane.setHalignment(timefield, HPos.LEFT);
    gridpane.add(timefield, 1, 3);
    timefield.setText("100");
    timefield.getText();
    
    // Fishfield
    GridPane.setHalignment(fishfield, HPos.LEFT);
    gridpane.add(fishfield, 1, 4);
    fishfield.setText("100");
    fishfield.getText();

    // StartButton
    GridPane.setHalignment(saveButt, HPos.RIGHT);
    gridpane.add(saveButt, 2, 4);
    
    // ResetButton
    GridPane.setHalignment(resetButt, HPos.LEFT);
    gridpane.add(resetButt, 1, 7);
    resetButt.setMinSize(105, 20);
    resetButt.setAlignment(Pos.BASELINE_LEFT);
    
    // MessButton
    GridPane.setHalignment(messButt, HPos.LEFT);
    gridpane.add(messButt, 1, 8);
    messButt.setMinSize(105, 20);
    messButt.setAlignment(Pos.BASELINE_LEFT);
    
    // VisButton
    GridPane.setHalignment(visButt, HPos.LEFT);
    gridpane.add(visButt, 1, 9);
    visButt.setMinSize(105, 20);
    
    // ButtonLeer1
    GridPane.setHalignment(leer1, HPos.LEFT);
    gridpane.add(leer1, 2, 7);
    leer1.setMinSize(105, 20);
    leer1.setAlignment(Pos.BASELINE_CENTER);
    
    // ButtonLeer2
    GridPane.setHalignment(leer2, HPos.LEFT);
    gridpane.add(leer2, 2, 8);
    leer2.setMinSize(105, 20);
    leer2.setAlignment(Pos.BASELINE_CENTER);
    
    // ButtonLeer3
    GridPane.setHalignment(leer3, HPos.LEFT);
    gridpane.add(leer3, 2, 9);
    leer3.setMinSize(105, 20);
    leer3.setAlignment(Pos.BASELINE_CENTER);

    root.setCenter(gridpane);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}