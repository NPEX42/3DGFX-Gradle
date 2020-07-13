import java.awt.Color;

import uk.co.nerdprogramming.gfx.engine.DisplayManager;
import uk.co.nerdprogramming.gfx.engine.GLRenderer;
import uk.co.nerdprogramming.gfx.engine.Mesh;
import uk.co.nerdprogramming.gfx.engine.shaders.Shader;
import uk.co.nerdprogramming.gfx.engine.textures.Texture2D;
public class Main {

	public static void main(String[] args) {
		System.err.println("[Sandbox] Opening Window...");
		DisplayManager.Open(1080, 720, "Sandbox Application");
		GLRenderer glr = GLRenderer.get();
		
		float[] pos = {
				-1, -1, 0,
				+1, -1, 0,
				+1, +1, 0,
				-1, +1, 0,
		};
		
		float[] uvs = {
				0,0,
				1,0,
				1,1,
				0,1,
		};
		
		int[] tris = {0,1,2,2,3,0};
		System.err.println("[Sandbox] Building Mesh...");
		Mesh quad = Mesh.Create3D(pos,uvs, tris);
		
		System.err.println("[Sandbox] Loading Shaders...");
		Shader shader = Shader.Load(
				"uk/co/nerdprogramming/gfx/res/shaders/3d.vs",
				"uk/co/nerdprogramming/gfx/res/shaders/3d.fs"
		);
		
		if(shader == null) System.exit(-1);
		shader.SetAttrib(0, "a_Position");
		shader.SetAttrib(1, "a_UV");
		
		System.err.println("[Sandbox] Loading Textures...");
		Texture2D albedo = Texture2D.missing;
		if(albedo == null) { System.err.println("Unable To Locate Albedo"); System.exit(-1); }
		shader.UploadTexture2D("t_Albedo", albedo, Texture2D.ALBEDO, 0);
		long tp1, tp2, frameCount = 0, DC = 0;
		double tpfs, accumTime = 0;
		
		while(DisplayManager.Update()) {
			tp1 = System.nanoTime();
			glr.ClearColor(Color.BLUE);
			for(int i = 0; i < 1; i++) {
				glr.Render(quad, shader);
				DC++; //Increment the number of draw calls this frame.
			}
			tp2 = System.nanoTime();
			tpfs = (tp2 - tp1) / 1000000000f;
			System.err.printf("TPF: %03.9fS (%03.4fms) | FPS: %.2f | DC: %d\r",tpfs, tpfs * 1000f, 1f / tpfs, DC);
			accumTime += tpfs;
			frameCount++;
			DC = 0;
		}
		
		double avg = accumTime / frameCount;
		System.err.printf("\nAverage Frame Time: %.4fms (%.2f fps)",avg * 1000f,1f/avg);
		DisplayManager.Close();
	}

}
