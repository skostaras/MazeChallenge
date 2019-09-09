package com.skostaras.services;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.skostaras.entities.Coordinates;

public class MazeSolverWithDFSAlgorithmTest {

	@Test
	public void findExitPathTest() {

		Maze maze = new Maze();
		String[][] mazeArray = new String[][] { { "_", "_", "X", "S", "_" }, { "X", "X", "_", "_", "X" },
				{ "X", "X", "G", "X", "X" } };
		maze.setMazeArray(mazeArray);

		maze.alreadyVisitedMap = new boolean[3][5];
		maze.setEntryPointCoord(new Coordinates(0, 3));
		maze.setExitPointCoord(new Coordinates(2, 2));

		MazeSolverWithDFSAlgorithm mazeSolverWithDFSAlgorithm = new MazeSolverWithDFSAlgorithm();
		List<Coordinates> exitPath = new ArrayList<>();
		List<Coordinates> actualExitPath = mazeSolverWithDFSAlgorithm.findExitPath(maze, exitPath);

		List<Coordinates> expectedExitPath = new ArrayList<>();
		expectedExitPath.add(new Coordinates(0, 3));
		expectedExitPath.add(new Coordinates(1, 3));
		expectedExitPath.add(new Coordinates(1, 2));
		expectedExitPath.add(new Coordinates(2, 2));

		assertEquals(expectedExitPath.size(), actualExitPath.size());
		for (int i = 0; i < expectedExitPath.size(); i++) {
			assertEquals(expectedExitPath.get(i).getX(), actualExitPath.get(i).getX());
			assertEquals(expectedExitPath.get(i).getY(), actualExitPath.get(i).getY());
		}

	}

}
