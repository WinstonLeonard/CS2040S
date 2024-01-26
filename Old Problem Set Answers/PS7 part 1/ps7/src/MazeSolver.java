import java.util.HashMap;
import java.util.LinkedList;

public class MazeSolver implements IMazeSolver {
	private static final int NORTH = 0, SOUTH = 1, EAST = 2, WEST = 3;
	private static int[][] DELTAS = new int[][] {
		{ -1, 0 }, // North
		{ 1, 0 }, // South
		{ 0, 1 }, // East
		{ 0, -1 } // West
	};
	private Maze maze;
	private boolean solved = false;
	private boolean[][] visited;
	private int endRow, endCol;
	private HashMap<Integer, Integer> hashmap = new HashMap<>();

	public static class Node {
		public int row;
		public int col;
		public Node parent;
		Node(int row, int col, Node parent) {
			this.row = row;
			this.col= col;
			this.parent = parent;
		}
	}

	public MazeSolver() {
		// TODO: Initialize variables.
		solved = false;
		maze = null;
	}

	@Override
	public void initialize(Maze maze) {
		// TODO: Initialize the solver.
		this.maze = maze;
		visited = new boolean[maze.getRows()][maze.getColumns()];
		solved = false;
	}

	@Override
	public Integer pathSearch(int startRow, int startCol, int endRow, int endCol) throws Exception {
		// TODO: Find shortest path.
		hashmap = new HashMap<>();
		solved = false;
		if (maze == null) {
			throw new Exception("Oh no! You cannot call me without initializing the maze!");
		}

		if (startRow < 0 || startCol < 0 || startRow >= maze.getRows() || startCol >= maze.getColumns() ||
				endRow < 0 || endCol < 0 || endRow >= maze.getRows() || endCol >= maze.getColumns()) {
			throw new IllegalArgumentException("Invalid start/end coordinate");
		}

		// set all visited flag to false
		// before we begin our search
		for (int i = 0; i < maze.getRows(); ++i) {
			for (int j = 0; j < maze.getColumns(); ++j) {
				this.visited[i][j] = false;
				maze.getRoom(i, j).onPath = false;
			}
		}
		//this.endRow = endRow;
		//this.endCol = endCol;
		this.visited[startRow][startCol] = true;

		Node start = new Node(startRow, startCol, null);
		Node end = null;

		LinkedList<Node> frontier = new LinkedList<>();
		frontier.add(start);

		int steps = 0;
		while(!frontier.isEmpty()) {
			LinkedList<Node> nextFrontier = new LinkedList<>();
			steps = steps + 1;
			int counter = 0;
			for(Node v : frontier) {
				if(v.row == endRow && v.col == endCol) {
					solved = true;
					end = v;
				}
				if((canGo(v.row, v.col, NORTH)) && (visited[v.row-1][v.col] == false)) {
					//System.out.println("NODE " + v.row + " " +v.col + " can go NORTH");
					Node nextNode = new Node(v.row-1, v.col, v);
					nextFrontier.add(nextNode);
					visited[v.row-1][v.col] = true;
					counter++;
				}
				if((canGo(v.row, v.col, SOUTH)) && (visited[v.row+1][v.col] == false)) {
					//System.out.println("NODE " + v.row + " " +v.col + " can go SOUTH");
					Node nextNode = new Node(v.row + 1, v.col, v);
					nextFrontier.add(nextNode);
					visited[v.row + 1][v.col] = true;
					counter++;
				}
				if((canGo(v.row, v.col, EAST)) && (visited[v.row][v.col + 1] == false)) {
					//System.out.println("NODE " + v.row + " " +v.col + " can go EAST");
					Node nextNode = new Node(v.row, v.col +1, v);
					nextFrontier.add(nextNode);
					visited[v.row][v.col +1] = true;
					counter++;
				}
				if((canGo(v.row, v.col, WEST)) && (visited[v.row][v.col-1] == false)) {
					//System.out.println("NODE " + v.row + " " +v.col + " can go WEST");
					Node nextNode = new Node(v.row, v.col -1, v);
					nextFrontier.add(nextNode);
					visited[v.row][v.col -1] = true;
					counter++;
				}
				hashmap.put(steps, counter);
			}
			frontier = nextFrontier;
		}
		int counter2 = 0;
		Node currentNode = end;
		if(solved) {
			while(currentNode!=null) {
				counter2 = counter2 + 1;
				maze.getRoom(currentNode.row, currentNode.col).onPath = true;
				currentNode = currentNode.parent;
			}
			return counter2 -1;
		} else {
			return null;
		}
	}
	private boolean canGo(int row, int col, int dir) {
		// not needed since our maze has a surrounding block of wall
		// but Joe the Average Coder is a defensive coder!
		if (row + DELTAS[dir][0] < 0 || row + DELTAS[dir][0] >= maze.getRows()) return false;
		if (col + DELTAS[dir][1] < 0 || col + DELTAS[dir][1] >= maze.getColumns()) return false;

		switch (dir) {
			case NORTH:
				return !maze.getRoom(row, col).hasNorthWall();
			case SOUTH:
				return !maze.getRoom(row, col).hasSouthWall();
			case EAST:
				return !maze.getRoom(row, col).hasEastWall();
			case WEST:
				return !maze.getRoom(row, col).hasWestWall();
		}

		return false;
	}


	@Override
	public Integer numReachable(int k) throws Exception {
		// TODO: Find number of reachable rooms.
		if (k == 0) {
			return 1;
		} else {
			if(hashmap.get(k) == null) {
				return 0;
			}
			return hashmap.get(k);
		}
	}

	public static void main(String[] args) {
		// Do remember to remove any references to ImprovedMazePrinter before submitting
		// your code!
		try {
			Maze maze = Maze.readMaze("maze-sample2.0.txt");
			IMazeSolver solver = new MazeSolver();
			solver.initialize(maze);

			System.out.println(solver.pathSearch(0, 0, 4, 0));
			MazePrinter.printMaze(maze);
			ImprovedMazePrinter.printMaze(maze, 0,0);

			for (int i = 0; i <= 9; ++i) {
				System.out.println("Steps " + i + " Rooms: " + solver.numReachable(i));
			}
			System.out.println(solver.pathSearch(2, 0, 1, 1));
			MazePrinter.printMaze(maze);

			for (int i = 0; i <= 10; ++i) {
				System.out.println("Steps " + i + " Rooms: " + solver.numReachable(i));
			}



		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
