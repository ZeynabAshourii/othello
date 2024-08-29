package src.controller;

import src.model.Board;
import src.model.Piece;
import src.resources.BoardLoaderSingleton;
import src.resources.ConfigLoader;
import src.view.MainPanel;

import java.util.ArrayList;
public class Handler {
    private MainPanel mainPanel;
    private int windowWidth;
    private int windowHeight;
    private int rowCount;
    private int colCount;
    private Piece[][] board;
    private Board mainBoard;
    private int[] dx = {1, 1, -1, -1, 0, 0, 1, -1};
    private int[] dy = {-1, 1, 1, -1, 1, -1, 0, 0};
    private boolean playerTurn;
    public Handler(){
        init();
    }
    private void init(){
        mainBoard = BoardLoaderSingleton.getInstance().getMainBoard();
        playerTurn = false;
        mainPanel = MainPanel.getInstance();
        windowWidth = mainPanel.getWidth() - 5;
        windowHeight = mainPanel.getHeight() - 20;
        rowCount = mainBoard.getRowCount();
        colCount = mainBoard.getColCount();
        board = mainBoard.getCellState();
    }
    public void update(int x , int y){
        if(!isInsidePanel(x , y) || !isCellEmpty( x , y)){
            return;
        }
        boolean doesFlip = false;
        mainLoop:
        for (int k = 0; k < dx.length; k++) {
            ArrayList<Piece> flipRow = new ArrayList<>();
            for (int i = 1; i <= Math.max(rowCount, colCount); i++) {
                int nx = x + i * dx[k], ny = y + i * dy[k];
                if (nx >= colCount || nx < 0 || ny >= rowCount || ny < 0 || board[nx][ny] == null)
                    continue mainLoop;
                if (board[nx][ny].isColor() == playerTurn)
                    break;
                flipRow.add(board[nx][ny]);
            }
            for (Piece piece : flipRow) {
                doesFlip = true;
                piece.setColor(playerTurn);
            }
        }

        if (doesFlip) {
            mainBoard.addPiece(new Piece(x , y , playerTurn));
            board = mainBoard.getCellState();
        }
        if (!doesFlip)
            return;
        playerTurn = !playerTurn;
        if (!playerHasMove(playerTurn))
            playerTurn = !playerTurn;
    }

    public boolean playerHasMove(boolean player) {
        for (int i = 0; i < rowCount; i++)
            for (int j = 0; j < colCount; j++) {
                if (!isCellEmpty(i , j)) {
                    continue;
                }

                boolean doesFlip = false;

                mainLoop:
                for (int k = 0; k < dx.length; k++) {
                    ArrayList<Piece> flipRow = new ArrayList<>();
                    for (int l = 1; l <= Math.max(rowCount, colCount); l++) {
                        int nx = i + l * dx[k], ny = j + l * dy[k];
                        if (nx >= colCount || nx < 0 || ny >= rowCount || ny < 0 || board[nx][ny] == null)
                            continue mainLoop;
                        if (board[nx][ny].isColor() == player)
                            break;
                        flipRow.add(board[nx][ny]);
                    }
                    doesFlip = !flipRow.isEmpty();
                }

                if (doesFlip)
                    return true;
            }
        return false;
    }

    public boolean isInsidePanel(int x , int y){
        return x < rowCount && y < rowCount;
    }
    public boolean isCellEmpty(int x , int y){
        if(x >= 0 && y >= 0) {
            return board[x][y] == null;
        }
        else{
            return false;
        }
    }
    public boolean isGameOver(){
        return !(this.playerHasMove(false) || this.playerHasMove(true));
    }
    public int score(boolean player){
        int score = 0;
        for (Piece piece : mainBoard.getPieces()) {
            if(player) {
                score += piece.isColor() ? 0 : 1;
            }
            else {
                score += piece.isColor() ? 1 : 0;
            }
        }
        return score;
    }
    public String winner(){
        int p1Score = score(true);
        int p2Score = score(false);
        String gameEndStr = "";
        if (p1Score > p2Score)
            gameEndStr = ConfigLoader.getLines().get(9);
        else if (p2Score > p1Score)
            gameEndStr = ConfigLoader.getLines().get(10);
        else
            gameEndStr = ConfigLoader.getLines().get(11);
        return gameEndStr;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public Board getMainBoard() {
        return mainBoard;
    }

    public int getRowCount() {
        return rowCount;
    }
    public int getColCount() {
        return colCount;
    }
    public int getWindowHeight() {
        return windowHeight;
    }
    public int getWindowWidth() {
        return windowWidth;
    }
}
