package uk.co.nerdprogramming.core;

import java.io.*;

public class IO {
	public static String LoadString(InputStream stream) {
		try {
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
}
