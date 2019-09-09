package com.skostaras.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.skostaras.application.MainApp;
import com.skostaras.entities.Coordinates;
import com.skostaras.misc.ErrorMessage;
import com.skostaras.misc.Logging;

public class Maze {

	private static final String openRoad = "_";
	private static final String blockedRoad = "X";
	private static final String entryPoint = "S";
	private static final String exitPoint = "G";
	public String[][] mazeArray;
	private String mazeString = "";
	public boolean[][] alreadyVisitedMap;
	private Coordinates entryPointCoord;
	private Coordinates exitPointCoord;
	MazeFileValidator mazeFileValidator = new MazeFileValidator();
	Logging logging = new Logging();
	final static Logger logger = Logger.getLogger(Maze.class.getName());

	public Maze() {
		super();
	}

	public Maze(File mazeFile) {

		try {
			mazeString = mazeFileReader(mazeFile);
		} catch (FileNotFoundException e) {
			logging.logAndPrint(logger, Level.SEVERE, e.getMessage());
			System.exit(0);
		}

		mazeBuilder(mazeString);
	}

	public String mazeFileReader(File mazeFile) throws FileNotFoundException {

		Scanner scanner = new Scanner(mazeFile);

		while (scanner.hasNextLine()) {
			mazeString += scanner.nextLine() + "\n";
		}
		scanner.close();

		mazeFileValidator.emptyFileValidate(mazeString);

		return mazeString;
	}

	public void mazeBuilder(String mazeString) {

		// splits the mazeString into an String Array of lines (separated by 'new line'
		// or 'return key')
		String[] mazeStringLines = mazeString.split("[\r]?\n");

		// checks if every row (line) has the same number of columns
		mazeFileValidator.unevenLinesValidate(mazeStringLines);

		// checks if the source maze size is between 2 and Integer Max value
		mazeFileValidator.mazeSizeValidate(mazeStringLines);

		// creates a 2d String Array, with dimensions of number of Lines and Line length
		mazeArray = new String[mazeStringLines.length][mazeStringLines[0].length()];

		// creates a 2d Boolean Array, with the source text file dimensions, which will
		// eventually depict a map of coordinates that the escape algorithm has passed
		// through
		alreadyVisitedMap = new boolean[mazeStringLines.length][mazeStringLines[0].length()];

		boolean entryPointFound = false;
		boolean exitPointFound = false;

		// Builds the 2d String Array maze
		// Also does some validations in between
		for (int row = 0; row < getRows(); row++) {
			for (int column = 0; column < getColumns(); column++) {

				char mazeCharSymbol = mazeStringLines[row].charAt(column);
				String mazeStringSymbol = Character.toString(mazeCharSymbol);

				switch (mazeStringSymbol) {
				case openRoad:
					mazeArray[row][column] = openRoad;
					break;
				case blockedRoad:
					mazeArray[row][column] = blockedRoad;
					break;
				case entryPoint:
					if (entryPointFound) {
						logging.throwAndLogSevereException(logger,
								MainApp.getFileName() + ErrorMessage.MULTIPLE_ENTRY_POINTS.getValue());
					}
					mazeArray[row][column] = entryPoint;
					entryPointCoord = new Coordinates(row, column);
					entryPointFound = true;
					break;
				case exitPoint:
					if (exitPointFound) {
						logging.throwAndLogSevereException(logger,
								MainApp.getFileName() + ErrorMessage.MULTIPLE_EXIT_POINTS.getValue());
					}
					mazeArray[row][column] = exitPoint;
					exitPointCoord = new Coordinates(row, column);
					exitPointFound = true;
					break;
				default:
					logging.throwAndLogSevereException(logger, ErrorMessage.INVALID_CHARACTERS.getValue());
				}

			}
		}

		if (!entryPointFound) {
			logging.throwAndLogSevereException(logger, MainApp.getFileName() + ErrorMessage.NO_ENTRY_POINT.getValue());
		}
		if (!exitPointFound) {
			logging.throwAndLogSevereException(logger, MainApp.getFileName() + ErrorMessage.NO_EXIT_POINT.getValue());
		}

	}

	public StringBuilder getExitPath(List<Coordinates> exitPath) {
		
		StringBuilder result = new StringBuilder(getColumns() * (getRows() + 1));

		// conditional is here to avoid printing just the entry and exit points, when
		// path is empty
		if (!exitPath.isEmpty()) {

			// begins with the entry point
			result.append("(" + entryPointCoord.getX() + ":" + entryPointCoord.getY() + " (S)),");

			// iterates through the exitPath, excluding the first and the last elements
			for (int i = 1; i < exitPath.size() - 1; i++) {
				result.append("(" + exitPath.get(i).getX() + ":" + exitPath.get(i).getY() + "),");
			}

			// ends with the exit point
			result.append("(" + exitPointCoord.getX() + ":" + exitPointCoord.getY() + " (G))");
			
			return result;
		}
		return result;
	}
	
	public void printExitPath(String exitPath) {
		logging.logAndPrint(logger, Level.INFO, exitPath);
	}

	public boolean reachedEntryPoint(int x, int y) {
		if (x == entryPointCoord.getX() && y == entryPointCoord.getY()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean reachedExitPoint(int x, int y) {
		if (x == exitPointCoord.getX() && y == exitPointCoord.getY()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean pathIsBlocked(int x, int y) {
		if (x < 0 || y < 0 || x >= getRows() || y >= getColumns()) {
			return true;
		}
		if (mazeArray[x][y].equals(blockedRoad)) {
			return true;
		}
		return false;
	}

	public boolean pointAlreadyVisited(int x, int y) {
		return alreadyVisitedMap[x][y];
	}

	public void setAlreadyVisitedMap(int x, int y, boolean value) {
		alreadyVisitedMap[x][y] = value;
	}

	public Coordinates getEntryPointCoord() {
		return entryPointCoord;
	}

	public Coordinates getExitPointCoord() {
		return exitPointCoord;
	}
	
	public void setEntryPointCoord(Coordinates entryPointCoord) {
		this.entryPointCoord = entryPointCoord;
	}

	public void setExitPointCoord(Coordinates exitPointCoord) {
		this.exitPointCoord = exitPointCoord;
	}

	public int getRows() {
		return mazeArray.length;
	}

	public int getColumns() {
		return mazeArray[0].length;
	}

	public void setMazeArray(String[][] mazeArray) {
		this.mazeArray = mazeArray;
	}
}
