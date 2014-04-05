package entityTests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import entities.User;

public class UserTest {
	private static String USER_TEST_EMAIL_ADDRESS = "Test Email";
	private static String USER_TEST_FIRST_NAME = "Test First Name";
	private static String USER_TEST_LAST_NAME = "Test Last Name";
	private static String USER_TEST_PHONE_NUMBER = "Test Phone Number";
	private static String USER_TEST_USER_NAME = "Test User Name";
	private static String USER_TEST_USER_PASSWORD = "Test User Password";
	private static String USER_TEST_USER_TYPE = "Test User Type";
	private static User user;
	
	@BeforeClass
	public static void setUp(){
		user = new User();
	}

	@Test
	public void testSetUEType() {
		user.setEmailAddress(USER_TEST_EMAIL_ADDRESS);
		user.setFirstName(USER_TEST_FIRST_NAME);
		user.setLastName(USER_TEST_LAST_NAME);
		user.setPhoneNumber(USER_TEST_PHONE_NUMBER);
		user.setUserName(USER_TEST_USER_NAME);
		user.setUserPassword(USER_TEST_USER_PASSWORD);
		user.setUsertype(USER_TEST_USER_TYPE);
		assertEquals(USER_TEST_EMAIL_ADDRESS, user.getEmailAddress());
		assertEquals(USER_TEST_FIRST_NAME, user.getFirstName());
		assertEquals(USER_TEST_LAST_NAME, user.getLastName());
		assertEquals(USER_TEST_PHONE_NUMBER, user.getPhoneNumber());
		assertEquals(USER_TEST_USER_NAME, user.getUserName());
		assertEquals(USER_TEST_USER_PASSWORD, user.getUserPassword());
		assertEquals(USER_TEST_USER_TYPE, user.getUsertype());
	}
}
