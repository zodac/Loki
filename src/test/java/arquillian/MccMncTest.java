package arquillian;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import daos.MccMncDAO;
import entities.MccMnc;
import entities.MccMncPK;

@Ignore
@RunWith(Arquillian.class)
public class MccMncTest {
	private static String MCC_MNC_TEST_COUNTRY = "Test Country";
	private static String MCC_MNC_TEST_OPERATOR = "Test Operator";
	
	@EJB
	private static MccMncDAO mccMncDAO;
	private static MccMnc mccMnc;
	private static MccMncPK mpk;
	
	@Deployment(name="myJBOSS")
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, "daos", "jpas", "services", "webservices")
				.addAsResource("META-INF/persistence.xml","META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@BeforeClass
	public static void setUp(){
		mpk = new MccMncPK();
		mpk.setMcc(-1);
		mpk.setMnc(-2);
		
		mccMnc = new MccMnc();
		mccMnc.setId(mpk);
		mccMnc.setCountry(MCC_MNC_TEST_COUNTRY);
		mccMnc.setOperator(MCC_MNC_TEST_OPERATOR);
		mccMncDAO.addMccMnc(mccMnc);
	}
	
	@AfterClass
	public static void cleanUp(){
		mccMncDAO.removeMccMnc(mpk);
	}
	
	@Test
	public void testFailureClass(){
		MccMnc mccMnc = mccMncDAO.getMCC_MNC(mpk);
		assertEquals(MCC_MNC_TEST_COUNTRY, mccMnc.getCountry());
		assertEquals(MCC_MNC_TEST_OPERATOR, mccMnc.getOperator());
	}	
}