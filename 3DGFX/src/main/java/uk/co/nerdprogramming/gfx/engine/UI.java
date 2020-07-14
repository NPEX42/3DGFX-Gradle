package uk.co.nerdprogramming.gfx.engine;

import imgui.ImDrawData;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiDockNodeFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import uk.co.nerdprogramming.gfx.engine.textures.Texture2D;

public class UI {
	private static ImGuiIO io;
	private static ImGuiImplGl3 implgl3;
	private static ImGuiImplGlfw implglfw;
	
	public static void Init() {
		ImGui.createContext();
		io = ImGui.getIO();
		io.setConfigFlags(ImGuiConfigFlags.DockingEnable);
		
		io.setConfigDockingAlwaysTabBar(true);
		
		io.setDisplaySize(DisplayManager.GetWidth(), DisplayManager.GetHeight());
		ImGui.styleColorsDark();
		
		implglfw = new ImGuiImplGlfw();
		implglfw.init(DisplayManager.GetID(), true);
		
		implgl3 = new ImGuiImplGl3();
		implgl3.init("#version 330");
	}
	
	public static void NewFrame() {
		implglfw.newFrame();
		ImGui.newFrame();
	}
	
	public static void Render() {
		ImGui.render();
		implgl3.render(ImGui.getDrawData());
	}
	
	public static void Begin(String title) {
		ImGui.begin(title);
	}
	
	public static void End() {
		ImGui.end();
	}
	
	public static void DockSpace() {
		ImGui.dockSpace(ImGui.getID(0), 0, 0, ImGuiDockNodeFlags.PassthruCentralNode);
	}
	
	public static void BeginDocked() {
		
	}
	
	public static void Text(String text) {
		ImGui.labelText(null, text);
	}
	
	public static void ShowDemoWindow() {
		ImGui.showDemoWindow();
	}
	
	public static void ColorPicker(String label, float[] colorPtr) {
		ImGui.colorEdit3(label, colorPtr);
	}
	
	public static void GLViewPort(String title, Texture2D tex, float scale) {
		ImGui.setNextWindowSize(tex.getWidth() * scale, tex.getHeight() * scale);
		Begin(title);
		Image(tex, scale);
		End();
	}
	
	public static void Image(Texture2D tex, float scale) {
		ImGui.image(tex.getID(), tex.getWidth() * scale, tex.getHeight() * scale);
	}
	
	public static void SliderF1(String text, float[] ptr, float min, float max) {
		ImGui.sliderFloat(text, ptr, min, max);
	}
	
	//Event Manager Callback functions
	
}
