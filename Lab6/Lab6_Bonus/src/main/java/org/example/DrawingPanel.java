package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    private Random random = new Random();

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
        for (int i = 0; i < rows; i++) {
            int y = 50 + i * cellHeight;

            for (int j = 0; j < cols; j++) {
                int x = 50 + j * cellWidth;

                if (matrixRoad[i][j].hasNorthEdge()) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y - cellHeight, 3, cellHeight);
                }
                if (matrixRoad[i][j].hasSouthEdge()) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, cellWidth, 3);
                }
                if (matrixRoad[i][j].hasEastEdge()) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, cellWidth, 3);
                }
                if (matrixRoad[i][j].hasWestEdge()) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x - cellWidth, y, cellWidth, 3);
                }
            }
        }
    }

    private void generateRoads() {
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                int randomNumber1 = random.nextInt(2);
                int randomNumber2 = random.nextInt(2);
                int randomNumber3 = random.nextInt(2);
                int randomNumber4 = random.nextInt(2);

                if (randomNumber1 == 1) {
                    matrixRoad[i][j].setNorthEdge(true);
                    matrixRoad[i - 1][j].setSouthEdge(true);
                }
                if (randomNumber2 == 1) {
                    matrixRoad[i][j].setSouthEdge(true);
                    matrixRoad[i + 1][j].setNorthEdge(true);
                }
                if (randomNumber3 == 1) {
                    matrixRoad[i][j].setEastEdge(true);
                    matrixRoad[i][j + 1].setWestEdge(true);
                }
                if (randomNumber4 == 1) {
                    matrixRoad[i][j].setWestEdge(true);
                    matrixRoad[i][j - 1].setEastEdge(true);
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
        if (matrixColor[row][col] == 0 && !playerLost) {
            Point lastNode = nodeStack.isEmpty() ? null : nodeStack.peek();

            if (lastNode != null) {

                if (( matrixRoad[row][col].hasWestEdge() && matrixRoad[lastNode.x][lastNode.y].hasEastEdge()) ||
                        ( matrixRoad[row][col].hasEastEdge() && matrixRoad[lastNode.x][lastNode.y].hasWestEdge()) ||
                        ( matrixRoad[row][col].hasSouthEdge() && matrixRoad[lastNode.x][lastNode.y].hasNorthEdge()) ||
                        ( matrixRoad[row][col].hasNorthEdge() && matrixRoad[lastNode.x][lastNode.y].hasSouthEdge())) {
                    matrixColor[row][col] = currentPlayer;
                    nodeStack.push(new Point(row, col));
                    repaint();

                    if (isGameOver()) {
                        determineWinner();
                    } else {
                        currentPlayer = (currentPlayer == 1) ? 2 : 1;
                        performAIMove();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Mutarea nu este validă! Alegeți o celulă vecină conectată. Jucătorul " + currentPlayer + " a pierdut.", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            } else {

                matrixColor[row][col] = currentPlayer;
                nodeStack.push(new Point(row, col));
                repaint();

                if (isGameOver()) {
                    determineWinner();
                } else {
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                    performAIMove();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Cercul este deja colorat sau jocul s-a încheiat!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performAIMove() {
        if (!playerLost) {
            Point lastNode = nodeStack.peek();

            List<Point> possibleMoves = getPossibleMoves(lastNode.x, lastNode.y);

            if (!possibleMoves.isEmpty()) {
                Point selectedMove = possibleMoves.get(random.nextInt(possibleMoves.size()));

                if (( matrixRoad[selectedMove.x][selectedMove.y].hasWestEdge() && matrixRoad[lastNode.x][lastNode.y].hasEastEdge()) ||
                        ( matrixRoad[selectedMove.x][selectedMove.y].hasEastEdge() && matrixRoad[lastNode.x][lastNode.y].hasWestEdge()) ||
                        ( matrixRoad[selectedMove.x][selectedMove.y].hasSouthEdge() && matrixRoad[lastNode.x][lastNode.y].hasNorthEdge()) ||
                        ( matrixRoad[selectedMove.x][selectedMove.y].hasNorthEdge() && matrixRoad[lastNode.x][lastNode.y].hasSouthEdge())) {

                    matrixColor[selectedMove.x][selectedMove.y] = currentPlayer;
                    nodeStack.push(new Point(selectedMove.x, selectedMove.y));
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                    repaint();
                }
            } else {

                playerLost = true;
                JOptionPane.showMessageDialog(this, "AI-ul nu mai poate face mutări!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                determineWinner();
            }
        }
    }


    private boolean isGameOver() {

        Point lastNode = nodeStack.peek();
        List<Point> possibleMoves = getPossibleMoves(lastNode.x, lastNode.y);
        return possibleMoves.isEmpty();
    }

    private void determineWinner() {
        int countPlayer1 = 0;
        int countPlayer2 = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrixColor[i][j] == 1) {
                    countPlayer1++;
                } else if (matrixColor[i][j] == 2) {
                    countPlayer2++;
                }
            }
        }

        if (countPlayer1 > countPlayer2) {
            JOptionPane.showMessageDialog(this, "Jucătorul 1 a câștigat cu " + countPlayer1 + " cercuri colorate!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } else if (countPlayer2 > countPlayer1) {
            JOptionPane.showMessageDialog(this, "Jucătorul 2 a câștigat cu " + countPlayer2 + " cercuri colorate!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Egalitate! Amândoi jucătorii au " + countPlayer1 + " cercuri colorate!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private List<Point> getPossibleMoves(int row, int col) {
        List<Point> moves = new ArrayList<>();

        if (isValidMove(row - 1, col) && matrixColor[row - 1][col] == 0) {
            moves.add(new Point(row - 1, col)); // North
        }
        if (isValidMove(row + 1, col) && matrixColor[row + 1][col] == 0) {
            moves.add(new Point(row + 1, col)); // South
        }
        if (isValidMove(row, col - 1) && matrixColor[row][col - 1] == 0) {
            moves.add(new Point(row, col - 1)); // West
        }
        if (isValidMove(row, col + 1) && matrixColor[row][col + 1] == 0) {
            moves.add(new Point(row, col + 1)); // East
        }

        return moves;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

}
