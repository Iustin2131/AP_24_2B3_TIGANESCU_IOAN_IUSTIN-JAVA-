import javax.swing.*;

// configuration panel for introducing parameters regarding the grid size
class ConfigPanel extends JPanel {
    private final JTextField rowsField;
    private final JTextField colsField;

    public ConfigPanel(GameFrame gameFrame) {

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
            gameFrame.createNewGame(rows, cols);
        });
        add(createGameButton);
    }
}