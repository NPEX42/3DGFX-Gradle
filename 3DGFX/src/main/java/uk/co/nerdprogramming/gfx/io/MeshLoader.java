package uk.co.nerdprogramming.gfx.io;

import java.io.*;
import java.util.*;

import org.joml.*;

import uk.co.nerdprogramming.gfx.engine.Mesh;
import uk.co.nerdprogramming.std.core.IO;

public class MeshLoader {
	public static Mesh LoadMesh(String filePath) {
		String tempSource = IO.LoadString(filePath);
		if(tempSource == null) tempSource = IO.LoadStringJAR(filePath);
		if(tempSource == null) return LoadMesh("uk/co/nerdprogramming/gfx/res/meshes/quad.mesh");
		String[] source = tempSource.split("\n");
		float[]
				pos = new float[3],
				uvs = new float[2];
		int[] tris = new int[3];
		for(String line : source) {
			String[] sections = line.split(":");
			switch(sections[0]) {
			case "pos" : pos = ToFloatArray(sections[1].split(",")); break;
			case "uvs" : uvs = ToFloatArray(sections[1].split(",")); break;
			case "tris": tris = ToIntArray(sections[1].split(","));  break;
			default: System.err.println("[MeshLoader] Malformatted Mesh: '"+sections[0]+"' Is Not A Valid Identifier...");
			}
		}
		return Mesh.Create3D(pos, uvs, tris);
	}
	
	public static void ConvertOBJ(String filePath) {
		loadObjModel(filePath);
	}
	
	private static float[] ToFloatArray(String[] source) {
		float[] out = new float[source.length];
		for(int idx = 0; idx < out.length; idx++) {
			if(source[idx] != null) 
				out[idx] = Float.parseFloat(source[idx]);
		}
		return out;
	}
	
	private static int[] ToIntArray(String[] source) {
		int[] out = new int[source.length];
		for(int idx = 0; idx < out.length; idx++) {
			if(source[idx] != null) 
				out[idx] = Integer.parseInt(source[idx]);
		}
		return out;
	}
	
	private static String ToString(int[] source) {
		StringBuilder builder = new StringBuilder();
		for(int i : source) builder.append(i+",");
		return builder.toString();
	}
	
	private static String ToString(float[] source) {
		StringBuilder builder = new StringBuilder();
		for(float i : source) builder.append(i+",");
		return builder.toString();
	}
	
	private static void loadObjModel(String fileName) {
	FileReader fr = null;
	try {
		fr = new FileReader(new File(fileName));
	} catch (FileNotFoundException e) {
		System.err.println("Couldn't load file!");
		e.printStackTrace();
	}
	BufferedReader reader = new BufferedReader(fr);
	String line;
	List<Vector3f> vertices = new ArrayList<Vector3f>();
	List<Vector2f> textures = new ArrayList<Vector2f>();
	List<Vector3f> normals = new ArrayList<Vector3f>();
	List<Integer> indices = new ArrayList<Integer>();
	float[] verticesArray = null;
	float[] normalsArray = null;
	float[] textureArray = null;
	int[] indicesArray = null;
	try {

		while (true) {
			line = reader.readLine();
			String[] currentLine = line.split(" ");
			if (line.startsWith("v ")) {
				Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]),
						Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
				vertices.add(vertex);
			} else if (line.startsWith("vt ")) {
				Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]),
						Float.parseFloat(currentLine[2]));
				textures.add(texture);
			} else if (line.startsWith("vn ")) {
				Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]),
						Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
				normals.add(normal);
			} else if (line.startsWith("f ")) {
				textureArray = new float[vertices.size() * 2];
				normalsArray = new float[vertices.size() * 3];
				break;
			}
		}

		while (line != null) {
			if (!line.startsWith("f ")) {
				line = reader.readLine();
				continue;
			}
			String[] currentLine = line.split(" ");
			String[] vertex1 = currentLine[1].split("/");
			String[] vertex2 = currentLine[2].split("/");
			String[] vertex3 = currentLine[3].split("/");
			
			processVertex(vertex1,indices,textures,normals,textureArray,normalsArray);
			processVertex(vertex2,indices,textures,normals,textureArray,normalsArray);
			processVertex(vertex3,indices,textures,normals,textureArray,normalsArray);
			line = reader.readLine();
		}
		reader.close();

	} catch (Exception e) {
		e.printStackTrace();
	}
	
	verticesArray = new float[vertices.size()*3];
	indicesArray = new int[indices.size()];
	
	int vertexPointer = 0;
	for(Vector3f vertex:vertices){
		verticesArray[vertexPointer++] = vertex.x;
		verticesArray[vertexPointer++] = vertex.y;
		verticesArray[vertexPointer++] = vertex.z;
	}
	
	for(int i=0;i<indices.size();i++){
		indicesArray[i] = indices.get(i);
	}
	
	
	String out = "";
	out += "pos:"+ToString(verticesArray)+"\n";
	out += "uvs:"+ToString(textureArray)+"\n";
	out += "tris:"+ToString(indicesArray)+"\n";

	try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName.replaceAll(".obj", ".mesh")));
		writer.write(out);
		writer.flush();
		writer.close();
	} catch(IOException ioex) {
		System.err.println("Unable To Open File...");
	}
}

	private static void processVertex(String[] vertexData, List<Integer> indices,
		List<Vector2f> textures, List<Vector3f> normals, float[] textureArray,
		float[] normalsArray) {
	int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
	indices.add(currentVertexPointer);
	Vector2f currentTex = textures.get(Integer.parseInt(vertexData[1])-1);
	textureArray[currentVertexPointer*2] = currentTex.x;
	textureArray[currentVertexPointer*2+1] = 1 - currentTex.y;
	Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2])-1);
	normalsArray[currentVertexPointer*3] = currentNorm.x;
	normalsArray[currentVertexPointer*3+1] = currentNorm.y;
	normalsArray[currentVertexPointer*3+2] = currentNorm.z;	
}
}


