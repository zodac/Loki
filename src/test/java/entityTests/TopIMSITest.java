package entityTests;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.BeforeClass;
import org.junit.Test;

import queryEntities.TopIMSIByFailure;

public class TopIMSITest {
	private static BigDecimal TOP_IMSI_TEST_IMSI = BigDecimal.valueOf(-1);
	private static BigInteger TOP_IMSI_TEST_NUM_FAILURES = BigInteger.valueOf(-2);

	private static TopIMSIByFailure topIMSI;
	
	@BeforeClass
	public static void setUp(){
		topIMSI = new TopIMSIByFailure();
	}

	@Test
	public void testSetUEType() {
		topIMSI.setIMSI(TOP_IMSI_TEST_IMSI);
		topIMSI.setNumofFailures(TOP_IMSI_TEST_NUM_FAILURES);
		assertEquals(TOP_IMSI_TEST_IMSI, topIMSI.getIMSI());
		assertEquals(TOP_IMSI_TEST_NUM_FAILURES, topIMSI.getNumofFailures());
	}
}
