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

import edu.chalmers.brawlbuddies.Constants;
/**
 * A class to load resources for the game
 * @author Matz Larsson
 * @version 1.0
 *
 */
public class ResourceLoader {
	/**
	 * Get the image with a given file path
	 * @param path - the file path to the image
	 * @return Image- the image at the file path
	 */
	public static Image getImage(String path){
		try{
			InputStream imageStream = ClassLoader.getSystemResourceAsStream(path);
			return new Image(imageStream, path, false);
		}catch(SlickException ioe){
			GameLogger.getLogger().warning("Could not read image: "+path);
			return null;
		}
	}
	/**
	 * Get the Sound with a given file path
	 * @param path - the file path to the sound
	 * @return Sound - the sound at the file path
	 */
	public static Sound getSound(String path){
		try{
			return new Sound(path);
		}catch(SlickException ioe){
			GameLogger.getLogger().warning("Could not read sound: "+path);
			return null;
		}
	}
	/**
	 * Get a list of file names at a directory path
	 * @param dirPath - the directory path
	 * @return list<String> - a list of file names
	 */
	public static List<String> listFileNames(String dirPath){
		if(insideJar()){
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
					GameLogger.getLogger().warning("Could not list files");
				}
				
				return fileNames;
			}
			else {
				GameLogger.getLogger().warning("Could not read path: "+dirPath);
				return null;
			}
		}else{
			File mainPath = new File(Constants.ECLIPSE_RESOURCE_PREFIX+dirPath);
			File[] files = mainPath.listFiles();
			List<String> fileNames = new ArrayList<String>();
			for(int i = 0; i<files.length; i++){
				if(files[i].isFile()){
					fileNames.add(files[i].getPath().substring(Constants.ECLIPSE_RESOURCE_PREFIX.length()));
				}
			}
			return fileNames;
		}
	}
	/**
	 * Return if the resource loader is inside a jar
	 * @return boolean - true if the resource loader is inside a jar
 	 */
	public static boolean insideJar(){
		return ResourceLoader.class.getResource("ResourceLoader.class").toString().startsWith("jar:");
	}

}
