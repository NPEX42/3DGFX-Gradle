import java.awt.Color;

import uk.co.nerdprogramming.gfx.engine.DisplayManager;
import uk.co.nerdprogramming.gfx.engine.GLRenderer;
import uk.co.nerdprogramming.gfx.engine.Geometry;
import uk.co.nerdprogramming.gfx.engine.Material;
import uk.co.nerdprogramming.gfx.engine.Mesh;
import uk.co.nerdprogramming.gfx.engine.ShaderManager;
import uk.co.nerdprogramming.gfx.engine.textures.Texture2D;
import uk.co.nerdprogramming.gfx.io.MeshLoader;

public class Main {

	public static void main(String[] args) {
		System.err.println("[Sandbox] Opening Window...");
		DisplayManager.Open(1080, 720, "Sandbox Application");
		GLRenderer glr = GLRenderer.get();
		Geometry quad = Geometry.LoadGeom(
				"res/quad.mesh",
				"3d/basic",
				null,
				Color.blue
		);
		while(DisplayManager.Update()) {
			glr.ClearColor(Color.GRAY);
			glr.Render(quad);
		}
		DisplayManager.Close();
	}

}
