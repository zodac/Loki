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

import daos.UETypeDAO;
import entities.UEType;

@Ignore
@RunWith(Arquillian.class)
public class UETypeTest {
	private static final int UETYPE_TEST_TAC = -1;
	private static final String UETYPE_TEST_MODEL = "Test Phone Model";
	
	@EJB
	private static UETypeDAO uetDAO;
	private static UEType ueType;	
	
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
		ueType = new UEType();
		ueType.setTac(UETYPE_TEST_TAC);
		ueType.setModel(UETYPE_TEST_MODEL);
		uetDAO.addUEType(ueType);
	}
	
	@AfterClass
	public static void cleanUp(){
		uetDAO.removeUEType(ueType);
	}
	
	@Test
	public void testFailureClass(){
		UEType ueType = uetDAO.getUEType(UETYPE_TEST_TAC);
		assertEquals(UETYPE_TEST_MODEL, ueType.getModel());
	}	
}