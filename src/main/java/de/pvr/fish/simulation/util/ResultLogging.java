package de.pvr.fish.simulation.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.pvr.fish.simulation.config.Configuration;

public class ResultLogging {

	private static final Logger LOG = LogManager.getLogger(ResultLogging.class);

	ArrayList<Configuration> configs;

	private static final String DELIMITER = ";";
	private static final String NEW_LINE_SEPARATOR = "\n";

	private static final int COL_ITERATION = 0;
	private static final int COL_THREADS = 1;
	private static final int COL_FISHNUMBER = 2;
	private static final int COL_FIELDLENGTH = 3;
	private static final int COL_FIELDHEIGHT = 4;
	private static final int COL_NEIGHBOURS = 5;
	private static final int COL_DEATHANGLE = 6;
	private static final int COL_R1 = 7;
	private static final int COL_R2 = 8;
	private static final int COL_R3 = 9;
	private static final int COL_BODYLENGTH = 10;

	public ResultLogging() {
		configs = new ArrayList<Configuration>();
	}

	public void writeToCSV(String fileName) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);

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

	public void readFromCSV(String fileName) {

		BufferedReader fileReader = null;
		String line = "";
		try {
			fileReader = new BufferedReader(new FileReader(fileName));
			fileReader.readLine();

			while ((line = fileReader.readLine()) != null) {
				String[] tokens = line.split(DELIMITER);
				if (tokens.length > 0) {
					Configuration conf = new Configuration(Integer.parseInt(tokens[COL_ITERATION]),
							Integer.parseInt(tokens[COL_THREADS]), Integer.parseInt(tokens[COL_FISHNUMBER]),
							Integer.parseInt(tokens[COL_FIELDLENGTH]), Integer.parseInt(tokens[COL_FIELDHEIGHT]),
							Integer.parseInt(tokens[COL_NEIGHBOURS]), Integer.parseInt(tokens[COL_DEATHANGLE]),
							Double.parseDouble(tokens[COL_R1]), Double.parseDouble(tokens[COL_R2]),
							Double.parseDouble(tokens[COL_R3]), Integer.parseInt(tokens[COL_BODYLENGTH]));
					if (!this.configs.contains(conf)) {
						this.configs.add(conf);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				LOG.error("Error while closing fileReader !!!");
				e.printStackTrace();
			}
		}

	}

	public void addConfig(Configuration config) {
		if (!configs.contains(config)) {
			configs.add(config);
		}
	}
	
	public void removeIfContains(Configuration config) {
		if (configs.contains(config)) {
			configs.remove(config);
		}
	}
	
	public ArrayList<Configuration> getConfigs() {
		return this.configs;
	}

	private void setHeaders(FileWriter sb) throws IOException {
		sb.append("Iterations");
		sb.append(DELIMITER);
		sb.append("Threads");
		sb.append(DELIMITER);
		sb.append("FishNumber");
		sb.append(DELIMITER);
		sb.append("FieldLength");
		sb.append(DELIMITER);
		sb.append("FieldHeight");
		sb.append(DELIMITER);
		sb.append("NumberOfNeighbours");
		sb.append(DELIMITER);
		sb.append("DeathAngle");
		sb.append(DELIMITER);
		sb.append("R1");
		sb.append(DELIMITER);
		sb.append("R2");
		sb.append(DELIMITER);
		sb.append("R3");
		sb.append(DELIMITER);
		sb.append("BodyLength");
		sb.append(DELIMITER);

		sb.append("Runtime");
		sb.append(DELIMITER);
		sb.append("Sigma");
		sb.append(DELIMITER);
		sb.append("Kappa");
		sb.append(DELIMITER);
		sb.append("Phi");
		sb.append(DELIMITER);

		sb.append(NEW_LINE_SEPARATOR);
	}

	private void setConf(FileWriter sb, Configuration conf) throws IOException {
		sb.append(Integer.toString(conf.getIteration()));
		sb.append(DELIMITER);
		sb.append(Integer.toString(conf.getThreads()));
		sb.append(DELIMITER);
		sb.append(Integer.toString(conf.getFishNumber()));
		sb.append(DELIMITER);
		sb.append(Integer.toString(conf.getFieldLength()));
		sb.append(DELIMITER);
		sb.append(Integer.toString(conf.getFieldHeight()));
		sb.append(DELIMITER);
		sb.append(Integer.toString(conf.getNumberOfNeighbours()));
		sb.append(DELIMITER);
		sb.append(Integer.toString(conf.getDeathAngle()));
		sb.append(DELIMITER);
		sb.append(String.valueOf(conf.getR1()));
		sb.append(DELIMITER);
		sb.append(String.valueOf(conf.getR2()));
		sb.append(DELIMITER);
		sb.append(String.valueOf(conf.getR3()));
		sb.append(DELIMITER);
		sb.append(Integer.toString(conf.getBodyLength()));
		sb.append(DELIMITER);
		sb.append(String.valueOf(conf.getRuntime()));
		sb.append(DELIMITER);
		sb.append(String.valueOf(conf.getSigma()));
		sb.append(DELIMITER);
		sb.append(String.valueOf(conf.getKappa()));
		sb.append(DELIMITER);
		sb.append(String.valueOf(conf.getPhi()));
		sb.append(DELIMITER);

		sb.append(NEW_LINE_SEPARATOR);
	}
}
