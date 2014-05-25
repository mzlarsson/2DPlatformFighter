package edu.chalmers.brawlbuddies.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.newdawn.slick.util.Log;

public class GameLogger {
	
	private static ConsoleHandler handler;
	
	public static void setup(){
		//Remove info prints in console from Slick
		Log.setVerbose(false);
		//Remove error prints in console from JInput
		Logger.getLogger("net.java.games.input.DefaultControllerEnvironment").setLevel(Level.OFF);
	}

	public static Logger getLogger(){
		Logger logger = Logger.getLogger(GameLogger.class.getName());
		logger.setLevel(Level.ALL);
		logger.setUseParentHandlers(false);
		
		if(handler == null){
			initHandler();
		}
		
		if(logger.getHandlers().length==0){
			logger.addHandler(handler);
		}
		
		return logger;
	}
	
	private static void initHandler(){
		handler = new ConsoleHandler();
		handler.setFormatter(new Formatter(){
			@Override
			public String format(LogRecord log) {
				return log.getMessage()+"\n";
			}
		});
		handler.setLevel(Level.ALL);
	}

}
