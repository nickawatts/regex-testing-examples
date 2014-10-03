package com.thewonggei;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author Nick Watts
 * 
 */
public class PlainJavaExample {

	@Test
	public void testBasicMatching() {
		// Using inefficient convenience methods of the Pattern and String
		// classes.
		assertTrue(Pattern.matches(".*[pP]attern.*",
				"Fringe division is all over the pattern."));
		assertTrue("Fringe division is all over the pattern."
				.matches(".*[pP]attern.*"));

		// Using a compiled (efficient for re-use) pattern with matches
		Pattern pattern = Pattern.compile(".*[pP]attern.*");
		assertTrue(pattern.matcher("Fringe division is all over the pattern.")
				.matches());

		// Using a compiled (efficient for re-use) pattern with find
		pattern = Pattern.compile("[pP]attern");
		assertTrue(pattern.matcher("Fringe division is all over the pattern.")
				.find());
	}

	@Test
	public void testForSpecificMatches() {
		Pattern pattern = Pattern.compile("\\w+\\s+[pP]attern");
		Matcher matcher = pattern
				.matcher("Z.F.T. is responsible for the strange pattern of "
						+ "events. Fringe division is discovering 'The Pattern'.");
		
		for( int i=1; matcher.find(); i++ ) {
			if( i == 1 ) {
				assertEquals("strange pattern", matcher.group());
			}
			else if( i == 2 ) {
				assertEquals("The Pattern", matcher.group());
			}
		}
	}

	@Test
	public void testForSpecificGroupMatches() {
		Pattern pattern = Pattern.compile("(\\w+)\\s+[pP]attern");
		Matcher matcher = pattern
				.matcher("Z.F.T. is responsible for the strange pattern of "
						+ "events. Fringe division is discovering 'The Pattern'.");
		
		for( int i=1; matcher.find(); i++ ) {
			if( i == 1 ) {
				assertEquals("strange", matcher.group(1));
			}
			else if( i == 2 ) {
				assertEquals("The", matcher.group(1));
			}
		}
	}

}
