package uk.co.nerdprogramming.gfx.engine;
import static org.lwjgl.glfw.GLFW.*;
import static uk.co.nerdprogramming.gfx.engine.Constants.*;

import org.lwjgl.opengl.GL;
public class DisplayManager {
	private static long window;
	public static void Open(int width, int height, String title) {
		glfwInit();
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glfwSwapInterval(VBLANK_2ND);
	}
	
	public static boolean Update() {
		glfwSwapBuffers(window);
		glfwPollEvents();
		return !glfwWindowShouldClose(window);
	}
	
	public static void Close() {
		glfwDestroyWindow(window);
		glfwTerminate();
	}
	
	public static int GetWidth() {
		int[] 
		//We Need int*
		w = new int[1],
		h = new int[1];
		
		glfwGetWindowSize(window, w, h);
		return w[0];
	}
	
	public static int GetHeight() {
		int[] 
		//We Need int*
		w = new int[1],
		h = new int[1];
		
		glfwGetWindowSize(window, w, h);
		return h[0];
	}
	
}
