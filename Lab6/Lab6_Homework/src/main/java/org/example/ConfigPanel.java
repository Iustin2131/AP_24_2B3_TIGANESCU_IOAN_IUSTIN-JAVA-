package org.example;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class ConfigPanel extends JPanel {
    private final JTextField rowsField;
    private final JTextField colsField;

    public ConfigPanel(GameFrame gameFrame) {
        this.add(new JLabel("Rows:"));
        this.rowsField = new JTextField(5);
        this.add(this.rowsField);
        this.add(new JLabel("Cols:"));
        this.colsField = new JTextField(5);
        this.add(this.colsField);
        JButton createGameButton = new JButton("Create");
        createGameButton.addActionListener((e) -> {
            int rows = Integer.parseInt(this.rowsField.getText());
            int cols = Integer.parseInt(this.colsField.getText());
            gameFrame.startNewGame(rows, cols);
        });
        this.add(createGameButton);
    }


}
