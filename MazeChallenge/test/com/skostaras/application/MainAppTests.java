package com.skostaras.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.skostaras.constants.ErrorMessage;
import com.skostaras.entities.Coordinates;
import com.skostaras.services.Maze;
import com.skostaras.services.MazeSolverWithDFSAlgorithm;

public class MainAppTests {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void successSmallMazeSolutionTest() {

		String fileName = "validSolvableMaze1.txt";

		File mazeTxtFile = new File("resources/" + fileName);
		Maze maze = new Maze(mazeTxtFile);

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);

		String result = maze.getExitPath(exitPath).toString();

		assertEquals("(0:3 (S)),(1:3),(1:2),(2:2 (G))", result);
	}

	@Test
	public void successMediumMazeSolutionTest() {

		String fileName = "validSolvableMaze2Medium.txt";

		File mazeTxtFile = new File("resources/" + fileName);
		Maze maze = new Maze(mazeTxtFile);

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);

		String result = maze.getExitPath(exitPath).toString();

		assertEquals(
				"(0:3 (S)),(1:3),(1:2),(2:2),(3:2),(3:1),(4:1),(4:0),(5:0),(6:0),(6:1),(7:1),(7:2),(7:3),(8:3),(8:4),(9:4),(9:5),(10:5 (G))",
				result);
	}

	@Test
	public void emptyMazeTest() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(ErrorMessage.EMPTY_FILE.getValue());

		String fileName = "emptyMazeFile.txt";

		File mazeTxtFile = new File("resources/" + fileName);
		Maze maze = new Maze(mazeTxtFile);

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);
	}

	@Test
	public void multipleEntriesTest() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(ErrorMessage.MULTIPLE_ENTRY_POINTS.getValue());

		String fileName = "multipleEntriesMaze.txt";

		File mazeTxtFile = new File("resources/" + fileName);
		Maze maze = new Maze(mazeTxtFile);

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);
	}

	@Test
	public void multipleExitsTest() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(ErrorMessage.MULTIPLE_EXIT_POINTS.getValue());

		String fileName = "multipleExitsMaze.txt";

		File mazeTxtFile = new File("resources/" + fileName);
		Maze maze = new Maze(mazeTxtFile);

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);
	}

	@Test
	public void invalidCharsTest() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(ErrorMessage.INVALID_CHARACTERS.getValue());

		String fileName = "invalidCharsMaze.txt";

		File mazeTxtFile = new File("resources/" + fileName);
		Maze maze = new Maze(mazeTxtFile);

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);
	}

	@Test
	// TODO check this
	public void nonSolvableTest() {

//		expectedException.expect(IllegalArgumentException.class);
//		expectedException.expectMessage(ErrorMessage.NON_SOLVABLE.getValue());
		
		String fileName = "validNonSolvableMaze1.txt";

		File mazeTxtFile = new File("resources/" + fileName);
		Maze maze = new Maze(mazeTxtFile);

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		
		exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);
		
		
	}

	@Test
	public void unevenRowsTest() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(ErrorMessage.UNEVEN_ROWS.getValue());

		String fileName = "unevenRowsMaze.txt";

		File mazeTxtFile = new File("resources/" + fileName);
		Maze maze = new Maze(mazeTxtFile);

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);
	}

	@Test
	public void noEntryPointTest() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(ErrorMessage.NO_ENTRY_POINT.getValue());

		String fileName = "noEntryMaze.txt";

		File mazeTxtFile = new File("resources/" + fileName);
		Maze maze = new Maze(mazeTxtFile);

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);
	}

	@Test
	public void noExitPointTest() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(ErrorMessage.NO_EXIT_POINT.getValue());

		String fileName = "noExitMaze.txt";

		File mazeTxtFile = new File("resources/" + fileName);
		Maze maze = new Maze(mazeTxtFile);

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);
	}

	@Test
	public void emptyRowTest() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(ErrorMessage.EMPTY_ROW.getValue());

		String fileName = "emptyRowMaze.txt";

		File mazeTxtFile = new File("resources/" + fileName);
		Maze maze = new Maze(mazeTxtFile);

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);
	}

	@Test
	public void verySmallMazeTest() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(ErrorMessage.VERY_SMALL_MAZE.getValue());

		String fileName = "verySmallMaze.txt";

		File mazeTxtFile = new File("resources/" + fileName);
		Maze maze = new Maze(mazeTxtFile);

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		exitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);
	}

}
