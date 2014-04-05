package entityTests;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.hibernate.jpa.criteria.ValueHandlerFactory.BigIntegerValueHandler;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import queryEntities.EventIdCauseCodeCombo;
import queryEntities.MOCByFailureClass;

public class MOCByFailureClassTest {

	private static int cellId = 4;
	private static String country = "Guadeloupe-France";
	private static int failureClass = 0;
	private static BigInteger occurences = BigInteger.valueOf(56);
	private static String operator = "Saint Martin et Saint Barthelemy Telcell Sarl GP";
	private static MOCByFailureClass mocFailure;

	
	@Before
	public void setUp() throws Exception {
		mocFailure = new MOCByFailureClass();
	}

	@Test
	public void test() {
		mocFailure.setCellId(cellId);
		mocFailure.setCountry(country);
		mocFailure.setFailureClass(failureClass);
		mocFailure.setOccurences(occurences);
		mocFailure.setOperator(operator);
		assertEquals(cellId, mocFailure.getCellId());
		assertEquals(country,mocFailure.getCountry());
		assertEquals(failureClass,mocFailure.getFailureClass());
		assertEquals(occurences,mocFailure.getOccurences());
		assertEquals(operator, mocFailure.getOperator());
		
		
	}

}
