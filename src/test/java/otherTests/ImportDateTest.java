package otherTests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import services.ImportEJB;

public class ImportDateTest {
	
	@Test
	public void testDateValidity() throws ParseException {
		Date VALID_DATE = new SimpleDateFormat("yyyy/MM/dd hh:mm").parse("2013/01/01 00:00");
		Date INVALID_DATE_YEAR = new SimpleDateFormat("yyyy/MM/dd hh:mm").parse("1000/01/01 00:00");
		
		assertEquals(true, ImportEJB.isDateValid(VALID_DATE));
		assertEquals(false, ImportEJB.isDateValid(INVALID_DATE_YEAR));
	}
	
	@Test
	public void testLeapYear(){
		assertEquals(true, ImportEJB.isLeapYear(2000));
		assertEquals(false, ImportEJB.isLeapYear(2001));
	}
}
