package edu.chalmers.brawlbuddies.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLWriter {

	public static void write(String path, String xml){
		try {
			createPath(path);
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(xml);
			bw.close();
		} catch (IOException e) {
			GameLogger.getLogger().warning("Error while printing the file: "+path+"\n"+e.getMessage());
		}
	}
	
	private static void createPath(String path){
		File folder = new File(path).getParentFile();
		if(!folder.exists()){
			createPath(folder.getPath());
			folder.mkdir();
		}
	}
}
