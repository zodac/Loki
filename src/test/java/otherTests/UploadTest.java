package otherTests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import webservice.Upload;

public class UploadTest {
	public static String fileName = "test.txt";
	
	@Test
	public void testWriteFile() {
		byte[] content = new byte[100];
		Upload.writeFile(content, fileName);
		assertEquals(true, new File("C:\\tmp\\" + fileName).exists());
	}
}