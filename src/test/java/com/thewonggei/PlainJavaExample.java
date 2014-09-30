package com.thewonggei;

import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author Nick Watts
 *
 */
public class PlainJavaExample {

	@Test
	public void testBasicMatching() {
		//Using inefficient convenience methods of the Pattern and String classes.
		assertTrue(Pattern.matches(".*[pP]attern.*", "Fringe division is all over the pattern."));
		assertTrue("Fringe division is all over the pattern.".matches(".*[pP]attern.*"));

		//Using a compiled (efficient for re-use) pattern with matches
		Pattern pattern = Pattern.compile(".*[pP]attern.*");
		assertTrue(pattern.matcher("Fringe division is all over the pattern.").matches());
		
		//Using a compiled (efficient for re-use) pattern with find
		pattern = Pattern.compile("[pP]attern");
		assertTrue(pattern.matcher("Fringe division is all over the pattern.").find());		
	}
	
	@Test
	public void testForSpecificMatches() {
		
	}
	
	@Test
	public void testForSpecificGroupMatches() {
		
	}
		

}
