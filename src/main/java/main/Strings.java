package main;

public class Strings {	
	//Header
	public static String TITLE = "Project LOKI";
	public static String LOGGED_IN_AS = "Logged in as";
	public static String LOGOUT = "Logout";

	//Footer
	public static String COPYRIGHT_AND_NAMES = "&copy; Jason Costello, Tim Coghlan, Danny Flynn, Gavin Hughes, James McManus, Arouge Mehdi";
	
	//Login
	public static String LOGIN = "Login";
	
	//General
	public static String USERNAME = "Username";
	public static String USER_TYPE = "User Type";
	public static String EVENT_ID = "Event ID";
	public static String IMSI = "IMSI";
	public static String CAUSE_CODE = "Cause Code";
	public static String DURATION = "Total Duration (ms)";
	public static String OCCURANCES = "Occurrences";
	public static String NUM_FAILURES = "Number of failures";
	public static String SUBMIT = "Submit";
	
	//Profile page
	public static String PROFILE_PAGE = "Profile Page";
	public static String PROFILE_USERNAME = "Username:";
	public static String PROFILE_ROLE = "User Role:";
	public static String PROFILE_FIRSTNAME = "First Name:";
	public static String PROFILE_LASTNAME = "Last Name:";
	public static String PROFILE_EMAIL = "Email:";
	public static String PROFILE_PHONE = "Number:";
	
	//Query results
	public static String RESULT_IMSI = "IMSI:";
	public static String RESULT_PHONE_MODEL = "Model:";
	public static String RESULT_FROM = "From:";
	public static String RESULT_TO = "to:";
	public static String DATE_RANGE = "Range:";
	public static String QUERY_EXECUTION_TIME = "Query executed in %.2f ms";
	
	//Navigation
	public static String HOME = "Home";
	public static String QUERIES = "Queries";
	public static String CSQUERIES = "Customer Rep Queries";
	public static String SEQUERIES = "Support Eng Queries";
	public static String NMEQUERIES = "Network Management Eng Queries";
	public static String TWEETS = "Tweets by @ericsson";
	
	//Navigation - CSRep
	public static String UNIQUE_EVENTID_AND_CAUSECODE_COMBINATIONS_BY_IMSI = "Event ID and Cause Code combinations by IMSI";
	public static String NUM_FAILURES_BY_IMSI_BY_TIME_PERIOD = "Failures by IMSI in a given period";
	public static String UNIQUE_CAUSECODES_BY_IMSI = "Unique Cause Codes by IMSI";
	
	//Navigation - SEng
	public static String ALL_IMSIS_BY_TIME_PERIOD = "List all IMSIs in a given period";
	public static String NUM_FAILURES_BY_MODEL_BY_TIME_PERIOD = "Failures by phone model in a given period";
	public static String IMSIS_BY_FAILURE_CLASS = "IMSIs affected by a given Failure Class";
	
	//Navigation - NME
	public static String NUM_FAILURES_FOR_EACH_IMSI_BY_TIME_PERIOD = "Failures and their duration for each IMSI";
	public static String UNIQUE_EVENTID_AND_CAUSECODE_COMBINATION_AND_OCCURRENCES_BY_MODEL = "List unique Event ID and Cause Code combinations and their occurrences by phone model";
	public static String TOP_10_MARKET_OPERATOR_CELL_NODES_BY_TIME_PERIOD = "Top 10 Market/Operator/Cell Nodes in a given time period";
	public static String TOP_10_IMSIS = "Top 10 IMSIs in a given time period";
	public static String TOP_10_MARKET_OPERATOR_CELL_NODES_GRAPHICAL = "Top 10 Market/Operator/Cell Nodes with graphical representation";
	
	//Navigation - SysAdmin
	public static String IMPORT = "Import dataset";
	public static String ADD_USER = "Add user";
	public static String SHOW_USERS = "Show list of users";
	public static String LOGS = "View system log";
	
	//Form place holders
	public static String PH_USERNAME = "Username";
	public static String PH_PASSWORD = "Password";
	public static String PH_CONFIRM = "Confirm password";
	public static String PH_IMSI = "IMSI Value";
	public static String PH_PHONE_MODEL = "Phone Model";
	public static String PH_LNAME = "Last Name";
	public static String PH_FNAME = "First Name";
	public static String PH_EMAIL = "Email Address";
	public static String PH_PHONE = "Phone Number";
	
	//Form tool tips
	public static String TT_IMSI = "Enter a valid IMSI";
	public static String TT_PHONE_MODEL = "Enter a valid phone model";
	public static String TT_FROM = "From";
	public static String TT_TO = "To";
	public static String TT_EMAIL = "Must be in form 'email@address.com'";
	public static String TT_PHONE = "Must be between 7-15 digits";
	public static String TT_PASSWORD = "Must be at least 4 characters in length";
	
		//SysAdmin
	//Add User
	public static String CREATE_USER = "Create user";
	public static String SELECT_ROLE = "-- Select Role --";
	public static String CSR = "Customer Service Rep";
	public static String SE = "Support Engineer";
	public static String NME = "Network Management Engineer";
	
	//Import
	public static String UPLOAD = "Upload dataset";
	
	//List Users
	public static String ALL_USERS = "All users";
}
