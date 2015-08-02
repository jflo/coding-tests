package com.shapeways.recruiting;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PairEqualityTest {

	@Test
	public void testHappyPath() {
		Pair<String, String> pair1 = new Pair<String, String>("Bonnie", "Clyde");
		Pair<String, String> pair2 = new Pair<String, String>("Bonnie", "Clyde");
		
		assertTrue(pair1.equals(pair2));
	}
	
	@Test
	public void testOrdering() {
		Pair<String, String> vice1 = new Pair<String, String>("Crockett","Tubbs");
		Pair<String, String> vice2 = new Pair<String, String>("Tubbs","Crockett");
		
		assertTrue(vice1.equals(vice2));
		assertTrue(vice2.equals(vice1));
	}
	
	@Test
	public void testHashCode() {
		Pair<String, String> folk1 = new Pair<String, String>("Garfunkle", "Simon");
		Pair<String, String> folk2 = new Pair<String, String>("Simon", "Garfunkle");
		
		assertTrue(folk1.hashCode() == folk2.hashCode());
	}

}
