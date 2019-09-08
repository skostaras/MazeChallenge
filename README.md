# MazeChallenge
A program that outputs the route needed to escape a 2d maze (input).

MazeChallenge takes a **txt file as input (resources folder)**, validates it, parses it into 
a maze object and tries to find a way out of it. If successful, it outputs on console the
coordinates which are needed to escape the maze. 

The input txt should contain only the following: **"X" for a wall, "\_" for an open path,**
**"S" for the starting point and "G" for the ending point**. 

The **MainApp.class** contains the main method where the program can be started, as well as 
the String which needs to be changed for your custom Maze Files. 

MazeChallenge also contains several **tests** which, well.., test several, ehmm.. test cases 
where something could go wrong.

The included Algorithm is called **Depth First Search (DFS)** traversing Algorithm, which is 
an edge based method and works recursively where the vertices are explored along a path 
edge.


