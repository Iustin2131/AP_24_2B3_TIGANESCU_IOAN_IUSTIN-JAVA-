package org.example;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class View implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Format: view <file>");
            return;
        }

        String fileName = args[1];
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return;
        }


        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
            System.out.println("Opened file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
}
