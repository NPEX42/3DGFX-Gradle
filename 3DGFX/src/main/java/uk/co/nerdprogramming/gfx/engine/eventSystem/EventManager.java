package uk.co.nerdprogramming.gfx.engine.eventSystem;

import java.util.ArrayList;

import uk.co.nerdprogramming.gfx.engine.DisplayManager;
import uk.co.nerdprogramming.gfx.engine.eventSystem.events.*;
import static org.lwjgl.glfw.GLFW.*;

public class EventManager {
	private static ArrayList<ICloseCallback>       closeCallbacks = new ArrayList<ICloseCallback>();
	private static ArrayList<IResizeCallback>      resizeCallbacks = new ArrayList<IResizeCallback>();
	private static ArrayList<IKeyPressedCallback>  keyPressedCallbacks = new ArrayList<IKeyPressedCallback>();
	private static ArrayList<IKeyHeldCallback>     keyHeldCallbacks = new ArrayList<IKeyHeldCallback>();
	private static ArrayList<IKeyReleasedCallback> keyReleasedCallbacks = new ArrayList<IKeyReleasedCallback>();
	static {
		System.err.println("[EventManager] Initializing Event Manager...");
		glfwSetWindowCloseCallback(DisplayManager.GetID(), EventManager::DispatchCloseEvent);
		glfwSetWindowSizeCallback(DisplayManager.GetID(), EventManager::DispatchResizeEvent);
		glfwSetKeyCallback(DisplayManager.GetID(), EventManager::DispatchKeyEvent);
	}
	
	public static void AddCloseCallback(ICloseCallback cbfunc) {
		closeCallbacks.add(cbfunc);
	}
	
	public static void AddResizeCallback(IResizeCallback cbfunc) {
		resizeCallbacks.add(cbfunc);
	}
	
	public static void AddKeyPressedCallback(IKeyPressedCallback cbfunc) {
		keyPressedCallbacks.add(cbfunc);
	}
	
	public static void AddKeyHeldCallback(IKeyHeldCallback cbfunc) {
		keyHeldCallbacks.add(cbfunc);
	}
	
	public static void AddKeyReleasedCallback(IKeyReleasedCallback cbfunc) {
		keyReleasedCallbacks.add(cbfunc);
	}
	
	public static void DispatchCloseEvent(long window) {
		for(ICloseCallback callback : closeCallbacks) callback.invoke();
	}
	
	public static void DispatchResizeEvent(long window, int width, int height) {
		for(IResizeCallback callback : resizeCallbacks) callback.invoke(width, height);
	}
	
	public static void DispatchKeyEvent(long window, int key, int scancode, int action, int mods) {
		if(action == GLFW_PRESS)   { DispatchKeyPressedEvent (window, (char) key); return; }
		if(action == GLFW_REPEAT)  { DispatchKeyHeldEvent    (window, (char) key); return; }
		if(action == GLFW_RELEASE) { DispatchKeyReleasedEvent(window, (char) key); return; } 
	} 
	
	private static void DispatchKeyHeldEvent(long window, char key) {
		for(IKeyHeldCallback callback : keyHeldCallbacks) callback.invoke(key);
	}

	public static void DispatchKeyPressedEvent(long window, char key) {
		for(IKeyPressedCallback callback : keyPressedCallbacks) callback.invoke(key);
	}
	
	public static void DispatchKeyReleasedEvent(long window, char key) {
		for(IKeyReleasedCallback callback : keyReleasedCallbacks) callback.invoke(key);
	}
	
	@Deprecated
	public static void DispatchKeyTypedEvent(long window, char key) {
		//for(IKeyTypedCallback callback : resizeCallbacks) callback.invoke(key);
	}
}
