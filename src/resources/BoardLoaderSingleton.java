package src.resources;

import src.model.Board;
import src.model.Piece;

import java.util.ArrayList;
public class BoardLoaderSingleton {
    private static final BoardLoaderSingleton instance = new BoardLoaderSingleton();
    private Board mainBoard;
    private int x;
    private int y;
    private ArrayList<Piece> pieces = new ArrayList<>();
    private int row;
    private int col;
    private BoardLoaderSingleton(){
        init();
        row = Integer.parseInt(ConfigLoader.getLines().get(25));
        col = Integer.parseInt(ConfigLoader.getLines().get(26));
        mainBoard = new Board(row , col , pieces);
    }

    private void init(){
        pieces.clear();
        x = Integer.parseInt(ConfigLoader.getLines().get(13));
        y = Integer.parseInt(ConfigLoader.getLines().get(14));
        boolean color = Boolean.parseBoolean(ConfigLoader.getLines().get(15));
        pieces.add(new Piece(x, y, color));
        x = Integer.parseInt(ConfigLoader.getLines().get(16));
        y = Integer.parseInt(ConfigLoader.getLines().get(17));
        color = Boolean.parseBoolean(ConfigLoader.getLines().get(18));
        pieces.add(new Piece(x, y, color));
        x = Integer.parseInt(ConfigLoader.getLines().get(19));
        y = Integer.parseInt(ConfigLoader.getLines().get(20));
        color = Boolean.parseBoolean(ConfigLoader.getLines().get(21));
        pieces.add(new Piece(x, y, color));
        x = Integer.parseInt(ConfigLoader.getLines().get(22));
        y = Integer.parseInt(ConfigLoader.getLines().get(23));
        color = Boolean.parseBoolean(ConfigLoader.getLines().get(24));
        pieces.add(new Piece(x, y, color));
    }
    public Board getMainBoard() {
        return mainBoard;
    }
    public static void refresh(){
        instance.init();
        instance.mainBoard = new Board(instance.row, instance.col, instance.pieces);
    }
    public static BoardLoaderSingleton getInstance(){
        return instance;
    }
}
