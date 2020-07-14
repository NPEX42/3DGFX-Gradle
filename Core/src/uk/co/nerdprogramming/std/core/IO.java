package uk.co.nerdprogramming.std.core;

import java.io.*;

public class IO {
	public static String LoadString(InputStream stream) {
		try {
			if(stream == null) return null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			StringBuilder builder = new StringBuilder();
			String line = "";
			while((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append('\n');
			}
			return builder.toString();
		} catch(IOException ioex) {
			return null;
		}
	}
	
	public static String LoadString(String filePath) {
		try {
			return LoadString(new FileInputStream(filePath));
		} catch(IOException ioex) {
			return null;
		}
	}
	
	public static String LoadStringJAR(String filePath) {
		String source = IO.LoadString(IO.class.getClassLoader().getResourceAsStream(filePath));
		if(source == null) {
			source = IO.LoadString(IO.class.getClassLoader().getResourceAsStream(filePath));
		}
		return source;
	}
	

}
