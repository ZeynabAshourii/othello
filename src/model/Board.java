package src.model;

import java.util.ArrayList;
public class Board {
    private final int rowCount , colCount;
    private Piece[][] board;
    private ArrayList<Piece> pieces;
    public Board(int rowCount, int colCount, ArrayList<Piece> pieces) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.pieces = pieces;
        this.board = getCellState();
    }
    public Piece[][] getCellState() {
        Piece[][] board = new Piece[rowCount][colCount];
        for (Piece piece : this.pieces) {
            board[piece.getX()][piece.getY()] = piece;
        }
        return board;
    }
    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
}
