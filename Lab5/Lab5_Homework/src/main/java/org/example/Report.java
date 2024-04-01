package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Report implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Format: report <repository>");
            return;
        }

        String repositoryPath = args[1];
        File repository = new File(repositoryPath);

        if (!repository.exists() || !repository.isDirectory()) {
            System.out.println("Invalid repository directory.");
            return;
        }

        String outputPath = "repository_hierarchy.html";

        try {
            FileWriter fileWriter = new FileWriter(outputPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("<!DOCTYPE html>");
            bufferedWriter.newLine();
            bufferedWriter.write("<html>");
            bufferedWriter.newLine();
            bufferedWriter.write("<head>");
            bufferedWriter.newLine();
            bufferedWriter.write("<title>Repository Hierarchy</title>");
            bufferedWriter.newLine();
            bufferedWriter.write("</head>");
            bufferedWriter.newLine();
            bufferedWriter.write("<body>");
            bufferedWriter.newLine();
            bufferedWriter.write("<h1>Repository Hierarchy</h1>");
            bufferedWriter.newLine();
            bufferedWriter.write("<ul>");
            bufferedWriter.newLine();

            listFilesAndDirectories(repository, bufferedWriter, 1);

            bufferedWriter.write("</ul>");
            bufferedWriter.newLine();
            bufferedWriter.write("</body>");
            bufferedWriter.newLine();
            bufferedWriter.write("</html>");

            bufferedWriter.close();

            System.out.println("Repository hierarchy saved to: " + outputPath);
        } catch (IOException e) {
            System.out.println("Error saving repository hierarchy: " + e.getMessage());
        }
    }

    private void listFilesAndDirectories(File directory, BufferedWriter writer, int depth) throws IOException {
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");

        for (File file : files) {
            writer.write("<li>");
            writer.newLine();

            writer.write(file.getName());
            writer.newLine();

            if (file.isDirectory()) {
                writer.write("<ul>");
                writer.newLine();
                listFilesAndDirectories(file, writer, depth + 1);
                writer.write("</ul>");
                writer.newLine();
            }

            writer.write("</li>");
            writer.newLine();
        }
    }
}
