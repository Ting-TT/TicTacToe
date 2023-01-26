import java.util.*;

// TODO: CPU randomly select a place to put its piece, can be improved by applying some algorithms.
public class TicTacToe {
    static List<Integer> userPositions = new ArrayList<>();
    static List<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        printGameBoard(gameBoard);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the position you want to put your piece (1-9):");
            int userPosition = scanner.nextInt();
            // Avoid user choose a position out of index or a taken position.
            while (userPosition < 1 || userPosition > 9 || userPositions.contains(userPosition) || cpuPositions.contains(userPosition)) {
                if (userPosition < 1 || userPosition > 9) {
                    System.out.println("Wrong position number! You can only enter a position number between 1 to 9. Please reenter:");
                } else {
                    System.out.println("Position taken! Enter a new position:");
                }
                userPosition = scanner.nextInt();
            }
            placePiece(gameBoard, userPosition, "user");
            String result = checkWinner();
            if (!checkWinner().isEmpty()) {
                System.out.println(result);
                break;
            }

            Random random = new Random();
            int cpuPosition = random.nextInt(9) + 1;
            // Avoid cpu choose a taken position.
            while (userPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
                cpuPosition = random.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPosition, "cpu");

            result = checkWinner();
            if (!checkWinner().isEmpty()) {
                System.out.println(result);
                break;
            }
        }
    }

    private static void printGameBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static void placePiece(char[][] board, int position, String player) {
        char symbol = ' ';
        if (player.equals("user")) {
            symbol = 'X';
            userPositions.add(position);
        } else if (player.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(position);
            System.out.println("CPU's turn:");
        }

        switch (position) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                break;
        }
        printGameBoard(board);
    }

    private static String checkWinner() {
        List<Integer> row1 = Arrays.asList(1, 2, 3);
        List<Integer> row2 = Arrays.asList(4, 5, 6);
        List<Integer> row3 = Arrays.asList(7, 8, 9);
        List<Integer> col1 = Arrays.asList(1, 4, 7);
        List<Integer> col2 = Arrays.asList(2, 5, 8);
        List<Integer> col3 = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(3, 5, 7);

        List<List<Integer>> winningConditions = new ArrayList<>();
        winningConditions.add(row1);
        winningConditions.add(row2);
        winningConditions.add(row3);
        winningConditions.add(col1);
        winningConditions.add(col2);
        winningConditions.add(col3);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for (List l : winningConditions) {
            if (userPositions.containsAll(l)) {
                return "Congrats! You win the game!";
            } else if (cpuPositions.containsAll(l)) {
                return "Sorry... You lose the game :(";
            }
        }
        if (userPositions.size() + cpuPositions.size() == 9) return "CAT!"; // A tie game.
        return ""; // Game hasn't finished yet.
    }
}
