package src.model;

import src.view.MainPanel;

import java.awt.*;
public class Piece {
    private final int x, y;
    private boolean color;
    private int windowWidth;
    private int windowHeight;
    private Board mainBoard;
    private int rowCount;
    private int colCount;
    private int pieceSize;

    public Piece(int x, int y, boolean color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    private void init(){
        windowWidth = MainPanel.getInstance().getWidth() - 5;
        windowHeight = MainPanel.getInstance().getHeight() - 20;
        mainBoard = MainPanel.getInstance().getHandler().getMainBoard();
        rowCount = mainBoard.getRowCount();
        colCount = mainBoard.getColCount();
        pieceSize = Math.min(windowWidth / colCount, windowHeight / rowCount) / 2;
    }
    public void paintComponent(Graphics g) {
        init();
        int x = getX() * (windowWidth / colCount) + (windowWidth / colCount) / 2 - pieceSize / 2;
        int y = getY() * (windowHeight / rowCount) + (windowHeight / rowCount) / 2 - pieceSize / 2;
        g.setColor(color ? Color.WHITE : Color.BLACK);
        g.drawOval(x, y, pieceSize, pieceSize);
        g.fillOval(x, y, pieceSize, pieceSize);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isColor() {
        return color;
    }
    public void setColor(boolean color) {
        this.color = color;
    }
}
