package org.example;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private DrawingPanel drawingPanel;
    private ConfigPanel configPanel;
    private ControlPanel controlPanel;

    public GameFrame() {
        setTitle("Game Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        drawingPanel = new DrawingPanel();
        controlPanel = new ControlPanel(drawingPanel);
        configPanel = new ConfigPanel(this);

        add(drawingPanel);
        add(controlPanel, BorderLayout.SOUTH);
        add(configPanel, BorderLayout.NORTH);

        pack();
        setLocationToLeftTop();
        setVisible(true);
    }

    private void setLocationToLeftTop() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        setLocation(0, 0);
    }

    public void startNewGame(int rows, int cols) {

        remove(drawingPanel);
        drawingPanel = new DrawingPanel();
        controlPanel.setDrawingPanel(drawingPanel);
        add(drawingPanel);
        pack();
        setSize(new Dimension(600, 600));
        drawingPanel.initGrid(rows, cols);
        add(drawingPanel);
        revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }
}
