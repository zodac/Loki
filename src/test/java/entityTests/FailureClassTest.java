package entityTests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import entities.FailureClass;

public class FailureClassTest {
	private static int FAILURE_CLASS_TEST_ID = -1;
	private static String FAILURE_CLASS_TEST_DESC = "TEST FAILURE CLASS";
	private static FailureClass failureClass;
	
	@BeforeClass
	public static void setUp(){
		failureClass = new FailureClass();
	}

	@Test
	public void testSetFailureClass() {
		failureClass.setFailureClass(FAILURE_CLASS_TEST_ID);
		failureClass.setDescription(FAILURE_CLASS_TEST_DESC);
		assertEquals(FAILURE_CLASS_TEST_ID, failureClass.getFailureClass());
		assertEquals(FAILURE_CLASS_TEST_DESC, failureClass.getDescription());
	}
}
