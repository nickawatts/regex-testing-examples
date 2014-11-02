package com.thewonggei;

import static com.thewonggei.regexTester.hamcrest.RegexMatches.*;
import static com.thewonggei.regexTester.hamcrest.MatchesAt.*;
import static com.thewonggei.regexTester.hamcrest.CapturedGroupMatchesAt.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * This test suite demonstrates how to test multiple regexes against multiple
 * input strings using Hamcrest Matchers to drastically reduce the amount of
 * boiler-plate code used. The Hamcrest Matchers used in this suite come from
 * the regex-tester API (https://github.com/nickawatts/regex-tester). You must
 * have at least version 0.2 of regex-tester to get the Hamcrest Matchers.
 * 
 * @author Nick Watts
 */
public class HamcrestMatchersExample {

	@Test
	public void testBasicMatching() {
		String input = "Fringe division is all over the pattern."; 
		assertThat(input, doesMatchRegex(".*[pP]attern.*"));
		assertThat(input, doesNotMatchRegex("[pP]attern"));
	}

	@Test
	public void testForSpecificMatches() {
		Pattern pattern = Pattern.compile("\\w+\\s+[pP]attern");
		Matcher matcher = pattern
				.matcher("Z.F.T. is responsible for the strange pattern of "
						+ "events. Fringe division is discovering 'The Pattern'.");

		//Test arbitrary matches in the set of matched substrings.
		assertThat("strange pattern", matchesAt(1, matcher));
		assertThat("The Pattern", matchesAt(2, matcher));
	}
	
	@Test
	public void testForSpecificGroupMatches() {
		Pattern pattern = Pattern.compile("(\\w+)\\s+[pP]attern");
		Matcher matcher = pattern
				.matcher("Z.F.T. is responsible for the strange pattern of "
						+ "events. Fringe division is discovering 'The Pattern'.");

		//Test arbitrary captured text strings in the set of matched substrings.
		assertThat("strange", matchesGroupAt(1, matcher));
		assertThat("The", matchesGroupAt(2, matcher));
	}
}
