package de.pvr.fish.simulation.algorithm;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

import de.pvr.fish.simulation.model.Field;
import de.pvr.fish.simulaton.testdata.TestdataGenerator;

public class TestSimulation {

	
	@Test
	public void testSimulation10RandomFishes() {
		Field field = TestdataGenerator.getFieldWith10Fishes();
		
		field.nextInteration();
		field.nextInteration();
		field.nextInteration();
		field.nextInteration();
	}
	
	@Test
	public void testSimulation10SpecificFishes() {
		Field field = TestdataGenerator.getFieldWithSpecific10Fishes();
		
		field.nextInteration();
		field.nextInteration();
		field.nextInteration();
		field.nextInteration();
	}

}
