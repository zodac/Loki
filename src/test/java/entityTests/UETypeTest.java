package entityTests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import entities.UEType;

public class UETypeTest {
	private static int UETYPE_TEST_TAC = -1;
	private static String UETYPE_TEST_ACCESS_CAPABILITY = "Test Access Capability";
	private static String UETYPE_TEST_INPUT_MODE = "Test Input Mode";
	private static String UETYPE_TEST_MANUFACTURER = "Test Manufactorer";
	private static String UETYPE_TEST_MARKETING_NAME = "Test Marketing Name";
	private static String UETYPE_TEST_MODEL = "Test Model";
	private static String UETYPE_TEST_OS = "Test OS";
	private static String UETYPE_TEST_UETYPE= "Test UEtype";
	private static String UETYPE_TEST_VENDOR_NAME= "Test Vendor Name";
	private static UEType ueType;
	
	@BeforeClass
	public static void setUp(){
		ueType = new UEType();
	}

	@Test
	public void testSetUEType() {
		ueType.setTac(UETYPE_TEST_TAC);
		ueType.setAccessCapability(UETYPE_TEST_ACCESS_CAPABILITY);
		ueType.setInputMode(UETYPE_TEST_INPUT_MODE);
		ueType.setManufacturer(UETYPE_TEST_MANUFACTURER);
		ueType.setMarketingName(UETYPE_TEST_MARKETING_NAME);
		ueType.setModel(UETYPE_TEST_MODEL);
		ueType.setOs(UETYPE_TEST_OS);
		ueType.setUEType(UETYPE_TEST_UETYPE);
		ueType.setVendorName(UETYPE_TEST_VENDOR_NAME);
		assertEquals(UETYPE_TEST_TAC, ueType.getTac());
		assertEquals(UETYPE_TEST_ACCESS_CAPABILITY, ueType.getAccessCapability());
		assertEquals(UETYPE_TEST_INPUT_MODE, ueType.getInputMode());
		assertEquals(UETYPE_TEST_MANUFACTURER, ueType.getManufacturer());
		assertEquals(UETYPE_TEST_MARKETING_NAME, ueType.getMarketingName());
		assertEquals(UETYPE_TEST_MODEL, ueType.getModel());
		assertEquals(UETYPE_TEST_OS, ueType.getOs());
		assertEquals(UETYPE_TEST_UETYPE, ueType.getUEType());
		assertEquals(UETYPE_TEST_VENDOR_NAME, ueType.getVendorName());
	}
}
