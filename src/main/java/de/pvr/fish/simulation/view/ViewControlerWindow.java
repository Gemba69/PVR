package de.pvr.fish.simulation.view;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.commons.lang.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.algorithm.task.CalculatePositionTask;
import de.pvr.fish.simulation.application.SimulationApp;
import de.pvr.fish.simulation.config.FishParameter;
import de.pvr.fish.simulation.model.Fish;
import de.pvr.fish.simulation.util.ThreadPoolSingleton;
import de.pvr.fish.simulation.util.WatchAreaType;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
	
	private static final Logger LOG = LogManager.getLogger(ViewControlerWindow.class);

	private static double Width = 600;
	private static double Height = 600;
	private GridPane topGrid;
	private GridPane bottomGrid;
	Canvas fishCanvas = new Canvas(Width, Height);
	// Canvas bottomCanvas = new
	// Canvas(Double.parseDouble(fieldLengthTextField.getText()),
	// Double.parseDouble(fieldWidthTextField.getText()));
	private SimulationApp fieldWindow;
	private GraphicsContext gc = fishCanvas.getGraphicsContext2D();

	TextField iterationTextField;
	TextField threadTextField;
	TextField fishField;
	TextField fieldLengthTextField;
	TextField fieldWidthTextField;
	TextField deathAngelTextField;
	TextField neighbourFishTextField;
	TextField fishLengthTextField;
	TextField r1TextField;
	TextField r2TextField;
	TextField r3TextField;
	TextField speedupTextField;
	TextField kappaTextField;
	TextField phiTextField;
	TextField sigmaTextField;
	TextField mValueTextField5;
	
	private DrawControler drawWorker;
	private FutureTask<Boolean> drawTask;

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1200, 850, Color.WHITE);

		// Menüleiste
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		// root.setTop(menuBar);

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
		topGrid.setAlignment(Pos.TOP_CENTER);
		topGrid.setPadding(new Insets(5));
		topGrid.setHgap(10);
		topGrid.setVgap(10);
		topGrid.setPadding(new Insets(25, 25, 25, 25));
		topGrid.setStyle("-fx-background-color: ALICEBLUE");
		// BottomGrid
		bottomGrid = new GridPane();
		bottomGrid.setAlignment(Pos.CENTER);
		// bottomGrid.autosize();
		bottomGrid.setPadding(new Insets(5));
		bottomGrid.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		bottomGrid.setHgap(10);
		bottomGrid.setVgap(10);
		bottomGrid.setPadding(new Insets(10, 10, 10, 10));
		bottomGrid.setStyle("-fx-background-color: SKYBLUE");
		bottomGrid.getChildren().add(fishCanvas);
		// fishCanvas.widthProperty().bind(bottomGrid.widthProperty());
		// fishCanvas.heightProperty().bind(bottomGrid.heightProperty());
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
		column2.setHgrow(Priority.ALWAYS);
		topGrid.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7, column8,
				column9, column10, column11, column12, column13, column14);

		// Fenstertitel
		primaryStage.setTitle("PVR - Simulation");
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
		Label iterationCounterLabel = new Label("Anzahl an Iterationen:");
		Button twentyfiveIterationButton = new Button("25");

		// Spalte 2
		Label fishLabel = new Label("Anzahl der Fische:");
		this.fishField = new TextField();
		Label fieldlength = new Label("Feld Länge:");
		this.fieldLengthTextField = new TextField();
		Label fieldWidthLabel = new Label("Feld Breite:");
		this.fieldWidthTextField = new TextField();

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

		// StartButton / RestButton
		Button startButton = new Button("Start");
		Button resetButton = new Button("Reset");

		// Titel Spalte 5
		Text measuredValueHeading = new Text("Messwerte");
		measuredValueHeading.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
		topGrid.add(measuredValueHeading, 12, 0);

		// Spalte 5
		Label speedupLabel = new Label("Speedup:");
		TextField speedupTextField = new TextField();
		Label kappaLabel = new Label("Kappa:");
		TextField kappaTextField = new TextField();
		Label phiLabel = new Label("Phi:");
		TextField phiTextField = new TextField();
		Label sigmaLabel = new Label("Sigma:");
		TextField sigmaTextField = new TextField();
		Label mValue5Label = new Label("Messwert5:");
		TextField mValueTextField5 = new TextField();

		// -----------------------------------------------

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

		// OneIteration
		GridPane.setHalignment(iterationCounterLabel, HPos.LEFT);
		topGrid.add(iterationCounterLabel, 0, 5);
		GridPane.setHalignment(oneIterationButton, HPos.LEFT);
		topGrid.add(oneIterationButton, 1, 5);
		oneIterationButton.setMinSize(10, 20);
		oneIterationButton.setAlignment(Pos.BASELINE_CENTER);
		oneIterationButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				iterateOnce();
			}
		});

		// TenIteration
		GridPane.setHalignment(tenIterationButton, HPos.RIGHT);
		topGrid.add(tenIterationButton, 1, 5);
		tenIterationButton.setMinSize(10, 20);
		tenIterationButton.setAlignment(Pos.BASELINE_CENTER);
		tenIterationButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				iterateTenTimes();
			}
		});

		// twentyFiveIteration
		GridPane.setHalignment(twentyfiveIterationButton, HPos.LEFT);
		topGrid.add(twentyfiveIterationButton, 3, 5);
		twentyfiveIterationButton.setMinSize(10, 20);
		twentyfiveIterationButton.setAlignment(Pos.BASELINE_CENTER);
		twentyfiveIterationButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				iterateTwentyFiveTimes();
			}
		});

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
		// if (r3TextField.getText().trim().equals("")) {
		// r3TextField.setStyle("-fx-text-box-border: red");
		// }

		// Spalte 5
		// Speedup
		GridPane.setHalignment(speedupLabel, HPos.LEFT);
		topGrid.add(speedupLabel, 12, 1);
		GridPane.setHalignment(speedupTextField, HPos.LEFT);
		topGrid.add(speedupTextField, 13, 1);
		speedupTextField.setEditable(false);
		speedupTextField.setMouseTransparent(true);
		speedupTextField.setFocusTraversable(false);

		// Kappa
		GridPane.setHalignment(kappaLabel, HPos.LEFT);
		topGrid.add(kappaLabel, 12, 2);
		GridPane.setHalignment(kappaTextField, HPos.LEFT);
		topGrid.add(kappaTextField, 13, 2);
		kappaTextField.setEditable(false);
		kappaTextField.setMouseTransparent(true);
		kappaTextField.setFocusTraversable(false);
		kappaTextField.setText("Milli-Sek");
		kappaTextField.getText();

		// Phi
		GridPane.setHalignment(phiLabel, HPos.LEFT);
		topGrid.add(phiLabel, 12, 3);
		GridPane.setHalignment(phiTextField, HPos.LEFT);
		topGrid.add(phiTextField, 13, 3);
		phiTextField.setEditable(false);
		phiTextField.setMouseTransparent(true);
		phiTextField.setFocusTraversable(false);

		// Sigma
		GridPane.setHalignment(sigmaLabel, HPos.LEFT);
		topGrid.add(sigmaLabel, 12, 4);
		GridPane.setHalignment(sigmaTextField, HPos.LEFT);
		topGrid.add(sigmaTextField, 13, 4);
		sigmaTextField.setEditable(false);
		sigmaTextField.setMouseTransparent(true);
		sigmaTextField.setFocusTraversable(false);

		// Messwert5
		GridPane.setHalignment(mValue5Label, HPos.LEFT);
		topGrid.add(mValue5Label, 12, 5);
		GridPane.setHalignment(mValueTextField5, HPos.LEFT);
		topGrid.add(mValueTextField5, 13, 5);
		mValueTextField5.setEditable(false);
		mValueTextField5.setMouseTransparent(true);
		mValueTextField5.setFocusTraversable(false);

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

		// StartButton / ResetButton
		// StartButton
		GridPane.setHalignment(startButton, HPos.LEFT);
		topGrid.add(startButton, 7, 5);
		startButton.setMinSize(176, 20);
		startButton.setAlignment(Pos.BASELINE_CENTER);
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				createFieldWindow(Integer.parseInt(fieldLengthTextField.getText()),
						Integer.parseInt(fieldWidthTextField.getText()), Integer.parseInt(fishField.getText()),
						Integer.parseInt(threadTextField.getText()), Integer.parseInt(iterationTextField.getText()),
						Integer.parseInt(neighbourFishTextField.getText()),
						Integer.parseInt(deathAngelTextField.getText()), Double.parseDouble(r1TextField.getText()),
						Double.parseDouble(r2TextField.getText()), Double.parseDouble(r3TextField.getText()),
						Integer.parseInt(fishLengthTextField.getText()));

			}
		});
		this.gc.setLineWidth(3);

		// ResetButton
		GridPane.setHalignment(resetButton, HPos.LEFT);
		topGrid.add(resetButton, 6, 5);
		resetButton.setMinSize(80, 20);
		resetButton.setAlignment(Pos.BASELINE_CENTER);
		// Setting an action for the Clear button
		resetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				setDefaultValues();
			}
		});
		// Anzeigen des Panes
		// gc.setFill(Color.ALICEBLUE);
		topGrid.add(new Separator(), 0, 4, 5, 1);
		topGrid.add(new Separator(), 0, 7, 5, 1);
		topGrid.add(new Separator(), 6, 4, 5, 1);
		topGrid.add(new Separator(), 6, 7, 5, 1);
		topGrid.add(new Separator(), 12, 7, 2, 1);
		topGrid.add(sepVert1, 2, 1);
		topGrid.add(sepVert2, 8, 1);
		// root.setTop(menuBar);
		root.setTop(topGrid);
		// root.setBottom(bottomGrid);
		// root.setCenter(bottomCanvas);
		root.setCenter(bottomGrid);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
	}

	/*
	 * public boolean checkParamter(){ if (fishField.getText() && ()); return
	 * false;
	 * 
	 * 
	 * }
	 */
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	private boolean checkTextFieldInput() {
		if (isNumeric(iterationTextField.getText()) && isNumeric(threadTextField.getText())
				&& isNumeric(fishField.getText()) && isNumeric(fieldLengthTextField.getText())
				&& isNumeric(fieldWidthTextField.getText()) && isNumeric(deathAngelTextField.getText())
				&& isNumeric(neighbourFishTextField.getText()) && isNumeric(fishLengthTextField.getText())
				&& isNumeric(r1TextField.getText()) && isNumeric(r2TextField.getText())
				&& isNumeric(r3TextField.getText()))

			return true;
		return false;
	}

	public void showAllMeasures(WatchAreaType type) {
	//speedupTextField.setText(StopWatch(type).getNanoTime() / 1000000);
	//(double) (type).WatchAreaType(kappa).getNanoseconds / 1000000;
	//((double) getWatchAreaType(type).WatchAreaType(phi).getNanoseconds / 1000000);
	// Eventuell Laufzeit
	//((double) getWatchAreaType(type).WatchAreaType(SIGMA).getNanoseconds / 1000000);
	}

	/*
	 * // Größe ändern nachdem Start gedrückt wird private void
	 * redrawFish(double x1, double y1, double x2, double y2) {
	 * gc.setStroke(Color.ALICEBLUE); gc.fillRect(0, 0, bottomCanvas.getWidth(),
	 * bottomCanvas.getHeight()); this.gc.strokeLine(x1, y1, x2, y2);
	 * this.gc.strokeOval(x1, y1, 2, 2); }
	 */
	public void createFieldWindow(int fieldLength, int fieldHeight, int fishNumber, int threads, int iterations,
			int neighbours, int deathAngle, double r1, double r2, double r3, int bodyLength) {
		if (this.gc != null && this.fieldWindow != null) {
			this.gc.clearRect(0, 0, this.fieldWindow.getField().getLength(), this.fieldWindow.getField().getHeight());
		}
		LOG.debug("Start to create a new SimulationApp");
		this.fieldWindow = new SimulationApp(fieldLength, fieldHeight, fishNumber, threads, iterations, neighbours,
				deathAngle, r1, r2, r3, bodyLength);

		this.fishCanvas = new Canvas(fieldLength, fieldHeight);
		this.drawWorker = new DrawControler(this.fishCanvas, this.fieldWindow, this.gc);
		this.drawTask = new FutureTask<>(this.drawWorker);
		LOG.debug("Start to create Fishes at start position");
//		drawAllFishes();
//		for (int i = 0; i < iterations; i++) {
//			this.fieldWindow.startIteration();
//			drawAllFishes();
//		}
		createSimulationAndSimulate(this.fieldWindow.getIterations());
	}

	public void iterateOnce() {
		createSimulationAndSimulate(1);
	}

	public void iterateTenTimes() {
		createSimulationAndSimulate(10);
	}

	public void iterateTwentyFiveTimes() {
		createSimulationAndSimulate(25);

	}

	private void createSimulationAndSimulate(int iterations) {
//		LOG.debug("Starting initialize worker");
//        ArrayList<DrawControler> tasks = new ArrayList<DrawControler>();
//        tasks.add(this.drawWorker);
//        Platform.runLater(drawTask);
////        try {
////			ThreadPoolSingleton.getExecutorService().invokeAll(tasks);
////			Thread.sleep(3000);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//        
//        try {
//			drawTask.get();
//		} catch (InterruptedException | ExecutionException e) {
//			// TODO Auto-generated catch block
//			LOG.error(e);
//		}
		
		GuiWorker worker = new GuiWorker(fishCanvas, fieldWindow, gc, fieldWindow, iterations);
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
}