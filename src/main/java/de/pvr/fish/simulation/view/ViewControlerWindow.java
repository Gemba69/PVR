package de.pvr.fish.simulation.view;

import java.util.ArrayList;

import de.pvr.fish.simulation.application.Field;
import de.pvr.fish.simulation.application.SimulationApp;
import de.pvr.fish.simulation.model.Fish;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewControlerWindow extends Application {

	private GridPane topGrid;
	private TextField fieldWidthTextField;
	private TextField fieldLengthTextField;
	// private GridPane bottomGrid;
	Canvas bottomCanvas = new Canvas(900, 550);
	// Canvas bottomCanvas = new
	// Canvas(Double.parseDouble(fieldLengthTextField.getText()),
	// Double.parseDouble(fieldWidthTextField.getText()));
	private SimulationApp fieldWindow;
	private GraphicsContext gc = bottomCanvas.getGraphicsContext2D();

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 920, 900, Color.WHITE);

		// Menüleiste
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
		topGrid.setPadding(new Insets(5));
		topGrid.setHgap(10);
		topGrid.setVgap(10);
		topGrid.setPadding(new Insets(25, 25, 25, 25));
		topGrid.setStyle("-fx-background-color: white");

		// Spalten
		ColumnConstraints column1 = new ColumnConstraints(140);
		ColumnConstraints column2 = new ColumnConstraints(60);
		ColumnConstraints column3 = new ColumnConstraints(140);
		ColumnConstraints column4 = new ColumnConstraints(60);
		ColumnConstraints column5 = new ColumnConstraints(30);
		ColumnConstraints column6 = new ColumnConstraints(140);
		ColumnConstraints column7 = new ColumnConstraints(60);
		ColumnConstraints column8 = new ColumnConstraints(40);
		ColumnConstraints column9 = new ColumnConstraints(60);
		ColumnConstraints column10 = new ColumnConstraints(40);
		column2.setHgrow(Priority.ALWAYS);
		topGrid.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7, column8,
				column9, column10);

		// Fenstertitel
		primaryStage.setTitle("PVR - Simulation");
		primaryStage.show();

		// Titel Spalte 1 und 2
		Text simulationParameterHeading = new Text("Simulationsparameter");
		simulationParameterHeading.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
		topGrid.add(simulationParameterHeading, 0, 0);

		// Spalte 1
		Label iterationLabel = new Label("Iterationen:");
		TextField iterationTextField = new TextField();
		Label threadLabel = new Label("Anzahl der Threads:");
		TextField threadTextField = new TextField();
		Label oneIterationLabel = new Label("Ein Iterationsschritt:");
		Button oneIterationButton = new Button("Step");

		// Seperator
		final Separator sepHor = new Separator();
		sepHor.setValignment(VPos.CENTER);
		GridPane.setConstraints(sepHor, 7, 1);
		GridPane.setColumnSpan(sepHor, 7);

		// Spalte 2
		Label fishLabel = new Label("Anzahl der Fische:");
		TextField fishField = new TextField();
		Label fieldlength = new Label("Feld Länge:");
		TextField fieldLengthTextField = new TextField();
		Label fieldWidthLabel = new Label("Feld Breite:");
		TextField fieldWidthTextField = new TextField();

		// Titel Spalte 3 und 4
		Text fishConfigurationHeading = new Text("Fisch Konfiguration");
		fishConfigurationHeading.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
		topGrid.add(fishConfigurationHeading, 5, 0);

		// Spalte 3
		Label deathAngelLabel = new Label("Toter Winkel:");
		TextField deathAngelTextField = new TextField();
		Label neighbourFishLabel = new Label("Anzahl der Nachbarn:");
		TextField neighbourFishTextField = new TextField();
		Label fishLengthLabel = new Label("Länge des Fisches:");
		TextField fishLengthTextField = new TextField();

		// Spalte 4
		Label r1Label = new Label("R1:");
		TextField r1TextField = new TextField();
		Label r2Label = new Label("R2:");
		TextField r2TextField = new TextField();
		Label r3Label = new Label("R3:");
		TextField r3TextField = new TextField();

		// StartButton / RestButton
		Button startButton = new Button("Start");
		Button resetButton = new Button("Reset");

		// -----------------------------------------------

		// Spalte 1
		// Iterationnumber
		GridPane.setHalignment(iterationLabel, HPos.LEFT);
		topGrid.add(iterationLabel, 0, 1);
		GridPane.setHalignment(iterationTextField, HPos.LEFT);
		topGrid.add(iterationTextField, 1, 1);
		iterationTextField.setText("100");
		iterationTextField.getText();

		// Threadnumber
		GridPane.setHalignment(threadLabel, HPos.LEFT);
		topGrid.add(threadLabel, 0, 2);
		GridPane.setHalignment(threadTextField, HPos.LEFT);
		topGrid.add(threadTextField, 1, 2);
		threadTextField.setText("4");
		threadTextField.getText();

		// OneIteration
		GridPane.setHalignment(oneIterationLabel, HPos.LEFT);
		topGrid.add(oneIterationLabel, 0, 3);
		GridPane.setHalignment(oneIterationButton, HPos.LEFT);
		topGrid.add(oneIterationButton, 1, 3);
		oneIterationButton.setMinSize(60, 20);
		oneIterationButton.setAlignment(Pos.BASELINE_LEFT);
		oneIterationButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				iterateOnce();
				// fishLabel.setText("Accepted");
				//redrawFish(0, 0, 0, 0);
			}
		});

		// Methode nur ein Iterationsschritt

		// Spalte 2
		// FishNumber
		GridPane.setHalignment(fishLabel, HPos.LEFT);
		topGrid.add(fishLabel, 2, 1);
		GridPane.setHalignment(fishField, HPos.LEFT);
		topGrid.add(fishField, 3, 1);
		fishField.setText("100");
		fishField.getText();

		// FieldLength
		GridPane.setHalignment(fieldlength, HPos.LEFT);
		topGrid.add(fieldlength, 2, 2);
		GridPane.setHalignment(fieldLengthTextField, HPos.LEFT);
		topGrid.add(fieldLengthTextField, 3, 2);
		fieldLengthTextField.setText("600");
		fieldLengthTextField.getText();

		// FieldLength
		GridPane.setHalignment(fieldWidthLabel, HPos.LEFT);
		topGrid.add(fieldWidthLabel, 2, 3);
		GridPane.setHalignment(fieldWidthTextField, HPos.LEFT);
		topGrid.add(fieldWidthTextField, 3, 3);
		fieldWidthTextField.setText("600");
		fieldWidthTextField.getText();

		// Spalte 3
		// DeathAngel
		GridPane.setHalignment(deathAngelLabel, HPos.LEFT);
		topGrid.add(deathAngelLabel, 5, 1);
		GridPane.setHalignment(deathAngelTextField, HPos.LEFT);
		topGrid.add(deathAngelTextField, 6, 1);
		deathAngelTextField.setText("30");
		deathAngelTextField.getText();

		// NeighbourFish
		GridPane.setHalignment(neighbourFishLabel, HPos.LEFT);
		topGrid.add(neighbourFishLabel, 5, 2);
		GridPane.setHalignment(neighbourFishTextField, HPos.LEFT);
		topGrid.add(neighbourFishTextField, 6, 2);
		neighbourFishTextField.setText("4");
		neighbourFishTextField.getText();

		// FishLength
		GridPane.setHalignment(fishLengthLabel, HPos.LEFT);
		topGrid.add(fishLengthLabel, 5, 3);
		GridPane.setHalignment(fishLengthTextField, HPos.LEFT);
		topGrid.add(fishLengthTextField, 6, 3);
		fishLengthTextField.setText("2");
		fishLengthTextField.getText();

		// Spalte 4
		// R1
		GridPane.setHalignment(r1Label, HPos.CENTER);
		topGrid.add(r1Label, 7, 1);
		GridPane.setHalignment(r1TextField, HPos.LEFT);
		topGrid.add(r1TextField, 8, 1);
		r1TextField.setText("2");
		r1TextField.getText();

		// R2
		GridPane.setHalignment(r2Label, HPos.CENTER);
		topGrid.add(r2Label, 7, 2);
		GridPane.setHalignment(r2TextField, HPos.LEFT);
		topGrid.add(r2TextField, 8, 2);
		r2TextField.setText("4");
		r2TextField.getText();

		// R3
		GridPane.setHalignment(r3Label, HPos.CENTER);
		topGrid.add(r3Label, 7, 3);
		GridPane.setHalignment(r3TextField, HPos.LEFT);
		topGrid.add(r3TextField, 8, 3);
		r3TextField.setText("6");
		if (r3TextField.getText().trim().equals("")) {
			r3TextField.setStyle("-fx-text-box-border: red");
		}else
			r3TextField.getText();

	

		// StartButton / ResetButton
		// StartButton
		GridPane.setHalignment(startButton, HPos.LEFT);
		topGrid.add(startButton, 6, 6);
		startButton.setMinSize(180, 20);
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				fieldWindow(Integer.parseInt(fieldLengthTextField.getText()),
						Integer.parseInt(fieldWidthTextField.getText()), 
						Integer.parseInt(fishField.getText()),
						Integer.parseInt(threadTextField.getText()),
						Integer.parseInt(iterationTextField.getText()),
						Integer.parseInt(neighbourFishTextField.getText()),
						Integer.parseInt(deathAngelTextField.getText()), 2, 4, 6, 1);
				// fishLabel.setText("Accepted");
				//redrawFish(0, 0, 0, 0);
			}
		});
		// ResetButton
		GridPane.setHalignment(resetButton, HPos.LEFT);
		topGrid.add(resetButton, 5, 6);
		resetButton.setMinSize(80, 20);
		resetButton.setAlignment(Pos.BASELINE_CENTER);

		// Setting an action for the Clear button
		resetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				iterationTextField.setText("100");
				threadTextField.setText("4");
				fishField.setText("100");
				fieldLengthTextField.setText("600");
				fieldWidthTextField.setText("600");
				deathAngelTextField.setText("30");
				neighbourFishTextField.setText("4");
				fishLengthTextField.setText("2");
				r1TextField.setText("2");
				r2TextField.setText("4");
				r3TextField.setText("6");

			}
		});

		// Anzeigen des Panes
		gc.setFill(Color.ALICEBLUE);
		topGrid.add(new Separator(), 0, 4, 4, 1);
		topGrid.add(new Separator(), 5, 4, 4, 1);
		topGrid.add(new Separator(), 5, 8, 4, 1);
		root.setTop(menuBar);
		root.setCenter(topGrid);
		// root.setBottom(bottomGrid);
		root.setBottom(bottomCanvas);
		bottomCanvas.setStyle("-fx-background-color: red");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/*
	 * public boolean checkParamter(){ if (fishField.getText() && ()); return
	 * false;
	 * 
	 * 
	 * }
	 */
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	// 01 0+0 1+1
	private void drawFish(double x1, double y1, double x2, double y2) {
		this.gc.strokeLine(x1, y1, x2, y2);
		this.gc.strokeOval(x1, y1, 2, 2);
		gc.setFill(Color.ALICEBLUE);
		// gc.sets(fieldLengthTextField.getText(),
		// fieldWidthTextField.getText());

	}
/*
	// Größe ändern nachdem Start gedrückt wird
	private void redrawFish(double x1, double y1, double x2, double y2) {
		gc.setStroke(Color.ALICEBLUE);
		gc.fillRect(0, 0, bottomCanvas.getWidth(), bottomCanvas.getHeight());
		this.gc.strokeLine(x1, y1, x2, y2);
		this.gc.strokeOval(x1, y1, 2, 2);
	}
*/
	public void fieldWindow(int fieldLength, int fieldHeight, int fishNumber, int threads, int iterations,
			int neighbours, int deathAngle, int r1, int r2, int r3, int bodyLength) {

		this.fieldWindow = new SimulationApp(fieldLength, fieldHeight, fishNumber, threads, iterations, neighbours,
				deathAngle, r1, r2, r3, bodyLength);
		for (int i = 0; i < iterations; i++) {
			this.gc.clearRect(0, 0, fieldLength, fieldHeight);
			for (Fish fish : this.fieldWindow.getFishList()) {
				drawFish(fish.getPosition().getCoordinateX(), fish.getPosition().getCoordinateY(),
						fish.getLengthPosition().getCoordinateX(), fish.getLengthPosition().getCoordinateY());
				// drawFish(fish.getNextPosition().getCoordinateX(),
				// fish.getNextPosition().getCoordinateY(),
				// fish.getPosition().getCoordinateX(),
				// fish.getPosition().getCoordinateY());
			}
			this.fieldWindow.startIteration();
		}

	}
	
	public void iterateOnce() {
		this.fieldWindow.startIteration();
	}
}