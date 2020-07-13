package uk.co.nerdprogramming.gfx.engine;

import java.util.HashMap;

import uk.co.nerdprogramming.gfx.engine.shaders.Shader;

public class ShaderManager {
	private ShaderManager() {}
	private static HashMap<String, Shader> shaderTable =  new HashMap<String, Shader>();
	
	public static void SetShader(String name, Shader shader) {
		if(!shaderTable.containsKey(name))
			shaderTable.put(name, shader);
	}
	
	public static Shader GetShader(String name) {
		return shaderTable.getOrDefault(name, null);
	}
	
	public static void LoadShader(String name, String vertexFile, String fragmentFile) {
		if(!shaderTable.containsKey(name)) {
			SetShader(name, Shader.LoadGLSL(vertexFile, fragmentFile));
		} 
	}
	
	public static void Destruct() {
		for(String key : shaderTable.keySet()) {
			shaderTable.get(key).Delete();
			shaderTable.remove(key);
		}
	}
}
