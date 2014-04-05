package entityTests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import entities.MccMnc;
import entities.MccMncPK;

public class MccMncTest {
	private static int MCC_MNC_MCC_TEST = -1;
	private static int MCC_MNC_MNC_TEST = -2;
	private static String MCC_MNC_COUNTRY_TEST = "Test MCC Country";
	private static String MCC_MNC_OPERATOR_TEST = "Test MCC Operator";
	private static MccMnc mccMnc;
	
	@BeforeClass
	public static void setUp(){
		mccMnc = new MccMnc(); 
	}

	@Test
	public void testSetMccMnc() {
		MccMncPK mpk = new MccMncPK();
		mpk.setMcc(MCC_MNC_MCC_TEST);
		mpk.setMnc(MCC_MNC_MNC_TEST);
		mccMnc.setId(mpk);
		mccMnc.setCountry(MCC_MNC_COUNTRY_TEST);
		mccMnc.setOperator(MCC_MNC_OPERATOR_TEST);
		assertEquals(MCC_MNC_MCC_TEST, mccMnc.getId().getMcc());
		assertEquals(MCC_MNC_MNC_TEST, mccMnc.getId().getMnc());
		assertEquals(MCC_MNC_COUNTRY_TEST, mccMnc.getCountry());
		assertEquals(MCC_MNC_OPERATOR_TEST, mccMnc.getOperator());
	}
}
