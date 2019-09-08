package com.skostaras.application;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {
	
	@SuppressWarnings("finally")
	public void throwAndLogSevereException(Logger logger, String message) {
		try {
			throw new IllegalArgumentException(message);
		} catch (Exception e) {
			logger.setUseParentHandlers(false);
			logAndPrint(logger, Level.SEVERE, e.getMessage());
		} finally {
			throw new IllegalArgumentException(message);
		}
	}

	public void logAndPrint(Logger logger, Level logLevel, String message) {
		FileHandler handler;
		try {
			handler = new FileHandler("mazeChallenge.log", true);
			logger.addHandler(handler);
			logger.log(logLevel, message);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
