package org.example;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private DrawingPanel drawingPanel;

    public GameFrame() {
        setTitle("Game Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        drawingPanel = new DrawingPanel();
        ControlPanel controlPanel = new ControlPanel(drawingPanel);

        add(drawingPanel);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationToLeftTop();
        setVisible(true);
    }

    private void setLocationToLeftTop() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        setLocation(0, 0); // Setează poziția în colțul stânga sus
    }

    public void startNewGame(int rows, int cols) {

        remove(drawingPanel);
        drawingPanel = new DrawingPanel();
        drawingPanel.initGrid(rows, cols);
        add(drawingPanel);
        revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }
}
