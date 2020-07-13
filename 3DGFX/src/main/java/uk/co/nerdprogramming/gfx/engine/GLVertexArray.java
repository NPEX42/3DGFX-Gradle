package uk.co.nerdprogramming.gfx.engine;

import static org.lwjgl.opengl.GL46.*;


public class GLVertexArray {
	private int ID;
	
	public static GLVertexArray Create() {
		GLVertexArray va = new GLVertexArray();
		va.Bind();
		return va;
	}

	public GLVertexArray() {
		ID = glGenVertexArrays();
	}
	
	public void Bind() {
		glBindVertexArray(ID);
	}
	
	public void Delete() {
		glDeleteVertexArrays(ID);
	}
}