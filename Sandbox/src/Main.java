import java.awt.Color;

import uk.co.nerdprogramming.gfx.engine.DisplayManager;
import uk.co.nerdprogramming.gfx.engine.GLRenderer;
public class Main {

	public static void main(String[] args) {
		DisplayManager.Open(1080, 720, "Sandbox");
		GLRenderer glr = GLRenderer.get();
		while(DisplayManager.Update()) {
			glr.ClearColor(Color.blue);
		}
		DisplayManager.Close();
	}

}
