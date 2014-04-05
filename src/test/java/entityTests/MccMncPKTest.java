package entityTests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import entities.MccMncPK;

public class MccMncPKTest {
	private static int MCC_MNC_MCC_TEST = -1;
	private static int MCC_MNC_MNC_TEST = -2;
	private static MccMncPK mpk;
	
	@BeforeClass
	public static void setUp(){
		mpk = new MccMncPK();
	}

	@Test
	public void testSetEventCausePK() {
		mpk.setMcc(MCC_MNC_MCC_TEST);
		mpk.setMnc(MCC_MNC_MNC_TEST);
		assertEquals(MCC_MNC_MCC_TEST, mpk.getMcc());
		assertEquals(MCC_MNC_MNC_TEST, mpk.getMnc());
	}
	
	@Test
	public void testEquals(){
		assertEquals(true, mpk.equals(mpk));
	}
	
	@Test
	public void testHashCode(){
		assertEquals(false, mpk.hashCode()==new MccMncPK().hashCode());
	}
}
