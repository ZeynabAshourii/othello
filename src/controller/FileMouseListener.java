package src.controller;

import src.view.MainPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FileMouseListener implements MouseListener {
    private Handler handler = MainPanel.getInstance().getHandler();

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX() / (handler.getWindowWidth() / handler.getRowCount());
        int y = e.getY() / (handler.getWindowHeight() / handler.getColCount());
        handler.update(x , y-1);
    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
