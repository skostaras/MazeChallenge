import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazeSolverWithDFSAlgorithm {

	private static final int[] NORTH = { -1, 0 };
	private static final int[] SOUTH = { 1, 0 };
	private static final int[] WEST = { 0, -1 };
	private static final int[] EAST = { 0, 1 };
	private static final int[][] movementDirections = { EAST, SOUTH, WEST, NORTH };

	public List<Coordinates> findExitPath(Maze maze) {

		List<Coordinates> exitPath = new ArrayList<>();

		if (exitPathFound(maze, maze.getEntryPointCoord().getX(), maze.getEntryPointCoord().getY(), exitPath)) {
			return exitPath;
		} else {
			System.out.println(MainApp.fileName + ErrorMessage.NON_SOLVABLE.getValue());
			return Collections.emptyList();
		}
	}

	// TODO change func name, why boolean?
	private boolean exitPathFound(Maze maze, int row, int column, List<Coordinates> exitPath) {

		if (maze.pathIsBlocked(row, column) || maze.pointAlreadyVisited(row, column)) {
			return false;
		}

		exitPath.add(new Coordinates(row, column));
		maze.setAlreadyVisitedMap(row, column, true);

		if (maze.reachedExitPoint(row, column)) {
			return true;
		}

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
