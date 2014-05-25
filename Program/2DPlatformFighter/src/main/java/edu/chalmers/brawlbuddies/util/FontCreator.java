package edu.chalmers.brawlbuddies.util;

import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;

import edu.chalmers.brawlbuddies.Constants;

public class FontCreator {

	public static final int SMALL = 0;
	public static final int MEDIUM = 1;
	public static final int BIG = 2;
	public static final int BIG_BOLD = 3;
	public static final int LARGE = 4;
	public static final int LARGE_BOLD = 5;
	
	private static Font smallFont;
	private static Font mediumFont;
	private static Font bigFont;
	private static Font bigBoldFont;
	private static Font largeFont;
	private static Font largeBoldFont;
	
	public static Font getFont(int sizeIndex){
		if(bigFont == null){
			try {
				java.awt.Font f = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, ClassLoader.getSystemResourceAsStream(Constants.FONTS + "SF Slapstick Comic.ttf"));
				smallFont = new TrueTypeFont(f.deriveFont(14.0f).deriveFont(java.awt.Font.PLAIN), true);
				mediumFont = new TrueTypeFont(f.deriveFont(18.0f).deriveFont(java.awt.Font.PLAIN), true);
				bigFont = new TrueTypeFont(f.deriveFont(24.0f).deriveFont(java.awt.Font.PLAIN), true);
				bigBoldFont = new TrueTypeFont(f.deriveFont(24.0f).deriveFont(java.awt.Font.BOLD), true);
				largeFont = new TrueTypeFont(f.deriveFont(30.0f).deriveFont(java.awt.Font.PLAIN), true);
				largeBoldFont = new TrueTypeFont(f.deriveFont(30.0f).deriveFont(java.awt.Font.BOLD), true);
			} catch (FontFormatException e) {
				GameLogger.getLogger().severe("Invalid font given");
			} catch (IOException e) {
				GameLogger.getLogger().warning("Could not read font: "+(Constants.FONTS + "SF Slapstick Comic.ttf"));
			}
		}
		
		switch(sizeIndex){
			case SMALL:		return smallFont;
			case MEDIUM:	return mediumFont;
			case BIG:		return bigFont;
			case BIG_BOLD:	return bigBoldFont;
			case LARGE:		return largeFont;
			case LARGE_BOLD:return largeBoldFont;
			default:		return mediumFont;
		}
		
	}

}
