package entityTests;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.BeforeClass;
import org.junit.Test;

import queryEntities.TopMOCEntity;

public class TopMOCTest {
	private static int TOP_MOC_TEST_CELL_ID = -1;
	private static String TOP_MOC_TEST_COUNTRY = "Test Country";
	private static BigInteger TOP_MOC_TEST_NUM_FAILURE = BigInteger.valueOf(-1);
	private static BigDecimal TOP_MOC_TEST_OF_ALL_FAILURES = BigDecimal.valueOf(-1);
	private static String TOP_MOC_TEST_OPERATOR = "Test Operator";
	private static TopMOCEntity topMOC;
	
	@BeforeClass
	public static void setUp(){
		topMOC = new TopMOCEntity();
	}

	@Test
	public void testSetUEType() {
		topMOC.setCellId(TOP_MOC_TEST_CELL_ID);
		topMOC.setCountry(TOP_MOC_TEST_COUNTRY);
		topMOC.setNumberOfFailures(TOP_MOC_TEST_NUM_FAILURE);
		topMOC.setOfAllFailures(TOP_MOC_TEST_OF_ALL_FAILURES);
		topMOC.setOperator(TOP_MOC_TEST_OPERATOR);
		assertEquals(TOP_MOC_TEST_CELL_ID, topMOC.getCellId());
		assertEquals(TOP_MOC_TEST_COUNTRY, topMOC.getCountry());
		assertEquals(TOP_MOC_TEST_NUM_FAILURE, topMOC.getNumberOfFailures());
		assertEquals(TOP_MOC_TEST_OF_ALL_FAILURES, topMOC.getOfAllFailures());
		assertEquals(TOP_MOC_TEST_OPERATOR, topMOC.getOperator());
	}
}
