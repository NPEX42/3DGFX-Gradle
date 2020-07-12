import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.nerdprogramming.gfx.engine.DisplayManager;

public class DisplayTester {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DisplayManager.Open(1080, 720, "TESTING");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DisplayManager.Close();
	}

	@Test
	public void test() {
		assertTrue(DisplayManager.GetWidth() == 1080);
		assertTrue(DisplayManager.GetHeight() == 720);
	}

}
