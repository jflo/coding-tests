package com.shapeways.recruiting;

import static org.junit.Assert.*;

import org.junit.Test;

public class PairEqualityTest {

	@Test
	public void testHappyPath() {
		UnorderedPair<String, String> pair1 = new UnorderedPair<String, String>("Bonnie", "Clyde");
		UnorderedPair<String, String> pair2 = new UnorderedPair<String, String>("Bonnie", "Clyde");
		
		assertTrue(pair1.equals(pair2));
	}
	
	@Test
	public void testOrdering() {
		UnorderedPair<String, String> vice1 = new UnorderedPair<String, String>("Crockett","Tubbs");
		UnorderedPair<String, String> vice2 = new UnorderedPair<String, String>("Tubbs","Crockett");
		
		assertTrue(vice1.equals(vice2));
		assertTrue(vice2.equals(vice1));
	}
	
	@Test
	public void testHashCode() {
		UnorderedPair<String, String> folk1 = new UnorderedPair<String, String>("Garfunkle", "Simon");
		UnorderedPair<String, String> folk2 = new UnorderedPair<String, String>("Simon", "Garfunkle");
		
		assertTrue(folk1.hashCode() == folk2.hashCode());
	}

}
