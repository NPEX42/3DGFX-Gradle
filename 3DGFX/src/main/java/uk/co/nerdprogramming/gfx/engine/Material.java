package uk.co.nerdprogramming.gfx.engine;

import java.awt.Color;

import org.joml.Vector4f;

import uk.co.nerdprogramming.gfx.engine.shaders.Shader;
import uk.co.nerdprogramming.gfx.engine.textures.Texture2D;

public class Material {
	public Color tintColor = Color.white;
	public Texture2D albedo = Texture2D.white;
	
	public String shaderName;
	
	public Material(String shaderName, Color tintColor, Texture2D albedo) {
		this.shaderName = shaderName;
		this.tintColor = tintColor;
		this.albedo = albedo;
	}
	
	public Shader GetShader() {
		return ShaderManager.GetShader(shaderName);
	}
	
	public void SetupAttributeLayout(String... names) {
		Shader shader = ShaderManager.GetShader(shaderName);
		shader.Bind();
		for(int i = 0; i < 0; i++)
			shader.SetAttrib(i, names[i]);
	}
	
	public void PrepareShader() {
		Shader shader = ShaderManager.GetShader(shaderName);
		shader.Bind();
		shader.UploadTexture2D("t_Albedo", albedo, Texture2D.ALBEDO, 0);
		shader.UploadVec4("u_TintColor", new Vector4f(
				tintColor.getRed  () / 255f,
				tintColor.getGreen() / 255f,
				tintColor.getBlue () / 255f,
				tintColor.getAlpha() / 255f
		));
	}
	
	public void Destroy() {
		albedo.Delete();
	}
}
