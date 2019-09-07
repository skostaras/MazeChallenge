import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File mazeTxtFile = new File("resources/validSolvableMaze1.txt");
		
		Maze maze = new Maze(mazeTxtFile);
		
		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		
		List<Coordinates> exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze);
		
		
		//TODO when empty list, return a message?
        maze.printExitPath(exitPath);
        maze.reset();
		

	}
	
			
	
	

	
	
	
	
	

}
