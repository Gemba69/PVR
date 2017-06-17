package de.pvr.fish.simulation.view;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.application.SimulationApp;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GuiWorker extends Task<Void> {

	private static final Logger LOG = LogManager.getLogger(GuiWorker.class);

	private Canvas fishCanvas;
	private SimulationApp fieldWindow;
	private GraphicsContext gc;
	private SimulationApp app;
	private int iterations;

	public GuiWorker(Canvas fishCanvas, SimulationApp fieldWindow, GraphicsContext gc,
			SimulationApp app, int iterations) {
		this.fishCanvas = fishCanvas;
		this.fieldWindow = fieldWindow;
		this.gc = gc;
		this.app = app;
		this.iterations = iterations;
	}

	@Override
	protected Void call() throws Exception {
		DrawControler drawWorker = new DrawControler(fishCanvas, fieldWindow, gc);
		for (int i = 0; i < this.iterations; i++) {
			this.app.startIteration();
			FutureTask<Boolean> drawTask = new FutureTask<>(drawWorker);
			LOG.debug("Starting initialize worker");
			ArrayList<DrawControler> tasks = new ArrayList<DrawControler>();
			tasks.add(drawWorker);
			Platform.runLater(drawTask);
			// try {
			// ThreadPoolSingleton.getExecutorService().invokeAll(tasks);
			// Thread.sleep(3000);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			try {
				drawTask.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				LOG.error(e);
			}
		}
		return null;
	}

}
