package otherTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import webservice.CSRQueries;
import webservice.NMEQueries;
import webservice.SEQueries;

public class QueryDateParsing {
	private static String inputDateFull = "2013-01-01T01:00:00";
	private static String inputDateShort = "2013-01-01T01:00";

	@Test
	public void testSEQuery() {
		assertEquals("Tue Jan 01 01:00:00 GMT 2013", SEQueries.trimDate(inputDateFull).toString());
		assertEquals("Tue Jan 01 01:00:00 GMT 2013", SEQueries.trimDate(inputDateShort).toString());
	}
	
	@Test
	public void testNMEQuery() {
		assertEquals("Tue Jan 01 01:00:00 GMT 2013", NMEQueries.trimDate(inputDateFull).toString());
		assertEquals("Tue Jan 01 01:00:00 GMT 2013", NMEQueries.trimDate(inputDateShort).toString());
	}
	
	@Test
	public void testCSRQuery() {
		assertEquals("Tue Jan 01 01:00:00 GMT 2013", CSRQueries.trimDate(inputDateFull).toString());
		assertEquals("Tue Jan 01 01:00:00 GMT 2013", CSRQueries.trimDate(inputDateShort).toString());
	}
}
