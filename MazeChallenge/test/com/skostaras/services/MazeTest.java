package com.skostaras.services;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.skostaras.entities.Coordinates;

public class MazeTest {

	private static String fileName = "validSolvableMaze1.txt";
	File mazeTxtFile = new File("resources/" + fileName);
	Maze maze = new Maze();
	String mazeString = "__XS_\nXX__X\nXXGXX\n";

	@Test
	public void mazeFileReaderTest() {
		try {
			String actualMazeString = maze.mazeFileReader(mazeTxtFile);
			assertEquals(mazeString, actualMazeString.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void mazeBuilderTest() {
		String[][] expectedMazeArray = new String[][] { { "_", "_", "X", "S", "_" }, { "X", "X", "_", "_", "X" },
				{ "X", "X", "G", "X", "X" } };

		maze.mazeBuilder(mazeString);
		assertArrayEquals(expectedMazeArray, maze.mazeArray);
	}

	@Test
	public void getExitPathTest() {
		maze.mazeBuilder(mazeString);

		List<Coordinates> exitPath = new ArrayList<>();
		exitPath.add(new Coordinates(0, 3));
		exitPath.add(new Coordinates(1, 3));
		exitPath.add(new Coordinates(1, 2));
		exitPath.add(new Coordinates(2, 2));

		String expectedResult = "(0:3 (S)),(1:3),(1:2),(2:2 (G))";
		StringBuilder actualResult = maze.getExitPath(exitPath);

		assertEquals(expectedResult, actualResult.toString());
	}

}
