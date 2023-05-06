public class GameOfLife {
    private int rows;
    private int columns;
    private boolean[][] grid;
    private boolean[][] nextGeneration;

    public GameOfLife(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new boolean[rows][columns];
        this.nextGeneration = new boolean[rows][columns];
    }

    public void initializeRandomly() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = Math.random() < 0.5;
            }
        }
    }

    public void updateGeneration() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int liveNeighbors = countLiveNeighbors(i, j);

                if (grid[i][j]) {
                    // Cell is alive
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        nextGeneration[i][j] = false; // Cell dies due to underpopulation or overpopulation
                    } else {
                        nextGeneration[i][j] = true; // Cell survives to the next generation
                    }
                } else {
                    // Cell is dead
                    if (liveNeighbors == 3) {
                        nextGeneration[i][j] = true; // Cell becomes alive due to reproduction
                    } else {
                        nextGeneration[i][j] = false; // Cell remains dead
                    }
                }
            }
        }

        // Update the grid with the next generation
        for (int i = 0; i < rows; i++) {
            System.arraycopy(nextGeneration[i], 0, grid[i], 0, columns);
        }
    }

    private int countLiveNeighbors(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue; // Skip the current cell
                }

                int neighborRow = row + i;
                int neighborCol = col + j;

                // Check if the neighbor cell is within the grid bounds
                if (neighborRow >= 0 && neighborRow < rows && neighborCol >= 0 && neighborCol < columns) {
                    if (grid[neighborRow][neighborCol]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public void displayGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i][j] ? "X " : "- ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int rows = 10;
        int columns = 10;
        GameOfLife game = new GameOfLife(rows, columns);
        game.initializeRandomly();

        for (int generation = 1; generation <= 10; generation++) {
            System.out.println("Generation: " + generation);
            game.displayGrid();
            game.updateGeneration();
        }
    }
}
