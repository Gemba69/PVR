package de.pvr.fish.simulation.util;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.config.Configuration;

public class ResultLogging {

	private static final Logger LOG = LogManager.getLogger(ResultLogging.class);
	ArrayList<Configuration> configs;

	private static final String DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	public ResultLogging() {
		configs = new ArrayList<Configuration>();
	}

	public void writeToCSV(String fileName) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);

			// TODO Auto-generated catch block

			setHeaders(fileWriter);

			for (Configuration conf : configs) {
				setConf(fileWriter, conf);
			}

		} catch (FileNotFoundException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				LOG.error("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();

			}
		}
	}

	public void addConfig(Configuration config) {
		configs.add(config);
	}

	private void setHeaders(FileWriter sb) throws IOException {
		sb.append("Iterations");
		sb.append(DELIMITER);
	}
	
	private void setConf(FileWriter sb, Configuration conf) throws IOException {
		sb.append("Iterations");
		sb.append(DELIMITER);
	}
}
