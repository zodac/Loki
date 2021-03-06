package seleniumTests;


import static org.junit.Assert.assertEquals;


import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrowserTests {
	private WebDriver driver;
	private String baseUrl;
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/Loki";
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Test
	public void loginTest() {
		// sets the URL
		driver.get(baseUrl);
		// waits for page to load
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// clicks the input area
		driver.findElement(By.id("userName")).click();
		// puts "user" in the input area
		driver.findElement(By.id("userName")).sendKeys("user");
		// clicks the input area
		driver.findElement(By.id("password")).click();
		// puts "pass" in the input area
		driver.findElement(By.id("password")).sendKeys("pass");
		// clicks the login button
		driver.findElement(By.id("loginSubmit")).click();
	    assertEquals("http://localhost:8080/Loki/webpages/admin/sysHome.jsp", this.driver.getCurrentUrl());
	}

	@Test
	public void addUserTest() {
		driver.get(baseUrl);

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("userName")).click();
		driver.findElement(By.id("userName")).sendKeys("user");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("pass");
		driver.findElement(By.id("loginSubmit")).click();

		driver.get(baseUrl + "/webpages/admin/sysAddUser.jsp");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("role")).click();
		driver.findElement(By.id("role")).sendKeys("s");
		driver.findElement(By.id("userName")).click();
		driver.findElement(By.id("userName")).sendKeys("NewtestUser11");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("pass");
		driver.findElement(By.id("confirm")).click();
		driver.findElement(By.id("confirm")).sendKeys("pass");
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("jasoncos@gmail.com");
		driver.findElement(By.id("fname")).click();
		driver.findElement(By.id("fname")).sendKeys("Jason");
		driver.findElement(By.id("lname")).click();
		driver.findElement(By.id("lname")).sendKeys("Costello");
		driver.findElement(By.id("phone")).click();
		driver.findElement(By.id("phone")).sendKeys("0895676560");

		driver.findElement(By.id("addUserSubmit")).click();
		assertEquals("http://localhost:8080/Loki/webpages/admin/sysListUsers.jsp", this.driver.getCurrentUrl());  
	}


	@Test
	public void customerRepQrycsEventCause() {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("userName")).click();
		driver.findElement(By.id("userName")).sendKeys("csr");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("pass");
		driver.findElement(By.id("loginSubmit")).click();
		driver.get(baseUrl + "/webpages/customerRep/csEventCause.jsp");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("imsi")).click();
		driver.findElement(By.id("imsi")).sendKeys("344930000000011");
		driver.findElement(By.id("imsiSubmit")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement qryResultTbl = driver.findElement(By.id("datatablehtml"));
		Assert.assertEquals(true, qryResultTbl.isDisplayed());
	}

	@Test
	public void networkManEngQrynmeCountNumFailuresSubmit() {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("userName")).click();
		driver.findElement(By.id("userName")).sendKeys("nme");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("pass");
		driver.findElement(By.id("loginSubmit")).click();
		driver.get(baseUrl + "/webpages/networkManEng/nmeCountNumFailures.jsp");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("from")).click();
		driver.findElement(By.id("from")).sendKeys("2013-02-01T00:00:00");
		driver.findElement(By.id("to")).click();
		driver.findElement(By.id("to")).sendKeys("2013-10-06T23:59:00");
		driver.findElement(By.id("countNumFailuresSubmit")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement qryResultTbl = driver.findElement(By.id("datatablehtml"));
		Assert.assertEquals(true, qryResultTbl.isDisplayed());
		}

	@Test
	public void supportEngQrynmeCountNumFailuresSubmit() {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("userName")).click();
		driver.findElement(By.id("userName")).sendKeys("se");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("pass");
		driver.findElement(By.id("loginSubmit")).click();
		driver.get(baseUrl + "/webpages/supportEng/seListIMSI.jsp");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("from")).click();
		driver.findElement(By.id("from")).sendKeys("2013-01-26T00:00:00");
		driver.findElement(By.id("to")).click();
		driver.findElement(By.id("to")).sendKeys("2013-09-31T22:59:00");
		driver.findElement(By.id("seListIMSISubmit")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement qryResultTbl = driver.findElement(By.id("datatablehtml"));
		Assert.assertEquals(true, qryResultTbl.isDisplayed());
	}

	@Test
	public void userPermissions(){
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("userName")).click();
		driver.findElement(By.id("userName")).sendKeys("nme");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("pass");
		driver.findElement(By.id("loginSubmit")).click();
		driver.get(baseUrl + "/webpages/admin/sysAddUser.jsp");
		assertEquals("http://localhost:8080/Loki/webpages/networkManEng/nmeHome.jsp", this.driver.getCurrentUrl());
	}
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
