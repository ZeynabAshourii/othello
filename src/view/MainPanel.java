package src.view;

import src.controller.Handler;
import src.model.Piece;
import src.resources.BoardLoaderSingleton;
import src.resources.ConfigLoader;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private MainFrame mainFrame;
    private static MainPanel instance;
    private Handler handler;
    private Color color;
    public MainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        instance = this;
        init();
    }
    private void init(){
        int windowWidth = Integer.parseInt(ConfigLoader.getLines().get(4));
        int windowHeight = Integer.parseInt(ConfigLoader.getLines().get(5));
        this.setSize(new Dimension(windowWidth, windowHeight));
        setLayout(null);
        int red = Integer.parseInt(ConfigLoader.getLines().get(6));
        int green = Integer.parseInt(ConfigLoader.getLines().get(7));
        int blue = Integer.parseInt(ConfigLoader.getLines().get(8));
        color = new Color(red , green , blue);
        handler = new Handler();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(color);

        g.setColor(Color.WHITE);
        if(handler.isGameOver()){
            String gameEndStr = handler.winner();
            g.drawString(gameEndStr, 0, handler.getWindowHeight() + 15);
        }
        else {
            g.drawString("Player turn: " + (handler.isPlayerTurn() ? "white" : "black"), 0, handler.getWindowHeight() + 15);
        }

        for (int i = 0; i <= handler.getRowCount(); i++) {
            g.drawLine(0, i * handler.getWindowHeight() / handler.getRowCount(), handler.getWindowWidth() , i * handler.getWindowHeight() / handler.getRowCount());
            g.drawLine(i * handler.getWindowWidth() / handler.getColCount(), 0, i * handler.getWindowWidth() / handler.getRowCount(), handler.getWindowHeight());
        }

        for (Piece piece : handler.getMainBoard().getPieces()) {
            piece.paintComponent(g);
        }
    }
    public static void update() throws InterruptedException {

        while (true) {
            Thread.sleep(Long.parseLong(ConfigLoader.getLines().get(2)));
            instance.repaint();
            instance.revalidate();
            if (instance.handler.isGameOver()) {
                JOptionPane.showMessageDialog(instance, ConfigLoader.getLines().get(12));
                BoardLoaderSingleton.refresh();
                instance.mainFrame.dispose();
                MainFrame mainFrame1 = new MainFrame();
                MainPanel.update();
            }
        }
    }
    public static MainPanel getInstance() {
        return instance;
    }
    public Handler getHandler() {
        return handler;
    }
}
