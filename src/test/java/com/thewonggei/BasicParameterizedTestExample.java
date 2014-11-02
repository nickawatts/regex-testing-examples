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
 * less boiler-plate code than is used in {@link PlainJavaExample}.  This suite 
 * uses the JUnit construct of a parameterized test to automatically create a 
 * test case for each input string provided in the {@link #getTestParameters()} 
 * method.
 * 
 * Notice that this test is only capable of either positive or negative testing
 * but not both. See {@link AdvancedParameterizedTestExample} for a way to get
 * around this limitation.
 * 
 * @author Nick Watts
 * @see Parameterized
 * @see AdvancedParameterizedTestExample
 */
@RunWith(value = Parameterized.class)
public class BasicParameterizedTestExample {
	/**
	 * A compiled regex is used for efficiency since this pattern is used in 
	 * each test case generated at runtime by the parameterized test construct.
	 */
	private Pattern pattern = Pattern
			.compile("\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b");
	/**
	 * This field gets input strings injected into it at runtime.
	 */
	private String input;

	/**
	 * This method signature is prescribed by the JUnit parameterized test
	 * construct. You can supply as many input strings as you wish. 
	 * 
	 * HINT: If you want to test many input strings you could pull them in from
	 * a text file in this method.
	 * 
	 * @return A collection of input strings to inject into the input field
	 * each time a test case is generated.
	 */
	@Parameters
	public static Collection<String[]> getTestParameters() {
		return Arrays.asList(new String[][] { { "0.0.0.0" }, { "127.0.0.1" },
				{ "255.255.255.255" } });
	}

	/**
	 * In the JUnit parameterized test construct, the arguments to the constructor
	 * define the fields that receive injected values (supplied by the 
	 * {@link #getTestParameters()} method) to form individual test cases at
	 * runtime.
	 */
	public BasicParameterizedTestExample(String input) {
		this.input = input;
	}

	@Test
	public void test() {
		assertTrue(pattern.matcher(input).matches());
	}

}
