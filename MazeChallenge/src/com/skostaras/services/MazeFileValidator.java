package com.skostaras.services;
import com.skostaras.constants.ErrorMessage;

public class MazeFileValidator {

	public void emptyFileValidate(String mazeString) {

		if (mazeString == null || (mazeString = mazeString.trim()).length() == 0) {
			throw new IllegalArgumentException(ErrorMessage.EMPTY_FILE.getValue());
		}
	}

	public void unevenLinesValidate(String[] mazeStringLines) {

		int firstLineLength = mazeStringLines[0].length();

		for (int i = 0; i < mazeStringLines.length; i++) {
			
			if (mazeStringLines[i].equals(""))
				throw new IllegalArgumentException(ErrorMessage.EMPTY_ROW.getValue() + (i+1) + "." );
			
			if (mazeStringLines[i].length() != firstLineLength) 
				throw new IllegalArgumentException(ErrorMessage.UNEVEN_ROWS.getValue());
			
		}
	}
	
	public void mazeSizeValidate(String[] mazeStringLines) {
		
		if (mazeStringLines.length < 2)
			throw new IllegalArgumentException(ErrorMessage.VERY_SMALL_MAZE.getValue());
		
		if (mazeStringLines.length > Integer.MAX_VALUE)
			throw new IllegalArgumentException(ErrorMessage.VERY_BIG_MAZE.getValue());
		
	}

}
