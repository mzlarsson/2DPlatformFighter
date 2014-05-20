package edu.chalmers.brawlbuddies.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class XMLWriter {

	public static void write(String path, String xml){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(xml);
			bw.close();
		} catch (IOException e) {
			System.out.println("Error while printing the file: "+path+"\n"+e.getMessage());
		}
	}
}
