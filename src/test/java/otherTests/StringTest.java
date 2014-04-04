package otherTests;

import static org.junit.Assert.assertEquals;
import main.Strings;

import org.junit.Test;

@SuppressWarnings("unused")
public class StringTest {

	@Test
	public void testStrings() {
		Strings strings = new Strings();
		assertEquals(Strings.TITLE, "Project LOKI");
		assertEquals(Strings.LOGGED_IN_AS, "Logged in as");
		assertEquals(Strings.LOGOUT, "Logout");
		assertEquals(Strings.COPYRIGHT_AND_NAMES, "&copy; Jason Costello, Tim Coghlan, Danny Flynn, Gavin Hughes, James McManus, Arouge Mehdi");
		assertEquals(Strings.LOGIN, "Login");
		assertEquals(Strings.USERNAME, "Username");
		assertEquals(Strings.USER_TYPE, "User Type");
		assertEquals(Strings.EVENT_ID, "Event ID");
		assertEquals(Strings.IMSI, "IMSI");
		assertEquals(Strings.CAUSE_CODE, "Cause Code");
		assertEquals(Strings.DURATION, "Total Duration (ms)");
		assertEquals(Strings.OCCURANCES, "Occurrences");
		assertEquals(Strings.NUM_FAILURES, "Number of failures");
		assertEquals(Strings.SUBMIT, "Submit");
		assertEquals(Strings.PROFILE_PAGE, "Profile Page");
		assertEquals(Strings.PROFILE_USERNAME, "Username:");
		assertEquals(Strings.PROFILE_ROLE, "User Role:");
		assertEquals(Strings.RESULT_IMSI, "IMSI:");
		assertEquals(Strings.RESULT_PHONE_MODEL, "Model:");
		assertEquals(Strings.RESULT_FROM, "From:");
		assertEquals(Strings.RESULT_TO, "to:");
		assertEquals(Strings.QUERY_EXECUTION_TIME, "Query executed in %.2f ms");
		assertEquals(Strings.HOME, "Home");
		assertEquals(Strings.QUERIES, "Queries");
		assertEquals(Strings.TWEETS, "Tweets by @ericsson");
		assertEquals(Strings.UNIQUE_EVENTID_AND_CAUSECODE_COMBINATIONS_BY_IMSI, "Event ID and Cause Code combinations by IMSI");
		assertEquals(Strings.NUM_FAILURES_BY_IMSI_BY_TIME_PERIOD, "Failures by IMSI in a given period");
		assertEquals(Strings.UNIQUE_CAUSECODES_BY_IMSI, "Unique Cause Codes by IMSI");
		assertEquals(Strings.ALL_IMSIS_BY_TIME_PERIOD, "List all IMSIs in a given period");
		assertEquals(Strings.NUM_FAILURES_BY_MODEL_BY_TIME_PERIOD, "Failures by phone model in a given period");
		assertEquals(Strings.NUM_FAILURES_FOR_EACH_IMSI_BY_TIME_PERIOD, "Failures and their duration for each IMSI");
		assertEquals(Strings.UNIQUE_EVENTID_AND_CAUSECODE_COMBINATION_AND_OCCURRENCES_BY_MODEL, "List unique Event ID and Cause Code combinations and their occurrences by phone model");
		assertEquals(Strings.IMPORT, "Import dataset");
		assertEquals(Strings.LOGS, "View system log");
		assertEquals(Strings.ADD_USER, "Add user");
		assertEquals(Strings.SHOW_USERS, "Show list of users");
		assertEquals(Strings.PH_USERNAME, "Username");
		assertEquals(Strings.PH_PASSWORD, "Password");
		assertEquals(Strings.PH_CONFIRM, "Confirm password");
		assertEquals(Strings.PH_IMSI, "IMSI Value");
		assertEquals(Strings.PH_PHONE_MODEL, "Phone Model");
		assertEquals(Strings.PH_FNAME, "First Name");
		assertEquals(Strings.PH_LNAME, "Last Name");
		assertEquals(Strings.PH_EMAIL, "Email Address");
		assertEquals(Strings.PH_PHONE, "Phone Number");
		assertEquals(Strings.TT_IMSI, "Enter a valid IMSI");
		assertEquals(Strings.TT_PHONE_MODEL, "Enter a valid phone model");
		assertEquals(Strings.TT_FROM, "From");
		assertEquals(Strings.TT_TO, "To");
		assertEquals(Strings.TT_EMAIL, "Must be in form 'email@address.com'");
		assertEquals(Strings.TT_PHONE, "Must be between 7-15 digits");
		assertEquals(Strings.TT_PASSWORD, "Must be at least 4 characters in length");
		assertEquals(Strings.CREATE_USER, "Create user");
		assertEquals(Strings.SELECT_ROLE, "-- Select Role --");
		assertEquals(Strings.CSR, "Customer Service Rep");
		assertEquals(Strings.SE, "Support Engineer");
		assertEquals(Strings.NME, "Network Management Engineer");
		assertEquals(Strings.UPLOAD, "Upload dataset");
		assertEquals(Strings.ALL_USERS, "All users");
	}
}