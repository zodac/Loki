package entityTests;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import entities.InvalidCallFailure;

public class InvalidCallFailureTest {
	private static String INVALID_CALL_FAILURE_TEST_CAUSE_CODE = "Test Cause Code";
	private static int INVALID_CALL_FAILURE_TEST_CELL_ID = -1;
	private static Date INVALID_CALL_FAILURE_TEST_DATE = new Date();
	private static int INVALID_CALL_FAILURE_TEST_DURATION = -2;
	private static int INVALID_CALL_FAILURE_TEST_EVENT_ID = -3;
	private static String INVALID_CALL_FAILURE_TEST_FAILURE_CLASS = "Test Failure Class";
	private static long INVALID_CALL_FAILURE_TEST_HIER321_ID = -4;
	private static long INVALID_CALL_FAILURE_TEST_HIER32_ID = -5;
	private static long INVALID_CALL_FAILURE_TEST_HIER3_ID = -6;
	private static int INVALID_CALL_FAILURE_TEST_ID = -7;
	private static BigInteger INVALID_CALL_FAILURE_TEST_IMSI = BigInteger.valueOf(-8);
	private static int INVALID_CALL_FAILURE_TEST_MARKET = -9;
	private static String INVALID_CALL_FAILURE_TEST_NE_VERSION = "Test NE Version";
	private static int INVALID_CALL_FAILURE_TEST_OPERATOR = -10;
	private static int INVALID_CALL_FAILURE_TEST_UETYPE = -11;

	private static InvalidCallFailure invalidcallFailure;
	
	@BeforeClass
	public static void setUp(){
		invalidcallFailure = new InvalidCallFailure();
	}

	@Test
	public void testSetFailureClass() {
		invalidcallFailure.setCauseCode(INVALID_CALL_FAILURE_TEST_CAUSE_CODE);
		invalidcallFailure.setCellId(INVALID_CALL_FAILURE_TEST_CELL_ID);
		invalidcallFailure.setDate(INVALID_CALL_FAILURE_TEST_DATE);
		invalidcallFailure.setDuration(INVALID_CALL_FAILURE_TEST_DURATION);
		invalidcallFailure.setEventId(INVALID_CALL_FAILURE_TEST_EVENT_ID);
		invalidcallFailure.setFailureClass(INVALID_CALL_FAILURE_TEST_FAILURE_CLASS);
		invalidcallFailure.setHier321Id(INVALID_CALL_FAILURE_TEST_HIER321_ID);
		invalidcallFailure.setHier32Id(INVALID_CALL_FAILURE_TEST_HIER32_ID);
		invalidcallFailure.setHier3Id(INVALID_CALL_FAILURE_TEST_HIER3_ID);
		invalidcallFailure.setId(INVALID_CALL_FAILURE_TEST_ID);
		invalidcallFailure.setImsi(INVALID_CALL_FAILURE_TEST_IMSI);
		invalidcallFailure.setMarket(INVALID_CALL_FAILURE_TEST_MARKET);
		invalidcallFailure.setNEVersion(INVALID_CALL_FAILURE_TEST_NE_VERSION);
		invalidcallFailure.setOperator(INVALID_CALL_FAILURE_TEST_OPERATOR);
		invalidcallFailure.setUEType(INVALID_CALL_FAILURE_TEST_UETYPE);
		assertEquals(INVALID_CALL_FAILURE_TEST_CAUSE_CODE, invalidcallFailure.getCauseCode());
		assertEquals(INVALID_CALL_FAILURE_TEST_CELL_ID, invalidcallFailure.getCellId());
		assertEquals(INVALID_CALL_FAILURE_TEST_DATE, invalidcallFailure.getDate());
		assertEquals(INVALID_CALL_FAILURE_TEST_DURATION, invalidcallFailure.getDuration());
		assertEquals(INVALID_CALL_FAILURE_TEST_EVENT_ID, invalidcallFailure.getEventId());
		assertEquals(INVALID_CALL_FAILURE_TEST_FAILURE_CLASS, invalidcallFailure.getFailureClass());
		assertEquals(INVALID_CALL_FAILURE_TEST_HIER321_ID, invalidcallFailure.getHier321Id());
		assertEquals(INVALID_CALL_FAILURE_TEST_HIER32_ID, invalidcallFailure.getHier32Id());
		assertEquals(INVALID_CALL_FAILURE_TEST_HIER3_ID, invalidcallFailure.getHier3Id());
		assertEquals(INVALID_CALL_FAILURE_TEST_ID, invalidcallFailure.getId());
		assertEquals(INVALID_CALL_FAILURE_TEST_IMSI, invalidcallFailure.getImsi());
		assertEquals(INVALID_CALL_FAILURE_TEST_MARKET, invalidcallFailure.getMarket());
		assertEquals(INVALID_CALL_FAILURE_TEST_NE_VERSION, invalidcallFailure.getNEVersion());
		assertEquals(INVALID_CALL_FAILURE_TEST_OPERATOR, invalidcallFailure.getOperator());
		assertEquals(INVALID_CALL_FAILURE_TEST_UETYPE, invalidcallFailure.getUEType());
	}
}
