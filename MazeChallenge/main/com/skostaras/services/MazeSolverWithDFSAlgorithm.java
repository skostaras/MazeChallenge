package com.skostaras.services;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.skostaras.application.Logging;
import com.skostaras.application.MainApp;
import com.skostaras.constants.ErrorMessage;
import com.skostaras.entities.Coordinates;

public class MazeSolverWithDFSAlgorithm {

	private static final int[] NORTH = { -1, 0 };
	private static final int[] SOUTH = { 1, 0 };
	private static final int[] WEST = { 0, -1 };
	private static final int[] EAST = { 0, 1 };
	private static final int[][] movementDirections = { EAST, SOUTH, WEST, NORTH };
	Logging logging = new Logging();
	final static Logger logger = Logger.getLogger(MazeSolverWithDFSAlgorithm.class.getName());

	public List<Coordinates> findExitPath(Maze maze, List<Coordinates> exitPath) {

		if (exitPathFound(maze, maze.getEntryPointCoord().getX(), maze.getEntryPointCoord().getY(), exitPath)) {
			return exitPath;
		} else {
//			logger.setUseParentHandlers(false);
			logging.logAndPrint(logger, Level.INFO, MainApp.getFileName() + ErrorMessage.NON_SOLVABLE.getValue());
			System.exit(0);
			return Collections.emptyList();
		}
	}

	private boolean exitPathFound(Maze maze, int row, int column, List<Coordinates> exitPath) {

		// the path can be blocked when there is an inner or an outer wall 
		if (maze.pathIsBlocked(row, column) || maze.pointAlreadyVisited(row, column)) {
			return false;
		}

		// Initially, the entry points coords are being added.
		// After having passed the rest of the conditionals, the right coords are being
		// added into the exitPath
		exitPath.add(new Coordinates(row, column));
		maze.setAlreadyVisitedMap(row, column, true);

		if (maze.reachedExitPoint(row, column)) {
			return true;
		}
		
		//the iterations takes place recursively in exitPathFound()
		for (int[] movementDirection : movementDirections) {
			Coordinates coordinates = new Coordinates(row + movementDirection[0], column + movementDirection[1]);
			if (exitPathFound(maze, coordinates.getX(), coordinates.getY(), exitPath)) {
				return true;
			}
		}
		exitPath.remove(exitPath.size() - 1);
		
		return false;
	}

}
