package uk.co.nerdprogramming.gfx.cameras;

import org.joml.Matrix4f;

public class OrthoCamera extends Camera {

	@Override
	public Matrix4f GetProjection(float width, float height) {
		return new Matrix4f().ortho(0, width, height, 0, +1, -1);
	}

}
