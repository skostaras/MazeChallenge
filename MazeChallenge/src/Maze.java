import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {

	MazeFileValidator mazeFileValidator = new MazeFileValidator();

	private static final String openRoad = "_";
	private static final String blockedRoad = "X";
	private static final String entryPoint = "S";
	private static final String exitPoint = "G";
	// why do we need a path? private static final String path = "P";

	private String[][] maze;
	private boolean[][] passedThroughMap;
	private Coordinates entryPointCoord;
	private Coordinates exitPointCoord;

	public Maze(File mazeFile) {

		String mazeString = "";

		try {
			mazeString = mazeFileReader(mazeFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mazeBuilder(mazeString);

	}

	private String mazeFileReader(File mazeFile) throws FileNotFoundException {

		String mazeString = "";

		Scanner scanner = new Scanner(mazeFile);

		while (scanner.hasNextLine()) {
			mazeString += scanner.nextLine() + "\n";
		}

		scanner.close();
		
		mazeFileValidator.emptyFileValidate(mazeString);

		return mazeString;

	}

	private void mazeBuilder(String mazeString) {

		// splits the mazeString lines (separated by 'new line' or 'return key') into an
		// Array of Strings
		String[] mazeStringLines = mazeString.split("[\r]?\n");
		
//		TODO check if this works, otherwise check the baeldung solution below
		mazeFileValidator.unevenLinesValidate(mazeStringLines);
		
//		baeldung alternate
//		for (int row = 0; row < maze.length; row++) {
//			if (mazeStringLines[row].length() != maze[0].length) {
//				throw new IllegalArgumentException("line " + (row + 1) + " wrong length (was "
//						+ mazeStringLines[row].length() + " but should be " + maze[0].length + ")");
//			}
//		}
		

		// creates a 2d String Array, with dimensions of number of Lines and Line length
		maze = new String[mazeStringLines.length][mazeStringLines[0].length()];

		// creates a 2d Boolean Array, with the same maze dimensions, which will
		// eventually depict a map of coordinates that the escape algorithm passed
		// through
		passedThroughMap = new boolean[mazeStringLines.length][mazeStringLines[0].length()];

		// builds the 2d String Array maze		
		for (int row = 0; row < maze.length; row++) {
			
			for (int column = 0; column < maze[0].length; column++) {
				
				char mazeCharSymbol = mazeStringLines[row].charAt(column);
				String mazeStringSymbol = Character.toString(mazeCharSymbol);

				switch (mazeStringSymbol) {

				case openRoad:
					maze[row][column] = openRoad;
					break;

				case blockedRoad:
					maze[row][column] = blockedRoad;
					break;

				case entryPoint:
					maze[row][column] = entryPoint;
					entryPointCoord = new Coordinates(row, column);
					break;

				case exitPoint:
					maze[row][column] = exitPoint;
					exitPointCoord = new Coordinates(row, column);
					break;

				default:
					throw new IllegalArgumentException(
							"The source Maze Text File contains at least an invalid character. Valid characters are '_', 'X', 'S' and 'G'.");
				}
				
		
				
				
			}
		}
		

	}

	public boolean pathIsBlocked(int x, int y) {

		if (maze[x][y].equals(blockedRoad) || x < 0 || y < 0 || x >= maze.length || y >= maze[0].length) {
			return true;
		}

		return false;

	}
	
	public boolean pointAlreadyVisited(int x, int y) {
		return passedThroughMap[x][y];
	}
	
	
	
	
	
	
}
