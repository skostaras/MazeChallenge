import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazeSolverWithDFSAlgorithm {
	
	
	// an array with every possible direction movement through a 2d space
	private static final int[][] movementDirections = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public List<Coordinates> findExitPath(Maze maze) {

		List<Coordinates> pathTaken = new ArrayList<>();

		if (foundExitPath(maze, maze.getEntryPointCoord().getX(), maze.getEntryPointCoord().getY(), pathTaken)) {
			return pathTaken;
		}
		//TODO when does it reach here?
		return Collections.emptyList();
	}

	// TODO change func name, why boolean?
	private boolean foundExitPath(Maze maze, int row, int column, List<Coordinates> pathTaken) {
		
		if (maze.pathIsBlocked(row, column) || maze.pointAlreadyVisited(row, column)) {
			return false;
		}
		
		
		pathTaken.add(new Coordinates(row, column));
		maze.setAlreadyVisitedMap(row, column, true);
		
		
		if (maze.reachedExitPoint(row, column)) {
			return true;
		}
		
		
		for (int[] movementDirection : movementDirections) {
			
			Coordinates coordinates = new Coordinates(row + movementDirection[0], column + movementDirection[1]);
			
//			Coordinates coordinates = getNextCoordinate(row, column, movementDirection[0], movementDirection[1]);

//			TODO check if this works and then refactor the above code
//			foundExitPath(maze, coordinates.getX(), coordinates.getY(), pathTaken);
			
	          if (foundExitPath(maze, coordinates.getX(), coordinates.getY(), pathTaken)) {
	                return true;
	            }
			
		}
		
		pathTaken.remove(pathTaken.size() - 1);
		return false;
	}
	
//    private Coordinates getNextCoordinate(int row, int col, int i, int j) {
//        return new Coordinates(row + i, col + j);
//    }

}
