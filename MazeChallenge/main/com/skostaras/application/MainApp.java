package com.skostaras.application;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.skostaras.entities.Coordinates;
import com.skostaras.services.Maze;
import com.skostaras.services.MazeSolverWithDFSAlgorithm;

public class MainApp {

//	 private static String fileName = "validSolvableMaze1.txt";
	 private static String fileName = "validNonSolvableMaze1.txt";
//	 private static String fileName = "nonValid2EntryPoints.txt";
//	 private static String fileName = "emptyMazeFile.txt";
//	 private static String fileName = "verySmallMaze.txt";

	public static void main(String[] args) {

		File mazeTxtFile = new File("resources/" + getFileName());

		// Reads the txt file, parses it into a String Array, and then builds a 2d
		// String maze out of it. Also does some validations in between.
		Maze maze = new Maze(mazeTxtFile);

		// Tries to find a maze solution (exitPath) through a DFS Algorithm.
		// A Depth First Search (DFS) traversing Algorithm is the edge based method and
		// works recursively where the vertices are explored along a path edge.
		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);

		maze.printExitPath(exitPath);
	}


	public static String getFileName() {
		return fileName;
	}

}
