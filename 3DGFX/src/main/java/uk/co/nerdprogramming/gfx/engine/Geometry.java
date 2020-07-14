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
	
	static {
		error = new Geometry(
				new Material("3d/basic", Color.white, Texture2D.missing), 
				MeshLoader.LoadMesh("NONEXISTEN"));
	}
}
