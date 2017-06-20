package de.pvr.fish.simulation.view;

import static de.pvr.fish.simulation.util.WatchAreaType.*;

import org.apache.commons.lang3.time.StopWatch;

import java.io.File;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.algorithm.task.CalculateNewPositionTask;
import de.pvr.fish.simulation.application.SimulationApp;
import de.pvr.fish.simulation.config.Configuration;
import de.pvr.fish.simulation.config.ConfigurationHandler;
import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.config.ThreadPoolSingleton;
import de.pvr.fish.simulation.model.Aquarium;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.util.WatchAreaType;
import de.pvr.fish.simulation.util.MeasureUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainViewController extends Application {

	private static final Logger LOG = LogManager.getLogger(MainViewController.class);

	private static double Width = 1200;
	private static double Height = 400;
	private GridPane topGrid;
	private GridPane bottomGrid;
	Canvas fishCanvas = new Canvas(Width, Height);
	private SimulationApp app;
	private GraphicsContext gc = fishCanvas.getGraphicsContext2D();

	private ConfigurationHandler configHandler = new ConfigurationHandler();

	private TextField iterationTextField;
	private TextField threadTextField;
	private TextField fishField;
	private TextField fieldLengthTextField;
	private TextField fieldWidthTextField;
	private TextField deathAngelTextField;
	private TextField neighbourFishTextField;
	private TextField fishLengthTextField;
	private TextField r1TextField;
	private TextField r2TextField;
	private TextField r3TextField;
	private TextField runtimeTextField;
	private TextField kappaTextField;
	private TextField phiTextField;
	private TextField sigmaTextField;

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1250, 850, Color.WHITE);

		// TopGrid
		topGrid = new GridPane();
		topGrid.setAlignment(Pos.TOP_CENTER);
		topGrid.setPadding(new Insets(5));
		topGrid.setHgap(10);
		topGrid.setVgap(10);
		topGrid.setPadding(new Insets(25, 25, 25, 25));
		topGrid.setStyle("-fx-background-color: ALICEBLUE");

		// BottomGrid
		bottomGrid = new GridPane();
		bottomGrid.setAlignment(Pos.CENTER);
		bottomGrid.setPadding(new Insets(5));
		bottomGrid.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		bottomGrid.setHgap(10);
		bottomGrid.setVgap(10);
		bottomGrid.setPadding(new Insets(10, 10, 10, 10));
		bottomGrid.setStyle("-fx-background-color: SKYBLUE");
		bottomGrid.getChildren().add(fishCanvas);
		bottomGrid.prefHeightProperty().bind(fishCanvas.heightProperty());
		bottomGrid.prefWidthProperty().bind(fishCanvas.widthProperty());

		// Spalten
		ColumnConstraints column1 = new ColumnConstraints(140);
		ColumnConstraints column2 = new ColumnConstraints(60);
		ColumnConstraints column3 = new ColumnConstraints(4);
		ColumnConstraints column4 = new ColumnConstraints(100);
		ColumnConstraints column5 = new ColumnConstraints(60);
		ColumnConstraints column6 = new ColumnConstraints(30);
		ColumnConstraints column7 = new ColumnConstraints(140);
		ColumnConstraints column8 = new ColumnConstraints(60);
		ColumnConstraints column9 = new ColumnConstraints(4);
		ColumnConstraints column10 = new ColumnConstraints(22);
		ColumnConstraints column11 = new ColumnConstraints(60);
		ColumnConstraints column12 = new ColumnConstraints(60);
		ColumnConstraints column13 = new ColumnConstraints(100);
		ColumnConstraints column14 = new ColumnConstraints(100);
		// column2.setHgrow(Priority.ALWAYS);
		topGrid.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7, column8,
				column9, column10, column11, column12, column13, column14);

		// Fenstertitel
		primaryStage.setTitle("Paralleles und Verteiltes Rechnen - Visualisierung eines Fischschwarms");
		primaryStage.show();

		// Titel Spalte 1 und 2
		Text simulationParameterHeading = new Text("Simulationsparameter");
		simulationParameterHeading.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
		topGrid.add(simulationParameterHeading, 0, 0);

		// Spalte 1
		Label iterationLabel = new Label("Anzahl der Iterationen:");
		this.iterationTextField = new TextField();
		Label threadLabel = new Label("Anzahl der Threads:");
		this.threadTextField = new TextField();
		Button oneIterationButton = new Button("1");
		Button tenIterationButton = new Button("10");
		Label iterationCounterLabel = new Label("Anzahl an Iterationen");
		Button twentyfiveIterationButton = new Button("25");
		Label loadSafeLabel = new Label("Konfigurationen und Messungen");
		Button saveAsTextButton = new Button("Speichern");
		Button openFileButton = new Button("Öffnen");

		// Spalte 2
		Label fishLabel = new Label("Anzahl der Fische:");
		this.fishField = new TextField();
		Label fieldlength = new Label("Feld Länge:");
		this.fieldLengthTextField = new TextField();
		Label fieldWidthLabel = new Label("Feld Breite:");
		this.fieldWidthTextField = new TextField();
		Button startMeasureButton = new Button("Messungen durchführen");
		Label allMeasureLabel = new Label("Gesamtmessung");

		// Titel Spalte 3 und 4
		Text fishConfigurationHeading = new Text("Fisch Konfiguration");
		fishConfigurationHeading.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
		topGrid.add(fishConfigurationHeading, 6, 0);

		// Spalte 3
		Label deathAngelLabel = new Label("Toter Winkel:");
		this.deathAngelTextField = new TextField();
		Label neighbourFishLabel = new Label("Anzahl der Nachbarn:");
		this.neighbourFishTextField = new TextField();
		Label fishLengthLabel = new Label("Länge des Fisches:");
		this.fishLengthTextField = new TextField();

		// Spalte 4
		Label r1Label = new Label("R1:");
		this.r1TextField = new TextField();
		Label r2Label = new Label("R2:");
		this.r2TextField = new TextField();
		Label r3Label = new Label("R3:");
		this.r3TextField = new TextField();
		Label resetLabel = new Label("Zurücksetzen");
		Button resetButton = new Button("Reset");

		// Titel Spalte 5
		Text measuredValueHeading = new Text("Messwerte (ms)");
		measuredValueHeading.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
		topGrid.add(measuredValueHeading, 12, 0);

		// Spalte 5
		Label runtimeLabel = new Label("Laufzeit:");
		this.runtimeTextField = new TextField();
		Label kappaLabel = new Label("Kappa:");
		this.kappaTextField = new TextField();
		Label phiLabel = new Label("Phi:");
		this.phiTextField = new TextField();
		Label sigmaLabel = new Label("Sigma:");
		this.sigmaTextField = new TextField();
		Button startButton = new Button("Start");

		// Positionierung und Funktionalität

		// Spalte 1
		// Iterationnumber
		GridPane.setHalignment(iterationLabel, HPos.LEFT);
		topGrid.add(iterationLabel, 0, 1);
		GridPane.setHalignment(iterationTextField, HPos.LEFT);
		topGrid.add(iterationTextField, 1, 1);

		// Threadnumber
		GridPane.setHalignment(threadLabel, HPos.LEFT);
		topGrid.add(threadLabel, 0, 2);
		GridPane.setHalignment(threadTextField, HPos.LEFT);
		topGrid.add(threadTextField, 1, 2);

		// FishNumber
		GridPane.setHalignment(fishLabel, HPos.LEFT);
		topGrid.add(fishLabel, 0, 3);
		GridPane.setHalignment(fishField, HPos.LEFT);
		topGrid.add(fishField, 1, 3);

		// Spalte 2

		// FieldLength
		GridPane.setHalignment(fieldlength, HPos.LEFT);
		topGrid.add(fieldlength, 3, 2);
		GridPane.setHalignment(fieldLengthTextField, HPos.LEFT);
		topGrid.add(fieldLengthTextField, 4, 2);

		// FieldLength
		GridPane.setHalignment(fieldWidthLabel, HPos.LEFT);
		topGrid.add(fieldWidthLabel, 3, 3);
		GridPane.setHalignment(fieldWidthTextField, HPos.LEFT);
		topGrid.add(fieldWidthTextField, 4, 3);

		// Label Laden und Speichern
		GridPane.setHalignment(loadSafeLabel, HPos.LEFT);
		topGrid.add(loadSafeLabel, 0, 5);
		loadSafeLabel.setMinSize(200, 20);
		loadSafeLabel.setAlignment(Pos.BASELINE_LEFT);

		// Gesamtmessen Label
		GridPane.setHalignment(allMeasureLabel, HPos.LEFT);
		topGrid.add(allMeasureLabel, 3, 5);
		allMeasureLabel.setMinSize(200, 20);
		allMeasureLabel.setAlignment(Pos.BASELINE_LEFT);

		// Speichern der Daten
		GridPane.setHalignment(saveAsTextButton, HPos.RIGHT);
		topGrid.add(saveAsTextButton, 1, 6);
		saveAsTextButton.setMinSize(100, 20);
		saveAsTextButton.setAlignment(Pos.BASELINE_CENTER);
		saveAsTextButton.setOnAction((ActionEvent event) -> {
			FileChooser fileChooser = new FileChooser();

			// Set extension filter
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
			fileChooser.getExtensionFilters().add(extFilter);

			// Show save file dialog
			File file = fileChooser.showSaveDialog(primaryStage);

			if (file != null) {
				saveFile(file);
			}
		});

		// Öffnen
		GridPane.setHalignment(openFileButton, HPos.LEFT);
		topGrid.add(openFileButton, 0, 6);
		openFileButton.setMinSize(100, 20);
		openFileButton.setAlignment(Pos.BASELINE_CENTER);
		openFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null) {
					configHandler.readFromCSV(file.getAbsolutePath());
					setValues(configHandler.getConfigs().get(1));
				}
			}
		});

		// Messungen durchführen
		GridPane.setHalignment(startMeasureButton, HPos.RIGHT);
		topGrid.add(startMeasureButton, 4, 6);
		startMeasureButton.setMinSize(170, 20);
		startMeasureButton.setAlignment(Pos.BASELINE_CENTER);
		startMeasureButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				simulateMultiConfigs();
			}
		});

		// Spalte 3
		// DeathAngel
		GridPane.setHalignment(deathAngelLabel, HPos.LEFT);
		topGrid.add(deathAngelLabel, 6, 1);
		GridPane.setHalignment(deathAngelTextField, HPos.LEFT);
		topGrid.add(deathAngelTextField, 7, 1);

		// NeighbourFish
		GridPane.setHalignment(neighbourFishLabel, HPos.LEFT);
		topGrid.add(neighbourFishLabel, 6, 2);
		GridPane.setHalignment(neighbourFishTextField, HPos.LEFT);
		topGrid.add(neighbourFishTextField, 7, 2);

		// FishLength
		GridPane.setHalignment(fishLengthLabel, HPos.LEFT);
		topGrid.add(fishLengthLabel, 6, 3);
		GridPane.setHalignment(fishLengthTextField, HPos.LEFT);
		topGrid.add(fishLengthTextField, 7, 3);

		// OneIteration Label
		GridPane.setHalignment(iterationCounterLabel, HPos.LEFT);
		topGrid.add(iterationCounterLabel, 6, 5);
		iterationCounterLabel.setMinSize(140, 20);
		iterationCounterLabel.setAlignment(Pos.BASELINE_LEFT);

		// OneIteration
		GridPane.setHalignment(oneIterationButton, HPos.LEFT);
		topGrid.add(oneIterationButton, 6, 6);
		oneIterationButton.setMinSize(40, 20);
		oneIterationButton.setAlignment(Pos.BASELINE_CENTER);
		oneIterationButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				iterateOnce();
			}
		});

		// TenIteration
		GridPane.setHalignment(tenIterationButton, HPos.CENTER);
		topGrid.add(tenIterationButton, 6, 6);
		tenIterationButton.setMinSize(40, 20);
		tenIterationButton.setAlignment(Pos.BASELINE_CENTER);
		tenIterationButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				iterateTenTimes();
			}
		});

		// TwentyFiveIteration
		GridPane.setHalignment(twentyfiveIterationButton, HPos.RIGHT);
		topGrid.add(twentyfiveIterationButton, 6, 6);
		twentyfiveIterationButton.setMinSize(40, 20);
		twentyfiveIterationButton.setAlignment(Pos.BASELINE_CENTER);
		twentyfiveIterationButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				iterateTwentyFiveTimes();
			}
		});

		// Spalte 4
		// R1
		GridPane.setHalignment(r1Label, HPos.CENTER);
		topGrid.add(r1Label, 9, 1);
		GridPane.setHalignment(r1TextField, HPos.LEFT);
		topGrid.add(r1TextField, 10, 1);

		// R2
		GridPane.setHalignment(r2Label, HPos.CENTER);
		topGrid.add(r2Label, 9, 2);
		GridPane.setHalignment(r2TextField, HPos.LEFT);
		topGrid.add(r2TextField, 10, 2);

		// R3
		GridPane.setHalignment(r3Label, HPos.CENTER);
		topGrid.add(r3Label, 9, 3);
		GridPane.setHalignment(r3TextField, HPos.LEFT);
		topGrid.add(r3TextField, 10, 3);

		// ResetLabel
		GridPane.setHalignment(resetLabel, HPos.LEFT);
		topGrid.add(resetLabel, 9, 5);
		resetLabel.setMinSize(200, 20);
		resetLabel.setAlignment(Pos.BASELINE_LEFT);

		// ResetButton
		GridPane.setHalignment(resetButton, HPos.RIGHT);
		topGrid.add(resetButton, 10, 6);
		resetButton.setMinSize(92, 20);
		resetButton.setAlignment(Pos.BASELINE_CENTER);
		// Setting an action for the Clear button
		resetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				setDefaultValues();
			}
		});

		// Spalte 5
		// Laufzeit
		GridPane.setHalignment(runtimeLabel, HPos.LEFT);
		topGrid.add(runtimeLabel, 12, 1);
		GridPane.setHalignment(runtimeTextField, HPos.LEFT);
		topGrid.add(runtimeTextField, 13, 1);
		runtimeTextField.setEditable(false);
		runtimeTextField.setMouseTransparent(true);
		runtimeTextField.setFocusTraversable(false);

		// Sigma
		GridPane.setHalignment(sigmaLabel, HPos.LEFT);
		topGrid.add(sigmaLabel, 12, 2);
		GridPane.setHalignment(sigmaTextField, HPos.LEFT);
		topGrid.add(sigmaTextField, 13, 2);
		sigmaTextField.setEditable(false);
		sigmaTextField.setMouseTransparent(true);
		sigmaTextField.setFocusTraversable(false);

		// Phi
		GridPane.setHalignment(phiLabel, HPos.LEFT);
		topGrid.add(phiLabel, 12, 3);
		GridPane.setHalignment(phiTextField, HPos.LEFT);
		topGrid.add(phiTextField, 13, 3);
		phiTextField.setEditable(false);
		phiTextField.setMouseTransparent(true);
		phiTextField.setFocusTraversable(false);

		// Kappa
		GridPane.setHalignment(kappaLabel, HPos.LEFT);
		topGrid.add(kappaLabel, 12, 4);
		GridPane.setHalignment(kappaTextField, HPos.LEFT);
		topGrid.add(kappaTextField, 13, 4);
		kappaTextField.setEditable(false);
		kappaTextField.setMouseTransparent(true);
		kappaTextField.setFocusTraversable(false);

		// StartButton
		GridPane.setHalignment(startButton, HPos.LEFT);
		topGrid.add(startButton, 12, 6);
		startButton.setMinSize(210, 20);
		startButton.setAlignment(Pos.BASELINE_CENTER);
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				createAndStartSimulation(Integer.parseInt(fieldLengthTextField.getText()),
						Integer.parseInt(fieldWidthTextField.getText()), Integer.parseInt(fishField.getText()),
						Integer.parseInt(threadTextField.getText()), Integer.parseInt(iterationTextField.getText()),
						Integer.parseInt(neighbourFishTextField.getText()),
						Integer.parseInt(deathAngelTextField.getText()), Double.parseDouble(r1TextField.getText()),
						Double.parseDouble(r2TextField.getText()), Double.parseDouble(r3TextField.getText()),
						Integer.parseInt(fishLengthTextField.getText()));

			}
		});
		this.gc.setLineWidth(3);

		// Vertikale Seperatoren
		// Seperator1
		Separator sepVert1 = new Separator();
		sepVert1.setOrientation(Orientation.VERTICAL);
		sepVert1.setValignment(VPos.BOTTOM);
		sepVert1.setPrefHeight(80);
		GridPane.setConstraints(sepVert1, 20, 20);
		GridPane.setRowSpan(sepVert1, 3);

		// Seperator2
		Separator sepVert2 = new Separator();
		sepVert2.setOrientation(Orientation.VERTICAL);
		sepVert2.setValignment(VPos.BOTTOM);
		sepVert2.setPrefHeight(80);
		GridPane.setConstraints(sepVert2, 20, 20);
		GridPane.setRowSpan(sepVert2, 3);
		setDefaultValues();

		// Seperator3
		Separator sepVert3 = new Separator();
		sepVert3.setOrientation(Orientation.VERTICAL);
		sepVert3.setValignment(VPos.BOTTOM);
		sepVert3.setPrefHeight(30);
		GridPane.setConstraints(sepVert3, 10, 10);
		GridPane.setRowSpan(sepVert3, 2);
		setDefaultValues();

		// Seperator4
		Separator sepVert4 = new Separator();
		sepVert4.setOrientation(Orientation.VERTICAL);
		sepVert4.setValignment(VPos.BOTTOM);
		sepVert4.setPrefHeight(30);
		GridPane.setConstraints(sepVert4, 10, 10);
		GridPane.setRowSpan(sepVert4, 2);
		setDefaultValues();
		
		// Anzeigen des Panes
		topGrid.add(new Separator(), 0, 4, 5, 1);
		topGrid.add(new Separator(), 0, 7, 5, 1);
		topGrid.add(new Separator(), 6, 4, 5, 1);
		topGrid.add(new Separator(), 6, 7, 5, 1);
		topGrid.add(new Separator(), 12, 5, 2, 1);
		topGrid.add(new Separator(), 12, 7, 2, 1);
		topGrid.add(sepVert1, 2, 1);
		topGrid.add(sepVert2, 8, 1);
		topGrid.add(sepVert3, 2, 5);
		topGrid.add(sepVert4, 8, 5);
		root.setTop(topGrid);
		root.setCenter(bottomGrid);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
	}

	private void saveFile(File file) {
		this.configHandler.writeToCSV(file.getAbsolutePath());
	}

	public void createAndStartSimulation(int fieldLength, int fieldHeight, int fishNumber, int threads, int iterations,
			int neighbours, int deathAngle, double r1, double r2, double r3, int bodyLength) {
		createSimulation(fieldLength, fieldHeight, fishNumber, threads, iterations, neighbours, deathAngle, r1, r2, r3,
				bodyLength);
		iterateAndDraw(this.app.getIterations());
	}

	public void createSimulation(int fieldLength, int fieldHeight, int fishNumber, int threads, int iterations,
			int neighbours, int deathAngle, double r1, double r2, double r3, int bodyLength) {
		if (this.gc != null && this.app != null) {
			this.gc.clearRect(0, 0, this.app.getAquarium().getLength(), this.app.getAquarium().getHeight());
		}
		LOG.debug("Start to create a new SimulationApp");
		this.app = new SimulationApp(fieldLength, fieldHeight, fishNumber, threads, iterations, neighbours, deathAngle,
				r1, r2, r3, bodyLength);
		this.fishCanvas = new Canvas(fieldLength, fieldHeight);
		// this.gc = fishCanvas.getGraphicsContext2D();
		for (Fish fish : this.app.getAquarium().getFishes()) {
			drawFish(fish.getPosition().getCoordinateX(), fish.getPosition().getCoordinateY(),
					fish.getLengthPosition().getCoordinateX(), fish.getLengthPosition().getCoordinateY());
		}
	}

	private void drawFish(double x1, double y1, double x2, double y2) {
		this.gc.strokeLine(x1, y1, x2, y2);
		this.gc.strokeOval(x1 - 1, y1 - 1, 3, 3);
	}

	public void createAndStartSimulation(Configuration conf) {
		createAndStartSimulation(conf.getFieldLength(), conf.getFieldHeight(), conf.getFishNumber(), conf.getThreads(),
				conf.getIteration(), conf.getNumberOfNeighbours(), conf.getDeathAngle(), conf.getR1(), conf.getR2(),
				conf.getR3(), conf.getBodyLength());
	}

	public SimulationApp createSimulation(Configuration conf) {
		createSimulation(conf.getFieldLength(), conf.getFieldHeight(), conf.getFishNumber(), conf.getThreads(),
				conf.getIteration(), conf.getNumberOfNeighbours(), conf.getDeathAngle(), conf.getR1(), conf.getR2(),
				conf.getR3(), conf.getBodyLength());
		return this.app;
	}

	public void iterateOnce() {
		iterateAndDraw(1);
	}

	public void iterateTenTimes() {
		iterateAndDraw(10);
	}

	public void iterateTwentyFiveTimes() {
		iterateAndDraw(25);
	}

	private void iterateAndDraw(int iterations) {
		GuiTask worker = new GuiTask(this.fishCanvas, this.gc, app, iterations);
		Thread workerThread = new Thread(worker);
		// ThreadPoolSingleton.getExecutorService().execute(workerThread);
		workerThread.start();
	}

	private void iterateAndDraw(int iterations, SimulationApp app) {
		GuiTask worker = new GuiTask(fishCanvas, gc, app, iterations);
		Thread workerThread = new Thread(worker);
		workerThread.start();
	}

	private void setDefaultValues() {
		this.iterationTextField.setText(Integer.toString(FishParameter.DEFAULT_ITERATIONS));
		this.threadTextField.setText(Integer.toString(FishParameter.DEFAULT_THREADS));
		this.fishField.setText(Integer.toString(FishParameter.DEFAULT_NUMBER_FISH));
		this.fieldLengthTextField.setText(Integer.toString(FishParameter.DEFAULT_FIELD_LENGTH));
		this.fieldWidthTextField.setText(Integer.toString(FishParameter.DEFAULT_FIELD_HEIGHT));
		this.deathAngelTextField.setText(Integer.toString(FishParameter.DEFAULT_DEATH_ANGLE));
		this.neighbourFishTextField.setText(Integer.toString(FishParameter.DEFAULT_NUMBER_OF_NEIGHBOURS));
		this.fishLengthTextField.setText(Integer.toString(FishParameter.DEFAULT_FISH_BODY_LENGTH));
		this.r1TextField.setText(String.valueOf(FishParameter.DEFAULT_RADIUS1));
		this.r2TextField.setText(String.valueOf(FishParameter.DEFAULT_RADIUS2));
		this.r3TextField.setText(String.valueOf(FishParameter.DEFAULT_RADIUS3));
	}

	private void setValues(int iterations, int threads, int fishNumber, int fieldLength, int fieldHeight,
			int deathAngel, int neighbours, int bodyLength, double r1, double r2, double r3) {
		this.iterationTextField.setText(Integer.toString(iterations));
		this.threadTextField.setText(Integer.toString(threads));
		this.fishField.setText(Integer.toString(fishNumber));
		this.fieldLengthTextField.setText(Integer.toString(fieldLength));
		this.fieldWidthTextField.setText(Integer.toString(fieldHeight));
		this.deathAngelTextField.setText(Integer.toString(deathAngel));
		this.neighbourFishTextField.setText(Integer.toString(neighbours));
		this.fishLengthTextField.setText(Integer.toString(bodyLength));
		this.r1TextField.setText(String.valueOf(r1));
		this.r2TextField.setText(String.valueOf(r2));
		this.r3TextField.setText(String.valueOf(r3));
	}

	private void setValues(Configuration conf) {
		setValues(conf.getIteration(), conf.getThreads(), conf.getFishNumber(), conf.getFieldLength(),
				conf.getFieldHeight(), conf.getDeathAngle(), conf.getNumberOfNeighbours(), conf.getBodyLength(),
				conf.getR1(), conf.getR2(), conf.getR3());
	}

	private void addConfigToConfigList() {
		Configuration conf = new Configuration(Integer.parseInt(this.iterationTextField.getText()),
				Integer.parseInt(this.threadTextField.getText()), Integer.parseInt(this.fishField.getText()),
				Integer.parseInt(this.fieldLengthTextField.getText()),
				Integer.parseInt(this.fieldWidthTextField.getText()),
				Integer.parseInt(this.neighbourFishTextField.getText()),
				Integer.parseInt(this.deathAngelTextField.getText()), Double.parseDouble(this.r1TextField.getText()),
				Double.parseDouble(this.r2TextField.getText()), Double.parseDouble(this.r3TextField.getText()),
				Integer.parseInt(this.fishLengthTextField.getText()),
				Double.parseDouble(this.runtimeTextField.getText()), Double.parseDouble(this.kappaTextField.getText()),
				Double.parseDouble(this.sigmaTextField.getText()), Double.parseDouble(this.phiTextField.getText()));

		this.configHandler.addConfig(conf);
	}

	private void addResultValuesToConf(Configuration conf) {
		conf.setRuntime(Double.parseDouble(this.runtimeTextField.getText()));
		conf.setKappa(Double.parseDouble(this.kappaTextField.getText()));
		conf.setSigma(Double.parseDouble(this.sigmaTextField.getText()));
		conf.setPhi(Double.parseDouble(this.phiTextField.getText()));
	}

	private void simulateMultiConfigs() {
		GuiTask worker = new GuiTask(fishCanvas, gc);
		Thread workerThread = new Thread(worker);
		workerThread.start();
	}

	private class GuiTask extends Task<Void> {

		private Canvas fishCanvas;
		private GraphicsContext gc;
		private SimulationApp app;
		private int iterations;
		private boolean multiSimulationFlag;

		public GuiTask(Canvas fishCanvas, GraphicsContext gc, SimulationApp app, int iterations) {
			super();
			this.fishCanvas = fishCanvas;
			this.gc = gc;
			this.app = app;
			this.iterations = iterations;
			this.multiSimulationFlag = false;
		}

		public GuiTask(Canvas fishCanvas, GraphicsContext gc, Configuration conf) {
			super();
			this.fishCanvas = fishCanvas;
			this.gc = gc;
			this.app = createSimulation(conf);
			this.iterations = conf.getIteration();
			this.multiSimulationFlag = false;
		}

		public GuiTask(Canvas fishCanvas, GraphicsContext gc) {
			super();
			this.fishCanvas = fishCanvas;
			this.gc = gc;
			this.multiSimulationFlag = true;
		}

		@Override
		protected Void call() throws Exception {
			LOG.debug("Start iteration multi = " + this.multiSimulationFlag);
			if (this.multiSimulationFlag) {
				ArrayList<Configuration> configs = configHandler.getConfigs();
				for (Configuration conf : configs) {
					// resultLogger.removeIfContains(conf);
					this.app = createSimulation(conf);
					this.fishCanvas = new Canvas(conf.getFieldLength(), conf.getFieldHeight());
					// this.gc = fishCanvas.getGraphicsContext2D();
					setValues(conf);
					iterateAndDraw(conf.getIteration());
					addResultValuesToConf(conf);
				}
			} else {
				iterateAndDraw(this.iterations);
				addConfigToConfigList();
			}
			return null;
		}

		private void iterateAndDraw(int iteration) {
			LOG.debug("Start iteration multi with iteration = " + iteration);
			DrawStep drawWorker = new DrawStep(fishCanvas, app, gc);
			MeasureUtil.resetAllWatches();
			MeasureUtil.startWatch(RUNTIME);
			for (int i = 0; i < iteration; i++) {
				LOG.debug("Start iteration number " + i);
				this.app.startIteration();
				FutureTask<Boolean> drawTask = new FutureTask<>(drawWorker);
				Platform.runLater(drawTask);
				try {
					drawTask.get();
				} catch (InterruptedException | ExecutionException e) {
					LOG.error(e);
				}
			}
			MeasureUtil.suspend(RUNTIME);
			showAllMeasures();
		}

		private void showAllMeasures() {
			MeasureUtil.logAllWatches();
			runtimeTextField.setText(String.valueOf(MeasureUtil.getMeasuredTimeFor(RUNTIME)));
			sigmaTextField.setText(String.valueOf(MeasureUtil.getMeasuredTimeFor(SIGMA)));
			phiTextField.setText(String.valueOf(MeasureUtil.getMeasuredTimeFor(PHI)));
			kappaTextField.setText(String.valueOf(MeasureUtil.getMeasuredTimeFor(KAPPA)));
		}
	}
}
