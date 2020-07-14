package uk.co.nerdprogramming.gfx.cameras;

import org.joml.Matrix4f;

public class IdentityCamera extends Camera {

	@Override
	public Matrix4f GetProjection(float width, float height) {
		return new Matrix4f().identity();
	}

}
