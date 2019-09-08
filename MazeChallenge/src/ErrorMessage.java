
public enum ErrorMessage {

	EMPTY_FILE("The source Maze Text File is empty."),
	MULTIPLE_ENTRY_POINTS(" contains more than one Entry Points (S)."),
	MULTIPLE_EXIT_POINTS(" contains more than one Exit Points (G)."),
	INVALID_CHARACTERS(
			"The source Maze Text File contains at least one invalid character. Valid characters are '_', 'X', 'S' and 'G' (letters are case sensitive)."),
	NON_SOLVABLE(" doesn't have a solution."), UNEVEN_ROWS(
			"The source Maze Text File contains rows with different number of columns. All rows should be equal in length."),
	NO_ENTRY_POINT(" doesn't have an Entry Point (S)"),
	NO_EXIT_POINT(" doesn't have an Exit Point (G)");

	private final String value;

	private ErrorMessage(String value) {

		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
