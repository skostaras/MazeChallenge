import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Maze {

	MazeFileValidator mazeFileValidator = new MazeFileValidator();

	private static final String openRoad = "_";
	private static final String blockedRoad = "X";
	private static final String entryPoint = "S";
	private static final String exitPoint = "G";
	private static final String escapePath = "P";

	private String[][] maze;
	private boolean[][] alreadyVisitedMap;
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
							"The source Maze Text File contains at least an invalid character. Valid characters are '_', 'X', 'S' and 'G'.");
				}

			}
		}

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
	
	public void printExitPath(List<Coordinates> exitPath) {
		
		StringBuilder result = new StringBuilder(getColumns() * (getRows() + 1));

		result.append("(" + entryPointCoord.getX() + ":" + entryPointCoord.getY() + " (S)),");
		
		 for (int i = 0; i < exitPath.size() - 1; i++) { 
			 
			 if(i != 0) {
				 
				 result.append("(" + exitPath.get(i).getX() + ":" + exitPath.get(i).getY() + "),");
				 
			 }
			 
			 
	      }   		

		

		
		result.append("(" + exitPointCoord.getX() + ":" +  exitPointCoord.getY() + " (G))");

		
		System.out.println(result.toString());
		
		
//		String[][] tempMaze = Arrays.stream(maze).map(String[]::clone).toArray(String[][]::new);

//		for (Coordinates coordinate : exitPath) {
//			if (reachedEntryPoint(coordinate.getX(), coordinate.getY())
//					|| reachedExitPoint(coordinate.getX(), coordinate.getY())) {
//				continue;
//			}
//			//TODO change this 4 below, to whatever...
//			tempMaze[coordinate.getX()][coordinate.getY()] = escapePath;
//		}
		
//		System.out.println(tempMaze);
		
//		System.out.println(toString(tempMaze));
	}
	
	//TODO study
	public String toString(String[][] maze) {

		StringBuilder result = new StringBuilder(getColumns() * (getRows() + 1));

		result.append("(" + entryPointCoord.getX() + ":" + entryPointCoord.getY() + " (S)),");

		for (int row = 0; row < getRows(); row++) {
			for (int column = 0; column < getColumns(); column++) {
				

				if (maze[row][column].equals(escapePath)) {
					result.append("(" + row + ":" + column + "),");
					
					
//				} else if (maze[row][column].equals(blockedRoad)) {
//					result.append('#');
//				} else if (maze[row][column].equals(entryPoint)) {
//					result.append('S');
//				} else if (maze[row][column].equals(exitPoint)) {
//					result.append('E');
//				} else {
//					result.append('.');
				}
			}
		
//			result.append('\n');
		}
		
		result.append("(" + exitPointCoord.getX() + ":" +  exitPointCoord.getY() + " (G))");

		return result.toString();
		
	}
	
	//TODO study
    public void reset() {
        for (int i = 0; i < alreadyVisitedMap.length; i++)
            Arrays.fill(alreadyVisitedMap[i], false);
    }

}
