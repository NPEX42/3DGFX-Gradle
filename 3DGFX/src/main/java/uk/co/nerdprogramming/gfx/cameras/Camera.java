package uk.co.nerdprogramming.gfx.cameras;

import org.joml.Matrix4f;

public abstract class Camera {
	public abstract Matrix4f GetProjection(float width, float height);
}
