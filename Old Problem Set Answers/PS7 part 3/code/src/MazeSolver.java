import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.PriorityQueue;
public class MazeSolver implements IMazeSolver {
	private static final int TRUE_WALL = Integer.MAX_VALUE;
	private static final int EMPTY_SPACE = 0;

	private static final List<Function<Room, Integer>> WALL_FUNCTIONS = Arrays.asList(
			Room::getNorthWall,
			Room::getEastWall,
			Room::getWestWall,
			Room::getSouthWall
	);
	private static final int[][] DELTAS = new int[][] {
			{ -1, 0 }, // North
			{ 0, 1 }, // East
			{ 0, -1 }, // West
			{ 1, 0 } // South
	};

	private Maze maze;

	private boolean solved;

	private PriorityQueue<Node> pq;

	private HashMap<String, Node> hashmap;

	private boolean[][] visited;

	private class Node implements Comparable<Node> {
		public int row;
		public int col;
		public int distance;

		public List<String> visited2;

		public Node(int row, int col, int distance) {
			this.row = row;
			this.col = col;
			this.distance = distance;
			this.visited2 = new ArrayList<>();
		}

		@Override
		public int compareTo(Node other) {
			if(this.distance < other.distance) {
				return -1;
			} else if (this.distance > other.distance) {
				return 1;
			} else {
				return 0;
			}
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
		this.maze =  maze;
		visited = new boolean[maze.getRows()][maze.getColumns()];
		solved = false;
	}

	@Override
	public Integer pathSearch(int startRow, int startCol, int endRow, int endCol) throws Exception {
		// TODO: Find minimum fear level.
		if (maze == null) {
			throw new Exception("Oh no! You cannot call me without initializing the maze!");
		}

		if (startRow < 0 || startCol < 0 || startRow >= maze.getRows() || startCol >= maze.getColumns() ||
				endRow < 0 || endCol < 0 || endRow >= maze.getRows() || endCol >= maze.getColumns()) {
			throw new IllegalArgumentException("Invalid start/end coordinate");
		}
		for (int i = 0; i < maze.getRows(); ++i) {
			for (int j = 0; j < maze.getColumns(); ++j) {
				this.visited[i][j] = false;
			}
		}
		this.visited[startRow][startCol] = true;
		pq = new PriorityQueue<>();
		hashmap = new HashMap<>();
		Node start = new Node(startRow, startCol, 0);

		pq.add(start);
		StringBuilder s = new StringBuilder();
		s.append(startRow).append(startCol);
		hashmap.put(s.toString(), start);

		while(!pq.isEmpty()) {
			Node min = pq.remove();
			System.out.println("Distance of min: " + min.distance + " from coordinates " + min.row + " , " + min.col);
			int edgeWeightNorth = maze.getRoom(min.row, min.col).getNorthWall();
			int edgeWeightSouth = maze.getRoom(min.row, min.col).getSouthWall();
			int edgeWeightEast =  maze.getRoom(min.row, min.col).getEastWall();
			int edgeWeightWest =  maze.getRoom(min.row, min.col).getWestWall();

			StringBuilder minCoordinates = new StringBuilder();
			minCoordinates.append(min.row).append(min.col);

			StringBuilder northCoordinates = new StringBuilder();
			northCoordinates.append(min.row-1).append(min.col);

			StringBuilder southCoordinates = new StringBuilder();
			southCoordinates.append(min.row+1).append(min.col);

			StringBuilder eastCoordinates = new StringBuilder();
			eastCoordinates.append(min.row).append(min.col + 1);

			StringBuilder westCoordinates = new StringBuilder();
			westCoordinates.append(min.row).append(min.col - 1);

			Node current = hashmap.get(minCoordinates.toString());

			if(edgeWeightNorth != Integer.MAX_VALUE && !current.visited2.contains(northCoordinates.toString())) {
				int rowCoordinates = min.row - 1;
				int colCoordinates = min.col;
				if (edgeWeightNorth == 0){
					edgeWeightNorth = 1;
				}
				StringBuilder coordinates = new StringBuilder();
				coordinates.append(rowCoordinates).append(colCoordinates);
				Node x = hashmap.get(coordinates.toString());
				if (x != null) {
					System.out.println("Distance: " + x.distance);
					if (x.distance > edgeWeightNorth + min.distance) {
						Node next = new Node(rowCoordinates, colCoordinates, edgeWeightNorth + min.distance);
						StringBuilder sb = new StringBuilder();
						sb.append(next.row).append(next.col);
						hashmap.putIfAbsent(sb.toString(), next); //changed
						hashmap.get(sb.toString()).distance = edgeWeightNorth + min.distance;
						pq.add(next);
						current.visited2.add(northCoordinates.toString());
					}
				} else {
					Node next = new Node(rowCoordinates, colCoordinates, edgeWeightNorth + min.distance);
					StringBuilder sb = new StringBuilder();
					sb.append(next.row).append(next.col);
					hashmap.putIfAbsent(sb.toString(), next);
					hashmap.get(sb.toString()).distance = edgeWeightNorth + min.distance;
					pq.add(next);;
					current.visited2.add(northCoordinates.toString());
				}
			}
			if(edgeWeightSouth != Integer.MAX_VALUE && !current.visited2.contains(southCoordinates.toString())) {
				int rowCoordinates = min.row + 1;
				int colCoordinates = min.col;
				if (edgeWeightSouth == 0){
					edgeWeightSouth = 1;
				}
				StringBuilder coordinates = new StringBuilder();
				coordinates.append(rowCoordinates).append(colCoordinates);
				Node x = hashmap.get(coordinates.toString());
				if (x != null) {
					if (x.distance > edgeWeightSouth + min.distance) {
						Node next = new Node(rowCoordinates, colCoordinates, edgeWeightSouth + min.distance);
						StringBuilder sb = new StringBuilder();
						sb.append(next.row).append(next.col);
						hashmap.putIfAbsent(sb.toString(), next);
						hashmap.get(sb.toString()).distance = edgeWeightSouth + min.distance;
						pq.add(next);
						current.visited2.add(southCoordinates.toString());
					}
				} else {
					Node next = new Node(rowCoordinates, colCoordinates, edgeWeightSouth + min.distance);
					StringBuilder sb = new StringBuilder();
					sb.append(next.row).append(next.col);
					hashmap.putIfAbsent(sb.toString(), next);
					hashmap.get(sb.toString()).distance = edgeWeightSouth + min.distance;
					pq.add(next);
					current.visited2.add(southCoordinates.toString());
				}
			}
			if(edgeWeightEast != Integer.MAX_VALUE && !current.visited2.contains(eastCoordinates.toString())) {
				int rowCoordinates = min.row;
				int colCoordinates = min.col + 1;
				if (edgeWeightEast == 0) {
					edgeWeightEast = 1;
				}
				StringBuilder coordinates = new StringBuilder();
				coordinates.append(rowCoordinates).append(colCoordinates);
				Node x = hashmap.get(coordinates.toString());
				if (x != null) {
					if (x.distance > edgeWeightEast + min.distance) {
						Node next = new Node(rowCoordinates, colCoordinates, edgeWeightEast + min.distance);
						StringBuilder sb = new StringBuilder();
						sb.append(next.row).append(next.col);
						hashmap.putIfAbsent(sb.toString(), next);
						hashmap.get(sb.toString()).distance = edgeWeightEast + min.distance;
						pq.add(next);
						current.visited2.add(eastCoordinates.toString());
					}
				} else {
					Node next = new Node(rowCoordinates, colCoordinates, edgeWeightEast + min.distance);
					StringBuilder sb = new StringBuilder();
					sb.append(next.row).append(next.col);
					hashmap.putIfAbsent(sb.toString(), next);
					hashmap.get(sb.toString()).distance = edgeWeightEast + min.distance;
					pq.add(next);
					current.visited2.add(eastCoordinates.toString());
				}
			}
			if(edgeWeightWest != Integer.MAX_VALUE && !current.visited2.contains(westCoordinates.toString())) {
				int rowCoordinates = min.row;
				int colCoordinates = min.col - 1;
				if (edgeWeightWest == 0){
					edgeWeightWest = 1;
				}
				StringBuilder coordinates = new StringBuilder();
				coordinates.append(rowCoordinates).append(colCoordinates);
				Node x = hashmap.get(coordinates.toString());
				if (x != null) {
					if (x.distance > edgeWeightWest + min.distance) {
						Node next = new Node(rowCoordinates, colCoordinates, edgeWeightWest + min.distance);
						StringBuilder sb = new StringBuilder();
						sb.append(next.row).append(next.col);
						hashmap.putIfAbsent(sb.toString(), next);
						hashmap.get(sb.toString()).distance = edgeWeightWest + min.distance;
						pq.add(next);
						current.visited2.add(westCoordinates.toString());
					}
				} else {
					Node next = new Node(rowCoordinates, colCoordinates, edgeWeightWest + min.distance);
					StringBuilder sb = new StringBuilder();
					sb.append(next.row).append(next.col);
					hashmap.putIfAbsent(sb.toString(), next);
					hashmap.get(sb.toString()).distance = edgeWeightWest + min.distance;
					pq.add(next);
					current.visited2.add(westCoordinates.toString());
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(endRow).append(endCol);
		if(hashmap.get(sb.toString()) == null) {
			return null;
		} else {
			return hashmap.get(sb.toString()).distance;
		}
	}

	@Override
	public Integer bonusSearch(int startRow, int startCol, int endRow, int endCol) throws Exception {
		// TODO: Find minimum fear level given new rules.
		return null;
	}

	@Override
	public Integer bonusSearch(int startRow, int startCol, int endRow, int endCol, int sRow, int sCol) throws Exception {
		// TODO: Find minimum fear level given new rules and special room.
		return null;
	}

	public static void main(String[] args) {
		try {
			Maze maze = Maze.readMaze("haunted-maze-sample.txt");
			IMazeSolver solver = new MazeSolver();
			solver.initialize(maze);

			System.out.println(solver.pathSearch(0, 1, 1, 4));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
