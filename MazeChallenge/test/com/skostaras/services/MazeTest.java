package com.skostaras.services;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Test;

public class MazeTest {

	private static String fileName = "validSolvableMaze1.txt";
	File mazeTxtFile = new File("resources/" + fileName);
	Maze maze = new Maze();

	@Test
	public void mazeFileReaderTest() {

		try {
			String mazeString = maze.mazeFileReader(mazeTxtFile);
			assertEquals("__XS_\nXX__X\nXXGXX\n", mazeString.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void mazeBuilderTest() {

		String mazeString = "__XS_\nXX__X\nXXGXX\n";
		String[][] expectedMaze = new String[][] { { "_", "_", "X", "S", "_" }, { "X", "X", "_", "_", "X" },
				{ "X", "X", "G", "X", "X" } };

		maze.mazeBuilder(mazeString);
		assertArrayEquals(expectedMaze, maze.maze);
	}

}
