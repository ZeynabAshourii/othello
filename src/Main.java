package src;

import src.resources.ConfigLoader;
import src.view.MainFrame;
import src.view.MainPanel;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConfigLoader.load();
        MainFrame mainFrame = new MainFrame();
        MainPanel.update();
    }
}
