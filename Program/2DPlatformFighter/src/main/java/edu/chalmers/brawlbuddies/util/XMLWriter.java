package edu.chalmers.brawlbuddies.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * A class that hold methods for writing XML documents.
 * @author Matz Larsson
 * @version 1.0
 *
 */
public class XMLWriter {
	/**
	 * Write to a xml document at a given path
	 * @param path - the file path
	 * @param xml - the xml document to be written
	 */
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
	/**
	 * Creates a file path by a given file path name
	 * @param path - the file path
	 */
	private static void createPath(String path){
		File folder = new File(path).getParentFile();
		if(!folder.exists()){
			createPath(folder.getPath());
			folder.mkdir();
		}
	}
}
