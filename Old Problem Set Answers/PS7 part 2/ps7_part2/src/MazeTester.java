import static org.junit.Assert.*;
import org.junit.Test;

public class MazeTester {
    IMazeSolver solver = new MazeSolverWithPower();

    @Test
    public void testPathSearch1() {
        try {
            Maze maze = Maze.readMaze("maze-sample.txt");
            solver.initialize(maze);
            int expected = 9;
            int answer = solver.pathSearch(0, 0, 4, 3);
            ImprovedMazePrinter.printMaze(maze, 0, 0);
            assertEquals(expected, answer);

        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch2() {
        try {
            Maze maze = Maze.readMaze("maze-dense.txt");
            solver.initialize(maze);
            Integer expected = null;
            Integer answer = solver.pathSearch(0, 0, 3, 3);
            ImprovedMazePrinter.printMaze(maze, 0, 0);
            assertEquals(expected, answer);

        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch3() {
        try {
            Maze maze = Maze.readMaze("maze-dense.txt");
            solver.initialize(maze);
            Integer expected = 0;
            Integer answer = solver.pathSearch(0, 0, 0, 0);
            ImprovedMazePrinter.printMaze(maze, 0, 0);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch4() {
        try {
            Maze maze = Maze.readMaze("maze-horizontal.txt");
            solver.initialize(maze);
            Integer expected = 1;
            Integer answer = solver.pathSearch(0, 4, 0, 5);
            ImprovedMazePrinter.printMaze(maze, 0, 4);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch5() {
        try {
            Maze maze = Maze.readMaze("maze-horizontal.txt");
            solver.initialize(maze);
            Integer expected = 1;
            Integer answer = solver.pathSearch(0, 1, 0, 0);
            ImprovedMazePrinter.printMaze(maze, 0, 1);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch6() {
        try {
            Maze maze = Maze.readMaze("maze-sample.txt");
            solver.initialize(maze);
            int expected = 5;
            int answer = solver.pathSearch(4, 3, 3, 3);
            ImprovedMazePrinter.printMaze(maze, 4, 3);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch7() {
        try {
            Maze maze = Maze.readMaze("maze-empty.txt");
            solver.initialize(maze);
            solver.pathSearch(0, 0, 2, 3);
            ImprovedMazePrinter.printMaze(maze, 0, 0);
            int expected = 6;
            int answer = solver.pathSearch(0, 0, 3, 3);
            ImprovedMazePrinter.printMaze(maze, 0, 0);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    IMazeSolverWithPower solverWithPower = new MazeSolverWithPower();

    @Test
    public void testPathSearch8() {
        try {
            Maze maze = Maze.readMaze("maze-dense.txt");
            solverWithPower.initialize(maze);
            int expected = 1;
            int answer = solverWithPower.pathSearch(0, 0, 0, 1, 1);
            ImprovedMazePrinter.printMaze(maze, 0, 0);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch9() {
        try {
            Maze maze = Maze.readMaze("maze-sample.txt");
            solverWithPower.initialize(maze);
            int expected = 5;
            int answer = solverWithPower.pathSearch(0, 0, 4, 1, 2);
            ImprovedMazePrinter.printMaze(maze, 0, 0);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch10() {
        try {
            Maze maze = Maze.readMaze("maze-dense.txt");
            solverWithPower.initialize(maze);
            int expected = 6;
            int answer = solverWithPower.pathSearch(0, 0, 3, 3, 6);
            ImprovedMazePrinter.printMaze(maze, 0, 0);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch11() {
        try {
            Maze maze = Maze.readMaze("maze-hard.txt");
            solverWithPower.initialize(maze);
            int expected = 20;
            int answer = solverWithPower.pathSearch(0, 0, 8, 4, 1);
            ImprovedMazePrinter.printMaze(maze, 0, 0);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch12() {
        try {
            Maze maze = Maze.readMaze("maze-harder.txt");
            solverWithPower.initialize(maze);
            int expected = 42;
            int answer = solverWithPower.pathSearch(3, 0, 4, 3, 0);
            ImprovedMazePrinter.printMaze(maze, 3, 0);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch13() {
        try {
            Maze maze = Maze.readMaze("maze-harder.txt");
            solverWithPower.initialize(maze);
            int expected = 10;
            int answer = solverWithPower.pathSearch(3, 0, 4, 3, 1);
            ImprovedMazePrinter.printMaze(maze, 3, 0);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch14() {
        try {
            Maze maze = Maze.readMaze("maze-harder.txt");
            solverWithPower.initialize(maze);
            int expected = 6;
            int answer = solverWithPower.pathSearch(3, 0, 4, 3, 2);
            ImprovedMazePrinter.printMaze(maze, 3, 0);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch15() {
        try {
            Maze maze = Maze.readMaze("maze-harder.txt");
            solverWithPower.initialize(maze);
            int expected = 4;
            int answer = solverWithPower.pathSearch(3, 0, 4, 3, 3);
            ImprovedMazePrinter.printMaze(maze, 3, 0);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testNumReachable1() {
        try {
            Maze maze = Maze.readMaze("maze-sample.txt");
            solver.initialize(maze);
            solver.pathSearch(0, 0, 4, 3);
            ImprovedMazePrinter.printMaze(maze, 0, 0);
            int[] expected = new int[] {1, 1, 2, 1, 2, 4, 5, 5, 3, 1};
            for (int i = 0; i < 10; i++) {
                int answer = solver.numReachable(i);
                System.out.println("Steps " + i + " Rooms: " + answer);
                assertEquals(expected[i], answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testNumReachable2() {
        try {
            Maze maze = Maze.readMaze("maze-sample.txt");
            solver.initialize(maze);
            solver.pathSearch(0, 0, 4, 3);
            ImprovedMazePrinter.printMaze(maze, 0, 0);
            int[] expected = new int[] {1, 1, 2, 1, 2, 4, 5, 5, 3, 1, 0, 0};
            for (int i = 0; i < expected.length; i++) {
                int answer = solver.numReachable(i);
                System.out.println("Steps " + i + " Rooms: " + answer);
                assertEquals(expected[i], answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testNumReachable3() {
        try {
            Maze maze = Maze.readMaze("maze-dense.txt");
            solverWithPower.initialize(maze);
            solverWithPower.pathSearch(0, 0, 3, 3, 6);
            ImprovedMazePrinter.printMaze(maze, 0, 0);
            int[] expected = new int[] {1, 2, 3, 4, 3, 2, 1, 0, 0, 0, 0, 0};
            for (int i = 0; i < expected.length; i++) {
                int answer = solverWithPower.numReachable(i);
                System.out.println("Steps " + i + " Rooms: " + answer);
                assertEquals(expected[i], answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testNumReachable4() {
        try {
            Maze maze = Maze.readMaze("maze-harder.txt");
            solverWithPower.initialize(maze);
            solverWithPower.pathSearch(3, 0, 4, 3, 6);
            ImprovedMazePrinter.printMaze(maze, 3, 0);
            int[] expected = new int[] {1, 3, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 2, 1};
            for (int i = 0; i < expected.length; i++) {
                int answer = solverWithPower.numReachable(i);
                System.out.println("Steps " + i + " Rooms: " + answer);
                assertEquals(expected[i], answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testNumReachable5() {
        try {
            Maze maze = Maze.readMaze("maze-harder.txt");
            solverWithPower.initialize(maze);
            solverWithPower.pathSearch(3, 0, 4, 3, 2);
            ImprovedMazePrinter.printMaze(maze, 3, 0);
            int[] expected = new int[] {1, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 1};
            for (int i = 0; i < expected.length; i++) {
                int answer = solverWithPower.numReachable(i);
                System.out.println("Steps " + i + " Rooms: " + answer);
                assertEquals(expected[i], answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }
}