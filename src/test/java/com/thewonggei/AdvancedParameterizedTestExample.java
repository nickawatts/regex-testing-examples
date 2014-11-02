package com.thewonggei;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This test suite demonstrates how to repeatedly test input strings against a 
 * single regular expression using only the standard Java API and JUnit and with 
 * less boiler-plate code than is used in {@link PlainJavaExample} but with more
 * flexibility in {@link BasicParameterizedTestExample}.  This suite uses
 * the JUnit construct of a parameterized test to automatically create a test
 * case for each input string provided in the {@link #getTestParameters()} 
 * method.
 * 
 * Unlike {@link BasicParameterizedTestExample}, this implementation can do
 * both positive and negative assertions. The inner class 
 * {@link RegexInputStringInfo} is used to carry the state necessary to 
 * implement this flexibility.
 * 
 * @author Nick Watts
 * @see Parameterized
 * @see BasicParameterizedTestExample
 */
@RunWith(value=Parameterized.class)
public class AdvancedParameterizedTestExample {
	/**
	 * This field gets input strings injected into it at runtime.
	 */
	private RegexInputStringInfo rpojo;
	/**
	 * A compiled regex is used for efficiency since this pattern is used in 
	 * each test case generated at runtime by the parameterized test construct.
	 */
	private Pattern pattern = Pattern
			.compile("\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b");

	/**
	 * This method signature is prescribed by the JUnit parameterized test
	 * construct. You can supply as many input strings as you wish. 
	 * 
	 * HINT: If you want to test many input strings you could pull them in from
	 * a text file in this method.
	 * 
	 * @return A collection of input strings to inject into the rpojo field
	 * each time a test case is generated.
	 */
	@Parameters
	public static Collection<RegexInputStringInfo[]> getTestParameters() {
		return Arrays.asList(new RegexInputStringInfo[][] {
				{new RegexInputStringInfo(true, "0.0.0.0")},
				{new RegexInputStringInfo(true, "127.0.0.1")},
				{new RegexInputStringInfo(true, "255.255.255.255")},
				{new RegexInputStringInfo(false, "256.256.256.256")},
				{new RegexInputStringInfo(false, "A.B.C.D")}
		});
	}
	
	/**
	 * In the JUnit parameterized test construct, the arguments to the constructor
	 * define the fields that receive injected values (supplied by the 
	 * {@link #getTestParameters()} method) to form individual test cases at
	 * runtime.
	 */
	public AdvancedParameterizedTestExample(RegexInputStringInfo rpojo) {
		this.rpojo = rpojo;
	}
	
	@Test
	public void test() {
		assertEquals(rpojo.shouldItMatch, pattern.matcher(rpojo.testString).matches());
	}

}

/**
 * This class simply holds state that is used to allow for both positive and
 * negative testing of whether or not a regex matches against a given input
 * string.
 * 
 * @author Nick Watts
 */
class RegexInputStringInfo {
	/**
	 * True if the test string should result in a match against some
	 * regular expression, false otherwise.
	 */
	public boolean shouldItMatch;
	/**
	 * The string against which to test a regular expression for a match.
	 */
	public String testString;
	
	public RegexInputStringInfo(boolean shouldItMatch, String testString) {
		this.shouldItMatch = shouldItMatch;
		this.testString = testString;
	}
}