import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.nerdprogramming.gfx.engine.DisplayManager;

public class DisplayTester {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DisplayManager.Open(100, 720, "TESTING");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DisplayManager.Close();
	}

	@Test
	public void test() {
		assertEquals( "Width Didn't Match, Got "+DisplayManager.GetWidth(),DisplayManager.GetWidth(), 1080);
		assertEquals( "Height Didn't Match, Got "+DisplayManager.GetHeight(),DisplayManager.GetHeight(), 720);
	}

}
