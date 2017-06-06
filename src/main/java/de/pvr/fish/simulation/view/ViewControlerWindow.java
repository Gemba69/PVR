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

    Label fNameLbl = new Label("Iterationen");
    TextField fNameFld = new TextField();
    Label lNameLbl = new Label("Zeitdauer");
    TextField lNameFld = new TextField();
    Label Fishlabel = new Label("Anzahl der Fische");
    TextField fishtext = new TextField();

    Button saveButt = new Button("Start");
    Button resetButt = new Button("Reset");
    Button messButt = new Button("Messungen");
    Button visButt = new Button("Visualisierung");
    Button leer1 = new Button("Leer1");
    Button leer2 = new Button("Leer2");
    Button leer3 = new Button("Leer3");

    // First name label
    GridPane.setHalignment(fNameLbl, HPos.RIGHT);
    gridpane.add(fNameLbl, 0, 2);

    // Last name label
    GridPane.setHalignment(lNameLbl, HPos.RIGHT);
    gridpane.add(lNameLbl, 0, 3);
    
    // Fish Counter
    GridPane.setHalignment(Fishlabel, HPos.RIGHT);
    gridpane.add(Fishlabel, 0, 4);
    
    // First name field
    GridPane.setHalignment(fNameFld, HPos.LEFT);
    gridpane.add(fNameFld, 1, 2);

    // Last name field
    GridPane.setHalignment(lNameFld, HPos.LEFT);
    gridpane.add(lNameFld, 1, 3);
    
    // LFishtext
    GridPane.setHalignment(fishtext, HPos.LEFT);
    gridpane.add(fishtext, 1, 4);
    fishtext.setText("100");
    fishtext.getText();

    // Start button
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
    
    // Button leer1
    GridPane.setHalignment(leer1, HPos.LEFT);
    gridpane.add(leer1, 2, 7);
    leer1.setMinSize(105, 20);
    leer1.setAlignment(Pos.BASELINE_CENTER);
    
    // Button leer2
    GridPane.setHalignment(leer2, HPos.LEFT);
    gridpane.add(leer2, 2, 8);
    leer2.setMinSize(105, 20);
    leer2.setAlignment(Pos.BASELINE_CENTER);
    
    // Button leer3
    GridPane.setHalignment(leer3, HPos.LEFT);
    gridpane.add(leer3, 2, 9);
    leer3.setMinSize(105, 20);
    leer3.setAlignment(Pos.BASELINE_CENTER);

    root.setCenter(gridpane);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}