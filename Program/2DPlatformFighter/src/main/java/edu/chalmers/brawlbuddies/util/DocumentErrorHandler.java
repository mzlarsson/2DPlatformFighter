package edu.chalmers.brawlbuddies.util;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DocumentErrorHandler implements ErrorHandler{

	public static final int LEVEL_NONE = 0;
	public static final int LEVEL_FATAL_ERROR = 1;
	public static final int LEVEL_ERROR = 2;
	public static final int LEVEL_WARNING = 3;
	
	private int level = 0;

	public DocumentErrorHandler(int level){
		this.level = level;
	}

	public void error(SAXParseException exc) throws SAXException {
		if(level>=LEVEL_ERROR){
			GameLogger.getLogger().warning("Error in XML: "+exc.getMessage());
		}
	}

	public void fatalError(SAXParseException exc) throws SAXException {
		if(level>=LEVEL_FATAL_ERROR){
			GameLogger.getLogger().severe("Fatal Error in XML: "+exc.getMessage());
		}
	}

	public void warning(SAXParseException exc) throws SAXException {
		if(level>=LEVEL_WARNING){
			GameLogger.getLogger().warning("Warning in XML: "+exc.getMessage());
		}
	}

}
