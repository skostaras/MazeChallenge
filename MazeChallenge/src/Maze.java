import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Maze {

	private static final String openRoad = "_";
	private static final String blockedRoad = "X";
	private static final String entryPoint = "S";
	private static final String exitPoint = "G";
	private String[][] maze;
	private boolean[][] alreadyVisitedMap;
	private Coordinates entryPointCoord;
	private Coordinates exitPointCoord;
	MazeFileValidator mazeFileValidator = new MazeFileValidator();

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

		mazeFileValidator.unevenLinesValidate(mazeStringLines);

		// creates a 2d String Array, with dimensions of number of Lines and Line length
		maze = new String[mazeStringLines.length][mazeStringLines[0].length()];

		// creates a 2d Boolean Array, with the same maze dimensions, which will
		// eventually depict a map of coordinates that the escape algorithm passed
		// through
		alreadyVisitedMap = new boolean[mazeStringLines.length][mazeStringLines[0].length()];

		// builds the 2d String Array maze
		for (int row = 0; row < getRows(); row++) {
			for (int column = 0; column < getColumns(); column++) {

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
							"The source Maze Text File contains at least one invalid character. Valid characters are '_', 'X', 'S' and 'G'.");
				}

			}
		}

	}

	public void printExitPath(List<Coordinates> exitPath) {

		StringBuilder result = new StringBuilder(getColumns() * (getRows() + 1));
		
		
		//TODO tweek this for when there is no solution
		result.append("(" + entryPointCoord.getX() + ":" + entryPointCoord.getY() + " (S)),");
		
		for (int i = 1; i < exitPath.size() - 1; i++) {
			result.append("(" + exitPath.get(i).getX() + ":" + exitPath.get(i).getY() + "),");
		}
		
		result.append("(" + exitPointCoord.getX() + ":" + exitPointCoord.getY() + " (G))");

		System.out.println(result.toString());

	}

	// TODO study
	public void reset() {
		for (int i = 0; i < alreadyVisitedMap.length; i++)
			Arrays.fill(alreadyVisitedMap[i], false);
	}

	public boolean reachedEntryPoint(int x, int y) {
		if (x == entryPointCoord.getX() && y == entryPointCoord.getY()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean reachedExitPoint(int x, int y) {
		if (x == exitPointCoord.getX() && y == exitPointCoord.getY()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean pathIsBlocked(int x, int y) {
		if (x < 0 || y < 0 || x >= getRows() || y >= getColumns()) {
			return true;
		}
		if (maze[x][y].equals(blockedRoad)) {
			return true;
		}
		return false;
	}

	public boolean pointAlreadyVisited(int x, int y) {
		return alreadyVisitedMap[x][y];
	}

	public void setAlreadyVisitedMap(int x, int y, boolean value) {
		alreadyVisitedMap[x][y] = value;
	}

	public Coordinates getEntryPointCoord() {
		return entryPointCoord;
	}

	public Coordinates getExitPointCoord() {
		return exitPointCoord;
	}

	public int getRows() {
		return maze.length;
	}

	public int getColumns() {
		return maze[0].length;
	}

}
