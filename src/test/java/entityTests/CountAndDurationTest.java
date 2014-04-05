package entityTests;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.BeforeClass;
import org.junit.Test;

import queryEntities.CountAndDuarationOfIMSI;

public class CountAndDurationTest {
	private static BigInteger COUNT_DURATION_IMSI_TEST_IMSI = BigInteger.valueOf(-1);
	private static long COUNT_DURATION_IMSI_TEST_COUNT = -2;
	private static long COUNT_DURATION_IMSI_TEST_NUM_FAILURE = -3;

	private static CountAndDuarationOfIMSI cdIMSI;
	
	@BeforeClass
	public static void setUp(){
		cdIMSI = new CountAndDuarationOfIMSI();
	}

	@Test
	public void testSetUEType() {
		cdIMSI.setIMSI(COUNT_DURATION_IMSI_TEST_IMSI);
		cdIMSI.setCount(COUNT_DURATION_IMSI_TEST_COUNT);
		cdIMSI.setNumofFailures(COUNT_DURATION_IMSI_TEST_NUM_FAILURE);
		assertEquals(COUNT_DURATION_IMSI_TEST_IMSI, cdIMSI.getIMSI());
		assertEquals(Long.valueOf(COUNT_DURATION_IMSI_TEST_COUNT), Long.valueOf(cdIMSI.getCount()));
		assertEquals(Long.valueOf(COUNT_DURATION_IMSI_TEST_NUM_FAILURE),Long.valueOf(cdIMSI.getNumofFailures()));
	}
}
