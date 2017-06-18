package de.pvr.fish.simulation.view;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.application.SimulationApp;
import de.pvr.fish.simulation.model.Fish;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.application.Platform;

public class DrawStep implements Callable<Boolean> {

	private static final Logger LOG = LogManager.getLogger(DrawStep.class);

	private Canvas fishCanvas;
	private SimulationApp app;
	private GraphicsContext gc;

	public DrawStep(Canvas fishCanvas, SimulationApp fieldWindow, GraphicsContext gc) {
		super();
		this.fishCanvas = fishCanvas;
		this.app = fieldWindow;
		this.gc = gc;
	}

	@Override
	public Boolean call() throws Exception {
		LOG.debug("Start to draw Fishes");
		if (Platform.isFxApplicationThread()) {
			this.gc.clearRect(0, 0, this.app.getAquarium().getLength(), this.app.getAquarium().getHeight());
			for (Fish fish : this.app.getAquarium().getFishes()) {
				drawFish(fish.getPosition().getCoordinateX(), fish.getPosition().getCoordinateY(),
						fish.getLengthPosition().getCoordinateX(), fish.getLengthPosition().getCoordinateY());
			}
		}
		return true;
	}

	private void drawFish(double x1, double y1, double x2, double y2) {
		this.gc.strokeLine(x1, y1, x2, y2);
		this.gc.strokeOval(x1 - 1, y1 - 1, 3, 3);

	}

}
