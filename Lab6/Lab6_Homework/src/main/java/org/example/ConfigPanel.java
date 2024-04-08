package org.example;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    private final JTextField rowsField;
    private final JTextField colsField;
    private final GameFrame gameFrame;

    public ConfigPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;

        add(new JLabel("Rows:"));
        rowsField = new JTextField(5);
        add(rowsField);

        add(new JLabel("Cols:"));
        colsField = new JTextField(5);
        add(colsField);

        JButton createGameButton = new JButton("Create");
        createGameButton.addActionListener(e -> {
            int rows = Integer.parseInt(rowsField.getText());
            int cols = Integer.parseInt(colsField.getText());
            gameFrame.startNewGame(rows, cols);
        });
        add(createGameButton);
    }
}
