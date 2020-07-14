package uk.co.nerdprogramming.gfx.engine.input;

import uk.co.nerdprogramming.gfx.engine.eventSystem.EventManager;

public class Keyboard {
	static {
		EventManager.AddKeyPressedCallback(Keyboard::OnKeyPressed);
		EventManager.AddKeyHeldCallback(Keyboard::OnKeyHeld);
		EventManager.AddKeyReleasedCallback(Keyboard::OnKeyReleased);
	}
	
	public static void OnKeyPressed (char key) {keyStates[key] = true; }
	public static void OnKeyHeld    (char key) {keyStates[key] = true; }
	public static void OnKeyReleased(char key) {keyStates[key] = false;}
	
	private static boolean[] keyStates = new boolean[65536];
	
	public static boolean IsKeyDown(char key) {return  keyStates[key];}
	public static boolean IsKeyUP  (char key) {return !keyStates[key];}
	
	
}
