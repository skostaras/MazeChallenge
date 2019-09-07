import java.util.ArrayList;
import java.util.List;

public class MazeSolverWithDFSAlgorithm {
	
	
	// an array with every possible direction movement through a 2d space
	private static final int[][] movementDirections = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public List<Coordinates> findExitPath(Maze maze) {
		
		List<Coordinates> pathTaken = new ArrayList<>();
		
		
		
		
		
		
		
		return null;
		
		
	}
	
	
	
	private boolean mazeExplore(Maze maze, int row, int column, List<Coordinates> pathTaken) {
		
		if (maze.pathIsBlocked(row, column) || maze.pointAlreadyVisited(row, column)) {
			return false;
		}
		
		
		pathTaken.add(new Coordinates(row, column));
		maze.setVisited(row, column, true);
		if (maze.isExit(row, column)) {
			return true;
		}
		for (int[] direction : DIRECTIONS) {
			Coordinates coordinate = getNextCoordinate(row, column, direction[0], direction[1]);
			if (explore(maze, coordinate.getX(), coordinate.getY(), pathTaken)) {
				return true;
			}
		}
		pathTaken.remove(pathTaken.size() - 1);
		return false;
	}

}
