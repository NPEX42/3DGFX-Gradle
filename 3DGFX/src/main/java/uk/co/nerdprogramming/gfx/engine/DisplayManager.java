package uk.co.nerdprogramming.gfx.engine;
import static org.lwjgl.glfw.GLFW.*;
import static uk.co.nerdprogramming.gfx.engine.Constants.*;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
public class DisplayManager {
	private static long window;
	private static String title;
	public static void Open(int width, int height, String title) {
		glfwInit();
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glfwSwapInterval(VBLANK);
		
		DisplayManager.title = title;
	}
	
	public static boolean Update() {
		glfwSwapBuffers(window);
		glfwPollEvents();
		return !glfwWindowShouldClose(window);
	}
	
	public static void Close() {
		glfwSetWindowShouldClose(window, true);
		glfwDestroyWindow(window);
		glfwTerminate();
	}
	
	public static void SetViewportSize(int w, int h) {
		GL11.glViewport(0, 0, w, h);
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
	
	public static String GetTitle() {
		return title;
	}
	
	public static long GetID() {return window;}
	
}
