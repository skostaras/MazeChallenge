import java.io.File;
import java.util.List;

public class MainApp {

	static String fileName = "validSolvableMaze1.txt";
//	static String fileName = "validNonSolvableMaze1.txt";
//	static String fileName = "nonValid2EntryPoints.txt";
//	static String fileName = "emptyMazeFile.txt";
	
	public static void main(String[] args) {

		File mazeTxtFile = new File("resources/" + fileName);

		// Reads the txt file, parses it into a String Array, and then builds a 2d
		// String maze out of it. Also does some validations in between.
		Maze maze = new Maze(mazeTxtFile);

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze);

		maze.printExitPath(exitPath);
		// TODO why?
		maze.reset();
	}

}
