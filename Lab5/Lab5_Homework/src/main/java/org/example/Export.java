package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Export implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Utilizare: export <cale>");
            return;
        }

        String directoryPath = args[1];
        File directory = new File(directoryPath);

        if (!directory.isDirectory()) {
            System.out.println("Eroare: Calea specificată nu este un director.");
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        File outputFile = new File("exported_classes.json");

        try (FileWriter writer = new FileWriter(outputFile)) {
            // Obține o listă de fișiere .java din directorul specificat
            File[] javaFiles = directory.listFiles((dir, name) -> name.endsWith(".java"));

            if (javaFiles == null || javaFiles.length == 0) {
                System.out.println("Nu au fost găsite fișiere .java în directorul specificat.");
                return;
            }

            for (File javaFile : javaFiles) {
                // Citeste fiecare linie din fișierul .java și scrie-o ca un obiect JSON pe o linie nouă în fișierul de ieșire
                try (BufferedReader reader = new BufferedReader(new FileReader(javaFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String jsonLine = objectMapper.writeValueAsString(line);
                        writer.write(jsonLine + "\n");
                    }
                }
            }

            System.out.println("Conținutul fișierelor exportat cu succes în: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Eroare la exportul conținutului fișierelor: " + e.getMessage());
        }
    }
}
