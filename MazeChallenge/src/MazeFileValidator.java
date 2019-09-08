
public class MazeFileValidator {

	public void emptyFileValidate(String mazeString) {

		if (mazeString == null || (mazeString = mazeString.trim()).length() == 0) {
			throw new IllegalArgumentException(ErrorMessage.EMPTY_FILE.getValue());
		}
	}

	public void unevenLinesValidate(String[] mazeStringLines) {

		int firstLineLength = mazeStringLines[0].length();

		for (int i = 0; i < mazeStringLines.length; i++) {
			if (mazeStringLines[i].length() != firstLineLength) {
				throw new IllegalArgumentException(ErrorMessage.UNEVEN_ROWS.getValue());
			}
		}
	}

}
