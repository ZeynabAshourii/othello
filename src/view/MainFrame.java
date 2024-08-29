package src.view;

import src.controller.FileMouseListener;
import src.resources.ConfigLoader;

import javax.swing.*;
import java.awt.*;
public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private FileMouseListener fileMouseListener;
    public MainFrame() {
        init();
    }
    private void init() {
        mainPanel = new MainPanel(this);
        int screenWidth, screenHeight, windowWidth, windowHeight;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        windowWidth = Integer.parseInt(ConfigLoader.getLines().get(0));
        windowHeight = Integer.parseInt(ConfigLoader.getLines().get(1));
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(windowWidth, windowHeight));
        this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
        this.setVisible(Boolean.parseBoolean((ConfigLoader.getLines().get(3))));
        this.setContentPane(mainPanel);

        fileMouseListener = new FileMouseListener();
        addMouseListener(fileMouseListener);

        setVisible(true);
    }
}
