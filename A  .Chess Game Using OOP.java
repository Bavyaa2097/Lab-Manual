import java.util.Scanner;

// Representing individual spots on the board
class Spot {
    private int x;
    private int y;
    private Piece piece;

    public Spot(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Piece getPiece() { return piece; }
    public void setPiece(Piece piece) { this.piece = piece; }
}

// Abstract Piece class
abstract class Piece {
    private boolean white;
    private boolean killed = false;

    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isWhite() { return white; }
    public boolean isKilled() { return killed; }
    public void setKilled(boolean killed) { this.killed = killed; }

    public abstract boolean canMove(Board board, Spot start, Spot end);
    public abstract String getSymbol();
}

// Pawn Piece
class Pawn extends Piece {
    public Pawn(boolean white) { super(white); }

    @Override
    public String getSymbol() {
        return isWhite() ? "P" : "p";
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        int direction = isWhite() ? 1 : -1;
        int dx = end.getX() - start.getX();
        int dy = end.getY() - start.getY();

        // Forward move
        if (dy == 0 && dx == direction && end.getPiece() == null) {
            return true;
        }
        // Diagonal capture
        if (Math.abs(dy) == 1 && dx == direction && end.getPiece() != null && end.getPiece().isWhite() != this.isWhite()) {
            return true;
        }
        return false;
    }
}

// Rook Piece
class Rook extends Piece {
    public Rook(boolean white) { super(white); }

    @Override
    public String getSymbol() {
        return isWhite() ? "R" : "r";
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        return (start.getX() == end.getX() || start.getY() == end.getY());
    }
}

// Knight Piece
class Knight extends Piece {
    public Knight(boolean white) { super(white); }

    @Override
    public String getSymbol() {
        return isWhite() ? "N" : "n";
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return dx * dy == 2;
    }
}

// Bishop Piece
class Bishop extends Piece {
    public Bishop(boolean white) { super(white); }

    @Override
    public String getSymbol() {
        return isWhite() ? "B" : "b";
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        return Math.abs(start.getX() - end.getX()) == Math.abs(start.getY() - end.getY());
    }
}

// Queen Piece
class Queen extends Piece {
    public Queen(boolean white) { super(white); }

    @Override
    public String getSymbol() {
        return isWhite() ? "Q" : "q";
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return (dx == dy) || (start.getX() == end.getX() || start.getY() == end.getY());
    }
}

// King Piece
class King extends Piece {
    public King(boolean white) { super(white); }

    @Override
    public String getSymbol() {
        return isWhite() ? "K" : "k";
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return dx <= 1 && dy <= 1;
    }
}

// Chess Board
class Board {
    Spot[][] boxes = new Spot[8][8];

    public Board() {
        this.resetBoard();
    }

    public Spot getBox(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) return null;
        return boxes[x][y];
    }

    public void resetBoard() {
        // Black pieces (rows 6 and 7)
        boxes[7][0] = new Spot(7, 0, new Rook(false));
        boxes[7][1] = new Spot(7, 1, new Knight(false));
        boxes[7][2] = new Spot(7, 2, new Bishop(false));
        boxes[7][3] = new Spot(7, 3, new Queen(false));
        boxes[7][4] = new Spot(7, 4, new King(false));
        boxes[7][5] = new Spot(7, 5, new Bishop(false));
        boxes[7][6] = new Spot(7, 6, new Knight(false));
        boxes[7][7] = new Spot(7, 7, new Rook(false));

        for (int j = 0; j < 8; j++) {
            boxes[6][j] = new Spot(6, j, new Pawn(false));
        }

        // Empty spots
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Spot(i, j, null);
            }
        }

        // White pieces (rows 0 and 1)
        for (int j = 0; j < 8; j++) {
            boxes[1][j] = new Spot(1, j, new Pawn(true));
        }

        boxes[0][0] = new Spot(0, 0, new Rook(true));
        boxes[0][1] = new Spot(0, 1, new Knight(true));
        boxes[0][2] = new Spot(0, 2, new Bishop(true));
        boxes[0][3] = new Spot(0, 3, new Queen(true));
        boxes[0][4] = new Spot(0, 4, new King(true));
        boxes[0][5] = new Spot(0, 5, new Bishop(true));
        boxes[0][6] = new Spot(0, 6, new Knight(true));
        boxes[0][7] = new Spot(0, 7, new Rook(true));
    }

    public void printBoard() {
        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                Piece p = boxes[i][j].getPiece();
                if (p == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(p.getSymbol() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h\n");
    }
}

// Player Class
class Player {
    private String name;
    private boolean whiteSide;

    public Player(String name, boolean whiteSide) {
        this.name = name;
        this.whiteSide = whiteSide;
    }

    public boolean isWhiteSide() { return whiteSide; }
}

// Move Class
class Move {
    private Spot start;
    private Spot end;
    private Piece pieceMoved;
    private Piece pieceKilled;

    public Move(Spot start, Spot end) {
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
    }

    public Spot getStart() { return start; }
    public Spot getEnd() { return end; }
    public Piece getPieceMoved() { return pieceMoved; }
}

// Main Game Controller
public class ChessGame {
    private Board board = new Board();
    private Player currentTurn;

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        game.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Player white = new Player("White", true);
        Player black = new Player("Black", false);
        currentTurn = white;

        System.out.println("----- Chess Game (Console Version) -----\n");
        System.out.println("Initial Board Setup:");
        board.printBoard();

        while (true) {
            System.out.println((currentTurn.isWhiteSide() ? "White's" : "Black's") + " turn.");
            System.out.print("Enter move (e.g., e2 e4): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) break;

            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Invalid input format! Use format like 'e2 e4'.\n");
                continue;
            }

            int[] startCoords = parseCoord(parts[0]);
            int[] endCoords = parseCoord(parts[1]);

            if (startCoords == null || endCoords == null) {
                System.out.println("Invalid coordinate values.\n");
                continue;
            }

            Spot start = board.getBox(startCoords[0], startCoords[1]);
            Spot end = board.getBox(endCoords[0], endCoords[1]);
            Piece sourcePiece = start.getPiece();

            if (sourcePiece == null || sourcePiece.isWhite() != currentTurn.isWhiteSide()) {
                System.out.println("Invalid selection! Choose your own piece.\n");
                continue;
            }

            if (!sourcePiece.canMove(board, start, end)) {
                System.out.println("Error: Pawn cannot move diagonally without capturing.\n");
                continue;
            }

            // Execute move
            end.setPiece(sourcePiece);
            start.setPiece(null);

            String pieceName = sourcePiece.getClass().getSimpleName();
            System.out.println(pieceName + " moved from " + parts[0] + " to " + parts[1] + ".\n");

            board.printBoard();

            // Toggle turn
            currentTurn = (currentTurn == white) ? black : white;
        }

        scanner.close();
    }

    private int[] parseCoord(String coord) {
        if (coord.length() != 2) return null;
        int col = coord.charAt(0) - 'a';
        int row = coord.charAt(1) - '1';
        if (col < 0 || col > 7 || row < 0 || row > 7) return null;
        return new int[]{row, col};
    }
}
