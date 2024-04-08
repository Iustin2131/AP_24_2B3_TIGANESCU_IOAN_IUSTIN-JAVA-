package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class ControlPanel extends JPanel {

    private DrawingPanel drawingPanel;
    private static final String SAVE_FILE_PATH = "C:\\Users\\IUSTIN\\Desktop\\JAVA\\Lab6_Homework\\screenshot.png";

    public ControlPanel(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;

        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");
        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(e -> System.exit(0));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGameState();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGameState();
            }
        });

        add(loadButton);
        add(saveButton);
        add(exitButton);
    }

    private void saveGameState() {

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_PATH))) {
            outputStream.writeObject(drawingPanel);
            JOptionPane.showMessageDialog(this, "Jocul a fost salvat cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la salvarea jocului: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }

        try {
            Robot robot = new Robot();
            Rectangle screenSize = drawingPanel.getBounds();
            BufferedImage screenshot = robot.createScreenCapture(screenSize);
            File screenshotFile = new File("C:\\Users\\IUSTIN\\Desktop\\JAVA\\Lab6_Homework\\screenshot.png");
            ImageIO.write(screenshot, "png", screenshotFile);
            JOptionPane.showMessageDialog(this, "Captura de ecran a fost salvată cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
        } catch (AWTException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la salvarea capturii de ecran: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void loadGameState() {
        try {

            BufferedImage screenshot = ImageIO.read(new File("C:\\Users\\IUSTIN\\Desktop\\JAVA\\Lab6_Homework\\screenshot.png"));

            Graphics g = drawingPanel.getGraphics();
            g.drawImage(screenshot, 0, 0, drawingPanel.getWidth(), drawingPanel.getHeight(), null);

            JOptionPane.showMessageDialog(this, "Jocul a fost încărcat cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la încărcarea capturii de ecran: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}
