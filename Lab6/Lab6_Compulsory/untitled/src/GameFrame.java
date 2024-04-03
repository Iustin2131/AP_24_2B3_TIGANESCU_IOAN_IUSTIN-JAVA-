import javax.swing.*;
import java.awt.*;

// the main frame of the  app
class GameFrame extends JFrame {
    ConfigPanel configPanel;
    DrawingPanel drawingPanel;
    ControlPanel controlPanel;

    public GameFrame() {
        super("Grid Game");

        configPanel = new ConfigPanel(this);
        drawingPanel = new DrawingPanel();
        controlPanel = new ControlPanel();

        add(configPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
    }

    public void createNewGame(int rows, int cols) {
        drawingPanel.initGrid(rows, cols);
    }
}