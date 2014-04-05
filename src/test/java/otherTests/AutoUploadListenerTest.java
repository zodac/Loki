package otherTests;

import static org.junit.Assert.assertEquals;
import main.AutoUploadListener;

import org.junit.BeforeClass;
import org.junit.Test;

public class AutoUploadListenerTest {
	private static AutoUploadListener aul;
	
	@BeforeClass
	public static void setUp(){
		aul = AutoUploadListener.getInstance();
	}
	
	@Test
	public void testStartThread() {
		aul.start();
		assertEquals(true, aul.isAlive());
	}
	
	@Test
	public void testSingleton(){
		AutoUploadListener aul2 = AutoUploadListener.getInstance();
		assertEquals(true, aul.equals(aul2));
	}
}