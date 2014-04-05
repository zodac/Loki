package otherTests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import webservice.Log;

public class LogTest {
	private static File logFile;
	private static String fileName = "Log.txt";
	
	@BeforeClass
	public static void setUp(){
		logFile = new File(fileName);
	}
	
	@Test
	public void testCreation() {
		Log.createNewFile(logFile);
		assertEquals(true, logFile.exists());
	}
	
	@Test
	public void testLogAddition(){
		long initialSize = logFile.length()/1000;
		Log.addLogList("Test Entry");
		assertEquals(true, initialSize < logFile.length());
	}
	
	@Test
	public void testReset(){
		long initialSize = logFile.length();
		Log.resetLog(fileName);
		assertEquals(true, initialSize >= logFile.length());
	}
}