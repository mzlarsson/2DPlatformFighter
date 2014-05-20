package edu.chalmers.brawlbuddies.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class ResourceLoader {

	public static Image getImage(String path){
		try{
			InputStream imageStream = ClassLoader.getSystemResourceAsStream(path);
			return new Image(imageStream, path, false);
		}catch(SlickException ioe){
			System.out.println("Could not read image: "+path);
			return null;
		}
	}
	
	public static Sound getSound(String path){
		try{
			return new Sound(path);
		}catch(SlickException ioe){
			System.out.println("Could not read image: "+path);
			return null;
		}
	}
	
	public static List<String> listFileNames(String dirPath){
		if(ResourceLoader.class.getResource("ResourceLoader.class").toString().startsWith("jar:")){
			CodeSource src = ResourceLoader.class.getProtectionDomain().getCodeSource();
			if (src != null) {
				List<String> fileNames = new ArrayList<String>();
				
				try{
					URL jar = src.getLocation();
					ZipInputStream zip = new ZipInputStream(jar.openStream());
					while(true) {
						ZipEntry e = zip.getNextEntry();
						if (e == null){
							break;
						}else{
							if (!e.isDirectory()) {
								String name = e.getName();
								//Check if in correct folder
								if(name.startsWith(dirPath)){
									//Check that it is not in any subfolders
									String extra = name.substring(dirPath.length());
									if(!(extra.indexOf("/")>=0 || extra.indexOf("\\")>=0)){
										fileNames.add(name);
									}
								}
							}
						}
					}
				}catch(IOException ioe){
					System.out.println("Could not list files");
				}
				
				return fileNames;
			}
			else {
				System.out.println("Could not read path: "+dirPath);
				return null;
			}
		}else{
			String prefix = "res/";
			File mainPath = new File(prefix+dirPath);
			File[] files = mainPath.listFiles();
			List<String> fileNames = new ArrayList<String>();
			for(int i = 0; i<files.length; i++){
				if(files[i].isFile()){
					fileNames.add(files[i].getPath().substring(prefix.length()));
				}
			}
			return fileNames;
		}
	}

}
