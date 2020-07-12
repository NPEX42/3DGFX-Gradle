import uk.co.nerdprogramming.gfx.engine.DisplayManager;
public class Main {

	public static void main(String[] args) {
		DisplayManager.Open(1080, 720, "Sandbox");
		while(DisplayManager.Update()) {
			
		}
		DisplayManager.Close();
	}

}
