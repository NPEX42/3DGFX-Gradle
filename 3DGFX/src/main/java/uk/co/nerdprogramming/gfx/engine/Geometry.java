package uk.co.nerdprogramming.gfx.engine;

import java.awt.Color;

import uk.co.nerdprogramming.gfx.engine.textures.Texture2D;
import uk.co.nerdprogramming.gfx.io.MeshLoader;

public class Geometry {
	private Material mat;
	private Mesh mesh;
	
	private static Geometry error;
	
	public Material getMat() {
		return mat;
	}
	public Mesh getMesh() {
		return mesh;
	}
	public static Geometry GetErrorGeom() {return error;}
	public Geometry(Material mat, Mesh mesh) {
		super();
		this.mat = mat;
		this.mesh = mesh;
	}
	
	public static Geometry LoadGeom(String meshPath, String shaderName, String vertexPath, String fragPath, String albedoFilePath, Color tintColor) {
		ShaderManager.LoadShader(shaderName, vertexPath, fragPath);
		Texture2D albedo;
		if(albedoFilePath == null) {
			albedo = Texture2D.white;
		} else {
			albedo = Texture2D.Load(albedoFilePath);
		}
		Material mat = new Material(shaderName, tintColor, albedo);
		mat.SetupAttributeLayout("a_Positions","a_UVS");
		Mesh mesh = MeshLoader.LoadMesh(meshPath);
		
		return new Geometry(mat, mesh);
	}
	
	public static Geometry LoadGeom(String meshPath, String shaderName, String albedoFilePath, Color tintColor) {
		Texture2D albedo;
		if(albedoFilePath == null) {
			albedo = Texture2D.white;
		} else {
			albedo = Texture2D.Load(albedoFilePath);
		}
		Material mat = new Material(shaderName, tintColor, albedo);
		mat.SetupAttributeLayout("a_Positions","a_UVS");
		Mesh mesh = MeshLoader.LoadMesh(meshPath);
		
		return new Geometry(mat, mesh);
	}
	
	static {
		ShaderManager.LoadShader("3d/basic", "uk/co/nerdprogramming/gfx/res/shaders/3d.vs.glsl", "uk/co/nerdprogramming/gfx/res/shaders/3d.fs.glsl");
		error = new Geometry(
				new Material("3d/basic", Color.white, Texture2D.missing), 
				MeshLoader.LoadMesh("NONEXISTENT"));
	}
}
