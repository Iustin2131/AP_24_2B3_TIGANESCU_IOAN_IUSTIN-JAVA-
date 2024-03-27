package org.example;
import java.io.File;

public class Repository{
    private File masterDirectory;
    public Repository (String masterDirectory) {
        this.masterDirectory=new File(masterDirectory);
    }

    public void displayContent() {
        File[] files = masterDirectory.listFiles();
        if (files != null) {
            System.out.println(masterDirectory.getName() + ":");
            for (File file : files) {
                System.out.println("\t" + file.getName());
            }
        } else {
            System.out.println("the masterDirectory is empty");
        }
    }
}