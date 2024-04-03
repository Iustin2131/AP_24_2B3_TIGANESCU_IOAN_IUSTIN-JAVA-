import javax.swing.*;
import java.awt.*;

class DrawingPanel extends JPanel {
    private int rows;
    private int cols;

    public DrawingPanel() {
        this.rows = 10;
        this.cols = 10;
    }

    public void initGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / cols;
        int cellHeight = height / rows;

        for (int i = 0; i <= cols; i++) {
            g.drawLine(i * cellWidth, 0, i * cellWidth, height);
        }

        for (int i = 0; i <= rows; i++) {
            g.drawLine(0, i * cellHeight, width, i * cellHeight);
        }
    }
}