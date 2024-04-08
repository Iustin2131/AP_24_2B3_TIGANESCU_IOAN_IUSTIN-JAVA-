package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

class DrawingPanel extends JPanel {
    private int rows;
    private int cols;
    private int[][] matrixColor;
    private Node[][] matrixRoad;
    private int radius;
    private int cellWidth;
    private int cellHeight;
    private int currentPlayer = 1;
    private boolean roadsDrawn;
    private Stack<Point> nodeStack;
    private boolean playerLost;

    public DrawingPanel() {
        this.rows = 10;
        this.cols = 10;
        this.roadsDrawn = false;
        this.matrixColor = new int[rows][cols];
        this.matrixRoad = new Node[rows][cols];
        this.nodeStack = new Stack<>();
        this.playerLost = false;
        initMatrix();
        addMouseListener(new CustomMouseListener());
    }

    private void initMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrixColor[i][j] = 0;
                matrixRoad[i][j] = new Node();
            }
        }
    }

    public void initGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrixColor = new int[rows][cols];
        this.matrixRoad = new Node[rows][cols];
        this.roadsDrawn = false;
        initMatrix();
        addMouseListener(new CustomMouseListener());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        cellWidth = (width - 100) / cols;
        cellHeight = (height - 100) / rows;

        drawGrid(g);

        if (!roadsDrawn) {
            generateRoads();
            roadsDrawn = true;
        } else {
            drawRoads(g);
        }
    }

    private void drawGrid(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        radius = (width / 30) - 1;

        for (int i = 0; i < cols; i++) {
            int x = 50 + i * cellWidth;
            g.drawLine(x, 50, x, height / cols * (cols - 1) - 50);
        }

        for (int i = 0; i < rows; i++) {
            int y = 50 + i * cellHeight;
            g.drawLine(50, y, width / cols * (cols - 1) - 50, y);
        }

        for (int i = 0; i < rows; i++) {
            int y = 50 + i * cellHeight;

            for (int j = 0; j < cols; j++) {
                int x = 50 + j * cellWidth;

                if (matrixColor[i][j] == 0) {
                    g.drawOval(x - radius / 2, y - radius / 2, radius, radius);
                    g.setColor(Color.WHITE);
                    g.fillOval(x - radius / 2, y - radius / 2, radius, radius);
                } else {
                    g.setColor(matrixColor[i][j] == 1 ? Color.RED : Color.BLUE);
                    g.fillOval(x - radius / 2, y - radius / 2, radius, radius);
                }

                g.setColor(Color.BLACK);
                g.drawOval(x - radius / 2, y - radius / 2, radius, radius);
            }
        }
    }

    private void drawRoads(Graphics g) {
        for (int i = 0; i < rows ; i++) {
            int y = 50 + i * cellHeight;

            for (int j = 0; j < cols; j++) {
                int x = 50 + j * cellWidth;

                if (matrixRoad[i][j].hasNorthEdge()) {
                    g.setColor(Color.BLACK);

                    g.fillRect(x, y - cellHeight, 3, cellHeight);
                }
                if (matrixRoad[i][j].hasSouthEdge()) {
                    g.setColor(Color.BLACK);
                    // g.fillRect(x, y+ cellHeight, 3, cellHeight);
                }
                if (matrixRoad[i][j].hasEastEdge()) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x-1, y, cellWidth, 3);
                }
                if (matrixRoad[i][j].hasEastEdge()) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x+1, y, cellWidth, 3);
                }
            }
        }
        System.out.println("Matricea matrixRoad după generarea drumurilor:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                System.out.print("Node[" + i + "][" + j + "]: N=" + matrixRoad[i][j].hasNorthEdge() +
                        ", S=" + matrixRoad[i][j].hasSouthEdge() +
                        ", E=" + matrixRoad[i][j].hasEastEdge() +
                        ", W=" + matrixRoad[i][j].hasWestEdge() + " | ");
            }
            System.out.println();
        }
    }

    private void generateRoads() {
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                int randomNumber1 = (int) (Math.random() * 2);
                int randomNumber2 = (int) (Math.random() * 2);
                int randomNumber3 = (int) (Math.random() * 2);
                int randomNumber4 = (int) (Math.random() * 2);

                if (randomNumber1 == 1) {
                    matrixRoad[i][j].setNorthEdge(true);
                    matrixRoad[i-1][j].setSouthEdge(true);
                }
                if (randomNumber2 == 1) {
                    matrixRoad[i][j].setSouthEdge(true);
                    matrixRoad[i+1][j].setWestEdge(true);
                }

                if (randomNumber3 == 1) {
                    matrixRoad[i][j].setEastEdge(true);
                    matrixRoad[i][j +1].setSouthEdge(true);
                }
                if (randomNumber4 == 1) {
                    matrixRoad[i][j].setWestEdge(true);
                    matrixRoad[i][j-1].setEastEdge(true);
                }

            }
        }
    }



    private class CustomMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int circleCenterX = 50 + j * cellWidth;
                    int circleCenterY = 50 + i * cellHeight;
                    double distance = Math.sqrt(Math.pow(mouseX - circleCenterX, 2) + Math.pow(mouseY - circleCenterY, 2));

                    if (distance < radius / 2) {
                        handleCircleClick(i, j);
                        return;
                    }
                }
            }
        }
    }

    private void handleCircleClick(int row, int col) {
        if (matrixColor[row][col] == 0) {

            if (!nodeStack.isEmpty()) {
                Point lastNode = nodeStack.peek();

                // Verificăm conectivitatea între nodurile selectate
                if (( matrixRoad[row][col].hasWestEdge() && matrixRoad[lastNode.x][lastNode.y].hasEastEdge()) ||
                        ( matrixRoad[row][col].hasEastEdge() && matrixRoad[lastNode.x][lastNode.y].hasWestEdge()) ||
                        ( matrixRoad[row][col].hasSouthEdge() && matrixRoad[lastNode.x][lastNode.y].hasNorthEdge()) ||
                        ( matrixRoad[row][col].hasNorthEdge() && matrixRoad[lastNode.x][lastNode.y].hasSouthEdge())) {

                    matrixColor[row][col] = currentPlayer;
                    nodeStack.push(new Point(row, col));
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                    repaint();

                    System.out.println(lastNode.x + " " + row + " " + matrixRoad[row][col].hasEastEdge() + " " + matrixRoad[lastNode.x][lastNode.y].hasWestEdge());
                    System.out.println(lastNode.y + " " + col + " " + matrixRoad[row][col].hasSouthEdge() + " " + matrixRoad[lastNode.x][lastNode.y].hasNorthEdge());



                } else {
                    JOptionPane.showMessageDialog(this, "Jucătorul " + currentPlayer + " a pierdut!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    playerLost = true;

                    System.out.println(lastNode.x + " " + row + " " + matrixRoad[row][col].hasEastEdge() + " " + matrixRoad[lastNode.x][lastNode.y].hasWestEdge());
                    System.out.println(lastNode.y + " " + col + " " + matrixRoad[row][col].hasSouthEdge() + " " + matrixRoad[lastNode.x][lastNode.y].hasNorthEdge());

                }
            } else {
                matrixColor[row][col] = currentPlayer;
                nodeStack.push(new Point(row, col));
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
                repaint();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Cercul este deja colorat!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setMatrixRoad(Node[][] newMatrixRoad) {
        this.matrixRoad = newMatrixRoad;
        repaint(); // Repaint the panel to reflect the updated road matrix
    }

    public void setMatrixColor(int[][] newMatrixColor) {
        this.matrixColor = newMatrixColor;
        repaint(); // Repaint the panel to reflect the updated color matrix
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[][] getMatrixColor() {
        return matrixColor;
    }

    public Node[][] getMatrixRoad() {
        return matrixRoad;
    }

    public int getRadius() {
        return radius;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isRoadsDrawn() {
        return roadsDrawn;
    }

    public Stack<Point> getNodeStack() {
        return nodeStack;
    }

    public boolean isPlayerLost() {
        return playerLost;
    }
}
