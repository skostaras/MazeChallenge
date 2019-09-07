import java.io.File;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File mazeTxtFile = new File("resources/validSolvableMaze1.txt");
		
		Maze maze = new Maze(mazeTxtFile);
		
		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		
		mazeSolverWithDFSAlgorithm.findExitPath(maze);
		

	}

}
