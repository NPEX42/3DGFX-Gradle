package uk.co.nerdprogramming.gfx.engine.apis;

import java.awt.Color;

import uk.co.nerdprogramming.gfx.engine.Geometry;
import uk.co.nerdprogramming.gfx.engine.Mesh;
import uk.co.nerdprogramming.gfx.engine.shaders.Shader;

public interface RendererAPI {
	public void ClearColor(Color c);
	public void Render(Mesh mesh, Shader shader);
	public void Render(Geometry geom);
}
