import java.util.HashMap;
import java.util.LinkedList;

public class MazeSolverWithPower implements IMazeSolverWithPower {
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
	private boolean[][][] visited2;

	private boolean[][] visited3;
	private boolean[][][] visited4;
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

	public static class PowerNode {
		public int row;
		public int col;
		public int power;
		public PowerNode parent;

		PowerNode(int row, int col, int power, PowerNode parent) {
			this.row = row;
			this.col = col;
			this.power = power;
			this.parent = parent;
		}
	}

	public MazeSolverWithPower() {
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
					System.out.println("NODE " + v.row + " " +v.col + " can go NORTH");
					Node nextNode = new Node(v.row-1, v.col, v);
					nextFrontier.add(nextNode);
					visited[v.row-1][v.col] = true;
					counter++;
				}
				if((canGo(v.row, v.col, SOUTH)) && (visited[v.row+1][v.col] == false)) {
					System.out.println("NODE " + v.row + " " +v.col + " can go SOUTH");
					Node nextNode = new Node(v.row + 1, v.col, v);
					nextFrontier.add(nextNode);
					visited[v.row + 1][v.col] = true;
					counter++;
				}
				if((canGo(v.row, v.col, EAST)) && (visited[v.row][v.col + 1] == false)) {
					System.out.println("NODE " + v.row + " " +v.col + " can go EAST");
					Node nextNode = new Node(v.row, v.col +1, v);
					nextFrontier.add(nextNode);
					visited[v.row][v.col +1] = true;
					counter++;
				}
				if((canGo(v.row, v.col, WEST)) && (visited[v.row][v.col-1] == false)) {
					System.out.println("NODE " + v.row + " " +v.col + " can go WEST");
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

	private boolean canGoWithPower(int row, int col, int dir) {
		// not needed since our maze has a surrounding block of wall
		// but Joe the Average Coder is a defensive coder!
		if (row + DELTAS[dir][0] < 0 || row + DELTAS[dir][0] >= maze.getRows()) return false;
		if (col + DELTAS[dir][1] < 0 || col + DELTAS[dir][1] >= maze.getColumns()) return false;

		switch (dir) {
			case NORTH:
				return maze.getRoom(row, col).hasNorthWall();
			case SOUTH:
				return maze.getRoom(row, col).hasSouthWall();
			case EAST:
				return maze.getRoom(row, col).hasEastWall();
			case WEST:
				return maze.getRoom(row, col).hasWestWall();
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

	@Override
	public Integer pathSearch(int startRow, int startCol, int endRow,
							  int endCol, int superpowers) throws Exception {
		// TODO: Find shortest path with powers allowed.
		visited2 = new boolean[this.maze.getRows()][this.maze.getColumns()][superpowers+1];
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
				for(int k = 0; k < superpowers; k++) {
					this.visited2[i][j][k+1] = false;
					//maze.getRoom(i, j).onPath = false;
				}
			}
		}
		this.visited[startRow][startCol] = true;
		this.visited2[startRow][startCol][superpowers] = true;
		PowerNode start = new PowerNode(startRow, startCol, superpowers, null);
		PowerNode end = null;

		LinkedList<PowerNode> frontier = new LinkedList<>();
		frontier.add(start);

		int steps = 0;
		while(!frontier.isEmpty()) {
			//System.out.println(solved);
			/*if(solved == true) {
				break;
			}*/
			LinkedList<PowerNode> nextFrontier = new LinkedList<>();
			steps = steps + 1;
			int counter = 0;
			for(PowerNode v : frontier) {
				if(v.row == endRow && v.col == endCol) {
					solved = true;
					if(end == null) {
						end = v;
					}
				}

				if((v.power > 0) && canGoWithPower(v.row, v.col, NORTH) && (visited2[v.row-1][v.col][v.power-1] == false)) {
					//System.out.println("NODE " + v.row + " " +v.col + " can go NORTH with POWER: " + (v.power-1));
					PowerNode nextNode = new PowerNode(v.row-1, v.col, v.power-1, v);
					//System.out.println("POWERS LEFT: " + (v.power-1));
					nextFrontier.add(nextNode);
					visited2[v.row-1][v.col][v.power-1] = true;

					if(visited[v.row-1][v.col] == false) {
						counter++;
						//System.out.println("Counter incremented during Power North when at: " + v.row + " " +v.col);
						visited[v.row-1][v.col] = true;
					}
				}

				if((canGo(v.row, v.col, NORTH)) && (visited2[v.row-1][v.col][v.power] == false)) {
					//System.out.println("NODE " + v.row + " " +v.col + " can go NORTH");
					PowerNode nextNode = new PowerNode(v.row-1, v.col, v.power, v);
					nextFrontier.add(nextNode);
					visited2[v.row-1][v.col][v.power] = true;

					if(visited[v.row-1][v.col] == false) {
						counter++;
						//System.out.println("Counter incremented during North when at: " + v.row + " " +v.col);
						visited[v.row-1][v.col] = true;
					}
				}

				if((v.power > 0) && canGoWithPower(v.row, v.col, SOUTH) && (visited2[v.row+1][v.col][v.power-1]== false)) {
					//System.out.println("NODE " + v.row + " " +v.col + " can go SOUTH with POWER: " + (v.power-1));
					PowerNode nextNode = new PowerNode(v.row + 1, v.col, v.power-1, v);
					//System.out.println("POWERS LEFT: " + (v.power-1));
					nextFrontier.add(nextNode);
					visited2[v.row+1][v.col][v.power-1] = true;

					if(visited[v.row+1][v.col] == false) {
						counter++;
						//System.out.println("Counter incremented during Power South when at: " + v.row + " " +v.col);
						visited[v.row+1][v.col] = true;
					}
				}

				if((canGo(v.row, v.col, SOUTH)) && (visited2[v.row+1][v.col][v.power] == false)) {
					//System.out.println("NODE " + v.row + " " +v.col + " can go SOUTH");
					PowerNode nextNode = new PowerNode(v.row + 1, v.col, v.power,v);
					nextFrontier.add(nextNode);
					visited2[v.row + 1][v.col][v.power] = true;

					if(visited[v.row+1][v.col] == false) {
						counter++;
						//System.out.println("Counter incremented during South when at: " + v.row + " " +v.col);
						visited[v.row+1][v.col] = true;
					}
				}

				if((v.power > 0) && canGoWithPower(v.row, v.col, EAST) && (visited2[v.row][v.col+1][v.power-1] == false)) {
					//System.out.println("NODE " + v.row + " " +v.col + " can go EAST with POWER : " + (v.power-1));
					PowerNode nextNode = new PowerNode(v.row, v.col+1, v.power-1, v);
					//System.out.println("POWERS LEFT: " + (v.power-1));
					nextFrontier.add(nextNode);
					visited2[v.row][v.col+1][v.power-1] = true;

					if(visited[v.row][v.col+1] == false) {
						//System.out.println("Counter incremented during Power East when at: " + v.row + " " +v.col);
						counter++;
						visited[v.row][v.col+1] = true;
					}
				}

				if((canGo(v.row, v.col, EAST)) && (visited2[v.row][v.col + 1][v.power] == false)) {
					//System.out.println("NODE " + v.row + " " +v.col + " can go EAST");
					PowerNode nextNode = new PowerNode(v.row, v.col +1, v.power,v);
					nextFrontier.add(nextNode);
					visited2[v.row][v.col +1][v.power] = true;
					if(visited[v.row][v.col+1] == false) {
						//System.out.println("Counter incremented during East when at: " + v.row + " " +v.col);
						counter++;
						visited[v.row][v.col+1] = true;
					}
				}

				if((v.power > 0) && canGoWithPower(v.row, v.col, WEST) && (visited2[v.row][v.col-1][v.power-1] == false)) {
					//System.out.println("NODE " + v.row + " " +v.col + " can go WEST with POWER : " + (v.power-1));
					PowerNode nextNode = new PowerNode(v.row, v.col-1, v.power-1, v);
					//System.out.println("POWERS LEFT: " + (v.power-1));
					nextFrontier.add(nextNode);
					visited2[v.row][v.col -1][v.power-1] = true;
					if(visited[v.row][v.col-1] == false) {
						//System.out.println("Counter incremented during Power West when at: " + v.row + " " +v.col);
						counter++;
						visited[v.row][v.col-1] = true;
					}
				}

				if((canGo(v.row, v.col, WEST)) && (visited2[v.row][v.col-1][v.power] == false)) {
					//System.out.println("NODE " + v.row + " " +v.col + " can go WEST");
					PowerNode nextNode = new PowerNode(v.row, v.col -1, v.power, v);
					nextFrontier.add(nextNode);
					visited2[v.row][v.col -1][v.power] = true;

					if(visited[v.row][v.col-1] == false) {
						//System.out.println("Counter incremented during West when at: " + v.row + " " +v.col);
						counter++;
						visited[v.row][v.col-1] = true;
					}
				}
				hashmap.put(steps, counter);
			}
			frontier = nextFrontier;
		}

		int counter2 = 0;
		PowerNode currentNode = end;
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

	public static void main(String[] args) {
		try {
			Maze maze = Maze.readMaze("maze-harder.txt");
			IMazeSolverWithPower solver = new MazeSolverWithPower();
			solver.initialize(maze);

			System.out.println(solver.pathSearch(3, 0, 4, 3, 6));
			MazePrinter.printMaze(maze);
			//ImprovedMazePrinter.printMaze(maze, 3,0);
			for (int i = 0; i <= 25; ++i) {
				System.out.println("Steps " + i + " Rooms: " + solver.numReachable(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
