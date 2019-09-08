package com.skostaras.services;
import java.util.logging.Logger;
import com.skostaras.application.Logging;
import com.skostaras.constants.ErrorMessage;

public class MazeFileValidator {
	
	Logging logging = new Logging();
	final static Logger logger = Logger.getLogger(MazeFileValidator.class.getName());

	public void emptyFileValidate(String mazeString) {

		if (mazeString == null || (mazeString = mazeString.trim()).length() == 0) {
			logging.throwAndLogSevereException(logger, ErrorMessage.EMPTY_FILE.getValue());
		}
	}

	public void unevenLinesValidate(String[] mazeStringLines) {

		int firstLineLength = mazeStringLines[0].length();

		for (int i = 0; i < mazeStringLines.length; i++) {
			
			if (mazeStringLines[i].equals(""))
				logging.throwAndLogSevereException(logger, ErrorMessage.EMPTY_ROW.getValue() + (i+1) + "." );
			
			if (mazeStringLines[i].length() != firstLineLength) 
				logging.throwAndLogSevereException(logger, ErrorMessage.UNEVEN_ROWS.getValue());
		}
	}
	
	public void mazeSizeValidate(String[] mazeStringLines) {
		
		if (mazeStringLines.length < 2)
			logging.throwAndLogSevereException(logger, ErrorMessage.VERY_SMALL_MAZE.getValue());
		
		if (mazeStringLines.length > Integer.MAX_VALUE)
			logging.throwAndLogSevereException(logger, ErrorMessage.VERY_BIG_MAZE.getValue());
	}

}
