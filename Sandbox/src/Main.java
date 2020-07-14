import java.awt.Color;

import uk.co.nerdprogramming.gfx.cameras.Camera;
import uk.co.nerdprogramming.gfx.cameras.IdentityCamera;
import uk.co.nerdprogramming.gfx.cameras.OrthoCamera;
import uk.co.nerdprogramming.gfx.engine.DisplayManager;
import uk.co.nerdprogramming.gfx.engine.Geometry;
import uk.co.nerdprogramming.gfx.engine.ShaderManager;
import uk.co.nerdprogramming.gfx.engine.UI;
import uk.co.nerdprogramming.gfx.engine.eventSystem.EventManager;
import uk.co.nerdprogramming.gfx.engine.textures.Texture2D;
import uk.co.nerdprogramming.gfx.opengl.GLRenderer;

public class Main {

	public static void main(String[] args) {
		float[] col = new float[4], scale = new float[1];
		
		System.err.println("[Sandbox] Opening Window...");
		DisplayManager.Open(1080, 720, "Sandbox Application");
		GLRenderer glr = GLRenderer.get();
		scale[0] = 128;
		Geometry quad = Geometry.LoadGeom(
				"res/quad.mesh",
				"3d/basic",
				null,
				Color.blue
		);
		
		EventManager.AddCloseCallback(Main::OnClose);
		EventManager.AddResizeCallback(Main::OnResize);
		
		Camera camera = new OrthoCamera();
		
		UI.Init();
		
		
		
		while(DisplayManager.Update()) {
			UI.NewFrame();
//			UI.ShowDemoWindow();
			UI.DockSpace();
			UI.Begin("Color window");
			UI.ColorPicker("Picker", col);
			UI.SliderF1("ViewPort Scale", scale, 0.01f, 256.0f);
			UI.End();
			
			UI.GLViewPort("ViewPort", Texture2D.missing, scale[0]);
			glr.ClearColor(new Color(col[0], col[1], col[2], col[3]));
			glr.Render(quad, camera);
			UI.Render();
		}
		DisplayManager.Close();
	}
	
	public static void OnClose() {
		System.err.println("Closing Window...");
		ShaderManager.Destruct();
	}
	
	public static void OnResize(int width, int height) {
		DisplayManager.SetViewportSize(width, height);
	}

}
