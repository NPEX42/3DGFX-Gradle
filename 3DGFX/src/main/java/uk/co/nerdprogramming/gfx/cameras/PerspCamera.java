package uk.co.nerdprogramming.gfx.cameras;

import org.joml.Matrix4f;

public class PerspCamera extends Camera {

	@Override
	public Matrix4f GetProjection(float width, float height) {
		return new Matrix4f().perspective(70.0f, width / height, 0.003f, 1000);
	}

}
